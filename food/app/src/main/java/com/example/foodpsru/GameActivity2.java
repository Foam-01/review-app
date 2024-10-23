package com.example.foodpsru;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class GameActivity2 extends AppCompatActivity {

    private ImageView wheelImage2;  // ImageView สำหรับ wheel_image2
    private Button startButton2;
    private TextView resultText2;
    private String[] messages2 = {"Message 1", "Message 2", "Message 3", "Message 4", "Message 5", "Message 6"};

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2);  // ใช้ layout ของ GameActivity2

        wheelImage2 = findViewById(R.id.wheel_image1);  // ค้นหา ImageView ของ wheel_image2
        startButton2 = findViewById(R.id.button);
        resultText2 = findViewById(R.id.resultText);

        startButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinWheelImage2();  // เรียกใช้การหมุน wheel_image2
            }
        });
    }

    private void spinWheelImage2() {
        // แสดง wheel_image2
        wheelImage2.setVisibility(View.VISIBLE);

        // สุ่มมุมสุดท้ายที่วงล้อจะหยุด
        Random random = new Random();
        int randomAngle = random.nextInt(360);  // สุ่มมุมระหว่าง 0 ถึง 359
        int fullRotation = 360 * 5;  // หมุน 5 รอบเต็ม
        int finalRotation = fullRotation + randomAngle;  // เพิ่มมุมสุ่มเข้าไปในค่าหมุน

        // หมุน wheel_image2 ด้วย Animation และตั้ง pivot ให้หมุนที่ด้านล่าง
        RotateAnimation rotate = new RotateAnimation(
                0f, finalRotation,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 1.0f
        );
        rotate.setDuration(3000);  // เวลาในการหมุน 3 วินาที
        rotate.setFillAfter(true);  // ให้ Animation หยุดค้างที่ตำแหน่งสุดท้าย

        wheelImage2.startAnimation(rotate);

        // สุ่มข้อความเมื่อหมุนเสร็จ
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                int randomIndex = random.nextInt(messages2.length);
                String randomMessage = messages2[randomIndex];
                resultText2.setText(randomMessage);
                resultText2.setVisibility(View.VISIBLE);
            }
        }, 3000);  // แสดงผลหลังจากหมุนเสร็จ
    }
}
