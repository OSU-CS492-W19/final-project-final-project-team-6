package com.example.android.sqliteweather.adapters;

import android.support.annotation.NonNull;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.sqliteweather.R;
import com.example.android.sqliteweather.data.SpeciesItem;

import java.util.List;

public class FavoriteSpeciesAdapter extends RecyclerView.Adapter<FavoriteSpeciesAdapter.SpeciesItemViewHolder> {
    private List<SpeciesItem> speciesItemList;
    OnFavoriteSpeciesItemClickListener onFavoriteSpeciesItemClickListener;

    public interface OnFavoriteSpeciesItemClickListener{
        void OnspeciesItemClick(SpeciesItem speciesItem);
    }

    public FavoriteSpeciesAdapter(OnFavoriteSpeciesItemClickListener listener){
        onFavoriteSpeciesItemClickListener = listener;
    }

    public void udateFavoriteSpecies(List<SpeciesItem> speciesItems){
        speciesItemList = speciesItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SpeciesItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.favorite_species_item, viewGroup, false);
        return new SpeciesItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SpeciesItemViewHolder speciesItemViewHolder, int i) {
        speciesItemViewHolder.bind(speciesItemList.get(i));
    }

    @Override
    public int getItemCount() {
        if(speciesItemList != null){
            return speciesItemList.size();
        } else {
            return 0;
        }
    }

    public class SpeciesItemViewHolder extends RecyclerView.ViewHolder {
        private TextView speciesName;

        public SpeciesItemViewHolder(@NonNull View itemView) {
            super(itemView);
            speciesName = itemView.findViewById(R.id.species_list_item);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SpeciesItem speciesItem = speciesItemList.get(getAdapterPosition());
                    onFavoriteSpeciesItemClickListener.OnspeciesItemClick(speciesItem);
                }
            });
        }

        public void bind(SpeciesItem speciesItem){
            speciesName.setText(speciesItem.name);
        }
    }
}
