package com.example.android.sqliteweather;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;

public class EntryAdapter extends RecyclerView.Adapter<EntryAdapter.EntryItemViewHolder> {

    private List<String> mEntryItems;
    private List<String> mURLS;
    private OnEntryItemClickListener mForecastItemClickListener;

    public interface OnEntryItemClickListener {
        void onEntryItemClick(String forecastItem);
    }

    public EntryAdapter(OnEntryItemClickListener clickListener) {
        mForecastItemClickListener = clickListener;
    }

    public void updateForecastItems(List<String> forecastItems) {
        mEntryItems = forecastItems;
        notifyDataSetChanged();
    }

    public void updateURLS(List<String> urls){
        mURLS = urls;
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
//        holder.bind(mEntryItems.get());
    }

    class EntryItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mEntryTextTV;


        public EntryItemViewHolder(View itemView) {
            super(itemView);
            mEntryTextTV = itemView.findViewById(R.id.tv_entry_text);
            itemView.setOnClickListener(this);
        }

        public void bind(String forecastItem) {
            String entryText = forecastItem;

            mEntryTextTV.setText(entryText);


        }

        @Override
        public void onClick(View v) {
            String forecastItem;
            if(mURLS == null){
                forecastItem = mEntryItems.get(getAdapterPosition());
            }else{
                forecastItem = mURLS.get(getAdapterPosition());
            }

            mForecastItemClickListener.onEntryItemClick(forecastItem);
        }
    }
}
