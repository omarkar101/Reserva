package lb.edu.aub.cmps297.reserva;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import lb.edu.aub.cmps297.reserva.database.Entities.Client;

import lb.edu.aub.cmps297.reserva.database.Entities.LoggedInUser;
import lb.edu.aub.cmps297.reserva.database.ViewModels.ClientViewModel;
import lb.edu.aub.cmps297.reserva.database.ViewModels.LoggedInUserViewModel;
import lb.edu.aub.cmps297.reserva.database.ViewModels.RestaurantViewModel;


public class ChangeUserInfo extends AppCompatActivity {
    private ImageButton userImg;
    private Button SaveChangesBtn;
    private Button CancelBtn;
    private EditText Name;
    private EditText PhoneNumber;

    private Client client;
    private LoggedInUserViewModel loggedInUserViewModel;
    private ClientViewModel clientViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_user_info);

        Name = findViewById(R.id.idUserEditInfoNameText);
        PhoneNumber = findViewById(R.id.idUserEditInfoPhoneNumberText);


        userImg = findViewById(R.id.idUserEditInfoUserImgEdit);
        userImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 3);
            }
        });


        loggedInUserViewModel = new ViewModelProvider(this).get(LoggedInUserViewModel.class);
        clientViewModel = new ViewModelProvider(this).get(ClientViewModel.class);
        LoggedInUser loggedInUser = loggedInUserViewModel.getUser();
        client = clientViewModel.getClient(loggedInUser.email);
        Name.setText(client.name);
        PhoneNumber.setText(client.phoneNumber);

        SaveChangesBtn = findViewById(R.id.idUserEditInfoSaveChanges);
        SaveChangesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clientViewModel.updateClientInfo(Name.getText().toString(),
                        client.email,PhoneNumber.getText().toString());
                finish();
            }
        });

        CancelBtn = findViewById(R.id.idUserEditInfoCancel);
        CancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            ImageView imageView = findViewById(R.id.idUserEditInfoUserImgEdit);
            imageView.setImageURI(selectedImage);
        }
    }
}