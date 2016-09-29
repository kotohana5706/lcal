package com.hoon.laesincalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.hoon.laesincalculator.utils.PreferenceUtils;
import com.hoon.laesincalculator.utils.ValidationUtils;



public class InsertSchoolActivityActivity extends BaseActivity {

    private EditText mEtScore1; // 교내수상
    private EditText mEtScore2; // 자치회 임원활동

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_school_activity);

        mEtScore1 = (EditText) findViewById(R.id.school_activity_score_1);
        mEtScore2 = (EditText) findViewById(R.id.school_activity_score_2);

        mEtScore1.setText(String.valueOf(PreferenceUtils.getDouble(R.string.pref_school_activity_score_1, 0)));
        mEtScore2.setText(String.valueOf(PreferenceUtils.getDouble(R.string.pref_school_activity_score_2, 0)));
    }

    public void saveSchoolActivity(View v) {
        try {
            double schoolActivityScore1 = ValidationUtils.validateNumber(mEtScore1.getText().toString(), 0, 4, 0);
            double schoolActivityScore2 = ValidationUtils.validateNumber(mEtScore2.getText().toString(), 0, 2, 0);

            PreferenceUtils.putDouble(R.string.pref_school_activity_score_1, schoolActivityScore1);
            PreferenceUtils.putDouble(R.string.pref_school_activity_score_2, schoolActivityScore2);

            finish();
        } catch (Exception e) {
            Toast.makeText(this, R.string.alert_invalid_input, Toast.LENGTH_SHORT).show();
        }
    }
}
