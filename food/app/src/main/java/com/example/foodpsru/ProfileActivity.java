package com.example.foodpsru;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private EditText profileName, profileEmail;
    private Button saveButton;
    private Button backButton;
    private Button logoutButton;
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "UserProfilePrefs";
    private static final String PROFILE_NAME_KEY = "ProfileName";
    private static final String PROFILE_EMAIL_KEY = "ProfileEmail";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // ผูก view กับตัวแปร
        profileName = findViewById(R.id.profile_name);
        profileEmail = findViewById(R.id.profile_email);
        saveButton = findViewById(R.id.save_button);
        backButton = findViewById(R.id.backButton);
        logoutButton = findViewById(R.id.logout_button);


        // ดึงข้อมูลจาก SharedPreferences
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        loadProfileData();


        // เมื่อกดปุ่มบันทึก ให้ทำการบันทึกข้อมูลโปรไฟล์ใหม่
        saveButton.setOnClickListener(v -> saveProfileData());

        backButton.setOnClickListener(v -> onBackPressed());
        logoutButton.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK); // ทำให้ MainActivity เป็นหน้าหลัก
            startActivity(intent);
            finish(); // ปิด ProfileActivity
        });
    }


    // ฟังก์ชันสำหรับโหลดข้อมูลโปรไฟล์จาก SharedPreferences
    private void loadProfileData() {
        String name = sharedPreferences.getString(PROFILE_NAME_KEY, "");
        String email = sharedPreferences.getString(PROFILE_EMAIL_KEY, "");

        // ตั้งค่าให้ช่องกรอกข้อมูลด้วยข้อมูลที่ดึงมา
        profileName.setText(name);
        profileEmail.setText(email);
    }

    // ฟังก์ชันสำหรับบันทึกข้อมูลโปรไฟล์ลงใน SharedPreferences
    private void saveProfileData() {
        String name = profileName.getText().toString();
        String email = profileEmail.getText().toString();

        // ตรวจสอบว่าชื่อและอีเมลไม่ว่างเปล่า
        if (TextUtils.isEmpty(name)) {
            profileName.setError("กรุณากรอกชื่อ");
            return;
        }

        if (TextUtils.isEmpty(email)) {
            profileEmail.setError("กรุณากรอกอีเมล");
            return;
        }

        // บันทึกข้อมูลลง SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PROFILE_NAME_KEY, name);
        editor.putString(PROFILE_EMAIL_KEY, email);
        editor.apply();

        // แจ้งเตือนว่าบันทึกสำเร็จ
        Toast.makeText(this, "บันทึกข้อมูลโปรไฟล์เรียบร้อย", Toast.LENGTH_SHORT).show();
    }
}
