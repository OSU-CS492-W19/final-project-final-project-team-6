package com.example.android.sqliteweather.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.sqliteweather.R;
import com.example.android.sqliteweather.data.StarshipItem;

import java.util.List;

public class FavoriteStarshipsAdapter extends RecyclerView.Adapter<FavoriteStarshipsAdapter.FavoriteStarshipViewHolder> {
    private List<StarshipItem> mStarshipItems;
    OnFavoriteStarshipItemClickListener clickListener;

    public interface OnFavoriteStarshipItemClickListener{
        void onFavoriteStarshipItemClick(StarshipItem item);
    }

    public FavoriteStarshipsAdapter(OnFavoriteStarshipItemClickListener listener){
        clickListener = listener;
    }

    public void updateFavoriteStarships(List<StarshipItem> starships){
        mStarshipItems = starships;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FavoriteStarshipViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.favorite_starship_item, viewGroup, false);
        return new FavoriteStarshipViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteStarshipViewHolder favoriteStarshipViewHolder, int i) {
        favoriteStarshipViewHolder.bind(mStarshipItems.get(i));
    }

    @Override
    public int getItemCount() {
        if(mStarshipItems != null){
            return mStarshipItems.size();
        } else {
            return 0;
        }
    }

    public class FavoriteStarshipViewHolder extends RecyclerView.ViewHolder {
        private TextView starshipName;

        public FavoriteStarshipViewHolder(@NonNull View itemView) {
            super(itemView);
            starshipName = itemView.findViewById(R.id.starship_list_item);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    StarshipItem starshipItem = mStarshipItems.get(getAdapterPosition());
                    clickListener.onFavoriteStarshipItemClick(starshipItem);
                }
            });
        }

        public void bind(StarshipItem item){starshipName.setText(item.name);}
    }
}
