package lb.edu.aub.cmps297.reserva;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignUpAdmin extends AppCompatActivity implements View.OnClickListener {
    private Button btnSignUpAsAdmin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_admin);

        btnSignUpAsAdmin = findViewById(R.id.idButtonSignUpAdmin);
        btnSignUpAsAdmin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        StaticStorage.isRestaurant = true;
        Intent intent2 = new Intent(SignUpAdmin.this, LogIn.class);
        startActivity(intent2);
    }
}