package com.example.eslam.mywedding.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eslam.mywedding.Adapters.List_Rests_Adapters.ItemViewHolder;
import com.example.eslam.mywedding.Models.HallsModels.Hall;
import com.example.eslam.mywedding.Models.Rests.Rests;
import com.example.eslam.mywedding.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class List_Rests_Adapters extends RecyclerView.Adapter<ItemViewHolder> {
    private List<Rests> modlesList;
    private final RestsAdapterOnClick restsAdapterOnClick;
    private static final String LOAD_IMAGE_URL = "http://wed.filerolesys.com/";

    public interface RestsAdapterOnClick {
        void onClick(Rests rests);
    }

    public List_Rests_Adapters(List<Rests> modlesList, RestsAdapterOnClick restsAdapterOnClick) {
        this.restsAdapterOnClick = restsAdapterOnClick;
        this.modlesList = modlesList;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_rests_fragments, parent, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Rests rests = modlesList.get(position);
        String image = rests.getImages().get(position).getImageUrl();
        String name = rests.getName();
        holder.restName.setText(name);
        Picasso.get().load(LOAD_IMAGE_URL + image).into(holder.restImage);
    }

    @Override
    public int getItemCount() {
        if (modlesList == null) {
            return 0;
        }
        return modlesList.size();
    }

    public void setList(List<Rests> modlesList) {
        this.modlesList = modlesList;
        notifyDataSetChanged();

    }

    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tv_name_rests_list)
        TextView restName;
        @BindView(R.id.im_rests_list)
        ImageView restImage;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            Rests listHalls = modlesList.get(adapterPosition);
            restsAdapterOnClick.onClick(listHalls);
        }
    }
}
