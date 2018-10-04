package com.example.eslam.mywedding.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eslam.mywedding.Models.Offers.Offer;
import com.example.eslam.mywedding.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListOffersHomeAdapters extends RecyclerView.Adapter<ListOffersHomeAdapters.ItemViewHolder> {
    private List<Offer> modlesList;
    private final offer offer;
    private static final String LOAD_IMAGE_URL = "http://wed.filerolesys.com/";

    public interface offer {
        void onClick(Offer offer);
    }


    public ListOffersHomeAdapters(List<Offer> modlesList, offer offer) {
        this.offer = offer;
        this.modlesList = modlesList;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_halls_home, parent, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Offer offer = modlesList.get(position);

        String image = offer.getImages();
        Picasso.get().load(LOAD_IMAGE_URL + image).into(holder.hallImage);

        String name = offer.getName();
        holder.hallName.setText(name);
    }

    @Override
    public int getItemCount() {
        if (modlesList == null) {
            return 0;
        }
        return modlesList.size();
    }

    public void setList(List<Offer> modlesList) {
        this.modlesList = modlesList;
        notifyDataSetChanged();

    }

    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tv_name_hall_list_home)
        TextView hallName;
        //        @BindView(R.id.tv_desc_hall_list_home)
//        TextView descName;
        @BindView(R.id.im_hall_list_home)
        ImageView hallImage;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            Offer listHalls = modlesList.get(adapterPosition);
            offer.onClick(listHalls);
        }
    }
}
