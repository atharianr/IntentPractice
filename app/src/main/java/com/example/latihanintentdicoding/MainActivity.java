package com.example.latihanintentdicoding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvResult;
    private int REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnMoveActivity = findViewById(R.id.btn_move_activity);
        btnMoveActivity.setOnClickListener(this);

        Button btnMoveWithDataActivity = findViewById(R.id.btn_move_activity_data);
        btnMoveWithDataActivity.setOnClickListener(this);

        Button btnMoveWithObject = findViewById(R.id.btn_move_activity_object);
        btnMoveWithObject.setOnClickListener(this);

        Button btnDialPhone = findViewById(R.id.btn_dial_number);
        btnDialPhone.setOnClickListener(this);

        Button btnMoveForResult = findViewById(R.id.btn_move_for_result);
        btnMoveForResult.setOnClickListener(this);
        tvResult = findViewById(R.id.tv_result);
    }

    @Override
    public void onClick(View view) {
        // intent biasa
        if (view.getId() == R.id.btn_move_activity) {
            Intent moveIntent = new Intent(MainActivity.this, MoveActivity.class);
            startActivity(moveIntent);
        }

        // intent pake data
        else if (view.getId() == R.id.btn_move_activity_data) {
            Intent moveWithDataIntent = new Intent(MainActivity.this, MoveWithDataActivity.class);
            moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_NAME, "DicodingAcademy Boy");
            moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_AGE, 5);
            startActivity(moveWithDataIntent);
        }

        // intent pake object
        else if (view.getId() == R.id.btn_move_activity_object) {
            Person person = new Person();
            person.setName("DicodingAcademy");
            person.setAge(5);
            person.setEmail("academy@dicoding.com");
            person.setCity("Bandung");
            Intent moveWithObjectIntent = new Intent(MainActivity.this, MoveWithObjectActivity.class);
            moveWithObjectIntent.putExtra(MoveWithObjectActivity.EXTRA_PERSON, person);
            startActivity(moveWithObjectIntent);
        }

        // intent implicit
        else if (view.getId() == R.id.btn_dial_number) {
            String phoneNumber = "081210841382";
            Intent dialPhoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
            startActivity(dialPhoneIntent);
        }

        // intent ke activity result
        else if (view.getId() == R.id.btn_move_for_result) {
            Intent moveForResultIntent = new Intent(MainActivity.this, MoveForResultActivity.class);
            startActivityForResult(moveForResultIntent, REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == MoveForResultActivity.RESULT_CODE) {
                int selectedValue = data.getIntExtra(MoveForResultActivity.EXTRA_SELECTED_VALUE, 0);
                tvResult.setText(String.format("Hasil : %s", selectedValue));
            }
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}