package ru.test.cafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailsActivity extends AppCompatActivity {
    private static final String EXTRA_NAME = "Name";
    private static final String EXTRA_DRINK_TYPE = "drinkType";
    private static final String EXTRA_ADDITIVES = "additives";
    private static final String EXTRA_DRINK = "drink";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
    }

    public static Intent newIntent(Context context, String name, String drinkType, ArrayList<String> additives, String drink) {
        Intent intent = new Intent(context, OrderDetailsActivity.class);
        intent.putExtra(EXTRA_NAME, name);
        intent.putExtra(EXTRA_DRINK_TYPE, drinkType);
        intent.putStringArrayListExtra(EXTRA_ADDITIVES, additives);
        intent.putExtra(EXTRA_DRINK, drink);
        return intent;
    }
}