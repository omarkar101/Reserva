package lb.edu.aub.cmps297.reserva;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignUp extends AppCompatActivity implements View.OnClickListener{

    private Button signUpAsUserBtn;
    private Button signUpAsAdminBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        signUpAsUserBtn = findViewById(R.id.idBtnSignUpAsUser);
        signUpAsUserBtn.setOnClickListener(this);

        signUpAsAdminBtn = findViewById(R.id.idBtnSignUpAsAdmin);
        signUpAsAdminBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.idBtnSignUpAsUser:
                Intent intent2 = new Intent(SignUp.this, SignUpUser.class);
                startActivity(intent2);
                break;
            case R.id.idBtnSignUpAsAdmin:
                Intent intent1 = new Intent(SignUp.this, SignUpAdmin.class);
                startActivity(intent1);
                break;
        }
    }
}