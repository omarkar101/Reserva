package lb.edu.aub.cmps297.reserva;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeToReserva extends AppCompatActivity implements View.OnClickListener {
    private Button signUpButton, logInButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_to_reserva);

        signUpButton = findViewById(R.id.idButtonWelcomeSignUp);
        logInButton = findViewById(R.id.idButtonWelcomeLogIn);

        signUpButton.setOnClickListener(this);
        logInButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.idButtonWelcomeLogIn:
                Intent intent2 = new Intent(WelcomeToReserva.this, LogIn.class);
                startActivity(intent2);
                break;
            case R.id.idButtonWelcomeSignUp:
                Intent intent1 = new Intent(WelcomeToReserva.this, SignUp.class);
                startActivity(intent1);
                break;
        }
    }
}