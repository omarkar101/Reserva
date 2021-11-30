package lb.edu.aub.cmps297.reserva.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import lb.edu.aub.cmps297.reserva.R;
import lb.edu.aub.cmps297.reserva.models.Restaurant;

public class RestaurantCurrentReservationAdapter extends RecyclerView.Adapter<RestaurantCurrentReservationAdapter.Viewholder>{

    private Context context;
    private ArrayList<Restaurant> restaurantsModelArrayList;
    public RestaurantCurrentReservationAdapter(Context context, ArrayList<Restaurant> restaurantsModelArrayList) {
        this.context = context;
        this.restaurantsModelArrayList = restaurantsModelArrayList;
    }

    @NonNull
    @Override
    public RestaurantCurrentReservationAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_reservation_current_reservation_card, parent, false);
        return new RestaurantCurrentReservationAdapter.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantCurrentReservationAdapter.Viewholder holder, int position) {
        Restaurant restaurant = restaurantsModelArrayList.get(position);
        holder.userNameandEmail.setText(restaurant.getName());
        holder.userPhoneNumber.setText(restaurant.getCellPhone());
        holder.userSeatsRequested.setText(restaurant.getNumSeats());
    }

    @Override
    public int getItemCount() {
        return restaurantsModelArrayList.size();
    }


    public static class Viewholder extends RecyclerView.ViewHolder {
        private TextView userNameandEmail;
        private TextView userPhoneNumber;
        private TextView userSeatsRequested;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            userNameandEmail = itemView.findViewById(R.id.idUserCurrentReservationText);
            userPhoneNumber = itemView.findViewById(R.id.idUserCurrentReservationPhoneNumberText);
            userSeatsRequested = itemView.findViewById(R.id.idUserCurrentReservationSeatsRequestedText);

        }
    }
}
