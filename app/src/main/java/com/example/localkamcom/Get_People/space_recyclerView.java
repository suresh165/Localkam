package com.example.localkamcom.Get_People;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class space_recyclerView extends RecyclerView.ItemDecoration {
    private final int space;

    public space_recyclerView(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
//        outRect.left = space;
//        outRect.right = space;
//        if (parent.getChildLayoutPosition(view)==0){
//            outRect.top = space;
//        }
        outRect.bottom = space;
    }
}
