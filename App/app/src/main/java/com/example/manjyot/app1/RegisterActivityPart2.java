package com.example.manjyot.app1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.regex.Pattern;

public class RegisterActivityPart2 extends AppCompatActivity {

    public static String RETURN_NAME_MESSAGE = "com.example.manjyot.app1.NAME_MESSAGE";
    public static String RETURN_EMAIL_MESSAGE = "com.example.manjyot.app1.EMAIL_MESSAGE";
    public static boolean RETURN_TO_PREV_REGISTER = false;

    private EditText passwordInput;
    private EditText confirmPasswordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_part2);

        final TasksApplication app = (TasksApplication) getApplication();

        //Get the name and email entered from prev register screen
        final String nameInputFromRegister = getIntent().getStringExtra(RegisterActivity.NAME_MESSAGE);
        final String emailInputFromRegister = getIntent().getStringExtra(RegisterActivity.EMAIL_MESSAGE);

        //Assign title of register screen as the name entered from previous register screen
        TextView nameRegisterTitle = findViewById(R.id.register_2_name_input);
        nameRegisterTitle.setText(nameInputFromRegister);

        ImageButton prevRegisterBtn = (ImageButton) findViewById(R.id.prev_register_btn);
        Button finishRegisterBtn = (Button) findViewById(R.id.finish_register_btn);

        passwordInput = (EditText) findViewById(R.id.register_pass_input);
        confirmPasswordInput = (EditText) findViewById(R.id.register_pass_confirm);

        prevRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnPrevRegister = new Intent(RegisterActivityPart2.this, RegisterActivity.class);
                returnPrevRegister.putExtra(RETURN_NAME_MESSAGE, nameInputFromRegister);
                returnPrevRegister.putExtra(RETURN_EMAIL_MESSAGE, emailInputFromRegister);
                RETURN_TO_PREV_REGISTER = true;
                startActivity(returnPrevRegister);

                //startActivity(new Intent(RegisterActivityPart2.this, RegisterActivity.class));
            }
        });

        finishRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String password = passwordInput.getText().toString();
                String confirmPassword = confirmPasswordInput.getText().toString();
                boolean registerPassSuccessful = false;
                boolean registerConfirmPassSuccessful = false;

                if (TextUtils.isEmpty(password)) { //If password field is left empty
                    passwordInput.setError("Please enter password");
                    registerPassSuccessful = false;
                } else if (!validatePassword(password)) { //If password is not valid
                    passwordInput.setError("Invalid password");
                    registerPassSuccessful = false;
                } else { //If password is valid
                    registerPassSuccessful = true;
                }

                if (TextUtils.isEmpty(confirmPassword)) { //If confirm pass field is empty
                    confirmPasswordInput.setError("Please enter password confirmation");
                } else if (!validatePassword(confirmPassword)) { //If confirm pass is not valid
                    confirmPasswordInput.setError("Invalid password");
                } else if (!validateConfirmPassword(password, confirmPassword)) { //If passwords do not match
                    confirmPasswordInput.setError("Passwords do not match");
                } else { //If valid
                    registerConfirmPassSuccessful = true;
                }

                //If successful entry for both fields, sign in
                if (registerPassSuccessful && registerConfirmPassSuccessful) {
                    //Reset variables
                    registerPassSuccessful = false;
                    registerConfirmPassSuccessful = false;

                    //Assign sign in credentials
                    app.logIn(nameInputFromRegister, emailInputFromRegister);
                    System.out.println(app.getSignedInName());

                    //Log in
                    startActivity(new Intent(RegisterActivityPart2.this, RegisterSuccessActivity.class));
                }
            }
        });

    }

    public boolean validatePassword(String password) {
        /**
         * Password must contain one digit from 0-9
         * Must be 6-22 characters
         */
        String expression = "((?=.*\\d).{6,22})";
        CharSequence cs = password;

        if (Pattern.compile(expression).matcher(cs).matches())
            return true;
        return false;
    }

    public boolean validateConfirmPassword(String password, String confirmPass) {
        if (password.contentEquals(confirmPass))
            return true;
        return false;
    }
}
