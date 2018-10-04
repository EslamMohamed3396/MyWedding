package com.example.eslam.mywedding.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eslam.mywedding.Models.HallsModels.Hall;
import com.example.eslam.mywedding.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class List_Halls_Adapters extends RecyclerView.Adapter<List_Halls_Adapters.ItemViewHolder> {
    private List<Hall> modlesList;
    private final HallsAdapterOnClick hallsAdapterOnClick;
    private static final String LOAD_IMAGE_URL = "http://wed.filerolesys.com/";

    public interface HallsAdapterOnClick {
        void onClick(Hall listHallsAdapters);
    }

    public List_Halls_Adapters(List<Hall> modlesList, HallsAdapterOnClick hallsAdapterOnClick1) {
        this.hallsAdapterOnClick = hallsAdapterOnClick1;
        this.modlesList = modlesList;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_halls_fragments_, parent, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;
    }
    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Hall hallsModles = modlesList.get(position);
        for (int i = 0; i < hallsModles.getImageModels().size(); i++) {
            String image = hallsModles.getImageModels().get(i).getImageUrl();
            Picasso.get().load(LOAD_IMAGE_URL + image).into(holder.hallImage);
        }
        String name = hallsModles.getName();
        holder.hallName.setText(name);
    }
    public void clear() {
        final int size = modlesList.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                modlesList.remove(0);
            }
            notifyItemRangeRemoved(0, size);
        }
    }

    @Override
    public int getItemCount() {
        if (modlesList == null) {
            return 0;
        }
        return modlesList.size();
    }

    public void setList(List<Hall> modlesList) {
        this.modlesList = modlesList;
        notifyDataSetChanged();

    }

    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tv_name_hall_list)
        TextView hallName;
        @BindView(R.id.im_hall_list)
        ImageView hallImage;


        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            Hall listHalls = modlesList.get(adapterPosition);
            hallsAdapterOnClick.onClick(listHalls);
        }
    }
}
