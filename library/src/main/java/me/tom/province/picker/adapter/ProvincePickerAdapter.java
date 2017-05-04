package me.tom.province.picker.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import me.tom.province.picker.R;
import me.tom.province.picker.model.Province;

public class ProvincePickerAdapter extends RecyclerView.Adapter<ProvincePickerAdapter.ViewHolder> {

    protected int mColumnWidth;
    protected int mColumnHeight;
    protected LayoutInflater mInflater;

    protected Province mSelectedProvince;
    protected ArrayList<Province> mProvinces;

    protected IItemClickListener mItemClickListener;

    public interface IItemClickListener {
        void onItemClick(Province province);
    }

    public ProvincePickerAdapter(Context context, ArrayList<Province> provinces, int columnWidth, int columnHeight) {
        mProvinces = provinces;
        mColumnWidth = columnWidth;
        mColumnHeight = columnHeight;
        mInflater = LayoutInflater.from(context);
    }

    public void setProvinces(ArrayList<Province> provinces) {
        mProvinces = provinces;
        notifyDataSetChanged();
    }

    public void setSelectedProvince(Province province) {
        mSelectedProvince = province;
        notifyDataSetChanged();
    }

    public void setItemClickListener(IItemClickListener listener) {
        mItemClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return mProvinces.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.province_picker_item, parent, false), mColumnWidth, mColumnHeight);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Province province = mProvinces.get(position);
        holder.abbrView.setText(province.abbr);
        holder.nameView.setText(province.name);
        if (mSelectedProvince != null && mSelectedProvince.name.equals(province.name)) {
            holder.itemView.setBackgroundResource(R.drawable.province_picker_item_selected);
        } else {
            holder.itemView.setBackgroundResource(R.drawable.province_picker_item_normal);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(mProvinces.get(position));
                }
            }
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView abbrView;
        public TextView nameView;

        public ViewHolder(View view, int width, int height) {
            super(view);
            abbrView = (TextView) view.findViewById(R.id.abbrView);
            nameView = (TextView) view.findViewById(R.id.nameView);
            ViewGroup.LayoutParams params = itemView.getLayoutParams();
            params.width = width;
            params.height = height;
            itemView.setLayoutParams(params);
        }
    }
}
