package lb.edu.aub.cmps297.reserva;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;

import java.util.concurrent.ExecutionException;

import lb.edu.aub.cmps297.reserva.Enums.UserType;
import lb.edu.aub.cmps297.reserva.database.Entities.Client;
import lb.edu.aub.cmps297.reserva.database.Entities.LoggedInUser;
import lb.edu.aub.cmps297.reserva.database.Entities.Restaurant;
import lb.edu.aub.cmps297.reserva.database.ViewModels.ClientViewModel;
import lb.edu.aub.cmps297.reserva.database.ViewModels.LoggedInUserViewModel;
import lb.edu.aub.cmps297.reserva.database.ViewModels.RestaurantViewModel;


public class LogIn extends AppCompatActivity implements View.OnClickListener {

    private ClientViewModel mClientViewModel;
    private RestaurantViewModel mRestaurantViewModel;
    private LoggedInUserViewModel mloggedInUserViewModel;
    public boolean checkCredentials(String email, String password, UserType userType) throws ExecutionException, InterruptedException {
        if(userType.equals(UserType.CLIENT)) {
            mClientViewModel = new ViewModelProvider(this).get(ClientViewModel.class);
            Client client = mClientViewModel.getClient(email);
            if(client == null) return false;
            return client.password.equals(password);
        } else if(userType.equals(UserType.RESTAURANT)) {
            mRestaurantViewModel = new ViewModelProvider(this).get(RestaurantViewModel.class);
            Restaurant restaurant = mRestaurantViewModel.getRestaurant(email);
            if (restaurant == null) return false;
            return restaurant.password.equals(password);
        }
        return true;
    }

    public boolean loginUser(String email, String password, UserType userType) throws ExecutionException, InterruptedException {
        if(!checkCredentials(email, password, userType)) {
            return false;
        }
        mloggedInUserViewModel = new ViewModelProvider(this).get(LoggedInUserViewModel.class);
        LoggedInUser loggedInUser = new LoggedInUser(email, userType.name());
        mloggedInUserViewModel.insert(loggedInUser);
        return true;
    }

    private Button loginAsRestaurantBtn;
    private Button loginAsClientBtn;
    private EditText emailEditText;
    private  EditText passwordEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        emailEditText = findViewById(R.id.idLoginEmailEditText);
        passwordEditText = findViewById(R.id.idLoginPasswordEditText);
        loginAsClientBtn = findViewById(R.id.idLoginLoginAsClientBtn);
        loginAsRestaurantBtn = findViewById(R.id.idLoginLoginAsRestaurantBtn);
        loginAsClientBtn.setOnClickListener(this);
        loginAsRestaurantBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        boolean loginSuccessful = false;
        switch (v.getId()) {
            case R.id.idLoginLoginAsClientBtn:
                try {
                    loginSuccessful = loginUser(emailEditText.getText().toString(), passwordEditText.getText().toString(), UserType.CLIENT);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.idLoginLoginAsRestaurantBtn:
                try {
                    loginSuccessful = loginUser(emailEditText.getText().toString(), passwordEditText.getText().toString(), UserType.RESTAURANT);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
        }
        if (!loginSuccessful) {
            Toast.makeText(LogIn.this, "Incorrect email or password!", Toast.LENGTH_LONG).show();
            return;
        }
        Intent intent = new Intent(LogIn.this, MainActivity.class);
        intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK | intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}