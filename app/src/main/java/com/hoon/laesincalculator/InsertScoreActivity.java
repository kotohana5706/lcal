package com.hoon.laesincalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;


import java.util.ArrayList;

import com.hoon.laesincalculator.utils.PreferenceUtils;


public class InsertScoreActivity extends BaseActivity {

    public static final String INTENT_SEMESTER = "INTENT_SEMESTER";
    public static final String INTENT_SUBJECTS = "INTENT_SUBJECTS";

    private TextView mTvTitle;
    private TableLayout mTlScores;

    private String mSemester;
    private ArrayList<String> mSubjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_score);

        mTvTitle = (TextView) findViewById(R.id.title);
        mTlScores = (TableLayout) findViewById(R.id.score_table);

        Intent intent = getIntent();
        mSemester = intent.getStringExtra(INTENT_SEMESTER);
        mSubjects = intent.getStringArrayListExtra(INTENT_SUBJECTS);

        mTvTitle.setText(String.format("%s%s", mSemester, getString(R.string.score_title)));

        for(String subject : mSubjects) {
            View vLayoutSubject = getLayoutInflater().inflate(R.layout.item_score, null);

            TextView tvSubject = (TextView) vLayoutSubject.findViewById(R.id.subject);
            EditText etScoreType1 = (EditText) vLayoutSubject.findViewById(R.id.score_type_1);
            EditText etScoreType2 = (EditText) vLayoutSubject.findViewById(R.id.score_type_2);
            EditText etScoreType3 = (EditText) vLayoutSubject.findViewById(R.id.score_type_3);
            EditText etScoreType4 = (EditText) vLayoutSubject.findViewById(R.id.score_type_4);

            tvSubject.setText(subject);
            etScoreType1.setText(String.valueOf(PreferenceUtils.getString("pref_" + mSemester + "_" + subject + "_type_1_score", "")));
            etScoreType2.setText(String.valueOf(PreferenceUtils.getDouble("pref_" + mSemester + "_" + subject + "_type_2_score", 0)));
            etScoreType3.setText(String.valueOf(PreferenceUtils.getDouble("pref_" + mSemester + "_" + subject + "_type_3_score", 0)));
            etScoreType4.setText(String.valueOf(PreferenceUtils.getDouble("pref_" + mSemester + "_" + subject + "_type_4_score", 0)));

            mTlScores.addView(vLayoutSubject);
        }
    }

    public void completeScoreInsertion(View v) {
        for(int i = 1; i < mTlScores.getChildCount(); i++) {
            View vLayoutSubject = mTlScores.getChildAt(i);

            TextView tvSubject = (TextView) vLayoutSubject.findViewById(R.id.subject);
            EditText etScoreType1 = (EditText) vLayoutSubject.findViewById(R.id.score_type_1);
            EditText etScoreType2 = (EditText) vLayoutSubject.findViewById(R.id.score_type_2);
            EditText etScoreType3 = (EditText) vLayoutSubject.findViewById(R.id.score_type_3);
            EditText etScoreType4 = (EditText) vLayoutSubject.findViewById(R.id.score_type_4);

            PreferenceUtils.putString("pref_" + mSemester + "_" + tvSubject.getText() + "_type_1_score", etScoreType1.getText().toString());
            PreferenceUtils.putDouble("pref_" + mSemester + "_" + tvSubject.getText() + "_type_2_score", Double.parseDouble(etScoreType2.getText().toString()));
            PreferenceUtils.putDouble("pref_" + mSemester + "_" + tvSubject.getText() + "_type_3_score", Double.parseDouble(etScoreType3.getText().toString()));
            PreferenceUtils.putDouble("pref_" + mSemester + "_" + tvSubject.getText() + "_type_4_score", Double.parseDouble(etScoreType4.getText().toString()));
        }

        finish();
    }
}
