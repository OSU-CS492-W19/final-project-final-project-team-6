package com.example.android.sqliteweather.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.sqliteweather.R;
import com.example.android.sqliteweather.data.VehicleItem;

import java.util.List;

public class FavoriteVehiclesAdpater extends RecyclerView.Adapter<FavoriteVehiclesAdpater.FavoriteVechicleViewHolder> {
    private List<VehicleItem> mVechicleList;
    OnFavoriteVechicleItemClickListener mOnVehicleClick;

    public FavoriteVehiclesAdpater(OnFavoriteVechicleItemClickListener clickListener){
        mOnVehicleClick = clickListener;
    }

    public void updateFavoriteVehicles(List<VehicleItem> vehicleItems){
        mVechicleList = vehicleItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FavoriteVechicleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.favorite_vehicle_item, viewGroup, false);
        return new FavoriteVechicleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteVechicleViewHolder favoriteVechicleViewHolder, int i) {
        favoriteVechicleViewHolder.bind(mVechicleList.get(i));
    }

    @Override
    public int getItemCount() {
        if(mVechicleList != null){
            return mVechicleList.size();
        } else {
            return 0;
        }
    }

    public interface OnFavoriteVechicleItemClickListener{
        void onFavoriteVechicleItemClick(VehicleItem vehicleItem);
    }

    public class FavoriteVechicleViewHolder extends RecyclerView.ViewHolder {
        private TextView vechicleName;

        public FavoriteVechicleViewHolder(@NonNull View itemView) {
            super(itemView);
            vechicleName = itemView.findViewById(R.id.vehicle_list_item);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    VehicleItem vehicleItem = mVechicleList.get(getAdapterPosition());
                    mOnVehicleClick.onFavoriteVechicleItemClick(vehicleItem);
                }
            });
        }

        public void bind(VehicleItem item){vechicleName.setText(item.name);}
    }
}
