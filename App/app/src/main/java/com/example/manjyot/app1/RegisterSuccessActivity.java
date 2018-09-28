package com.example.manjyot.app1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RegisterSuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_success);

        final TasksApplication app = (TasksApplication) getApplication();
        Button registerGetStarted = findViewById(R.id.register_success_start_btn);

        TextView registerSuccessNameField = findViewById(R.id.register_success_name_field);
        registerSuccessNameField.setText("Welcome to Tasks, " + app.getSignedInName());

        registerGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterSuccessActivity.this, NavigationActivity.class));
            }
        });

    }
}
