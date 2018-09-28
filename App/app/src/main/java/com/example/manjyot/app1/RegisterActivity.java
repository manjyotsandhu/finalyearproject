package com.example.manjyot.app1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegisterActivity extends AppCompatActivity {

    private EditText emailInput;
    private EditText nameInput;
    public static String NAME_MESSAGE = "com.example.manjyot.app1.NAME_MESSAGE";
    public static String EMAIL_MESSAGE = "com.example.manjyot.app1.EMAIL_MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ImageButton registerCloseBtn = (ImageButton) findViewById(R.id.close_register_btn);
        Button registerNextBtn = (Button) findViewById(R.id.next_register_btn);

        nameInput = (EditText) findViewById(R.id.register_name_input);
        emailInput = (EditText) findViewById(R.id.register_email_input);

        if (RegisterActivityPart2.RETURN_TO_PREV_REGISTER == true) {

            final String returnNameFromRegister = getIntent().getStringExtra(RegisterActivityPart2.RETURN_NAME_MESSAGE);
            final String returnEmailFromRegister = getIntent().getStringExtra(RegisterActivityPart2.RETURN_EMAIL_MESSAGE);

            nameInput.setText(returnNameFromRegister);
            emailInput.setText(returnEmailFromRegister);

            RegisterActivityPart2.RETURN_TO_PREV_REGISTER = false;
        }

        registerCloseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });


        registerNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameInput.getText().toString();
                String email = emailInput.getText().toString();

                if (TextUtils.isEmpty(name)) {
                    nameInput.setError("Please enter a name");
                }

                if (TextUtils.isEmpty(email)) { //If email field empty
                    emailInput.setError("Please enter an email");
                } else if (!validateEmail(email)) { //If email doesn't match conditions
                    emailInput.setError("Invalid email");
                } else {
                    Intent sendName = new Intent(RegisterActivity.this, RegisterActivityPart2.class);
                    sendName.putExtra(NAME_MESSAGE, name);
                    sendName.putExtra(EMAIL_MESSAGE, email);
                    startActivity(sendName);
                }
            }
        });



    }

    public boolean validateEmail(String email) {

        String expression = "^[\\w\\.]+@([\\w]+\\.)+[A-Z]{2,7}$";
        CharSequence cs = email;

        if (Pattern.compile(expression, Pattern.CASE_INSENSITIVE).matcher(cs).matches())
         return true;
        return false;
    }


}
