package lb.edu.aub.cmps297.reserva;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.NavInflater;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.ArrayList;

import lb.edu.aub.cmps297.reserva.databinding.ActivityMainBinding;
import lb.edu.aub.cmps297.reserva.models.Menu;
import lb.edu.aub.cmps297.reserva.models.Restaurant;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private BottomNavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ArrayList<Integer> menuImgs = new ArrayList<Integer>();
        menuImgs.add(R.drawable.menu1);
        menuImgs.add(R.drawable.menu2);

        Menu menu = new Menu(menuImgs);
        if(StaticStorage.restaurants.size() < 10) {
            for (int i = 0; i < 10; i++) {
                StaticStorage.restaurants.add(new Restaurant("Food" + i, "37214721", 100, "dfji23jfdoui3wenoc", "wmjenfnwe", R.drawable.ic_dashboard_black_24dp, menu));
            }
        }
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        navigationView = findViewById(R.id.nav_view);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_favorites,R.id.navigation_profile,R.id.navigation_settings,R.id.navigation_reservations,R.id.navigation_restaurant_settings,R.id.navigation_restaurant_home)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);

        if (StaticStorage.isRestaurant) {
            NavGraph graph = navController.getGraph();
            graph.setStartDestination(R.id.navigation_restaurant_home);
            navController.setGraph(graph);
            NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
            NavigationUI.setupWithNavController(binding.navView, navController);
            navigationView.getMenu().clear(); //clear old inflated items.
            navigationView.inflateMenu(R.menu.bottom_nav_menu_restaurant);
        } else {
            NavGraph graph = navController.getGraph();
            graph.setStartDestination(R.id.navigation_home);
            navController.setGraph(graph);
            NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
            NavigationUI.setupWithNavController(binding.navView, navController);
            navigationView.getMenu().clear(); //clear old inflated items.
            navigationView.inflateMenu(R.menu.bottom_nav_menu_client);
        }
    }
}