package com.example.eslam.mywedding.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eslam.mywedding.HelperMethod.Shared_Preferences;
import com.example.eslam.mywedding.Models.CarsDetails.CarsDeepDetails;
import com.example.eslam.mywedding.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListCarAdapters extends RecyclerView.Adapter<ListCarAdapters.ItemViewHolder> {
    private List<CarsDeepDetails> modlesList;
    private Context context;
    private String country="";
    private static final String LOAD_IMAGE_URL = "http://wed.filerolesys.com/";

    public ListCarAdapters(List<CarsDeepDetails> modlesList, Context context) {
        this.modlesList = modlesList;
        this.context = context;
        checkUser(context);
    }

    private void checkUser(Context context) {
        if (Shared_Preferences.getUser(context) != null) {
            country = Shared_Preferences.getUser(context).getCountry();
        }
    }


    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_cars_prepration, parent, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        CarsDeepDetails carsDeepDetails = modlesList.get(position);
        if (carsDeepDetails.getCountryData().getName().equals(country)
                || carsDeepDetails.getCountryData().getArName().equals(country)) {
            for (int i = 0; i < carsDeepDetails.getImages().size(); i++) {
                String image = carsDeepDetails.getImages().get(i).getImageUrl();
                Picasso.get().load(LOAD_IMAGE_URL + image).into(holder.carImage);
            }
            String name = carsDeepDetails.getName();
            holder.carName.setText(name);
            int price = carsDeepDetails.getPrice();
            holder.carPrice.setText(String.valueOf(price) + "  " + carsDeepDetails.getCountryData().getCurrencySymbol());
        }
    }

    @Override
    public int getItemCount() {
        if (modlesList == null) {
            return 0;
        }
        return modlesList.size();
    }

    public void setList(List<CarsDeepDetails> modlesList) {
        this.modlesList = modlesList;
        notifyDataSetChanged();

    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvCarName)
        TextView carName;
        @BindView(R.id.tvCarPrice)
        TextView carPrice;
        @BindView(R.id.im_Car)
        ImageView carImage;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


    }
}

