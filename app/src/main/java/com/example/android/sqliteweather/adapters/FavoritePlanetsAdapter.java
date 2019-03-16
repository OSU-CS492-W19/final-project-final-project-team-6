package com.example.android.sqliteweather.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.sqliteweather.R;
import com.example.android.sqliteweather.data.PlanetItem;

import java.util.List;

public class FavoritePlanetsAdapter extends RecyclerView.Adapter<FavoritePlanetsAdapter.PlanetItemViewHolder> {

    private List<PlanetItem> planetItems;
    private OnFavoritePlanetClickListener onFavoritePlanetClickListener;

    public interface OnFavoritePlanetClickListener{
        void onFavoritePlanetClick(PlanetItem planet);
    }

    FavoritePlanetsAdapter(OnFavoritePlanetClickListener clickListener){
        onFavoritePlanetClickListener = clickListener;
    }

    public void updateFavoritePlanets(List<PlanetItem> planetItemList){
        planetItems = planetItemList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PlanetItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.favorite_planet_item, viewGroup, false);
        return new PlanetItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PlanetItemViewHolder planetItemViewHolder, int i) {
        planetItemViewHolder.bind(planetItems.get(i));
    }

    @Override
    public int getItemCount() {
        if(planetItems != null){
            return planetItems.size();
        } else {
            return 0;
        }
    }

    public class PlanetItemViewHolder extends RecyclerView.ViewHolder {

        private TextView planetName;

        public PlanetItemViewHolder(@NonNull View itemView) {
            super(itemView);
            planetName = itemView.findViewById(R.id.planet_list_item);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PlanetItem planetItem = planetItems.get(getAdapterPosition());
                    onFavoritePlanetClickListener.onFavoritePlanetClick(planetItem);
                }
            });
        }

        public void bind(PlanetItem item){planetName.setText(item.name);}
    }
}
