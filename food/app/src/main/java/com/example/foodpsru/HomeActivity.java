package com.example.foodpsru;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView; // เพิ่มการนำเข้า ImageView
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private Button gameButton, openBookmarkActivity, openProfileActivity, buttonFood; // เพิ่ม buttonFood
    private ImageView imageButton; // เพิ่ม ImageView
    private SharedPreferences sharedPreferences;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // ตั้งค่าปุ่มเพื่อไปยัง GameActivity
        gameButton = findViewById(R.id.button1);
        gameButton.setOnClickListener(v -> openGameActivity());

        // เชื่อมต่อปุ่มเพื่อเปิด BookmarkActivity
        openBookmarkActivity = findViewById(R.id.button2);
        openBookmarkActivity.setOnClickListener(v -> openBookmarkActivity());

        // เชื่อมต่อปุ่มเพื่อเปิด ProfileActivity
        openProfileActivity = findViewById(R.id.button3);
        openProfileActivity.setOnClickListener(v -> openProfileActivity());

        // เชื่อมต่อปุ่มเพื่อเปิด FoodActivity
        buttonFood = findViewById(R.id.buttonFood); // ตรวจสอบว่า ID ตรงกับที่ใช้ใน XML
        buttonFood.setOnClickListener(v -> openFoodActivity()); // เรียกใช้ openFoodActivity()

        buttonFood = findViewById(R.id.buttonFood2); // ตรวจสอบว่า ID ตรงกับที่ใช้ใน XML
        buttonFood.setOnClickListener(v -> openFoodActivity2()); // เรียกใช้ openFoodActivity()
    }

    // ฟังก์ชันสำหรับไปยังหน้า GameActivity
    private void openGameActivity() {
        Intent intent = new Intent(HomeActivity.this, GameActivity.class);
        startActivity(intent);
    }

    // ฟังก์ชันสำหรับไปยังหน้า BookmarkActivity
    private void openBookmarkActivity() {
        Intent intent = new Intent(HomeActivity.this, BookmarkActivity.class);
        startActivity(intent);
    }

    // ฟังก์ชันสำหรับไปยังหน้า ProfileActivity
    private void openProfileActivity() {
        Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
        startActivity(intent);
    }

    // ฟังก์ชันสำหรับไปยังหน้า ActivityFood
    private void openFoodActivity() {
        Intent intent = new Intent(HomeActivity.this, FoodActivity.class);
        startActivity(intent);
    }
    private void openFoodActivity2() {
        Intent intent = new Intent(HomeActivity.this, FoodActivity2.class);
        startActivity(intent);
    }
}
