package lb.edu.aub.cmps297.reserva.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import lb.edu.aub.cmps297.reserva.R;
import lb.edu.aub.cmps297.reserva.models.Restaurant;

public class RestaurantIncomingRequestsAdapter extends RecyclerView.Adapter<RestaurantIncomingRequestsAdapter.Viewholder>{

    private Context context;
    private ArrayList<Restaurant> restaurantsModelArrayList;
    public RestaurantIncomingRequestsAdapter(Context context, ArrayList<Restaurant> restaurantsModelArrayList) {
        this.context = context;
        this.restaurantsModelArrayList = restaurantsModelArrayList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_reservation_incoming_requests_card, parent, false);
        return new RestaurantIncomingRequestsAdapter.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
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
            userNameandEmail = itemView.findViewById(R.id.idUserIncomingRequestText);
            userPhoneNumber = itemView.findViewById(R.id.idUserIncomingRequestPhoneNumberText);
            userSeatsRequested = itemView.findViewById(R.id.idUserIncomingRequestSeatsRequestedText);

        }
    }
}
