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
import com.example.eslam.mywedding.Models.Services.Servcy;
import com.example.eslam.mywedding.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListServicesInHallsAdapter extends RecyclerView.Adapter<ListServicesInHallsAdapter.ItemViewHolder> {
    private List<Servcy> servcies;
    private Context context;
    private String country="";

    public ListServicesInHallsAdapter(List<Servcy> servcies, Context context) {
        this.servcies = servcies;
        this.context = context;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_services_book_hall, parent, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemViewHolder holder, int position) {
        final Servcy servcy = servcies.get(position);
        if (servcy.getCountry().getArName().equals(country) || servcy.getCountry().getName().equals(country)) {
            holder.name.setText(servcy.getName());
            holder.price.setText(String.valueOf(servcy.getPrice()) + "  " + servcy.getCountry().getCurrencySymbol());

            holder.check.setOnCheckedChangeListener(null);

            holder.check.setChecked(servcy.getChecked());

        }
        holder.check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    servcies.get(holder.getAdapterPosition()).setChecked(true);
                } else {
                    servcies.get(holder.getAdapterPosition()).setChecked(false);
                }
            }
        });

    }

    public void clear() {
        final int size = servcies.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                servcies.remove(0);
            }

            notifyItemRangeRemoved(0, size);
        }
    }

    @Override
    public int getItemCount() {
        if (servcies == null) {
            return 0;
        }
        return servcies.size();
    }

    public void setListServices(List<Servcy> servcies) {
        this.servcies = servcies;
        notifyDataSetChanged();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.service_name)
        TextView name;

        @BindView(R.id.service_price)
        TextView price;

        @BindView(R.id.service_check)
        CheckBox check;

        private ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
