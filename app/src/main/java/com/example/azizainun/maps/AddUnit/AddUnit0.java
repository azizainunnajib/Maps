package com.example.azizainun.maps.AddUnit;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.azizainun.maps.Model_Detail;
import com.example.azizainun.maps.R;

public class AddUnit0 extends AppCompatActivity {

    Model_Detail asem = new Model_Detail();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_unit0);

        Fragment fNext = new AddUnit1();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame_next, fNext);
        ft.commit();
    }

    public Model_Detail getAsem() {
        return asem;
    }

    public void setAsem(Model_Detail asem) {
        this.asem = asem;
    }
}
