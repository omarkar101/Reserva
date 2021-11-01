package lb.edu.aub.cmps297.reserva;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import lb.edu.aub.cmps297.reserva.databinding.FragmentRestaurantHomeBinding;
import lb.edu.aub.cmps297.reserva.models.Menu;
import lb.edu.aub.cmps297.reserva.models.Restaurant;


public class RestaurantHomeFragment extends Fragment implements View.OnClickListener {
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
    private Button restaurantEditInfoBtn;

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
        restaurantDescriptionText = root.findViewById(R.id.idRestaurantHomeDescriptionText);
        restaurantPhoneNumberText = root.findViewById(R.id.idRestaurantHomePhoneNumberText);
        restaurantLocationText = root.findViewById(R.id.idRestaurantHomeLocationText);
        restaurantSeatsNumber = root.findViewById(R.id.idRestaurantHomeSeatsNumber);
        restaurantArrowUp = root.findViewById(R.id.idRestaurantHomeArrowUpBtn);
        restaurantArrowDown = root.findViewById(R.id.idRestaurantHomeArrowDownBtn);
        restaurantSaveChanges = root.findViewById(R.id.idRestaurantHomeSaveChangesBtn);
        restaurantEditInfoBtn = root.findViewById(R.id.idRestaurantHomeEditInfo);

        restaurantImg.setImageResource(restaurant.getImg());
        restaurantName.setText(restaurant.getName());
        restaurantDescriptionText.setText(restaurant.getDescription());
        restaurantPhoneNumberText.setText(restaurant.getCellPhone());
        restaurantLocationText.setText(restaurant.getLocation());
        restaurantSeatsNumber.setText("0");

        restaurantEditInfoBtn.setOnClickListener(this);

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

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this.getContext(), RestaurantEditInfo.class);
        startActivity(intent);
    }
}