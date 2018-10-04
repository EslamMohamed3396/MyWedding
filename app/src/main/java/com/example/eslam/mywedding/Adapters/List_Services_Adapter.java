package com.example.eslam.mywedding.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eslam.mywedding.Adapters.List_Services_Adapter.ItemViewHolder;
import com.example.eslam.mywedding.Models.Services.Servcy;
import com.example.eslam.mywedding.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class List_Services_Adapter extends RecyclerView.Adapter<ItemViewHolder> {
    private List<Servcy> modlesList;
    private final ServicesAdapterOnClick servicesAdapterOnClick;
    private static final String LOAD_IMAGE_URL = "http://wed.filerolesys.com/";

    public interface ServicesAdapterOnClick {
        void onClick(Servcy servcy);
    }

    public List_Services_Adapter(List<Servcy> modlesList, ServicesAdapterOnClick servicesAdapterOnClick) {
        this.servicesAdapterOnClick = servicesAdapterOnClick;
        this.modlesList = modlesList;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_services_fragments, parent, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Servcy servcy = modlesList.get(position);
        for (int i = 0; i < servcy.getImages().size(); i++) {
            String image = servcy.getImages().get(i).getImageUrl();
            Picasso.get().load(LOAD_IMAGE_URL + image).into(holder.servicesImage);
        }
        String name = servcy.getName();
        holder.servicesName.setText(name);
    }

    @Override
    public int getItemCount() {
        if (modlesList == null) {
            return 0;
        }
        return modlesList.size();
    }

    public void setList(List<Servcy> modlesList) {
        this.modlesList = modlesList;
        notifyDataSetChanged();

    }

    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tv_service_name)
        TextView servicesName;
        @BindView(R.id.im_servcis_list)
        ImageView servicesImage;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            Servcy listHalls = modlesList.get(adapterPosition);
            servicesAdapterOnClick.onClick(listHalls);
        }
    }
}
