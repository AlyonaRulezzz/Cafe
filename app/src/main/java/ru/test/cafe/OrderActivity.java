package ru.test.cafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
    }

    public static Intent launchOrderScreen(Context context, String etNameText) {
        Intent intent = new Intent(context, OrderActivity.class);
        intent.putExtra("etName", etNameText);
        return intent;
    }
}