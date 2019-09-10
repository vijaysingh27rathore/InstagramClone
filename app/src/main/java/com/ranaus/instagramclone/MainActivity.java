package com.ranaus.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText inputValue;
    TextView getData;
    String getAllData;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getData = (TextView) findViewById(R.id.getDataFromServer);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);

        getData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAllData = "";

                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("InputValues");


                parseQuery.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if (e == null)
                        {
                            if (objects.size() > 0)
                            {
                                for (ParseObject parseObject : objects)
                                {
                                    getAllData = getAllData + parseObject.get("Input").toString()+ "\n";
                                }
                                FancyToast.makeText(MainActivity.this,getAllData+"Hello World !",
                                        FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                            }
                            else
                            {
                                FancyToast.makeText(MainActivity.this,"Hello World !"
                                        ,FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                            }
                        }
                    }
                });
            }
        });



    }

    public void btnSubmit(View view) {

        inputValue = (EditText) findViewById(R.id.inputTxt);

        final String str = inputValue.getText().toString();

        ParseObject object = new ParseObject("InputValues");
        object.put("Input",str);
        object.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null)
                {
                    FancyToast.makeText(MainActivity.this,str+" added successfully!!!"
                            ,FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();                }
            }
        });

    }

    @Override
    public void onClick(View view) {
        //btnSignUp = (Button) findViewById(R.id.btnSignUp);
        switch (view.getId())
        {
            case R.id.btnSignUp:
                Intent intent = new Intent(MainActivity.this,SignUpLoginActivity.class);
                startActivity(intent);
                break;
        }
    }
}
