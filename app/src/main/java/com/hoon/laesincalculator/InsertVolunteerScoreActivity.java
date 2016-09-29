package com.hoon.laesincalculator;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.hoon.laesincalculator.utils.PreferenceUtils;
import com.hoon.laesincalculator.utils.ValidationUtils;


public class InsertVolunteerScoreActivity extends BaseActivity {

    private EditText mEtScore1; // 1학년
    private EditText mEtScore2; // 2학년
    private EditText mEtScore3; // 3학년
    private RadioGroup mRgVolunteerType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_volunteer_score);

        mEtScore1 = (EditText) findViewById(R.id.volunteer_work_time_1);
        mEtScore2 = (EditText) findViewById(R.id.volunteer_work_time_2);
        mEtScore3 = (EditText) findViewById(R.id.volunteer_work_time_3);
        mRgVolunteerType = (RadioGroup) findViewById(R.id.volunteer_work_type);

        mEtScore1.setText(String.valueOf(PreferenceUtils.getInt(R.string.pref_volunteer_score_1, 0)));
        mEtScore2.setText(String.valueOf(PreferenceUtils.getInt(R.string.pref_volunteer_score_2, 0)));
        mEtScore3.setText(String.valueOf(PreferenceUtils.getInt(R.string.pref_volunteer_score_3, 0)));
        int volunteerType = PreferenceUtils.getInt(R.string.pref_volunteer_type);
        mRgVolunteerType.clearCheck();
        switch (volunteerType) {
            case 1:
                mRgVolunteerType.check(R.id.volunteer_work_type_1);
                break;
            case 2:
                mRgVolunteerType.check(R.id.volunteer_work_type_2);
                break;
            case 3:
                mRgVolunteerType.check(R.id.volunteer_work_type_3);
                break;
            default:
                break;
        }
    }

    public void saveVolunteerScore(View v) {
        try {
            int volunteerScore1 = (int) ValidationUtils.validateNumber(mEtScore1.getText().toString(), 0, Integer.MAX_VALUE, 0);
            int volunteerScore2 = (int) ValidationUtils.validateNumber(mEtScore2.getText().toString(), 0, Integer.MAX_VALUE, 0);
            int volunteerScore3 = (int) ValidationUtils.validateNumber(mEtScore3.getText().toString(), 0, Integer.MAX_VALUE, 0);
            int volunteerType = Integer.parseInt(findViewById(mRgVolunteerType.getCheckedRadioButtonId()).getTag().toString());

            PreferenceUtils.putInt(R.string.pref_volunteer_score_1, volunteerScore1);
            PreferenceUtils.putInt(R.string.pref_volunteer_score_2, volunteerScore2);
            PreferenceUtils.putInt(R.string.pref_volunteer_score_3, volunteerScore3);
            PreferenceUtils.putInt(R.string.pref_volunteer_type, volunteerType);

            finish();
        } catch (Exception e) {
            Toast.makeText(this, R.string.alert_invalid_input, Toast.LENGTH_SHORT).show();
        }
    }
}
