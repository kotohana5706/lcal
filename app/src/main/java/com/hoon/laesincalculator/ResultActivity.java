package com.hoon.laesincalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.w3c.dom.Text;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.hoon.laesincalculator.utils.PreferenceUtils;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ResultActivity extends BaseActivity {

    //?? ?? ???
    private static final int ACHIEVEMENT_A = 5;
    private static final int ACHIEVEMENT_B = 4;
    private static final int ACHIEVEMENT_C = 3;
    private static final int ACHIEVEMENT_D = 2;
    private static final int ACHIEVEMENT_E = 1;

    //?? ?? ?? ??

    @Bind({R.id.score_1,R.id.score_2,R.id.score_3,R.id.score_4,R.id.score_5})
    List<TextView> mTvGradeList;

    private TextView mTvAttendance;
    private TextView mTvVolunteer;
    private TextView mTvSchoolActivity;
    private TextView mTvTotalScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);

        mTvAttendance = (TextView) findViewById(R.id.etc_score_1);
        mTvVolunteer = (TextView) findViewById(R.id.etc_score_2);
        mTvSchoolActivity = (TextView) findViewById(R.id.etc_score_3);
        mTvTotalScore = (TextView) findViewById(R.id.total_score);

        double[] scores = {
                calculateSubjectScore(1, 1) + calculateSubjectScore(1, 2),
                calculateSubjectScore(2, 1) + calculateSubjectScore(2, 2),
                calculateSubjectScore(3, 1) + calculateSubjectScore(3, 2),
                calculateArtScore(),
                0};
        scores[4] = round(scores[0] + scores[1] + scores[2] + scores[3], 3);

        double attendanceScore = calculateAttendance();
        double volunteerScore = calculateVolunteerWork();
        double schoolActivityScore = calculateSchoolActivity();
        double totalScore = round(scores[4] + attendanceScore + volunteerScore + schoolActivityScore, 3);

        for(int i = 0 ; i < mTvGradeList.size() ; ++i){
            mTvGradeList.get(i).setText(""+scores[i]);
        }
        mTvAttendance.setText(String.valueOf(attendanceScore));
        mTvVolunteer.setText(String.valueOf(volunteerScore));
        mTvSchoolActivity.setText(String.valueOf(schoolActivityScore));

        mTvTotalScore.setText(String.valueOf(totalScore));
    }

    public void returnToMain(View v) {
        finish();
    }

    //???? ??
    private double calculateSubjectScore(int grade, int semester) {
        String gradeSemester = "";
        if (grade == 1 && semester == 1) {
            gradeSemester = getString(R.string.grade_1_1);
        } else if (grade == 1 && semester == 2) {
            gradeSemester = getString(R.string.grade_1_2);
        } else if (grade == 2 && semester == 1) {
            gradeSemester = getString(R.string.grade_2_1);
        } else if (grade == 2 && semester == 2) {
            gradeSemester = getString(R.string.grade_2_2);
        } else if (grade == 3 && semester == 1) {
            gradeSemester = getString(R.string.grade_3_1);
        } else if (grade == 3 && semester == 2) {
            gradeSemester = getString(R.string.grade_3_2);
        }

        boolean isGradeSemesterIncluded = PreferenceUtils.getBoolean("pref_" + gradeSemester + "_include");
        if (!isGradeSemesterIncluded) {
            return 0;
        }

        double factor1 = 0;
        double factor2 = 0;

        if (grade == 1) {
            factor1 = 4;
            factor2 = 0.8;
        } else if (grade == 2) {
            factor1 = 6;
            factor2 = 1.2;
        } else if (grade == 3) {
            factor1 = 10;
            factor2 = 2.0;
        }

        double sumOfAchievement = 0;
        int subjectCount = 0;
        double sumOfOriginalScore = 0;
        double sumOfMean = 0;
        double sumOfStandardDeviation = 0;

        String[] subjects = getResources().getStringArray(R.array.subjects);

        for (String subject : subjects) {
            if (subject.equalsIgnoreCase("??") || subject.equalsIgnoreCase("??") || subject.equalsIgnoreCase("??")) {
                continue;
            }

            boolean isSubjectIncluded = PreferenceUtils.getBoolean("pref_" + gradeSemester + "_" + subject);
            if (isSubjectIncluded) {
                sumOfAchievement += translateAchievement(PreferenceUtils.getString("pref_" + gradeSemester + "_" + subject + "_type_1_score")); //????????
                subjectCount += 1; //???
                sumOfOriginalScore += PreferenceUtils.getDouble("pref_" + gradeSemester + "_" + subject + "_type_2_score", 0); //???? ??
                sumOfMean += PreferenceUtils.getDouble("pref_" + gradeSemester + "_" + subject + "_type_3_score", 0); //?????
                sumOfStandardDeviation += PreferenceUtils.getDouble("pref_" + gradeSemester + "_" + subject + "_type_4_score", 0); //???????
            }
        }

        double score = factor1 + (sumOfAchievement / subjectCount) * factor2 + functionF(sumOfOriginalScore, sumOfMean, sumOfStandardDeviation) * factor1; //?????

        return round(score, 3);
    }

    private double functionF(double sumOfOriginalScore, double sumOfMean, double sumOfStandardDeviation) { //fuctionF ? ?? ????
        try {
            NormalDistribution nd = new NormalDistribution(sumOfMean, sumOfStandardDeviation);
            return round(nd.cumulativeProbability(sumOfOriginalScore), 8);
        } catch (Exception e) {
            return 0;
        }
    }

    private double translateAchievement(String achievement) {
        switch (achievement){
            case "A":
                return ACHIEVEMENT_A;
            case "B":
                return ACHIEVEMENT_B;
            case "C":
                return ACHIEVEMENT_C;
            case "D":
                return ACHIEVEMENT_D;
            case "E":
                return ACHIEVEMENT_E;
        }
        return 0;
    }

    private double calculateArtScore() { //??,?? ??
        int achievementACount = 0;
        int achievementBCount = 0;
        int achievementCCount = 0;
        int subjectCount = 0;

        String[] gradeSemesters = new String[]{
                getString(R.string.grade_1_1),
                getString(R.string.grade_1_2),
                getString(R.string.grade_2_1),
                getString(R.string.grade_2_2),
                getString(R.string.grade_3_1),
                getString(R.string.grade_3_2)
        };
        String[] subjects = getResources().getStringArray(R.array.subjects);

        for (String subject : subjects) {
            if (!(subject.equalsIgnoreCase("??") || subject.equalsIgnoreCase("??") || subject.equalsIgnoreCase("??"))) {
                continue;
            }

            for (String gradeSemester : gradeSemesters) {
                boolean isGradeSemesterIncluded = PreferenceUtils.getBoolean("pref_" + gradeSemester + "_include");
                if (!isGradeSemesterIncluded) {
                    continue;
                }

                boolean isSubjectIncluded = PreferenceUtils.getBoolean("pref_" + gradeSemester + "_" + subject);
                if (isSubjectIncluded) {
                    String achievement = PreferenceUtils.getString("pref_" + gradeSemester + "_" + subject + "_type_1_score");
                    if ("A".equalsIgnoreCase(achievement)) {
                        achievementACount++;
                    } else if ("B".equalsIgnoreCase(achievement)) {
                        achievementBCount++;
                    } else if ("C".equalsIgnoreCase(achievement)) {
                        achievementCCount++;
                    }
                    subjectCount += 1;
                }
            }
        }

        if (subjectCount > 0) {
            double score = 10 + (20 * (3 * achievementACount + 2 * achievementBCount + achievementCCount) / (double) (3 * subjectCount)); //??????
            return round(score, 3);
        } else {
            return 16.667; //??? ????
        }
    }

    private double calculateAttendance() { //????
        int type1Grade1 = PreferenceUtils.getInt(R.string.pref_attendance_type_1_grade_1, 6);
        int type2Grade1 = PreferenceUtils.getInt(R.string.pref_attendance_type_2_grade_1, 18);
        int type3Grade1 = PreferenceUtils.getInt(R.string.pref_attendance_type_3_grade_1, 18);
        int type4Grade1 = PreferenceUtils.getInt(R.string.pref_attendance_type_4_grade_1, 18);
        int type1Grade2 = PreferenceUtils.getInt(R.string.pref_attendance_type_1_grade_2, 6);
        int type2Grade2 = PreferenceUtils.getInt(R.string.pref_attendance_type_2_grade_2, 18);
        int type3Grade2 = PreferenceUtils.getInt(R.string.pref_attendance_type_3_grade_2, 18);
        int type4Grade2 = PreferenceUtils.getInt(R.string.pref_attendance_type_4_grade_2, 18);
        int type1Grade3 = PreferenceUtils.getInt(R.string.pref_attendance_type_1_grade_3, 6);
        int type2Grade3 = PreferenceUtils.getInt(R.string.pref_attendance_type_2_grade_3, 18);
        int type3Grade3 = PreferenceUtils.getInt(R.string.pref_attendance_type_3_grade_3, 18);
        int type4Grade3 = PreferenceUtils.getInt(R.string.pref_attendance_type_4_grade_3, 18);

        int totalGrade1Absence = type1Grade1 + ((type2Grade1 + type3Grade1 + type4Grade1) / 3);
        int totalGrade2Absence = type1Grade2 + ((type2Grade2 + type3Grade2 + type4Grade2) / 3);
        int totalGrade3Absence = type1Grade3 + ((type2Grade3 + type3Grade3 + type4Grade3) / 3);

        double grade1Score = getAttendanceScore(6, totalGrade1Absence);
        double grade2Score = getAttendanceScore(7, totalGrade2Absence);
        double grade3Score = getAttendanceScore(7, totalGrade3Absence);

        double totalScore = 0;

        if (PreferenceUtils.getBoolean(R.string.pref_attendance_grade_1)) {
            totalScore += grade1Score;
        }

        if (PreferenceUtils.getBoolean(R.string.pref_attendance_grade_2)) {
            totalScore += grade2Score;
        }

        if (PreferenceUtils.getBoolean(R.string.pref_attendance_grade_3)) {
            totalScore += grade3Score;
        }

        return totalScore;
    }

    private double round(double value, int fractionDigits) { //??? ???? ?? ???
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(fractionDigits);
        df.setMinimumFractionDigits(fractionDigits);
        df.setRoundingMode(RoundingMode.HALF_UP);

        return Double.parseDouble(df.format(value));
    }

    private double getAttendanceScore(int maxScore, int absence) { //???? ??
        switch (absence) {
            case 0:
                return maxScore;
            case 1:
                return maxScore * 0.9;
            case 2:
                return maxScore * 0.8;
            case 3:
                return maxScore * 0.7;
            case 4:
                return maxScore * 0.6;
            case 5:
                return maxScore * 0.5;
            // 6? ??
            default:
                return maxScore * 0.4;
        }
    }

    private double calculateVolunteerWork() { //????
        int volunteerType = PreferenceUtils.getInt(R.string.pref_volunteer_type);
        int volunteer1 = PreferenceUtils.getInt(R.string.pref_volunteer_score_1, 0);
        int volunteer2 = PreferenceUtils.getInt(R.string.pref_volunteer_score_2, 0);
        int volunteer3 = PreferenceUtils.getInt(R.string.pref_volunteer_score_3, 0);

        int totalVolunteer = volunteer1 + volunteer2 + volunteer3;

        switch (volunteerType) {
            // ????
            case 1:
                return 20;
            // 20?? ??
            case 2:
                if(totalVolunteer > 20)return 20;
                else if(totalVolunteer <= 20 && totalVolunteer>=16) return totalVolunteer;
                else if (totalVolunteer >= 14) return 15;
                else if (totalVolunteer >= 12) return 14;
                else if (totalVolunteer >= 10) return 13;
                else if (totalVolunteer >= 8) return 12;
                else if (totalVolunteer >= 6) return 11;
                else if (totalVolunteer >= 4) return 10;
                else if (totalVolunteer >= 2) return 9;
                else return 8;
                // 60?? ??
            case 3:
                if(totalVolunteer < 10) return 9;
                else if(totalVolunteer > 60) return 20;
                else {
                    return (10 + ((totalVolunteer-10)/5));
                }
//                if (totalVolunteer >= 60) return 20;
//                else if (totalVolunteer >= 55) return 19;
//                else if (totalVolunteer >= 50) return 18;
//                else if (totalVolunteer >= 45) return 17;
//                else if (totalVolunteer >= 40) return 16;
//                else if (totalVolunteer >= 35) return 15;
//                else if (totalVolunteer >= 30) return 14;
//                else if (totalVolunteer >= 25) return 13;
//                else if (totalVolunteer >= 20) return 12;
//                else if (totalVolunteer >= 15) return 11;
//                else if (totalVolunteer >= 10) return 10;
//                else if (totalVolunteer >= 5) return 9;
//                else return 8;

            default:
                return 0;
        }
    }

    private double calculateSchoolActivity() { //????
        double score0 = 6; // ????
        double score1 = PreferenceUtils.getDouble(R.string.pref_school_activity_score_1, 0); // ????
        double score2 = PreferenceUtils.getDouble(R.string.pref_school_activity_score_2, 0); // ???????

        return Math.min(score0 + score1 + score2, 10);
    }
}
