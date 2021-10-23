package lb.edu.aub.cmps297.reserva.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import lb.edu.aub.cmps297.reserva.R;
import lb.edu.aub.cmps297.reserva.models.Restaurant;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.Viewholder>{
    private Context context;
    private ArrayList<Restaurant> restaurantsModelArrayList;

    public RestaurantAdapter(Context context, ArrayList<Restaurant> restaurantsModelArrayList) {
        this.context = context;
        this.restaurantsModelArrayList = restaurantsModelArrayList;
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
        Restaurant restaurant = restaurantsModelArrayList.get(position);
        holder.restaurantName.setText(restaurant.getName());
        holder.restaurantImg.setImageResource(restaurant.getImg());
    }

    @Override
    public int getItemCount() {
        return restaurantsModelArrayList.size();
    }

    public static class Viewholder extends RecyclerView.ViewHolder {
        private ImageView restaurantImg;
        private TextView restaurantName;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            restaurantImg = itemView.findViewById(R.id.idRestaurantImg);
            restaurantName = itemView.findViewById(R.id.idRestaurantName);
        }
    }
}
