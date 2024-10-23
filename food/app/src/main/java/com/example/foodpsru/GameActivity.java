package com.example.foodpsru;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private ImageView boxImage;
    private Button shakeButton;
    private Button backButton; // เพิ่มการอ้างอิงไปยังปุ่ม BACK
    private TextView foodNameTextView;
    private int[] boxImages = {
            R.drawable.box1,
            R.drawable.box2,
            R.drawable.box3,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        boxImage = findViewById(R.id.boxImage);
        shakeButton = findViewById(R.id.shakeButton);
        backButton = findViewById(R.id.backButton); // ผูกปุ่ม BACK
        foodNameTextView = findViewById(R.id.foodNameTextView);

        shakeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shakeBox();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // กลับไปยังหน้าก่อนหน้านี้เมื่อกด BACK
            }
        });
    }

    private void shakeBox() {
        Random random = new Random();
        int randomIndex = random.nextInt(boxImages.length);

        ObjectAnimator rotateAnimator = ObjectAnimator.ofFloat(boxImage, "rotation", 0f, 360f);
        rotateAnimator.setDuration(100);
        rotateAnimator.setRepeatCount(3);

        rotateAnimator.addListener(new android.animation.AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(android.animation.Animator animation) {
                boxImage.setImageResource(boxImages[randomIndex]);

                String foodName = "";
                switch (randomIndex) {
                    case 0:
                        foodName = "ข้าวผัด";
                        break;
                    case 1:
                        foodName = "แกงเขียวหวาน";
                        break;
                    case 2:
                        foodName = "ส้มตำ";
                        break;
                }

                foodNameTextView.setText(foodName);
                foodNameTextView.setAlpha(0f);

                ObjectAnimator fadeInAnimator = ObjectAnimator.ofFloat(foodNameTextView, "alpha", 0f, 1f);
                fadeInAnimator.setDuration(500);
                fadeInAnimator.start();
            }
        });

        rotateAnimator.start();
    }
}
