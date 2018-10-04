package com.example.eslam.mywedding.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.eslam.mywedding.HelperMethod.Shared_Preferences;
import com.example.eslam.mywedding.Models.Kitchen.CountryDatum;
import com.example.eslam.mywedding.Models.Kitchen.KitchenItem;
import com.example.eslam.mywedding.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListKitchenInHallsAdapter extends RecyclerView.Adapter<ListKitchenInHallsAdapter.ItemViewHolder> {
    public List<KitchenItem> kitchenItems;
    private KitchenItem kitchen;
    private String kind;
    private Context context;
    private String country="";

    public ListKitchenInHallsAdapter(List<KitchenItem> servcies, String kind, Context context) {
        this.context = context;
        this.kitchenItems = servcies;
        this.kind = kind;
        checkUser(context);

    }

    private void checkUser(Context context) {
        if (Shared_Preferences.getUser(context) != null) {
            country = Shared_Preferences.getUser(context).getCountry();
        }
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_kitchen, parent, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemViewHolder holder, final int position) {

        kitchen = kitchenItems.get(position);
        if (kitchen != null) {
            if (kitchen.getItemType().equals(kind) && kitchen.getActive() == 1) {
                for (CountryDatum countryDatum : kitchen.getCountryData()) {
                    if (countryDatum.getName().equals(country) || countryDatum.getArName().equals(country)) {
                        holder.name.setVisibility(View.VISIBLE);
                        holder.check.setVisibility(View.VISIBLE);
                        holder.price.setVisibility(View.VISIBLE);
                        holder.check.setOnCheckedChangeListener(null);
                        holder.check.setChecked(kitchen.getChecked());
                        holder.name.setText(kitchen.getName());
                        holder.price.setText(String.valueOf(countryDatum.getPivot().getPrice() + "  " + countryDatum.getCurrencySymbol()));
                    }
                }
            }
            holder.check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        kitchenItems.get(holder.getAdapterPosition()).setChecked(true);
                    } else {
                        kitchenItems.get(holder.getAdapterPosition()).setChecked(false);
                    }
                }
            });
        } else {
            holder.name.setVisibility(View.GONE);
            holder.check.setVisibility(View.GONE);
            holder.price.setVisibility(View.GONE);
        }
    }

    public void clear() {
        final int size = kitchenItems.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                kitchenItems.remove(0);
            }

            notifyItemRangeRemoved(0, size);
        }
    }

    @Override
    public int getItemCount() {
        if (kitchenItems == null) {
            return 0;
        }
        return kitchenItems.size();
    }

    public void setListServices(List<KitchenItem> kitchenItems, String kind) {
        this.kind = kind;
        this.kitchenItems = kitchenItems;
        notifyDataSetChanged();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.food_name)
        TextView name;

        @BindView(R.id.food_price)
        TextView price;

        @BindView(R.id.food_check)
        CheckBox check;

        private ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
