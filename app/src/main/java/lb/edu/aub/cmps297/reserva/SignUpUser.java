package lb.edu.aub.cmps297.reserva;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignUpUser extends AppCompatActivity implements View.OnClickListener {
    private Button btnSignUpAsUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_user);

        btnSignUpAsUser = findViewById(R.id.idButtonSignUpUser);
        btnSignUpAsUser.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        StaticStorage.isRestaurant = false;
        Intent intent2 = new Intent(SignUpUser.this, LogIn.class);
        startActivity(intent2);
    }
}