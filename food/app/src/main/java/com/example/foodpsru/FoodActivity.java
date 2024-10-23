package com.example.foodpsru;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class FoodActivity extends AppCompatActivity {

    private LinearLayout reviewContainer; // LinearLayout สำหรับเก็บรีวิว
    private Button reviewButton;
    private Button backButton;
    private Button shareButton;
    private Button button2; // ใช้ button2 แทน bookmarkButton
    private List<Pair<String, Float>> reviewsList = new ArrayList<>(); // เก็บรีวิวและคะแนน

    private static final int REVIEW_REQUEST_CODE = 1; // รหัสสำหรับส่งกลับ

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        reviewContainer = findViewById(R.id.reviewContainer); // ผูกกับ LinearLayout
        reviewButton = findViewById(R.id.button3);
        shareButton = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2); // ผูกกับปุ่ม bookmark
        backButton = findViewById(R.id.backButton);

        // การกดปุ่มสำหรับรีวิว
        reviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FoodActivity.this, ReviewsActivity.class);
                startActivityForResult(intent, REVIEW_REQUEST_CODE);
            }
        });

        // การกดปุ่มย้อนกลับ
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // กลับไปยังหน้าก่อนหน้านี้เมื่อกด BACK
            }
        });

        // การกดปุ่มแชร์
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "นี่คือลิงก์ที่น่าสนใจ: https://example.com");
                startActivity(Intent.createChooser(shareIntent, "แชร์ผ่าน"));
            }
        });

        // ปุ่ม Bookmark (button2 เดิม)
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToBookmark(); // เรียกฟังก์ชันสำหรับเพิ่ม Bookmark
                Toast.makeText(FoodActivity.this, "Bookmark ถูกกด", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // ฟังก์ชันสำหรับเพิ่มข้อมูล Bookmark
    private void addToBookmark() {
        // ข้อมูลอาหารและรูปภาพที่จะเพิ่มใน Bookmark
        String foodName = "ข้าวผัด"; // ชื่ออาหาร
        int menuImageResId = R.drawable.food1; // รูปภาพเมนูที่ต้องการแสดง

        // สร้าง Intent เพื่อส่งข้อมูลไปยัง BookmarkActivity
        Intent intent = new Intent(FoodActivity.this, BookmarkActivity.class);
        intent.putExtra("food_name", foodName);
        intent.putExtra("menu_image", menuImageResId);
        startActivity(intent);

        Toast.makeText(FoodActivity.this, "เมนูถูกบุ๊คมาร์กแล้ว", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REVIEW_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            String reviewText = data.getStringExtra("REVIEW_TEXT");
            float rating = data.getFloatExtra("REVIEW_RATING", 0f);

            // เก็บรีวิวและคะแนนในรายการ
            if (reviewText != null) {
                reviewsList.add(new Pair<>(reviewText, rating));
                updateReviewDisplay(); // แสดงผลรีวิวใหม่
            }
        } else {
            Toast.makeText(this, "ไม่มีการส่งรีวิว", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateReviewDisplay() {
        reviewContainer.removeAllViews(); // ล้างข้อมูลเก่าก่อน
        for (Pair<String, Float> review : reviewsList) {
            TextView reviewTextView = new TextView(this);
            reviewTextView.setText("รีวิว: " + review.first);

            TextView ratingTextView = new TextView(this);
            ratingTextView.setText("คะแนน: " + review.second + " ดาว");

            reviewContainer.addView(reviewTextView); // เพิ่มข้อความรีวิว
            reviewContainer.addView(ratingTextView); // เพิ่มคะแนน
        }
    }
}