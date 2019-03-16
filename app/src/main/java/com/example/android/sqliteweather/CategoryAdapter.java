package com.example.android.sqliteweather;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryItemViewHolder> {

    private List<String> mCategoryItems;
    OnCategoryItemClickListener mOnCategoryItemClickListener;

    public interface OnCategoryItemClickListener {
        void onCategoryItemClick(String item);
    }

    CategoryAdapter(OnCategoryItemClickListener onCategoryItemClickListener){
        mOnCategoryItemClickListener = onCategoryItemClickListener;
    }

    public void updateCategoryItems(List<String> categoryItems){
        mCategoryItems = categoryItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.category_search_list_item, viewGroup, false);
        return new CategoryItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryItemViewHolder categoryItemViewHolder, int i) {
        categoryItemViewHolder.bind(mCategoryItems.get(i));
    }

    @Override
    public int getItemCount() {
        if(mCategoryItems != null){
            return mCategoryItems.size();
        } else{
            return 0;
        }
    }

    class CategoryItemViewHolder extends RecyclerView.ViewHolder {
        private TextView CategoryItemTV;

        public CategoryItemViewHolder(@NonNull View itemView) {
            super(itemView);
            CategoryItemTV = itemView.findViewById(R.id.category_search_item);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String categoryItem = mCategoryItems.get(getAdapterPosition());
                    mOnCategoryItemClickListener.onCategoryItemClick(categoryItem);
                }
            });
        }

        public void bind(String categoryItem){
            CategoryItemTV.setText(categoryItem);
        }

    }
}
