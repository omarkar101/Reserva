package lb.edu.aub.cmps297.reserva.views;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import lb.edu.aub.cmps297.reserva.R;
import lb.edu.aub.cmps297.reserva.database.Entities.Restaurant;
import lb.edu.aub.cmps297.reserva.database.ViewModels.FavoriteRestaurantsByClientsViewModel;
import lb.edu.aub.cmps297.reserva.ui.restauarant.RestaurantDetails;
import lb.edu.aub.cmps297.reserva.StaticStorage;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.Viewholder> {

    private boolean isFav(List<Restaurant> favRestaurants, Restaurant restaurant) {
        for(int i = 0; i < favRestaurants.size(); i++) {
            if(favRestaurants.get(i).email.equals(restaurant.email)) return true;
        }
        return false;
    }

    private FavoriteRestaurantsByClientsViewModel favoriteRestaurantsByClientsViewModel;
    private String client_email;

    private Context context;
    private ArrayList<Restaurant> restaurantsEntityArrayList;
    public RestaurantAdapter(Context context, ArrayList<Restaurant> restaurantsModelArrayList) {
        this.context = context;
        this.restaurantsEntityArrayList = restaurantsModelArrayList;
    }

    public RestaurantAdapter(Context context, ArrayList<Restaurant> restaurantsModelArrayList,
                             FavoriteRestaurantsByClientsViewModel favoriteRestaurantsByClientsViewModel, String client_email) {
        this.context = context;
        this.restaurantsEntityArrayList = restaurantsModelArrayList;
        this.favoriteRestaurantsByClientsViewModel = favoriteRestaurantsByClientsViewModel;
        this.client_email = client_email;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_card_layout, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        Restaurant restaurant = restaurantsEntityArrayList.get(position);
        holder.restaurantName.setText(restaurant.name);
        holder.restaurantImg.setImageResource(R.drawable.ic_dashboard_black_24dp);
        holder.restaurantPhoneNumber.setText(restaurant.phoneNumber);
        holder.restaurantLocation.setText(restaurant.location);

        List<Restaurant> favRestaurants = favoriteRestaurantsByClientsViewModel.getAllFavoriteRestaurants(client_email);

        holder.favButton.setImageResource(isFav(favRestaurants, restaurant) ? R.drawable.black_favorite : R.drawable.white_favorite);
        holder.favButton.setOnClickListener(v -> {
            if(isFav(favRestaurants, restaurant)) {
                favoriteRestaurantsByClientsViewModel.removeFavoriteRestaurant(client_email, restaurant.email);
                holder.favButton.setImageResource(R.drawable.white_favorite);
            } else {
                favoriteRestaurantsByClientsViewModel.addFavoriteRestaurant(client_email, restaurant.email);
                holder.favButton.setImageResource(R.drawable.black_favorite);
            }
        });

        holder.itemView.setOnClickListener(view -> {
            Intent i = new Intent(context.getApplicationContext(), RestaurantDetails.class);
            StaticStorage.restaurantChosen = position;
            StaticStorage.restaurantChosenEmail = restaurant.email;
            context.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return restaurantsEntityArrayList.size();
    }

    public static class Viewholder extends RecyclerView.ViewHolder {
        private ImageButton favButton;
        private ImageView restaurantImg;
        private TextView restaurantName;
        private TextView restaurantPhoneNumber;
        private TextView restaurantLocation;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            restaurantImg = itemView.findViewById(R.id.idRestaurantImg);
            restaurantName = itemView.findViewById(R.id.idRestaurantName);
            favButton = itemView.findViewById(R.id.btnFavorite);
            restaurantPhoneNumber = itemView.findViewById(R.id.idRestaurantPhoneNumberText);
            restaurantLocation = itemView.findViewById(R.id.idRestaurantLocationText);
        }
    }
}
