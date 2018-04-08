package com.example.azizainun.maps.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.azizainun.maps.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    private Button register;
    private EditText email_reg;
    private EditText password_reg1;
    private EditText password_reg2;
    private FirebaseAuth mFirebaseAuth;
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
        setContentView(R.layout.activity_register);

        mFirebaseAuth = FirebaseAuth.getInstance();

        register = (Button) findViewById(R.id.OkeRegister);
        email_reg = (EditText) findViewById(R.id.email_reg);
        password_reg1 = (EditText) findViewById(R.id.password_reg1);
        password_reg2 = (EditText) findViewById(R.id.password_reg2);

        email_reg.addTextChangedListener(textWatcher);
        password_reg1.addTextChangedListener(textWatcher);
        password_reg2.addTextChangedListener(textWatcher);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = email_reg.getText().toString().trim();
                String password1 = password_reg1.getText().toString().trim();

                mFirebaseAuth.createUserWithEmailAndPassword(email, password1).
                        addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()) {
                                    Intent i = new Intent(Register.this, MainActivity.class);
                                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    i.addFlags((Intent.FLAG_ACTIVITY_CLEAR_TASK));
                                    startActivity(i);
                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                                    builder.setMessage(task.getException().getMessage())
                                            .setTitle(R.string.register_error_title)
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

    private  void checkFieldsForEmptyValues(){
        Button b = (Button) findViewById(R.id.OkeRegister);
        String s1 = email_reg.getText().toString();
        String s2 = password_reg1.getText().toString();
        String s3 = password_reg2.getText().toString();
        b.setEnabled(!s1.trim().isEmpty() && !s2.trim().isEmpty()&& !s3.trim().isEmpty() && s2.equals(s3));
    }

    public void ShowPass(View view) {

        password_reg1 = (EditText) findViewById(R.id.password_reg1);
        password_reg2 = (EditText) findViewById(R.id.password_reg2);
        TextView t= (TextView) findViewById(R.id.show_pass_reg);
        String o = (String) t.getText();
        if (o.equals("Show")) {
            password_reg1.setTransformationMethod(null);
            password_reg2.setTransformationMethod(null);
            t.setText("Hide");
        } else {
            password_reg1.setTransformationMethod(new PasswordTransformationMethod());
            password_reg2.setTransformationMethod(new PasswordTransformationMethod());
            t.setText("Show");
        }
    }

}
