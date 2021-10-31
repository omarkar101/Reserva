package lb.edu.aub.cmps297.reserva;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import lb.edu.aub.cmps297.reserva.databinding.FragmentReservationsBinding;
import lb.edu.aub.cmps297.reserva.databinding.FragmentRestaurantSettingsBinding;

public class RestaurantSettingsFragment extends Fragment {
    private FragmentRestaurantSettingsBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRestaurantSettingsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }
}