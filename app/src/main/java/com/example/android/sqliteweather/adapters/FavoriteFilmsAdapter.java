package com.example.android.sqliteweather.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.android.sqliteweather.R;
import com.example.android.sqliteweather.data.FilmItem;

import java.util.List;

public class FavoriteFilmsAdapter extends RecyclerView.Adapter<FavoriteFilmsAdapter.FilmItemViewHolder> {

    private List<FilmItem> films;
    OnFavoriteFilmClickListener onFavoriteFilm;

    public interface OnFavoriteFilmClickListener{
        void onFavoriteFilmClick(FilmItem filmItem);
    }

    public FavoriteFilmsAdapter(OnFavoriteFilmClickListener clickListener){
        onFavoriteFilm = clickListener;
    }

    @NonNull
    @Override
    public FilmItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        inflater.inflate(R.layout.favorite_film_item, viewGroup, false);
        return new FilmItemViewHolder(viewGroup);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmItemViewHolder filmItemViewHolder, int i) {
        filmItemViewHolder.bind(films.get(i));
    }

    @Override
    public int getItemCount() {
        if(films != null){
            return films.size();
        } else {
            return 0;
        }
    }

    public class FilmItemViewHolder extends RecyclerView.ViewHolder {
        TextView filmName;

        public FilmItemViewHolder(@NonNull View itemView) {
            super(itemView);
            filmName = itemView.findViewById(R.id.film_list_item);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FilmItem item = films.get(getAdapterPosition());
                    onFavoriteFilm.onFavoriteFilmClick(item);
                }
            });
        }

        public void bind(FilmItem filmItem){filmName.setText(filmItem.title);}
    }
}
