package com.example.azizainun.maps;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.text.style.TypefaceSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    private EditText eUser, ePass;
    private RequestQueue requestQueue;
    private StringRequest request;
    private String UID;
    private FirebaseAuth mFirebaseAuth;
    private Button LogButton;
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            checkFieldsForEmptyValues();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ePass = (EditText) findViewById(R.id.password);
        eUser = (EditText) findViewById(R.id.username);
        ePass.addTextChangedListener(textWatcher);
        eUser.addTextChangedListener(textWatcher);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "font/Raleway-Light.ttf");
        ePass.setTypeface(typeface);
        eUser.setTypeface(typeface);

        mFirebaseAuth = FirebaseAuth.getInstance();

        LogButton= (Button) findViewById(R.id.LoginButton);
        LogButton.setTypeface(typeface);

        LogButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String username = eUser.getText().toString().trim();
                String password = ePass.getText().toString().trim();

                /*if(password.equals("aaa")) {
                    Intent i = new Intent(Login.this, MainActivity.class);
                    startActivity(i);

                }*/

                mFirebaseAuth.signInWithEmailAndPassword(username, password)
                        .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Intent i = new Intent(Login.this, MainActivity.class);
                                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    i.addFlags((Intent.FLAG_ACTIVITY_CLEAR_TASK));
                                    startActivity(i);
                                    mFirebaseAuth.getCurrentUser();
                                    UID = mFirebaseAuth.getCurrentUser().getUid();
                                    Modelr useridw = new Modelr();
                                    useridw.setUID(UID);
                                    String era = useridw.getUID();
                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                                    builder.setMessage(task.getException().getMessage())
                                            .setTitle(R.string.login_error_title)
                                            .setPositiveButton(android.R.string.ok, null);
                                    AlertDialog dialog = builder.create();
                                    dialog.show();
                                }
                            }
                        });
            }
        });


        checkFieldsForEmptyValues();
    }


    public void ShowPass(View view) {
        Toast.makeText(this, "oke", Toast.LENGTH_SHORT).show();
        TextView t= (TextView) findViewById(R.id.show_pass);
        String o = (String) t.getText();
        if (o.equals("Show")) {
            ePass.setTransformationMethod(null);
            t.setText("Hide");
            ePass.setSelection(ePass.getText().length());
        } else {
            ePass.setTransformationMethod(new PasswordTransformationMethod());
            t.setText("Show");
            ePass.setSelection(ePass.getText().length());
        }
    }

    public void RegisterButton(View view) {
        Intent i = new Intent(this, Register.class);
        startActivity(i);
    }

/*    public void OkeRegister(View view) {
        ePass = (EditText) findViewById(R.id.password);
        eUser = (EditText) findViewById(R.id.username);
        final String username = ePass.getText().toString().trim();
        final String password = eUser.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject obj = new JSONObject(response);
                            if(obj.getBoolean("error")){
                                SharedPrefManager.getInstance(getApplicationContext())
                                        .userLogin(
                                                obj.getInt("id"),
                                                obj.getString("username"),
                                                obj.getString("email")
                                        );
                                Toast.makeText(getApplicationContext(), obj.getString("Login Succes"), Toast.LENGTH_SHORT);
                                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(i);
                                finish();
                            }else{
                                Toast.makeText(getApplicationContext(),obj.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);
                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }*/

    public void OkeLogin(View view) {
        String username = eUser.getText().toString();
        String password = ePass.getText().toString();

            mFirebaseAuth.signInWithEmailAndPassword("azizainunnajib@gmail.com", "FirebaseAziz1")
                    .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                        Intent i = new Intent(Login.this, MainActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        i.addFlags((Intent.FLAG_ACTIVITY_CLEAR_TASK));
                        startActivity(i);
                }
            });



        /*request = new StringRequest(Request.Method.POST, Constants.URL_REGISTER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response );
                    if (jsonObject.names().get(0).equals("success")) {
                        Toast.makeText(getApplicationContext(), "SUCCESS " + jsonObject.getString(("success")), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    } else {
                        Toast.makeText(getApplicationContext(), "Error" +jsonObject.getString("message"),Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //return super.getParams();
                HashMap<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("username", eUser.getText().toString());
                hashMap.put("email", eUser.getText().toString());
                hashMap.put("password", ePass.getText().toString());
                return hashMap;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(request);
      //  requestQueue.add(request);

        Toast.makeText(this, "maps", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);*/
    }

    private  void checkFieldsForEmptyValues(){
        Button b = (Button) findViewById(R.id.LoginButton);

        String s1 = eUser.getText().toString();
        String s2 = ePass.getText().toString();

        b.setEnabled(!s1.trim().isEmpty() && !s2.trim().isEmpty());
    }
}