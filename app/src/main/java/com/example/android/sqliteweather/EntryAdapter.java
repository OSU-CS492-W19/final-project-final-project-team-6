package com.example.android.sqliteweather;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.android.sqliteweather.data.CategoryItem;
import com.example.android.sqliteweather.data.PlanetItem;

import java.util.List;

public class EntryAdapter extends RecyclerView.Adapter<EntryAdapter.EntryItemViewHolder> {

    private List<CategoryItem> mEntryItems;
    private List<String> mURLS;
    private OnEntryItemClickListener mEntryItemClickListener;

    public interface OnEntryItemClickListener {
        void onEntryItemClick(CategoryItem entryItem);
    }

    public EntryAdapter(OnEntryItemClickListener clickListener) {
        mEntryItemClickListener = clickListener;
    }

    public void updateEntryItems(List<CategoryItem> entryItems) {
        mEntryItems = entryItems;
        notifyDataSetChanged();
    }

    public void updateURLS(List<String> urls){
        mURLS = urls;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mEntryItems != null) {
            return mEntryItems.size();
        } else {
            return 0;
        }
    }

    @Override
    public EntryItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.forecast_list_item, parent, false);
        return new EntryItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(EntryItemViewHolder holder, int position) {
        holder.bind(mEntryItems.get(position));
    }

    class EntryItemViewHolder extends RecyclerView.ViewHolder {
        private TextView mEntryTextTV;


        public EntryItemViewHolder(View itemView) {
            super(itemView);
            mEntryTextTV = itemView.findViewById(R.id.tv_entry_text);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CategoryItem entryItem = mEntryItems.get(getAdapterPosition());
                    mEntryItemClickListener.onEntryItemClick(entryItem);
                }
            });
        }

        public void bind(CategoryItem entryText) {
            mEntryTextTV.setText(entryText.name);
        }
    }
}
