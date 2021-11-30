package lb.edu.aub.cmps297.reserva.ui.settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import lb.edu.aub.cmps297.reserva.ChangeUserInfo;
import lb.edu.aub.cmps297.reserva.R;
import lb.edu.aub.cmps297.reserva.WelcomeToReserva;
import lb.edu.aub.cmps297.reserva.database.ViewModels.LoggedInUserViewModel;
import lb.edu.aub.cmps297.reserva.databinding.FragmentSettingsBinding;

public class SettingsFragment extends Fragment implements View.OnClickListener{

    private FragmentSettingsBinding binding;
    private Button logOutUserBtn;
    private Button userEditInfoBtn;
    private LoggedInUserViewModel loggedInUserViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        loggedInUserViewModel = new ViewModelProvider(this).get(LoggedInUserViewModel.class);

        logOutUserBtn = root.findViewById(R.id.idSettingsClientLogOut);
        userEditInfoBtn = root.findViewById(R.id.idClientSettingsEditInfo);
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
            case R.id.idSettingsClientLogOut:
                loggedInUserViewModel.deleteAll();
                Intent intent = new Intent(this.getContext(), WelcomeToReserva.class);
                intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK | intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
            case R.id.idClientSettingsEditInfo:
                Intent intent1 = new Intent(this.getContext(), ChangeUserInfo.class);
                startActivity(intent1);
                break;
        }
    }
}