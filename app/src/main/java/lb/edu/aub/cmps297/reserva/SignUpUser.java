package lb.edu.aub.cmps297.reserva;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import lb.edu.aub.cmps297.reserva.database.Entities.Client;
import lb.edu.aub.cmps297.reserva.database.Entities.Restaurant;
import lb.edu.aub.cmps297.reserva.database.ViewModels.ClientViewModel;
import lb.edu.aub.cmps297.reserva.database.ViewModels.RestaurantViewModel;

public class SignUpUser extends AppCompatActivity implements View.OnClickListener {

    private Button btnSignUpAsUser;
    private EditText userNameEditText;
    private EditText userEmailEditText;
    private EditText userPasswordEditText;
    private ClientViewModel clientViewModel;
    private RestaurantViewModel restaurantViewModel;

    private boolean signUpClient(String name, String email, String password) {
        Client clientFromDb = clientViewModel.getClient(email);
        if(clientFromDb != null || name.isEmpty() || email.isEmpty() || password.isEmpty()) return false; // the restaurant is already registered
        if(restaurantViewModel.getRestaurant(email) != null) return false;
        Client newClient = new Client(name, email, password);
        clientViewModel.insert(newClient);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_user);
        userNameEditText = findViewById(R.id.idSignUpClientNameEditText);
        userEmailEditText = findViewById(R.id.idSignUpClientEmailEditText);
        userPasswordEditText = findViewById(R.id.idSignUpClientPasswordEditText);
        btnSignUpAsUser = findViewById(R.id.idButtonSignUpUser);
        clientViewModel = new ViewModelProvider(this).get(ClientViewModel.class);
        restaurantViewModel = new ViewModelProvider(this).get(RestaurantViewModel.class);
        btnSignUpAsUser.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        boolean successFulSignUp = signUpClient(userNameEditText.getText().toString(),
                userEmailEditText.getText().toString(),
                userPasswordEditText.getText().toString());
        if(!successFulSignUp) {
            Toast.makeText(SignUpUser.this, "Error", Toast.LENGTH_LONG).show();
            return;
        }
        StaticStorage.isRestaurant = false;
        Intent intent2 = new Intent(SignUpUser.this, LogIn.class);
        startActivity(intent2);
    }
}