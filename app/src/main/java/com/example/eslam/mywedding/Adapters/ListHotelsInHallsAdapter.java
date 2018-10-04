package com.example.eslam.mywedding.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.example.eslam.mywedding.HelperMethod.Shared_Preferences;
import com.example.eslam.mywedding.Models.Hotels.Hotel;
import com.example.eslam.mywedding.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListHotelsInHallsAdapter extends RecyclerView.Adapter<ListHotelsInHallsAdapter.ItemViewHolder> {
    public List<Hotel> hotels;
    private Hotel hotel;
    private int stars;
    private String country = "";
    private Context context;

    public ListHotelsInHallsAdapter(List<Hotel> servcies, int stars, Context context) {
        this.hotels = servcies;
        this.stars = stars;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_hotels_book_hall, parent, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemViewHolder holder, final int position) {
        hotel = hotels.get(position);

        if (hotel.getStartsNumber() == stars) {
            if (hotel.getCountry().getArName().equals(country) || hotel.getCountry().getName().equals(country)) {
                holder.name.setVisibility(View.VISIBLE);
                holder.check.setVisibility(View.VISIBLE);
                holder.name.setText(hotel.getName());
                holder.price.setText(String.valueOf(hotel.getPrice()) + "  " + hotel.getCountry().getCurrencySymbol());
                holder.check.setOnCheckedChangeListener(null);
                holder.check.setChecked(hotel.getChecked());
            }
            holder.check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        hotels.get(holder.getAdapterPosition()).setChecked(true);
                        holder.days.setVisibility(View.VISIBLE);
                        holder.days.setText(String.valueOf(hotel.getEditTextValue()));
                    } else {
                        hotels.get(holder.getAdapterPosition()).setChecked(false);
                        holder.days.setVisibility(View.GONE);
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
        final int size = hotels.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                hotels.remove(0);
            }
            notifyItemRangeRemoved(0, size);
        }
    }

    @Override
    public int getItemCount() {
        if (hotels == null) {
            return 0;
        }
        return hotels.size();
    }

    public void setListServices(List<Hotel> hotels, int stars) {
        this.stars = stars;
        this.hotels = hotels;
        notifyDataSetChanged();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.hotel_name)
        TextView name;

        @BindView(R.id.hotel_price)
        TextView price;

        @BindView(R.id.hotel_edit)
        EditText days;
        @BindView(R.id.hotel_check)
        CheckBox check;

        private ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            days.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    hotels.get(getAdapterPosition()).setEditTextValue(String.valueOf(days.getText().toString()));
                }

                @Override
                public void afterTextChanged(Editable editable) {
                }
            });
        }
    }
}
