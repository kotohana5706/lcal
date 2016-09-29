package com.hoon.laesincalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;



public class SelectSemesterActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_semester);
    }

    public void onButtonClick(View v) {
        Intent intent = new Intent(SelectSemesterActivity.this, SelectSubjectActivity.class);

        switch (v.getId()) {
            case R.id.grade_1_1_insertion: intent.putExtra(SelectSubjectActivity.INTENT_SEMESTER, R.string.grade_1_1); break;
            case R.id.grade_1_2_insertion: intent.putExtra(SelectSubjectActivity.INTENT_SEMESTER, R.string.grade_1_2); break;
            case R.id.grade_2_1_insertion: intent.putExtra(SelectSubjectActivity.INTENT_SEMESTER, R.string.grade_2_1); break;
            case R.id.grade_2_2_insertion: intent.putExtra(SelectSubjectActivity.INTENT_SEMESTER, R.string.grade_2_2); break;
            case R.id.grade_3_1_insertion: intent.putExtra(SelectSubjectActivity.INTENT_SEMESTER, R.string.grade_3_1); break;
            case R.id.grade_3_2_insertion: intent.putExtra(SelectSubjectActivity.INTENT_SEMESTER, R.string.grade_3_2); break;
        }

        startActivity(intent);
    }

    public void returnToMain(View v) {
        finish();
    }
}
