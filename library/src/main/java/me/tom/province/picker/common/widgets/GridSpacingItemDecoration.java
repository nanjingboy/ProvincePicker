package me.tom.province.picker.common.widgets;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

    protected int mColumnCount;
    protected int mHorizontalSpace;
    protected int mVerticalSpace;

    public GridSpacingItemDecoration(int columnCount, int horizontalSpace, int verticalSpace) {
        mColumnCount = columnCount;
        mHorizontalSpace = horizontalSpace;
        mVerticalSpace = verticalSpace;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        if (position >= 0) {
            int column = position % mColumnCount;
            outRect.left = mHorizontalSpace - column * mHorizontalSpace / mColumnCount;
            outRect.right = (column + 1) * mHorizontalSpace / mColumnCount;
            if (position < mColumnCount) {
                outRect.top = mVerticalSpace;
            }
            outRect.bottom = mVerticalSpace;
        } else {
            outRect.left = 0;
            outRect.right = 0;
            outRect.top = 0;
            outRect.bottom = 0;
        }
    }
}