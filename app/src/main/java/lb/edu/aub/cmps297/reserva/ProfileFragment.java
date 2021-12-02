package lb.edu.aub.cmps297.reserva;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

import lb.edu.aub.cmps297.reserva.database.Entities.Client;
import lb.edu.aub.cmps297.reserva.database.Entities.LoggedInUser;
import lb.edu.aub.cmps297.reserva.database.ViewModels.ClientViewModel;
import lb.edu.aub.cmps297.reserva.database.ViewModels.LoggedInUserViewModel;
import lb.edu.aub.cmps297.reserva.database.ViewModels.RestaurantViewModel;
import lb.edu.aub.cmps297.reserva.databinding.FragmentProfileBinding;


public class ProfileFragment extends Fragment implements View.OnClickListener {

    private FragmentProfileBinding binding;

    private Button userReservationsBtn;

    private TextView userName;
    private TextView userPhoneNumber;
    private TextView userEmail;

    private ImageView clientProfileImg;

    private LoggedInUserViewModel loggedInUserViewModel;
    private Client client;
    private ClientViewModel clientViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        userName = root.findViewById(R.id.idUserProfileClientName);
        userPhoneNumber = root.findViewById(R.id.idUserProfileClientPhoneNumber);
        userEmail = root.findViewById(R.id.IdUserProfileClientEmail);

        clientProfileImg = root.findViewById(R.id.idClientProfileImg);

        userReservationsBtn = root.findViewById(R.id.idUserProfileYourReservationsBtn);

        userReservationsBtn.setOnClickListener(this);


        loggedInUserViewModel = new ViewModelProvider(this).get(LoggedInUserViewModel.class);
        clientViewModel = new ViewModelProvider(this).get(ClientViewModel.class);
        LoggedInUser loggedInUser = loggedInUserViewModel.getUser();

        client = clientViewModel.getClient(loggedInUser.email);

        userName.setText(client.name);
        userPhoneNumber.setText(client.phoneNumber);
        userEmail.setText(client.email);


        if (client.profileUri != null){
            File finalFile = new File(client.profileUri);
            clientProfileImg.setImageURI(Uri.fromFile(finalFile));
        }
        else{
            clientProfileImg.setImageResource(R.drawable.profile);
        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.idUserProfileYourReservationsBtn:
                Intent intent2 = new Intent(this.getContext(),UserReservations.class);
                startActivity(intent2);
                break;
        }
    }
}