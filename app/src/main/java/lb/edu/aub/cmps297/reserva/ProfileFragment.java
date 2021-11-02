package lb.edu.aub.cmps297.reserva;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import lb.edu.aub.cmps297.reserva.databinding.FragmentFavoritesBinding;
import lb.edu.aub.cmps297.reserva.databinding.FragmentProfileBinding;
import lb.edu.aub.cmps297.reserva.models.Restaurant;
import lb.edu.aub.cmps297.reserva.views.RestaurantAdapter;


public class ProfileFragment extends Fragment implements View.OnClickListener {

    private FragmentProfileBinding binding;
    private Button logOutUserBtn;
    private Button userEditInfoBtn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        logOutUserBtn = root.findViewById(R.id.idUserProfileLogOutBtn);
        userEditInfoBtn = root.findViewById(R.id.idUserProfileEditUserInfo);
        logOutUserBtn.setOnClickListener(this);
        userEditInfoBtn.setOnClickListener(this);
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
        }
    }
}