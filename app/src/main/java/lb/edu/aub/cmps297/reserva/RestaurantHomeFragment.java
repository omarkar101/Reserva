package lb.edu.aub.cmps297.reserva;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import lb.edu.aub.cmps297.reserva.databinding.FragmentHomeBinding;
import lb.edu.aub.cmps297.reserva.databinding.FragmentRestaurantHomeBinding;
import lb.edu.aub.cmps297.reserva.databinding.FragmentRestaurantSettingsBinding;
import lb.edu.aub.cmps297.reserva.models.Menu;
import lb.edu.aub.cmps297.reserva.models.Restaurant;
import lb.edu.aub.cmps297.reserva.views.MenuAdapter;


public class RestaurantHomeFragment extends Fragment {
    private FragmentRestaurantHomeBinding binding;
    private Restaurant restaurant;
    private ImageView restaurantImg;
    private TextView restaurantName;
    private Button restaurantViewMenu;
    private TextView restaurantDescriptionTitle;
    private TextView restaurantDescriptionText;
    private TextView restaurantPhoneNumberTitle;
    private TextView restaurantPhoneNumberText;
    private TextView restaurantLocationTitle;
    private TextView restaurantLocationText;
    private TextView restaurantSeatsTitle;
    private TextView restaurantSeatsNumber;
    private ImageButton restaurantArrowUp;
    private ImageButton restaurantArrowDown;
    private Button restaurantSaveChanges;
    private RecyclerView restaurantDetailsMenuRV;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRestaurantHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ArrayList<Integer> menuImgs = new ArrayList<Integer>();
        menuImgs.add(R.drawable.menu1);
        menuImgs.add(R.drawable.menu2);

        Menu menu = new Menu(menuImgs);
        restaurant = new Restaurant("Food stop", "37214721", 100, "dfji23jfdoui3wenoc", "wmjenfnwe", R.drawable.ic_dashboard_black_24dp, menu);
        restaurantImg = root.findViewById(R.id.idRestarauntHomeImg);
        restaurantName = root.findViewById(R.id.idRestaurantHomeName);
        restaurantViewMenu = root.findViewById(R.id.idRestaurantHomeViewMenu);
        restaurantDescriptionTitle = root.findViewById(R.id.idRestaurantHomeDescriptionTitle);
        restaurantDescriptionText = root.findViewById(R.id.idRestaurantHomeDescriptionText);
        restaurantPhoneNumberTitle = root.findViewById(R.id.idRestaurantHomePhoneNumberTitle);
        restaurantPhoneNumberText = root.findViewById(R.id.idRestaurantHomePhoneNumberText);
        restaurantLocationTitle = root.findViewById(R.id.idRestaurantHomeLocationTitle);
        restaurantLocationText = root.findViewById(R.id.idRestaurantHomeLocationText);
        restaurantSeatsTitle = root.findViewById(R.id.idRestaurantHomeSeatsTitle);
        restaurantSeatsNumber = root.findViewById(R.id.idRestaurantHomeSeatsNumber);
        restaurantArrowUp = root.findViewById(R.id.idRestaurantHomeArrowUpBtn);
        restaurantArrowDown = root.findViewById(R.id.idRestaurantHomeArrowDownBtn);
        restaurantSaveChanges = root.findViewById(R.id.idRestaurantHomeReserveBtn);

        restaurantImg.setImageResource(restaurant.getImg());
        restaurantName.setText(restaurant.getName());
        restaurantDescriptionText.setText(restaurant.getDescription());
        restaurantPhoneNumberText.setText(restaurant.getCellPhone());
        restaurantLocationText.setText(restaurant.getLocation());
        restaurantSeatsNumber.setText("0");

        restaurantArrowUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer count = Integer.parseInt(restaurantSeatsNumber.getText().toString());
                if (Integer.parseInt(restaurantSeatsNumber.getText().toString()) < restaurant.getNumSeats()) {
                    count++;
                    restaurantSeatsNumber.setText(count.toString());
                }
                restaurantSaveChanges.setEnabled(true);
            }
        });
        restaurantArrowDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer count = Integer.parseInt(restaurantSeatsNumber.getText().toString());
                if (Integer.parseInt(restaurantSeatsNumber.getText().toString()) > 0) {
                    count--;
                    restaurantSeatsNumber.setText(count.toString());
                }
                if (Integer.parseInt(restaurantSeatsNumber.getText().toString()) == 0) {
                    restaurantSaveChanges.setEnabled(false);
                }
            }
        });
//        restaurantSaveChanges.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

        return root;
    }
}