package com.ranaus.instagramclone;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUpLoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtSignInUsername,edtSignInPasswd,edtSignUpUsername,edtSignUpPasswd;
    Button btnSignIn,btnSignUp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_login_activity);

    }

    @Override
    public void onClick(View view) {
        edtSignInUsername = (EditText) findViewById(R.id.signIn_username);
        edtSignInPasswd = (EditText) findViewById(R.id.signIn_password);
        edtSignUpUsername = (EditText) findViewById(R.id.sign_Up_username);
        edtSignUpPasswd = (EditText) findViewById(R.id.signUp_password);
        btnSignIn = (Button) findViewById(R.id.Sign_In);
        btnSignUp = (Button) findViewById(R.id.Sign_Up);

        switch (view.getId())
        {
            case R.id.Sign_Up:
                final ParseUser appUser = new ParseUser();
                appUser.setUsername(edtSignUpUsername.getText().toString());
                appUser.setPassword(edtSignUpPasswd.getText().toString());
                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null)
                        {
                            FancyToast.makeText(SignUpLoginActivity.this,appUser.get("username")+" is signed up!!"
                                    ,FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                        }

                        else
                        {
                            FancyToast.makeText(SignUpLoginActivity.this,"Hello World !"
                                    ,FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                        }
                    }
                });

                break;

            case R.id.Sign_In:
                ParseUser.logInInBackground(edtSignInUsername.getText().toString(), edtSignInPasswd.getText().
                        toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {

                        if (user != null && e == null)
                        {
                            FancyToast.makeText(SignUpLoginActivity.this,user.get("username")+" is logged-in"
                                    ,FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                        }

                        else
                            FancyToast.makeText(SignUpLoginActivity.this,"Hello World !"
                                    ,FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();

                    }
                });

                break;
        }
    }
}
