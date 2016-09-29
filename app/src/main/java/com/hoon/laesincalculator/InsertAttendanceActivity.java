package com.hoon.laesincalculator;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.hoon.laesincalculator.utils.PreferenceUtils;
import com.hoon.laesincalculator.utils.ValidationUtils;


public class InsertAttendanceActivity extends BaseActivity {

    private CheckBox mCbGrade1;
    private CheckBox mCbGrade2;
    private CheckBox mCbGrade3;
    private EditText mEtType1Grade1;
    private EditText mEtType2Grade1;
    private EditText mEtType3Grade1;
    private EditText mEtType4Grade1;
    private EditText mEtType1Grade2;
    private EditText mEtType2Grade2;
    private EditText mEtType3Grade2;
    private EditText mEtType4Grade2;
    private EditText mEtType1Grade3;
    private EditText mEtType2Grade3;
    private EditText mEtType3Grade3;
    private EditText mEtType4Grade3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_attendance);

        mCbGrade1 = (CheckBox) findViewById(R.id.grade_1);
        mCbGrade2 = (CheckBox) findViewById(R.id.grade_2);
        mCbGrade3 = (CheckBox) findViewById(R.id.grade_3);
        mEtType1Grade1 = (EditText) findViewById(R.id.attendance_type_1_grade_1);
        mEtType2Grade1 = (EditText) findViewById(R.id.attendance_type_2_grade_1);
        mEtType3Grade1 = (EditText) findViewById(R.id.attendance_type_3_grade_1);
        mEtType4Grade1 = (EditText) findViewById(R.id.attendance_type_4_grade_1);
        mEtType1Grade2 = (EditText) findViewById(R.id.attendance_type_1_grade_2);
        mEtType2Grade2 = (EditText) findViewById(R.id.attendance_type_2_grade_2);
        mEtType3Grade2 = (EditText) findViewById(R.id.attendance_type_3_grade_2);
        mEtType4Grade2 = (EditText) findViewById(R.id.attendance_type_4_grade_2);
        mEtType1Grade3 = (EditText) findViewById(R.id.attendance_type_1_grade_3);
        mEtType2Grade3 = (EditText) findViewById(R.id.attendance_type_2_grade_3);
        mEtType3Grade3 = (EditText) findViewById(R.id.attendance_type_3_grade_3);
        mEtType4Grade3 = (EditText) findViewById(R.id.attendance_type_4_grade_3);

        mCbGrade1.setChecked(PreferenceUtils.getBoolean(R.string.pref_attendance_grade_1));
        mCbGrade2.setChecked(PreferenceUtils.getBoolean(R.string.pref_attendance_grade_2));
        mCbGrade3.setChecked(PreferenceUtils.getBoolean(R.string.pref_attendance_grade_3));
        mEtType1Grade1.setText(String.valueOf(PreferenceUtils.getInt(R.string.pref_attendance_type_1_grade_1, 0)));
        mEtType2Grade1.setText(String.valueOf(PreferenceUtils.getInt(R.string.pref_attendance_type_2_grade_1, 0)));
        mEtType3Grade1.setText(String.valueOf(PreferenceUtils.getInt(R.string.pref_attendance_type_3_grade_1, 0)));
        mEtType4Grade1.setText(String.valueOf(PreferenceUtils.getInt(R.string.pref_attendance_type_4_grade_1, 0)));
        mEtType1Grade2.setText(String.valueOf(PreferenceUtils.getInt(R.string.pref_attendance_type_1_grade_2, 0)));
        mEtType2Grade2.setText(String.valueOf(PreferenceUtils.getInt(R.string.pref_attendance_type_2_grade_2, 0)));
        mEtType3Grade2.setText(String.valueOf(PreferenceUtils.getInt(R.string.pref_attendance_type_3_grade_2, 0)));
        mEtType4Grade2.setText(String.valueOf(PreferenceUtils.getInt(R.string.pref_attendance_type_4_grade_2, 0)));
        mEtType1Grade3.setText(String.valueOf(PreferenceUtils.getInt(R.string.pref_attendance_type_1_grade_3, 0)));
        mEtType2Grade3.setText(String.valueOf(PreferenceUtils.getInt(R.string.pref_attendance_type_2_grade_3, 0)));
        mEtType3Grade3.setText(String.valueOf(PreferenceUtils.getInt(R.string.pref_attendance_type_3_grade_3, 0)));
        mEtType4Grade3.setText(String.valueOf(PreferenceUtils.getInt(R.string.pref_attendance_type_4_grade_3, 0)));
    }

    public void saveAttendance(View v) {
        try {
            boolean grade1 = mCbGrade1.isChecked();
            boolean grade2 = mCbGrade2.isChecked();
            boolean grade3 = mCbGrade3.isChecked();
            int type1Grade1 = (int) ValidationUtils.validateNumber(mEtType1Grade1.getText().toString(), 0, 365, 0);
            int type2Grade1 = (int) ValidationUtils.validateNumber(mEtType2Grade1.getText().toString(), 0, 365, 0);
            int type3Grade1 = (int) ValidationUtils.validateNumber(mEtType3Grade1.getText().toString(), 0, 365, 0);
            int type4Grade1 = (int) ValidationUtils.validateNumber(mEtType4Grade1.getText().toString(), 0, 365, 0);
            int type1Grade2 = (int) ValidationUtils.validateNumber(mEtType1Grade2.getText().toString(), 0, 365, 0);
            int type2Grade2 = (int) ValidationUtils.validateNumber(mEtType2Grade2.getText().toString(), 0, 365, 0);
            int type3Grade2 = (int) ValidationUtils.validateNumber(mEtType3Grade2.getText().toString(), 0, 365, 0);
            int type4Grade2 = (int) ValidationUtils.validateNumber(mEtType4Grade2.getText().toString(), 0, 365, 0);
            int type1Grade3 = (int) ValidationUtils.validateNumber(mEtType1Grade3.getText().toString(), 0, 365, 0);
            int type2Grade3 = (int) ValidationUtils.validateNumber(mEtType2Grade3.getText().toString(), 0, 365, 0);
            int type3Grade3 = (int) ValidationUtils.validateNumber(mEtType3Grade3.getText().toString(), 0, 365, 0);
            int type4Grade3 = (int) ValidationUtils.validateNumber(mEtType4Grade3.getText().toString(), 0, 365, 0);

            PreferenceUtils.putBoolean(R.string.pref_attendance_grade_1, grade1);
            PreferenceUtils.putBoolean(R.string.pref_attendance_grade_2, grade2);
            PreferenceUtils.putBoolean(R.string.pref_attendance_grade_3, grade3);
            PreferenceUtils.putInt(R.string.pref_attendance_type_1_grade_1, type1Grade1);
            PreferenceUtils.putInt(R.string.pref_attendance_type_2_grade_1, type2Grade1);
            PreferenceUtils.putInt(R.string.pref_attendance_type_3_grade_1, type3Grade1);
            PreferenceUtils.putInt(R.string.pref_attendance_type_4_grade_1, type4Grade1);
            PreferenceUtils.putInt(R.string.pref_attendance_type_1_grade_2, type1Grade2);
            PreferenceUtils.putInt(R.string.pref_attendance_type_2_grade_2, type2Grade2);
            PreferenceUtils.putInt(R.string.pref_attendance_type_3_grade_2, type3Grade2);
            PreferenceUtils.putInt(R.string.pref_attendance_type_4_grade_2, type4Grade2);
            PreferenceUtils.putInt(R.string.pref_attendance_type_1_grade_3, type1Grade3);
            PreferenceUtils.putInt(R.string.pref_attendance_type_2_grade_3, type2Grade3);
            PreferenceUtils.putInt(R.string.pref_attendance_type_3_grade_3, type3Grade3);
            PreferenceUtils.putInt(R.string.pref_attendance_type_4_grade_3, type4Grade3);

            finish();
        } catch (Exception e) {
            Toast.makeText(this, R.string.alert_invalid_input, Toast.LENGTH_SHORT).show();
        }
    }
}
