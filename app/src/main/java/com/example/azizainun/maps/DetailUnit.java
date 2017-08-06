package com.example.azizainun.maps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class DetailUnit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_unit);

        Model model = (Model) getIntent().getExtras().getSerializable("Model");

        EditText editText = (EditText)findViewById(R.id.editText90);
        editText.setText((model.getLokasi()));
    }
}
