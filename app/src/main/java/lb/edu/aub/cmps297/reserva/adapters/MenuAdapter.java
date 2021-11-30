package lb.edu.aub.cmps297.reserva.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import lb.edu.aub.cmps297.reserva.R;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.Viewholder>{
    private Context context;
    private ArrayList<Integer> menuList;
    public MenuAdapter(Context context, ArrayList<Integer> menuList) {
        this.context = context;
        this.menuList = menuList;
    }
    @NonNull
    @Override
    public MenuAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_card_layout, parent, false);
        return new MenuAdapter.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuAdapter.Viewholder holder, int position) {
        Integer image = menuList.get(position);

        holder.img.setImageResource(image);
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public static class Viewholder extends RecyclerView.ViewHolder {
        private ImageView img;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.idMenuImg);
        }
    }
}