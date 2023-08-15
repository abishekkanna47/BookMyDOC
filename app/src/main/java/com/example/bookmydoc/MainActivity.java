package com.example.bookmydoc;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


        private EditText etName, etPhoneNo;
        private Spinner spSlot;
        private Button btnBookSlot,btn;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            etName = findViewById(R.id.etName);
            etPhoneNo = findViewById(R.id.etPhoneNo);
            spSlot = findViewById(R.id.spSlot);
            btnBookSlot = findViewById(R.id.btnBookSlot);
            String[] slots = new String[]{"9:00 AM", "10:00 AM", "11:00 AM", "12:00 PM", "1:00 PM", "2:00 PM", "3:00 PM", "4:00 PM", "5:00 PM"};

            // Set the adapter for the spinner
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, slots);
            spSlot.setAdapter(adapter);
            btn=findViewById(R.id.button);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // on below line we are getting
                    // the instance of our calendar.
                    final Calendar c = Calendar.getInstance();

                    // on below line we are getting
                    // our day, month and year.
                    int year = c.get(Calendar.YEAR);
                    int month = c.get(Calendar.MONTH);
                    int day = c.get(Calendar.DAY_OF_MONTH);

                    // on below line we are creating a variable for date picker dialog.
                    DatePickerDialog datePickerDialog = new DatePickerDialog(
                            // on below line we are passing context.
                            MainActivity.this,
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth) {
                                    // on below line we are setting date to our text view.
                                    btn.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                                }
                            },
                            // on below line we are passing year,
                            // month and day for selected date in our date picker.
                            year, month, day);
                    // at last we are calling show to
                    // display our date picker dialog.
                    datePickerDialog.show();
                }
            });

            btnBookSlot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name = etName.getText().toString();
                    String phoneNo = etPhoneNo.getText().toString();
                    String appointmentDate = btn.getText().toString();
                    String slot = spSlot.getSelectedItem().toString();

                    // Send SMS without using SMS Manager
                    String message = "Hi " + name + ", your appointment is booked for " + appointmentDate + " at slot " + slot;
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNo, null, message, null, null);

                    Toast.makeText(MainActivity.this, "Appointment booked successfully!", Toast.LENGTH_LONG).show();
                    etName.setText("");
                    btn.setText("APPOINTMENT DATE");
                    etPhoneNo.setText("");

                }
            });
        }
    }

