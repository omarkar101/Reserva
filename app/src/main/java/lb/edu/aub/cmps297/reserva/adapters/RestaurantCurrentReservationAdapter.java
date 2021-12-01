package lb.edu.aub.cmps297.reserva.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import lb.edu.aub.cmps297.reserva.Enums.UserType;
import lb.edu.aub.cmps297.reserva.R;
import lb.edu.aub.cmps297.reserva.database.Entities.Reservation;
import lb.edu.aub.cmps297.reserva.database.ViewModels.ReservationViewModel;

public class RestaurantCurrentReservationAdapter extends RecyclerView.Adapter<RestaurantCurrentReservationAdapter.Viewholder>{

    private Context context;
    private ArrayList<Reservation> reservationArrayList;
    private String phoneNumber;
    private UserType userType;
    private ReservationViewModel reservationViewModel;

    public RestaurantCurrentReservationAdapter(Context context, ArrayList<Reservation> reservationArrayList) {
        this.context = context;
        this.reservationArrayList = reservationArrayList;
    }
    public RestaurantCurrentReservationAdapter(Context context, ArrayList<Reservation> reservationArrayList, String phoneNumber, UserType userType) {
        this.context = context;
        this.reservationArrayList = reservationArrayList;
        this.phoneNumber = phoneNumber;
        this.userType = userType;
    }

    public RestaurantCurrentReservationAdapter(Context context, ArrayList<Reservation> reservationArrayList, String phoneNumber, UserType userType, ReservationViewModel reservationViewModel) {
        this.context = context;
        this.reservationArrayList = reservationArrayList;
        this.phoneNumber = phoneNumber;
        this.userType = userType;
        this.reservationViewModel = reservationViewModel;
    }

    @NonNull
    @Override
    public RestaurantCurrentReservationAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_reservation_current_reservation_card, parent, false);
        return new RestaurantCurrentReservationAdapter.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantCurrentReservationAdapter.Viewholder holder, int position) {
        Reservation reservation = reservationArrayList.get(position);
        if(userType == UserType.CLIENT) {
            holder.userNameandEmail.setText(reservation.restaurantEmail);
        } else {
            holder.userNameandEmail.setText(reservation.clientEmail);
        }
        holder.userPhoneNumber.setText(phoneNumber);
        holder.userSeatsRequested.setText(reservation.seatsRequested);
        holder.clientCancelReservationBtn.setOnClickListener(v -> {

        });
    }

    @Override
    public int getItemCount() {
        return reservationArrayList.size();
    }

    public static class Viewholder extends RecyclerView.ViewHolder {
        private TextView userNameandEmail;
        private TextView userPhoneNumber;
        private TextView userSeatsRequested;
        private Button clientCancelReservationBtn;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            userNameandEmail = itemView.findViewById(R.id.idUserCurrentReservationText);
            userPhoneNumber = itemView.findViewById(R.id.idUserCurrentReservationPhoneNumberText);
            userSeatsRequested = itemView.findViewById(R.id.idUserCurrentReservationSeatsRequestedText);
            clientCancelReservationBtn = itemView.findViewById(R.id.idUserCurrentReservationEndReservationBtn);
        }
    }
}
