package me.tom.province.picker;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import java.util.ArrayList;

import me.tom.province.picker.adapter.ProvincePickerAdapter;
import me.tom.province.picker.common.widgets.GridSpacingItemDecoration;
import me.tom.province.picker.model.Province;

public class ProvincePicker {

    protected ProvincePickerAdapter mAdapter;

    protected RecyclerView mRecyclerView;
    protected AlertDialog mAlertDialog;

    protected int mColumnCount;

    protected IItemClickListener mItemClickListener;

    public interface IItemClickListener {
        void onItemClick(Province province);
    }

    public ProvincePicker(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.province_picker, null);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        ArrayList<Province> provinces = new ArrayList<>();
        provinces.add(new Province("北京", "京"));
        provinces.add(new Province("天津", "津"));
        provinces.add(new Province("上海", "沪"));
        provinces.add(new Province("重庆", "渝"));
        provinces.add(new Province("河北", "冀"));
        provinces.add(new Province("山西", "晋"));
        provinces.add(new Province("辽宁", "辽"));
        provinces.add(new Province("吉林", "吉"));
        provinces.add(new Province("黑龙江", "黑"));
        provinces.add(new Province("江苏", "苏"));
        provinces.add(new Province("浙江", "浙"));
        provinces.add(new Province("安徽", "皖"));
        provinces.add(new Province("福建", "闽"));
        provinces.add(new Province("江西", "赣"));
        provinces.add(new Province("山东", "鲁"));
        provinces.add(new Province("河南", "豫"));
        provinces.add(new Province("湖北", "鄂"));
        provinces.add(new Province("湖南", "湘"));
        provinces.add(new Province("广东", "粤"));
        provinces.add(new Province("海南", "琼"));
        provinces.add(new Province("四川", "川"));
        provinces.add(new Province("贵州", "贵"));
        provinces.add(new Province("云南", "云"));
        provinces.add(new Province("陕西", "陕"));
        provinces.add(new Province("甘肃", "甘"));
        provinces.add(new Province("青海", "青"));
        provinces.add(new Province("内蒙古", "蒙"));
        provinces.add(new Province("广西", "桂"));
        provinces.add(new Province("西藏", "藏"));
        provinces.add(new Province("宁夏", "宁"));
        provinces.add(new Province("新疆", "新"));
        provinces.add(new Province("香港", "港"));
        provinces.add(new Province("澳门", "澳"));
        provinces.add(new Province("台湾", "台"));

        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        Resources resources = context.getResources();
        mColumnCount = resources.getInteger(R.integer.province_picker_column_count);
        int horizontalSpace = resources.getDimensionPixelSize(R.dimen.province_picker_item_horizontal_space);
        int verticalSpace = resources.getDimensionPixelSize(R.dimen.province_picker_item_vertical_space);
        int columnWidth = (size.x - (mColumnCount + 1) * horizontalSpace) / mColumnCount;
        int columnHeight = (int) Math.ceil(columnWidth / 10.0f * 7);

        mAdapter = new ProvincePickerAdapter(context, provinces, columnWidth, columnHeight);
        mAdapter.setItemClickListener(new ProvincePickerAdapter.IItemClickListener() {
            @Override
            public void onItemClick(Province province) {
                mAdapter.setSelectedProvince(province);
                dismiss();
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(province);
                }
            }
        });

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(6, horizontalSpace, verticalSpace));

        mAlertDialog = new AlertDialog.Builder(context).create();
        mAlertDialog.getWindow().setGravity(Gravity.BOTTOM | Gravity.FILL_HORIZONTAL);
        mAlertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mAlertDialog.setCancelable(true);
        mAlertDialog.setCanceledOnTouchOutside(true);
        mAlertDialog.setView(view);
    }

    public boolean isShowing() {
        return mAlertDialog.isShowing();
    }

    public void setProvinces(ArrayList<Province> provinces) {
        mAdapter.setProvinces(provinces);
    }

    public void show(Province selectedProvince, IItemClickListener listener) {
        mItemClickListener = listener;
        mAdapter.setSelectedProvince(selectedProvince);
        if (!isShowing()) {
            mAlertDialog.show();
        }
    }

    public void dismiss() {
        if (isShowing()) {
            mAlertDialog.dismiss();
        }
    }
}
