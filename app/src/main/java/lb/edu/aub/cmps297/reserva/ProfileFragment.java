package lb.edu.aub.cmps297.reserva;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import lb.edu.aub.cmps297.reserva.databinding.FragmentFavoritesBinding;
import lb.edu.aub.cmps297.reserva.databinding.FragmentProfileBinding;
import lb.edu.aub.cmps297.reserva.models.Restaurant;
import lb.edu.aub.cmps297.reserva.views.RestaurantAdapter;


public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}