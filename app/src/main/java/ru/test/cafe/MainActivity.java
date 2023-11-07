package ru.test.cafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String etNameText;
    private String etPasswordText;
    private Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initViews();
                launchOrderActivity(etNameText);
            }
        });
    }

    private void initViews() {
        etNameText = ((EditText) findViewById(R.id.etName)).getText().toString().trim();
        etPasswordText = ((EditText) findViewById(R.id.etPassword)).getText().toString().trim();
        btnSignIn = findViewById(R.id.btnSignIn);
    }

    private void launchOrderActivity(String etNameText) {
        if (etNameText.isEmpty() || etPasswordText.isEmpty()) {
            Toast.makeText(MainActivity.this, R.string.Error_empty_name_or_password_in_signIn, Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, OrderActivity.class);
            intent.putExtra("etName", etNameText);
            startActivity(intent);
        }

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }
}