package com.example.android.sqliteweather;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.android.sqliteweather.utils.StarWarsUtils;

import java.util.List;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastItemViewHolder> {

    private List<String> mForecastItems;
    private List<String> mURLS;
    private OnForecastItemClickListener mForecastItemClickListener;

    public interface OnForecastItemClickListener {
        void onForecastItemClick(String forecastItem);
    }

    public ForecastAdapter(OnForecastItemClickListener clickListener) {
        mForecastItemClickListener = clickListener;
    }

    public void updateForecastItems(List<String> forecastItems) {
        mForecastItems = forecastItems;
        notifyDataSetChanged();
    }

    public void updateURLS(List<String> urls){
        mURLS = urls;
    }

    @Override
    public int getItemCount() {
        if (mForecastItems != null) {
            return mForecastItems.size();
        } else {
            return 0;
        }
    }

    @Override
    public ForecastItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.forecast_list_item, parent, false);
        return new ForecastItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ForecastItemViewHolder holder, int position) {
        holder.bind(mForecastItems.get(position));
//        holder.bind(mForecastItems.get());
    }

    class ForecastItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mForecastDateTV;
        private TextView mForecastTempDescriptionTV;
        private ImageView mWeatherIconIV;

        public ForecastItemViewHolder(View itemView) {
            super(itemView);
            mForecastDateTV = itemView.findViewById(R.id.tv_forecast_date);
            mForecastTempDescriptionTV = itemView.findViewById(R.id.tv_forecast_temp_description);
            mWeatherIconIV = itemView.findViewById(R.id.iv_weather_icon);
            itemView.setOnClickListener(this);
        }

        public void bind(String forecastItem) {
            Context context = mForecastDateTV.getContext();
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            String temperatureUnitsValue = sharedPreferences.getString(
                    context.getString(R.string.pref_units_key),
                    context.getString(R.string.pref_units_default_value)
            );
            String temperatureUnitsAbbr = StarWarsUtils.getTemperatureUnitsAbbr(context, temperatureUnitsValue);

            String dateString = forecastItem;

            mForecastDateTV.setText(dateString);


        }

        @Override
        public void onClick(View v) {
            String forecastItem;
            if(mURLS == null){
                forecastItem = mForecastItems.get(getAdapterPosition());
            }else{
                forecastItem = mURLS.get(getAdapterPosition());
            }

            mForecastItemClickListener.onForecastItemClick(forecastItem);
        }
    }
}
