package lb.edu.aub.cmps297.reserva.ui.restauarant;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import lb.edu.aub.cmps297.reserva.R;
import lb.edu.aub.cmps297.reserva.StaticStorage;
import lb.edu.aub.cmps297.reserva.models.Menu;
import lb.edu.aub.cmps297.reserva.models.Restaurant;
import lb.edu.aub.cmps297.reserva.views.MenuAdapter;

public class RestaurantHome extends AppCompatActivity {

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_restaurant_home);

        ArrayList<Integer> menuImgs = new ArrayList<Integer>();
        menuImgs.add(R.drawable.menu1);
        menuImgs.add(R.drawable.menu2);

        Menu menu = new Menu(menuImgs);
        restaurant = new Restaurant("Food stop", "37214721", 100, "dfji23jfdoui3wenoc", "wmjenfnwe", R.drawable.ic_dashboard_black_24dp, menu);
        restaurantImg = findViewById(R.id.idRestarauntHomeImg);
        restaurantName = findViewById(R.id.idRestaurantHomeName);
        restaurantViewMenu = findViewById(R.id.idRestaurantHomeViewMenu);
        restaurantDescriptionTitle = findViewById(R.id.idRestaurantHomeDescriptionTitle);
        restaurantDescriptionText = findViewById(R.id.idRestaurantHomeDescriptionText);
        restaurantPhoneNumberTitle = findViewById(R.id.idRestaurantHomePhoneNumberTitle);
        restaurantPhoneNumberText = findViewById(R.id.idRestaurantHomePhoneNumberText);
        restaurantLocationTitle = findViewById(R.id.idRestaurantHomeLocationTitle);
        restaurantLocationText = findViewById(R.id.idRestaurantHomeLocationText);
        restaurantSeatsTitle = findViewById(R.id.idRestaurantHomeSeatsTitle);
        restaurantSeatsNumber = findViewById(R.id.idRestaurantHomeSeatsNumber);
        restaurantArrowUp = findViewById(R.id.idRestaurantHomeArrowUpBtn);
        restaurantArrowDown = findViewById(R.id.idRestaurantHomeArrowDownBtn);
        restaurantSaveChanges = findViewById(R.id.idRestaurantHomeReserveBtn);

        restaurantImg.setImageResource(restaurant.getImg());
        restaurantName.setText(restaurant.getName());
        restaurantDescriptionText.setText(restaurant.getDescription());
        restaurantPhoneNumberText.setText(restaurant.getCellPhone());
        restaurantLocationText.setText(restaurant.getLocation());
        restaurantSeatsNumber.setText("0");


        restaurantDetailsMenuRV = findViewById(R.id.idRVRestaurantDetailsMenuRV);

        MenuAdapter menuAdapter = new MenuAdapter(this, StaticStorage.restaurants.get(StaticStorage.restaurantChosen).getMenu().getImgList());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        restaurantDetailsMenuRV.setLayoutManager(linearLayoutManager);
        restaurantDetailsMenuRV.setAdapter(menuAdapter);

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
        restaurantSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}
