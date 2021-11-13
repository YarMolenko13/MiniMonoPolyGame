package com.techstroy.minimonopoly;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.techstroy.minimonopoly.cage.Cage;
import com.techstroy.minimonopoly.chip.Chip;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "Minimonopoly";

    private GridLayout grid;
    // 0 - 12
    private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        this.grid = findViewById(R.id.gridLayout);

        final Button button = findViewById(R.id.buttonMove);
        button.setOnClickListener(this);

        drawUI();
        moveChip(0);
    }

    public void onClick(View v) {
        final GridLayout grid = findViewById(R.id.gridLayout);
        final ImageView diceImageViewF = findViewById(R.id.diceFirst);
        final ImageView diceImageViewS = findViewById(R.id.diceSecond);

        deleteChipFromField();

        int[] drawables = new int[]{R.drawable.dice1, R.drawable.dice2, R.drawable.dice3,
                                    R.drawable.dice4, R.drawable.dice5, R.drawable.dice6};

        Random random = new Random();
        int randIntF = random.nextInt(drawables.length) + 1;
        int randIntS = random.nextInt(drawables.length) + 1;

        int steps = randIntF + randIntS ;

        Log.d(TAG, "INDEX: " + index);
//        Log.d(TAG, "MOVE: " + (steps + 1));
        Log.d(TAG, "MOVE: " + steps);

        int diceF = drawables[randIntF - 1];
        int diceS = drawables[randIntS - 1];

        Drawable dr1 = getResources().getDrawable(diceF);
        Drawable dr2 = getResources().getDrawable(diceS);

        diceImageViewF.setImageDrawable(dr1);
        diceImageViewS.setImageDrawable(dr2);

        moveChip(steps);
    }

    private void deleteChipFromField() {
        FrameLayout frame;
        if (index == 6) {
            frame = (FrameLayout) grid.getChildAt(1);
        }
        else if (index < 6 && index > 0) {
            frame = (FrameLayout) grid.getChildAt(index + 1);
        }
        else {
            frame = (FrameLayout) grid.getChildAt(index);
        }
        if (findViewById(R.id.chip) != null) {
            frame.removeView(findViewById(R.id.chip));
        }
    }

    private void moveChip(int steps) {
        Chip chip = new Chip(this, R.drawable.person);

        index += (index + steps) > 11 ? steps - 12 : steps;
        FrameLayout frame;

        if (index == 6) {
            frame = (FrameLayout) grid.getChildAt(1);
        }
        else if (index < 6 && index > 0) {
            frame = (FrameLayout) grid.getChildAt(index + 1);
        }
        else {
            frame = (FrameLayout) grid.getChildAt(index);
        }

        frame.addView(chip);
    }

    private void drawUI() {
        GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
        layoutParams.height = layoutParams.width;
        grid.setLayoutParams(layoutParams);

        List<Cage> cages = new ArrayList<>();

        // row 1
        Cage cage = new Cage(this, 0, 1,
                R.color.city_sector_0, R.string.price_0, R.string.city_0);
        cages.add(cage);
        Cage cage1 = new Cage(this, 0, 2,
                R.color.city_sector_0, R.string.price_1, R.string.city_1);
        cages.add(cage1);
        Cage cage2 = new Cage(this, 0, 3,
                R.color.city_sector_0, R.string.price_2, R.string.city_2);
        cages.add(cage2);

        // col 2
        Cage cage3 = new Cage(this, 1, 3,
                R.color.city_sector_1, R.string.price_3, R.string.city_3);
        cages.add(cage3);
        Cage cage4 = new Cage(this, 2, 3,
                R.color.city_sector_1, R.string.price_4, R.string.city_4);
        cages.add(cage4);

        // row 2
        Cage cage5 = new Cage(this, 3, 2,
                R.color.city_sector_2, R.string.price_5, R.string.city_5);
        cages.add(cage5);
        Cage cage6 = new Cage(this, 3, 1,
                R.color.city_sector_2, R.string.price_6, R.string.city_6);
        cages.add(cage6);
        Cage cage7 = new Cage(this, 3, 0,
                R.color.city_sector_2, R.string.price_7, R.string.city_7);
        cages.add(cage7);

        // col 1

        Cage cage8 = new Cage(this, 2, 0,
                R.color.city_sector_3, R.string.price_8, R.string.city_8);
        cages.add(cage8);
        Cage cage9 = new Cage(this, 1, 0,
                R.color.city_sector_3, R.string.price_9, R.string.city_9);
        cages.add(cage9);

        addCages(cages);
    }

    private void addCages(List<Cage> views) {
        for (Cage v : views) {
            grid.addView(v);
        }
    }
}