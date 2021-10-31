package lb.edu.aub.cmps297.reserva;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import lb.edu.aub.cmps297.reserva.databinding.FragmentProfileBinding;
import lb.edu.aub.cmps297.reserva.databinding.FragmentReservationsBinding;

public class ReservationsFragment extends Fragment {
    private FragmentReservationsBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentReservationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }
}