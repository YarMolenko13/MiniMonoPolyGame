package com.techstroy.minimonopoly.chip;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.widget.FrameLayout;

import androidx.core.content.ContextCompat;

import com.techstroy.minimonopoly.R;

public class Chip extends androidx.appcompat.widget.AppCompatImageView {

    public Chip(Context context, int image) {
        super(context);

        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);
//        layoutParams.setGravity(Gravity.CENTER);
        setLayoutParams(layoutParams);
        setId(R.id.chip);

        setScaleX(0.5f);
        setScaleY(0.5f);

        Drawable drawable = ContextCompat.getDrawable(context, image);
        setImageDrawable(drawable);
    }
}
