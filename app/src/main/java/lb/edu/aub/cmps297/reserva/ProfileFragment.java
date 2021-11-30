package lb.edu.aub.cmps297.reserva;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import lb.edu.aub.cmps297.reserva.databinding.FragmentProfileBinding;


public class ProfileFragment extends Fragment implements View.OnClickListener {

    private FragmentProfileBinding binding;
    private Button logOutUserBtn;
    private Button userEditInfoBtn;
    private Button userReservationsBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        logOutUserBtn = root.findViewById(R.id.idUserProfileLogOutBtn);
        userEditInfoBtn = root.findViewById(R.id.idUserProfileEditUserInfo);
        userReservationsBtn = root.findViewById(R.id.idUserProfileYourReservationsBtn);
        logOutUserBtn.setOnClickListener(this);
        userEditInfoBtn.setOnClickListener(this);
        userReservationsBtn.setOnClickListener(this);
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
            case R.id.idUserProfileLogOutBtn:
                Intent intent = new Intent(this.getContext(), WelcomeToReserva.class);
                intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK | intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
            case R.id.idUserProfileEditUserInfo:
                Intent intent1 = new Intent(this.getContext(), ChangeUserInfo.class);
                startActivity(intent1);
                break;
            case R.id.idUserProfileYourReservationsBtn:
                Intent intent2 = new Intent(this.getContext(),UserReservations.class);
                startActivity(intent2);
                break;
        }
    }
}