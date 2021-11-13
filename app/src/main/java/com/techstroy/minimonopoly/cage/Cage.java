package com.techstroy.minimonopoly.cage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.techstroy.minimonopoly.R;

public class Cage extends FrameLayout {
    private int rowNumber;
    private int colNumber;
    private int sectorColor;
    private int cityPriceS;
    private int cityName;

    private static final int CAGE_SIZE = 98;

    public Cage(@NonNull Context context, int rowNumber, int colNumber, int sectorColor, int cityPriceS, int cityName) {
        super(context);
        this.rowNumber = rowNumber;
        this.colNumber = colNumber;
        this.sectorColor = sectorColor;
        this.cityPriceS = cityPriceS;
        this.cityName = cityName;

        final int px = convertDP_PX(CAGE_SIZE);
        final int color = getResources().getColor(sectorColor);
        final Drawable border = getResources().getDrawable(R.drawable.border);

        GridLayout.LayoutParams params = new GridLayout.LayoutParams();

        params.height = px;
        params.width = px;
        params.rowSpec = GridLayout.spec(rowNumber);
        params.columnSpec = GridLayout.spec(colNumber, 1, 1);
        params.setGravity(Gravity.FILL);

        setBackground(border);

        setLayoutParams(params);

        /*
        Дети:
        - фон
        - цена
        - название
         */
        addView(createBackground(context, color, px));
        addView(createPrice(context, cityPriceS));
        addView(createText(context));
    }

    private ImageView createBackground(Context context, int color, int px) {
        ImageView background = new ImageView(context);
        Bitmap bitmap = Bitmap.createBitmap(px, px, Bitmap.Config.ARGB_8888);
        bitmap.eraseColor(color);
        background.setImageBitmap(bitmap);
        return background;
    }

    private TextView createText(Context context) {
        TextView text = new TextView(context);
        text.setText(cityName);
        text.setTextColor(Color.WHITE);
        text.setTextSize(18);
        text.setGravity(Gravity.CENTER);
        text.setPadding(0, convertDP_PX(12), 0, 0);
        return text;
    }

    private TextView createPrice(Context context, int priceText) {
        TextView price = new TextView(context);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

        price.setText(priceText);
        price.setTextColor(Color.BLACK);
        price.setBackgroundColor(Color.WHITE);
        price.setGravity(Gravity.CENTER);
        price.setPadding(0, 3, 0, 3);

        price.setLayoutParams(layoutParams);
        return price;
    }

    private int convertDP_PX(int DP) {
        return  (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DP,  getResources().getDisplayMetrics());
    }
}
