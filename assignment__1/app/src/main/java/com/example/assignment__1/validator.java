package com.example.assignment__1;

import android.content.Context;
import android.widget.Toast;

public class validator {
    public static void validateworkedHours(String hoursWorked, Context context) {
        if (hoursWorked.isEmpty()) {
            Toast.makeText(context, "Hours worked cannot be empty", Toast.LENGTH_SHORT).show();
        } else {
            try {
                double hours = Double.parseDouble(hoursWorked);
                if (hours < 0) {
                    Toast.makeText(context, "Hours worked must be non-negative", Toast.LENGTH_SHORT).show();
                }
            } catch (NumberFormatException e) {
                Toast.makeText(context, "Invalid number for hours worked", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public static void validatehourlyRate(String hourlyRate, Context context) {
        if (hourlyRate.isEmpty()) {
            Toast.makeText(context, "Hourly rate cannot be empty", Toast.LENGTH_SHORT).show();
        } else {
            try {
                double rate = Double.parseDouble(hourlyRate);
                if (rate <= 0) {
                    Toast.makeText(context, "Hourly rate must be positive", Toast.LENGTH_SHORT).show();
                }
            } catch (NumberFormatException e) {
                Toast.makeText(context, "Invalid number for hourly rate", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
