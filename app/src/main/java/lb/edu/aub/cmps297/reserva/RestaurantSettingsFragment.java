package lb.edu.aub.cmps297.reserva;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import lb.edu.aub.cmps297.reserva.databinding.FragmentReservationsBinding;
import lb.edu.aub.cmps297.reserva.databinding.FragmentRestaurantSettingsBinding;

public class RestaurantSettingsFragment extends Fragment implements View.OnClickListener{
    private FragmentRestaurantSettingsBinding binding;
    private Button btnAccountInfo;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRestaurantSettingsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        btnAccountInfo = root.findViewById(R.id.idRestaurantSettingsEditAccountInfoBtn);
        btnAccountInfo.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View v) {
        Intent intent2 = new Intent(this.getContext(), RestaurantEditInfo.class);
        startActivity(intent2);
    }
}