package lb.edu.aub.cmps297.reserva;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LogIn extends AppCompatActivity implements View.OnClickListener {
    private Button loginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        loginBtn = findViewById(R.id.idButtonLogIn);
        loginBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent2 = new Intent(LogIn.this, MainActivity.class);
        startActivity(intent2);
        finish();
    }
}