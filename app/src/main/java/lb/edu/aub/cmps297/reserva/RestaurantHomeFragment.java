package lb.edu.aub.cmps297.reserva;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import lb.edu.aub.cmps297.reserva.database.Entities.LoggedInUser;
import lb.edu.aub.cmps297.reserva.database.ViewModels.LoggedInUserViewModel;
import lb.edu.aub.cmps297.reserva.database.ViewModels.RestaurantViewModel;
import lb.edu.aub.cmps297.reserva.databinding.FragmentRestaurantHomeBinding;
import lb.edu.aub.cmps297.reserva.models.Menu;
import lb.edu.aub.cmps297.reserva.database.Entities.Restaurant;


public class RestaurantHomeFragment extends Fragment {
    private FragmentRestaurantHomeBinding binding;
    private Restaurant restaurant;
    private ImageView restaurantImg;
    private TextView restaurantName;
    private TextView restaurantDescriptionText;
    private TextView restaurantPhoneNumberText;
    private TextView restaurantLocationText;
    private TextView restaurantSeatsNumber;
    private ImageButton restaurantArrowUp;
    private ImageButton restaurantArrowDown;
    private Button restaurantSaveChanges;

    private LoggedInUserViewModel loggedInUserViewModel;

    private RestaurantViewModel restaurantViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRestaurantHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        ArrayList<Integer> menuImgs = new ArrayList<Integer>();
        menuImgs.add(R.drawable.menu1);
        menuImgs.add(R.drawable.menu2);

        Menu menu = new Menu(menuImgs);
        loggedInUserViewModel = new ViewModelProvider(this).get(LoggedInUserViewModel.class);
        restaurantViewModel = new ViewModelProvider(this).get(RestaurantViewModel.class);
        LoggedInUser loggedInUser = loggedInUserViewModel.getUser();


        restaurant = restaurantViewModel.getRestaurant(loggedInUser.email);
        if(restaurant == null) return root;

        restaurantImg = root.findViewById(R.id.idRestarauntHomeImg);
        restaurantName = root.findViewById(R.id.idRestaurantHomeName);
        restaurantDescriptionText = root.findViewById(R.id.idRestaurantHomeDescriptionText);
        restaurantPhoneNumberText = root.findViewById(R.id.idRestaurantHomePhoneNumberText);
        restaurantLocationText = root.findViewById(R.id.idRestaurantHomeLocationText);
        restaurantSeatsNumber = root.findViewById(R.id.idRestaurantHomeSeatsNumber);
        restaurantArrowUp = root.findViewById(R.id.idRestaurantHomeArrowUpBtn);
        restaurantArrowDown = root.findViewById(R.id.idRestaurantHomeArrowDownBtn);
        restaurantSaveChanges = root.findViewById(R.id.idRestaurantHomeSaveChangesBtn);

        restaurantImg.setImageResource(R.drawable.ic_dashboard_black_24dp);
        restaurantName.setText(restaurant.name);
        restaurantDescriptionText.setText(restaurant.description);
        restaurantPhoneNumberText.setText(restaurant.phoneNumber);
        restaurantLocationText.setText(restaurant.location);
        restaurantSeatsNumber.setText(Integer.valueOf(restaurant.seatsMaxCapacity).toString());



        restaurantSaveChanges.setOnClickListener(view -> {
            restaurantViewModel.updateRestaurantMaxSeatsNumber(restaurant.email, Integer.parseInt(restaurantSeatsNumber.getText().toString()));
            Toast.makeText(RestaurantHomeFragment.this.getContext(), "Saved Changes!", Toast.LENGTH_LONG).show();
        });
        restaurantArrowUp.setOnClickListener(view -> {
            Integer count = Integer.parseInt(restaurantSeatsNumber.getText().toString());
            count++;
            restaurantSeatsNumber.setText(count.toString());
            restaurantSaveChanges.setEnabled(true);

        });
        restaurantArrowDown.setOnClickListener(view -> {
            Integer count = Integer.parseInt(restaurantSeatsNumber.getText().toString());
            if (Integer.parseInt(restaurantSeatsNumber.getText().toString()) > 0) {
                count--;
                restaurantSeatsNumber.setText(count.toString());
            }
            if (Integer.parseInt(restaurantSeatsNumber.getText().toString()) == 0) {
                restaurantSaveChanges.setEnabled(false);
            }
        });

        return root;
    }
}