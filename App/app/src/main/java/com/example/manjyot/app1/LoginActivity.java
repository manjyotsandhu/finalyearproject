package com.example.manjyot.app1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final TasksApplication app = (TasksApplication) getApplication();

        Button logInBtn = (Button) findViewById(R.id.login_btn);
        final EditText usernameInput = (EditText) findViewById(R.id.login_username_input);
        final EditText passwordInput = (EditText) findViewById(R.id.login_password_input);

        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = usernameInput.getText().toString();
                String password = passwordInput.getText().toString();

                if(TextUtils.isEmpty(username)) {
                    usernameInput.setError("Cannot leave username empty");
                }

                if (TextUtils.isEmpty(password)) {
                    passwordInput.setError("Cannot leave password empty");
                }

                if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
                    app.logIn(username, "example@email.com");
                    startActivity(new Intent(LoginActivity.this, NavigationActivity.class));
                }
            }
        });

        Button registerBtn = (Button) findViewById(R.id.login_reg_btn);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

    }

}
