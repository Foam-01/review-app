package com.example.foodpsru;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class BookmarkActivity extends AppCompatActivity {

    private LinearLayout bookmarkContainer; // LinearLayout สำหรับแสดงรายการบุ๊คมาร์ก
    private ArrayList<Bookmark> bookmarks = new ArrayList<>(); // เก็บข้อมูลบุ๊คมาร์ก

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);

        bookmarkContainer = findViewById(R.id.bookmarkContainer);

        // รับข้อมูลจาก Intent ที่ส่งมาจาก FoodActivity
        Intent intent = getIntent();
        String foodName = intent.getStringExtra("food_name");
        int menuImageResId = intent.getIntExtra("menu_image", R.drawable.default_image); // รูปเริ่มต้นหากไม่มีข้อมูล

        // เช็คว่ามีข้อมูลมาหรือไม่
        if (foodName != null) {
            addBookmark(foodName, menuImageResId);
        }

        // แสดงบุ๊คมาร์ก
        displayBookmarks();
    }

    // ฟังก์ชันสำหรับเพิ่มรายการบุ๊คมาร์ก
    private void addBookmark(String foodName, int menuImageResId) {
        bookmarks.add(new Bookmark(foodName, menuImageResId)); // แก้ไขการสร้าง Bookmark
        Toast.makeText(this, foodName + " ถูกบุ๊คมาร์กเรียบร้อยแล้ว", Toast.LENGTH_SHORT).show();
    }

    // ฟังก์ชันสำหรับแสดงรายการบุ๊คมาร์ก
    private void displayBookmarks() {
        bookmarkContainer.removeAllViews(); // ล้างข้อมูลเก่า

        for (Bookmark bookmark : bookmarks) { // ใช้ Bookmark ที่ถูกต้อง
            View bookmarkView = getLayoutInflater().inflate(R.layout.activity_bookmark, null);
            TextView foodNameTextView = bookmarkView.findViewById(R.id.foodName);
            ImageView foodImageView = bookmarkView.findViewById(R.id.foodImage);
            Button removeButton = bookmarkView.findViewById(R.id.removeButton); // ปุ่มลบ

            foodNameTextView.setText(bookmark.getFoodName());
            foodImageView.setImageResource(bookmark.getMenuImageResId());

            // กำหนด Listener ให้ปุ่มลบ
            removeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeBookmark(bookmark); // เรียกฟังก์ชันลบโดยส่ง Bookmark ที่ถูกต้อง
                }
            });

            bookmarkContainer.addView(bookmarkView); // เพิ่มรายการไปยัง LinearLayout
        }
    }

    // ฟังก์ชันสำหรับลบบุ๊คมาร์ก
    private void removeBookmark(Bookmark bookmark) {
        bookmarks.remove(bookmark); // ลบจากรายการ
        displayBookmarks(); // อัพเดตการแสดงผล
        Toast.makeText(this, "ลบบุ๊คมาร์กเรียบร้อยแล้ว", Toast.LENGTH_SHORT).show();
    }

    // คลาส Bookmark สำหรับเก็บข้อมูล
    class Bookmark {
        private String foodName;
        private int menuImageResId;

        public Bookmark(String foodName, int menuImageResId) {
            this.foodName = foodName;
            this.menuImageResId = menuImageResId;
        }

        public String getFoodName() {
            return foodName;
        }

        public int getMenuImageResId() {
            return menuImageResId;
        }
    }
}
