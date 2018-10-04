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

public class ListFavoriteHalls extends RecyclerView.Adapter<ListFavoriteHalls.ItemViewHolder> {
    private List<Hall> modlesList;
    private final ListFavoriteHallsOnClick listFavoriteHallsOnClick;
    private static final String LOAD_IMAGE_URL = "http://wed.filerolesys.com/";

    public interface ListFavoriteHallsOnClick {
        void onClick(Hall listHallsAdapters);
    }

    public ListFavoriteHalls(List<Hall> modlesList, ListFavoriteHallsOnClick listFavoriteHallsOnClick) {
        this.listFavoriteHallsOnClick = listFavoriteHallsOnClick;
        this.modlesList = modlesList;
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
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_fav_halls, parent, false);
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
        @BindView(R.id.tv_name_hall_list_fav)
        TextView hallName;
        @BindView(R.id.im_hall_list_fav)
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
            listFavoriteHallsOnClick.onClick(listHalls);
        }
    }
}
