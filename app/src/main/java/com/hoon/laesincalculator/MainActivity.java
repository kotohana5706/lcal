package com.hoon.laesincalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.main_menu_1,R.id.main_menu_2,R.id.main_menu_3,R.id.main_menu_4}) void clickMainMenu(View v){
        switch (v.getId()) {
            case R.id.main_menu_1:
                startActivity(new Intent(MainActivity.this, SelectSemesterActivity.class));
                break;
            case R.id.main_menu_2:
                startActivity(new Intent(MainActivity.this, InsertAttendanceActivity.class));
                break;
            case R.id.main_menu_3:
                startActivity(new Intent(MainActivity.this, InsertVolunteerScoreActivity.class));
                break;
            case R.id.main_menu_4:
                startActivity(new Intent(MainActivity.this, InsertSchoolActivityActivity.class));
                break;
        }
    }

    @OnClick(R.id.result) void clickResult(View v){
        startActivity(new Intent(MainActivity.this, ResultActivity.class));
    }
}
