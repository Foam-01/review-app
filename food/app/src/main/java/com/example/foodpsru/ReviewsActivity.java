package com.example.foodpsru;

// แก้ให้ตรงกับ package ของคุณ

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ReviewsActivity extends AppCompatActivity {

    private RatingBar ratingBar;
    private EditText editTextInput;
    private Button buttonSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews); // เชื่อมโยงกับไฟล์ XML ของหน้ารีวิว

        // ผูกกับ UI
        ratingBar = findViewById(R.id.ratingBar);
        editTextInput = findViewById(R.id.editTextInput);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        // ตั้งค่าการคลิกของปุ่มส่ง
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float rating = ratingBar.getRating(); // รับคะแนนที่ผู้ใช้เลือก
                String reviewText = editTextInput.getText().toString(); // รับข้อความรีวิว

                if (reviewText.isEmpty()) {
                    Toast.makeText(ReviewsActivity.this, "กรุณาเขียนรีวิวก่อนส่ง", Toast.LENGTH_SHORT).show();
                    return;
                }

                // สร้าง Intent เพื่อนำข้อมูลกลับไปยัง MainActivity
                Intent resultIntent = new Intent();
                resultIntent.putExtra("REVIEW_TEXT", reviewText); // ส่งข้อความรีวิว
                resultIntent.putExtra("REVIEW_RATING", rating);   // ส่งคะแนน
                setResult(RESULT_OK, resultIntent); // ตั้งค่าผลลัพธ์ที่ส่งกลับ
                finish(); // ปิดหน้ารีวิว
            }
        });
    }
}
