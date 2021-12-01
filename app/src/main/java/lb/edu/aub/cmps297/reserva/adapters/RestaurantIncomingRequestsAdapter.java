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

import lb.edu.aub.cmps297.reserva.Enums.ReservationStatus;
import lb.edu.aub.cmps297.reserva.R;
import lb.edu.aub.cmps297.reserva.database.Entities.Client;
import lb.edu.aub.cmps297.reserva.database.Entities.Reservation;
import lb.edu.aub.cmps297.reserva.database.ViewModels.ClientViewModel;
import lb.edu.aub.cmps297.reserva.database.ViewModels.ReservationViewModel;

public class RestaurantIncomingRequestsAdapter extends RecyclerView.Adapter<RestaurantIncomingRequestsAdapter.Viewholder>{

    private Context context;
    private ArrayList<Reservation> incomingReservationsArrayList;
    private ReservationViewModel reservationViewModel;
    private ClientViewModel clientViewModel;
    public RestaurantIncomingRequestsAdapter(Context context, ArrayList<Reservation> incomingReservationsArrayList) {
        this.context = context;
        this.incomingReservationsArrayList = incomingReservationsArrayList;
    }

    public RestaurantIncomingRequestsAdapter(Context context, ArrayList<Reservation> incomingReservationsArrayList,
                                             ReservationViewModel reservationViewModel, ClientViewModel clientViewModel) {
        this.context = context;
        this.incomingReservationsArrayList = incomingReservationsArrayList;
        this.reservationViewModel = reservationViewModel;
        this.clientViewModel = clientViewModel;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_reservation_incoming_requests_card, parent, false);
        return new RestaurantIncomingRequestsAdapter.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        Reservation reservation = incomingReservationsArrayList.get(position);
        Client client = clientViewModel.getClient(reservation.clientEmail);

        holder.userIncomingRequestAcceptBtn.setOnClickListener(v -> {
            holder.userIncomingRequestAcceptBtn.setEnabled(false);
            holder.userIncomingRequestAcceptBtn.setClickable(false);
            reservationViewModel.updateReservation(reservation.id.toString(), ReservationStatus.ACCEPTED.name());
        });

        holder.userNameandEmail.setText(reservation.clientEmail);
        holder.userPhoneNumber.setText(client.phoneNumber);
        holder.userSeatsRequested.setText(reservation.seatsRequested);
    }

    @Override
    public int getItemCount() {
        return incomingReservationsArrayList.size();
    }

    public static class Viewholder extends RecyclerView.ViewHolder {
        private TextView userNameandEmail;
        private TextView userPhoneNumber;
        private TextView userSeatsRequested;
        private Button userIncomingRequestAcceptBtn;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            userNameandEmail = itemView.findViewById(R.id.idUserIncomingRequestText);
            userPhoneNumber = itemView.findViewById(R.id.idUserIncomingRequestPhoneNumberText);
            userSeatsRequested = itemView.findViewById(R.id.idUserIncomingRequestSeatsRequestedText);
            userIncomingRequestAcceptBtn = itemView.findViewById(R.id.idUserIncomingRequestAcceptBtn);
        }
    }
}
