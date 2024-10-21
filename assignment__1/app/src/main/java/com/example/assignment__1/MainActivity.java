package com.example.assignment__1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText HoursWorked;
    private EditText  HourlyRate;
    private TextView Pay ;
    private TextView  OvertimePay;
    private TextView   TotalPay;
    private TextView   Tax;
    private Button Calculate;
    private validator Validator;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle("Pay Calculator");
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });
        HoursWorked = findViewById(R.id.HoursWorked);
        HourlyRate = findViewById(R.id.HourlyRate);
        Pay = findViewById(R.id.Pay);
        OvertimePay = findViewById(R.id.OvertimePay);
        TotalPay = findViewById(R.id.TotalPay);
        Tax = findViewById(R.id.Tax);
        Calculate = findViewById(R.id.Calculate);

        Calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Validator.validateworkedHours(HoursWorked.getText().toString(), MainActivity.this);
                Validator.validatehourlyRate(HourlyRate.getText().toString(), MainActivity.this);
                displayOutputs();
            }
        });
    }
    private void displayOutputs(){
        double workedHours = Double.parseDouble(HoursWorked.getText().toString());
        double hourlyRate = Double.parseDouble(HourlyRate.getText().toString());
        double pay = payCalculate(workedHours,hourlyRate);
        double overtimePay = overtimepayCalculate (workedHours,hourlyRate);
        double tax = taxCalculate (pay);
        double totalPay = totalpayCalculate(pay,tax);

        Pay.setText("Pay: $" + String.format("%.2f", pay));
        OvertimePay.setText("Overtime Pay: $" + String.format("%.2f",overtimePay));
        Tax.setText("Tax: $" + String.format("%.2f", tax));
        TotalPay.setText("Total Pay: $" + String.format("%.2f",totalPay));
    }

    private double payCalculate(double workedHours, double hourlyRate){
        if (workedHours <= 40) {
            return workedHours * hourlyRate;
        } else {
            return (40 * hourlyRate) + overtimepayCalculate(workedHours, hourlyRate);
        }
    }

    private double overtimepayCalculate(double workerHours, double hourlyRate){
        if (workerHours <= 40) {
            return 0;
        } else {
            return (workerHours - 40) * hourlyRate * 1.5;
        }
    }

    private double taxCalculate(double pay){
        return pay * 0.18;
    }

    private double totalpayCalculate(double pay, double tax){
        return pay - tax;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menu) {

        if (menu.getItemId() == R.id.Aboutme) {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
            return true;
        }
  return true;
    }
}
