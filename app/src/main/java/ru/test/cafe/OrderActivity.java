package ru.test.cafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {
    private static final String EXTRA_NAME = "etName";

    private TextView tvHelloWhatDoUWant;

    private RadioGroup rgDrinkType;
    private RadioButton rbTea;
    private RadioButton rbCoffee;

    private TextView tvWhatToAdd;

    private CheckBox cbSugar;
    private CheckBox cbMilk;
    private CheckBox cbLemon;

    private Spinner spTea;
    private Spinner spCoffee;

    private Button btnMakeOrder;

    private String drinkType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        initViews();
        Intent intent = getIntent();
        String name = intent.getStringExtra(EXTRA_NAME);
        addNameIntvHelloWhatDoUWant(name);

        rgDrinkType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == rbTea.getId()) {
                    drinkType = getString(R.string.rbTea);
                    drinkIsTea();
                } else if (checkedId == rbCoffee.getId()) {
                    drinkType = getString(R.string.rbCoffee);
                    drinkIsCoffee();
                }
            }
        });
        rbCoffee.setChecked(true);

        btnMakeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnMakeOrderOnClick(name);
            }
        });
    }

    private void btnMakeOrderOnClick(String name) {
        ArrayList<String> additives = new ArrayList<>();
        if (cbSugar.isChecked())
            additives.add(cbSugar.getText().toString());
        if (cbMilk.isChecked())
            additives.add(cbMilk.getText().toString());
        if ((drinkType.equals(getString(R.string.rbTea))) && (cbLemon.isChecked()))
            additives.add(cbLemon.getText().toString());

        String drink = "";
        if (drinkType.equals(getString(R.string.rbTea))) {
            drink = spTea.getSelectedItem().toString();
        } else if (drinkType.equals(getString(R.string.rbCoffee))) {
            drink = spCoffee.getSelectedItem().toString();
        }

        Intent intent = OrderDetailsActivity.newIntent(this, name, drinkType, additives, drink);
        startActivity(intent);
        System.out.println(name);
        System.out.println(drinkType);
        System.out.println(additives);
        System.out.println(drink);
    }

    private void drinkIsTea() {
        tvWhatToAdd.setText(String.format(getString(R.string.tvWhatToAdd),
                getString(R.string.rbTea).toLowerCase()));
        cbLemon.setVisibility(View.VISIBLE);
        spTea.setVisibility(View.VISIBLE);
        spCoffee.setVisibility(View.INVISIBLE);
    }

    private void drinkIsCoffee() {
        tvWhatToAdd.setText(String.format(getString(R.string.tvWhatToAdd),
                getString(R.string.rbCoffee).toLowerCase()));
        cbLemon.setVisibility(View.INVISIBLE);
        spTea.setVisibility(View.INVISIBLE);
        spCoffee.setVisibility(View.VISIBLE);
    }

    public static Intent launchOrderScreen(Context context, String etNameText) {
        Intent intent = new Intent(context, OrderActivity.class);
        intent.putExtra(EXTRA_NAME, etNameText);
        return intent;
    }

    private void initViews() {
        tvHelloWhatDoUWant = findViewById(R.id.tvHelloWhatDoUWant);
        rgDrinkType = findViewById(R.id.rgDrinkType);
        rbTea = findViewById(R.id.rbTea);
        rbCoffee = findViewById(R.id.rbCoffee);
        tvWhatToAdd = findViewById(R.id.tvWhatToAdd);
        cbSugar = findViewById(R.id.cbSugar);
        cbMilk = findViewById(R.id.cbMilk);
        cbLemon = findViewById(R.id.cbLemon);
        spTea = findViewById(R.id.spTea);
        spCoffee = findViewById(R.id.spCoffee);
        btnMakeOrder = findViewById(R.id.btnMakeOrder);
    }

    private void addNameIntvHelloWhatDoUWant(String name) {
        String greeting = String.format(getString(R.string.tvHelloWhatDoUWant), name);
        tvHelloWhatDoUWant.setText(greeting);
    }
}