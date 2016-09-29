package com.hoon.laesincalculator;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.hoon.laesincalculator.R;

import java.util.ArrayList;
import java.util.List;

import com.hoon.laesincalculator.utils.PreferenceUtils;


public class SelectSubjectActivity extends BaseActivity {

    public static final String INTENT_SEMESTER = "INTENT_SEMESTER";

    private TextView mTvTitle;
    private RadioGroup mRgInclude;
    private LinearLayout mLlSubjectContainer;

    private String mSemester;
    private String[] mSubjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_subject);

        mTvTitle = (TextView) findViewById(R.id.title);
        mRgInclude = (RadioGroup) findViewById(R.id.include);
        mLlSubjectContainer = (LinearLayout) findViewById(R.id.subject_container);

        Intent intent = getIntent();
        int semesterStringId = intent.getIntExtra(INTENT_SEMESTER, R.string.grade_1_1);
        mSemester = getString(semesterStringId);

        mTvTitle.setText(String.format("%s%s", mSemester, getString(R.string.select_subject_title)));

        boolean isIncluded = PreferenceUtils.getBoolean("pref_" + mSemester + "_include");
        if (isIncluded) {
            mRgInclude.check(R.id.include_yes);
        } else {
            mRgInclude.check(R.id.include_no);
        }

        mSubjects = getResources().getStringArray(R.array.subjects);

        for(String subject : mSubjects) {
            CheckBox cbSubject = new CheckBox(this);

            boolean isChecked = PreferenceUtils.getBoolean("pref_" + mSemester + "_" + subject);
            cbSubject.setChecked(isChecked);
            cbSubject.setText(subject);

            mLlSubjectContainer.addView(cbSubject);
        }
    }

    public void completeSubjectSelection(View v) {
        boolean isInclude = Boolean.parseBoolean(findViewById(mRgInclude.getCheckedRadioButtonId()).getTag().toString());
        PreferenceUtils.putBoolean("pref_" + mSemester + "_include", isInclude);

        ArrayList<String> selectedSubjects = new ArrayList<>();
        for(int i = 0; i < mLlSubjectContainer.getChildCount(); i++) {
            CheckBox cbSubject = (CheckBox) mLlSubjectContainer.getChildAt(i);
            PreferenceUtils.putBoolean("pref_" + mSemester + "_" + cbSubject.getText(), cbSubject.isChecked());

            if (cbSubject.isChecked()) {
                selectedSubjects.add(cbSubject.getText().toString());
            }
        }

        Intent intent = new Intent(SelectSubjectActivity.this, InsertScoreActivity.class);
        intent.putExtra(InsertScoreActivity.INTENT_SEMESTER, mSemester);
        intent.putExtra(InsertScoreActivity.INTENT_SUBJECTS, selectedSubjects);
        startActivity(intent);
        finish();
    }
}
