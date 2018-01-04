package com.prescripxion.www.prescripxion2module;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Signup_form_one extends AppCompatActivity {

    public static final int RESULT_LOAD_IMG = 444;
    private String imagePath = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.signupAppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_form_one);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        final Button get_photo_button = (Button) findViewById(R.id.upload_pic_button);

        final EditText signup_name_edittext = (EditText) findViewById(R.id.signupName);
        final EditText signup_mobile_edittext = (EditText) findViewById(R.id.signupMobile);

        final Button sign_up_next_button = (Button) findViewById(R.id.button_signup_next);

        final TextView goto_login_text = (TextView) findViewById(R.id.textView_signupform_login_button);

        goto_login_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder a_builder = new AlertDialog.Builder(Signup_form_one.this);
                a_builder.setMessage("Do you want to go the Login Page?")
                        .setCancelable(false)
                        .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                final SharedPreferences sharedPreferences = getSharedPreferences("signUpInfo", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("userName","");
                                editor.putString("userMobile", "");
                                editor.putString("userAge", "");
                                editor.putString("userGender", "");
                                editor.putString("userBloodGroup", "");
                                editor.putString("userAddress", "");
                                editor.apply();
                                Intent loginPage = new Intent(getApplicationContext(),LoginActivity.class);
                                startActivity(loginPage);
                                finish();
                            }
                        })
                        .setNegativeButton("No",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }) ;
                AlertDialog alert = a_builder.create();
                alert.setTitle("Login Page Confirmation");
                alert.show();
            }
        });



        final TextView goto_login_text2 = (TextView) findViewById(R.id.textView2);

        goto_login_text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder a_builder = new AlertDialog.Builder(Signup_form_one.this);
                a_builder.setMessage("Do you want to go the Login Page?")
                        .setCancelable(false)
                        .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                final SharedPreferences sharedPreferences = getSharedPreferences("signUpInfo", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("userName","");
                                editor.putString("userMobile", "");
                                editor.putString("userAge", "");
                                editor.putString("userGender", "");
                                editor.putString("userBloodGroup", "");
                                editor.putString("userAddress", "");
                                editor.apply();
                                Intent loginPage = new Intent(getApplicationContext(),LoginActivity.class);
                                startActivity(loginPage);
                                finish();
                            }
                        })
                        .setNegativeButton("No",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }) ;
                AlertDialog alert = a_builder.create();
                alert.setTitle("Login Page Confirmation");
                alert.show();
            }
        });


        get_photo_button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                        photoPickerIntent.setType("image/*");
                        startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
                        if(!imagePath.equals("")){
                            Toast.makeText(getApplicationContext(), "Successfully uploaded Profile Picture", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );



        sign_up_next_button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(signup_name_edittext.getText().toString().equals("") ||
                                signup_mobile_edittext.getText().toString().equals("")){
                            Toast.makeText(getApplicationContext(), "Please fill up the form", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            final SharedPreferences sharedPreferences = getSharedPreferences("signUpInfo", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("userName",signup_name_edittext.getText().toString());
                            editor.putString("userMobile", signup_mobile_edittext.getText().toString());
                            editor.apply();
                            Intent nextSignup = new Intent(getApplicationContext(), Signup_form_two.class);
                            startActivity(nextSignup);
                            finish();
                        }
                    }
                }
        );
    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            final Uri imageUri = data.getData();
            if(imageUri!=null){
                imagePath = imageUri.getPath();
                final SharedPreferences sharedPreferences = getSharedPreferences("signUpInfo", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("userImageExists", true);
                editor.putString("userImagePath", imagePath);
                editor.apply();
            }
        }
    }


    @Override
    protected void onRestart() {
        setTheme(R.style.signupAppTheme);
        super.onRestart();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder a_builder = new AlertDialog.Builder(Signup_form_one.this);
        a_builder.setMessage("Do you want to go the Login Page?")
                .setCancelable(false)
                .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final SharedPreferences sharedPreferences = getSharedPreferences("signUpInfo", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("userName","");
                        editor.putString("userMobile", "");
                        editor.putString("userAge", "");
                        editor.putString("userGender", "");
                        editor.putString("userBloodGroup", "");
                        editor.putString("userAddress", "");
                        editor.apply();
                        Intent loginPage = new Intent(getApplicationContext(),LoginActivity.class);
                        startActivity(loginPage);
                        finish();
                    }
                })
                .setNegativeButton("No",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }) ;
        AlertDialog alert = a_builder.create();
        alert.setTitle("Login Page Confirmation");
        alert.show();
    }
}
