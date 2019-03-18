package com.example.android.sqliteweather.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.sqliteweather.R;
import com.example.android.sqliteweather.data.PeopleItem;

import java.util.List;

public class FavoritePeopleAdapter extends RecyclerView.Adapter<FavoritePeopleAdapter.PeopleItemViewHolder> {
    List<PeopleItem> mPeopleItems;
    OnFavoritePeopleClickListener onFavoritePeopleClickListener;

    public interface OnFavoritePeopleClickListener{
        void onFavoritePeopleClick(PeopleItem person);
    }

    public FavoritePeopleAdapter(OnFavoritePeopleClickListener clickListener){
        onFavoritePeopleClickListener = clickListener;
    }

    @NonNull
    @Override
    public PeopleItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.favorite_people_item, viewGroup, false);
        return new PeopleItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PeopleItemViewHolder peopleItemViewHolder, int i) {
        peopleItemViewHolder.bind(mPeopleItems.get(i));
    }

    @Override
    public int getItemCount() {
        if(mPeopleItems != null){
            return mPeopleItems.size();
        } else {
            return 0;
        }
    }

    public class PeopleItemViewHolder extends RecyclerView.ViewHolder {
        TextView personName;

        public PeopleItemViewHolder(@NonNull View itemView) {
            super(itemView);
            personName = itemView.findViewById(R.id.people_list_item);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PeopleItem peopleItem = mPeopleItems.get(getAdapterPosition());
                    onFavoritePeopleClickListener.onFavoritePeopleClick(peopleItem);
                }
            });
        }

        public void bind(PeopleItem item){personName.setText(item.name);}
    }
}
