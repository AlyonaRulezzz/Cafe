package ru.test.cafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailsActivity extends AppCompatActivity {
    private static final String EXTRA_NAME = "Name";
    private static final String EXTRA_DRINK_TYPE = "drinkType";
    private static final String EXTRA_DRINK = "drink";
    private static final String EXTRA_ADDITIVES = "additives";

    private TextView tvName;
    private TextView tvDrink;
    private TextView tvDrinkType;
    private TextView tvAdditivies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        initViews();

        setValues();
    }

    private void setValues() {
        Intent intent = getIntent();
        tvName.setText(intent.getStringExtra(EXTRA_NAME));
        tvDrink.setText(intent.getStringExtra(EXTRA_DRINK));
        tvDrinkType.setText(intent.getStringExtra(EXTRA_DRINK_TYPE));
        tvAdditivies.setText(intent.getStringArrayListExtra(EXTRA_ADDITIVES).toString());
    }

    private void initViews() {
        tvName = findViewById(R.id.tvName);
        tvDrink = findViewById(R.id.tvDrink);
        tvDrinkType = findViewById(R.id.tvDrinkType);
        tvAdditivies = findViewById(R.id.tvAdditivies);
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