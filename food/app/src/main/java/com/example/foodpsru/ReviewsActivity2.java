package com.example.foodpsru;



import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ReviewsActivity2 extends AppCompatActivity {

    private RatingBar ratingBar;
    private EditText editTextInput;
    private Button buttonSubmit;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review2); // เชื่อมโยงกับไฟล์ XML ของหน้ารีวิว

        // ผูกกับ UI
        ratingBar = findViewById(R.id.ratingBar2);
        editTextInput = findViewById(R.id.editTextInput2);
        buttonSubmit = findViewById(R.id.buttonSubmit2);

        // ตั้งค่าการคลิกของปุ่มส่ง
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float rating = ratingBar.getRating(); // รับคะแนนที่ผู้ใช้เลือก
                String reviewText = editTextInput.getText().toString(); // รับข้อความรีวิว

                if (reviewText.isEmpty()) {
                    Toast.makeText(ReviewsActivity2.this, "กรุณาเขียนรีวิวก่อนส่ง", Toast.LENGTH_SHORT).show();
                    return;
                }

                // สร้าง Intent เพื่อนำข้อมูลกลับไปยัง MainActivity
                Intent resultIntent = new Intent();
                resultIntent.putExtra("REVIEW_TEXT", reviewText);
                resultIntent.putExtra("REVIEW_RATING", rating);   // ส่งคะแนน
                setResult(RESULT_OK, resultIntent); // ตั้งค่าผลลัพธ์ที่ส่งกลับ
                finish(); // ปิดหน้ารีวิว
            }
        });
    }
}
