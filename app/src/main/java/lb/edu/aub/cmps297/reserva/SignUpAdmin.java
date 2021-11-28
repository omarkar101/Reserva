package lb.edu.aub.cmps297.reserva;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import lb.edu.aub.cmps297.reserva.database.Entities.Restaurant;
import lb.edu.aub.cmps297.reserva.database.ViewModels.LoggedInUserViewModel;
import lb.edu.aub.cmps297.reserva.database.ViewModels.RestaurantViewModel;

public class SignUpAdmin extends AppCompatActivity implements View.OnClickListener {
    private Button btnSignUpAsAdmin;

    private RestaurantViewModel mRestaurantViewModel;
    private EditText restaurantNameEditText;
    private EditText restaurantEmailEditText;
    private EditText restaurantPasswordEditText;

    private boolean signUpRestaurant(String name, String email, String password) {
        Restaurant restaurantFromDb = mRestaurantViewModel.getRestaurant(email);
        if(restaurantFromDb != null) return false; // the restaurant is already registered
        Restaurant newRestaurant = new Restaurant(name, email, password);
        mRestaurantViewModel.insert(newRestaurant);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_admin);
        mRestaurantViewModel = new ViewModelProvider(this).get(RestaurantViewModel.class);
        restaurantNameEditText = findViewById(R.id.idSignUpRestaurantNameEditText);
        restaurantEmailEditText = findViewById(R.id.idSignUpEmailEditText);
        restaurantPasswordEditText = findViewById(R.id.idSignUpPasswordEditText);
        btnSignUpAsAdmin = findViewById(R.id.idButtonSignUpAdmin);
        btnSignUpAsAdmin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        boolean successfulSignUp = signUpRestaurant(restaurantNameEditText.getText().toString(),
                restaurantEmailEditText.getText().toString(), restaurantPasswordEditText.getText().toString());
        if(!successfulSignUp) {
            Toast.makeText(SignUpAdmin.this, "Error", Toast.LENGTH_LONG).show();
            return;
        }
        StaticStorage.isRestaurant = true;
        Intent intent2 = new Intent(SignUpAdmin.this, LogIn.class);
        startActivity(intent2);
    }
}