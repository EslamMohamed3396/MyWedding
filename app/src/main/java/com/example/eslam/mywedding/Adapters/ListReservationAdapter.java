package com.example.eslam.mywedding.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eslam.mywedding.API.ApiClient.ApiClient;
import com.example.eslam.mywedding.API.ApiInterFace.ApiInterface;
import com.example.eslam.mywedding.HelperMethod.Shared_Preferences;
import com.example.eslam.mywedding.Models.MyReservation.MyReservation;
import com.example.eslam.mywedding.Models.MyReservation.ReservatioCancel;
import com.example.eslam.mywedding.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListReservationAdapter extends RecyclerView.Adapter<ListReservationAdapter.ItemViewHolder> {
    private List<MyReservation> myReservations;
    private Context context;
    private static final String AUTH_BEARER = "Bearer ";


    public ListReservationAdapter(List<MyReservation> myReservations, Context context) {
        this.myReservations = myReservations;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_reservation_list_item, parent, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemViewHolder holder, int position) {
        MyReservation myReservation = myReservations.get(position);
        holder.price.setText(myReservation.getPrice());
        holder.tv_id.setText(String.valueOf(myReservation.getId()));
        holder.tv_date.setText(myReservation.getDate());
        if (myReservation.getService() != null && !myReservation.getService().equals("")) {
            holder.name.setText(myReservation.getService().getName());
            holder.details.setText(myReservation.getService().getDetailsAr());
        } else if (myReservation.getOfferData() != null && !myReservation.getOfferData().equals("")) {
            holder.name.setText(myReservation.getOfferData().getName());
            holder.details.setText(myReservation.getOfferData().getDetails());
        } else {
            holder.name.setText(myReservation.getHall().getName());
            holder.details.setText(myReservation.getHall().getDetails());
        }
        final String auth = AUTH_BEARER + Shared_Preferences.getAuth(context);
        if (myReservation.getStatus() == 1) {
            holder.tv_status.setText("قيد الانتظار");
            holder.cancel.setVisibility(View.VISIBLE);
            holder.pay.setVisibility(View.VISIBLE);

            final int i = myReservation.getId();
            holder.pay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String url = "http://wed.filerolesys.com/payment/" + i;
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    context.startActivity(i);
                }
            });
            holder.cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
                    Call<ReservatioCancel> reservatioCancelCall = apiInterface.reservationCancel(auth, i);
                    final AlertDialog alertDialog = new SpotsDialog.Builder()
                            .setContext(context)
                            .setMessage(R.string.signing)
                            .setCancelable(false)
                            .build();
                    alertDialog.show();
                    reservatioCancelCall.enqueue(new Callback<ReservatioCancel>() {
                        @Override
                        public void onResponse(Call<ReservatioCancel> call, Response<ReservatioCancel> response) {
                            alertDialog.dismiss();
                            if (response.isSuccessful()) {
                                if (response.body() != null) {
                                    Toasty.success(context,  context.getString(R.string.Canceld), Toast.LENGTH_LONG).show();
                                    holder.cancel.setVisibility(View.GONE);
                                    holder.pay.setVisibility(View.GONE);
                                    holder.tv_status.setText("تم الالغاء");
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ReservatioCancel> call, Throwable t) {
                            Log.d("urlCancel", "" + t.getMessage());
                        }
                    });
                }
            });
        } else if (myReservation.getStatus() == 2) {
            holder.tv_status.setText("تم الدفع");
            holder.cancel.setVisibility(View.GONE);
            holder.pay.setVisibility(View.GONE);
        } else {
            holder.tv_status.setText("تم الالغاء");
            holder.cancel.setVisibility(View.GONE);
            holder.pay.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        if (myReservations == null) {
            return 0;
        }
        return myReservations.size();
    }

    public void setListServices(List<MyReservation> myReservations) {
        this.myReservations = myReservations;
        notifyDataSetChanged();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name_of_reservation)
        TextView name;
        @BindView(R.id.price_my_reservation)
        TextView price;
        @BindView(R.id.details_of_reservation)
        TextView details;
        @BindView(R.id.id_number)
        TextView tv_id;
        @BindView(R.id.reserv_date)
        TextView tv_date;
        @BindView(R.id.reserv_status)
        TextView tv_status;
        @BindView(R.id.btn_pay)
        Button pay;
        @BindView(R.id.cancel_reservation)
        Button cancel;

        private ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}

