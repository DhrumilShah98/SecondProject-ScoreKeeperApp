package com.example.dhrumilshah.tennis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int mScoreTeamA = 0;
    private int mScoreTeamB = 0;
    private int mFoulTeamA = 0;
    private int mFoulTeamB = 0;
    private int mServeFailA = 0;
    private int mNetTouchA = 0;
    private int mOtherFaultA = 0;
    private int mServeFailB = 0;
    private int mNetTouchB = 0;
    private int mOtherFaultB = 0;
    private int mSetA1 = 0;
    private int mSetA2 = 0;
    private int mSetA3 = 0;
    private int mSetB1 = 0;
    private int mSetB2 = 0;
    private int mSetB3 = 0;
    private boolean mSetA1Bool = false;
    private boolean mSetA2Bool = false;
    private boolean mSetA3Bool = false;
    private boolean mSetB1Bool = false;
    private boolean mSetB2Bool = false;
    private boolean mSetB3Bool = false;
    private int mFinalScoreOfA = 0;
    private int mFinalScoreOfB = 0;
    private TextView mServeFailATV;
    private TextView mNetTouchATV;
    private TextView mOtherFaultATV;
    private TextView mDisplayFoulATV;
    private TextView mDisplayScoreATV;
    private TextView mServeFailBTV;
    private TextView mNetTouchBTV;
    private TextView mOtherFaultBTV;
    private TextView mDisplayFoulBTV;
    private TextView mDisplayScoreBTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mServeFailATV = findViewById(R.id.serve_fail_a);
        mNetTouchATV = findViewById(R.id.net_touch_a);
        mOtherFaultATV = findViewById(R.id.other_fault_a);
        mDisplayFoulATV = findViewById(R.id.team_a_foul);
        mDisplayScoreATV = findViewById(R.id.team_a_score);

        mServeFailBTV = findViewById(R.id.serve_fail_b);
        mNetTouchBTV = findViewById(R.id.net_touch_b);
        mOtherFaultBTV = findViewById(R.id.other_fault_b);
        mDisplayFoulBTV = findViewById(R.id.team_b_foul);
        mDisplayScoreBTV = findViewById(R.id.team_b_score);

    }

    /**
     * Displays the given score for Team A.
     * The method "oneA" is called when the "POINT" button in "Team A" is pressed.
     * Point will update the "scoreTeamA" in reference to "scoreTeamB".
     * The rules of score update is based on Tennis game rule.
     */
    public void oneA(View Button){
        oneA();
    }
    private void oneA(){
        if(!mSetA3Bool) {
            mScoreTeamA = getCurrentScore(R.id.team_a_score);
            mScoreTeamB = getCurrentScore(R.id.team_b_score);
            if (mScoreTeamB <= 30 && mScoreTeamA == 40) {
                setCurrentSetA();
            } else if (mScoreTeamB == 40 && mScoreTeamA == 40) {
                mScoreTeamA = 50;
                setCurrentScore(R.id.team_a_score, mScoreTeamA);
            } else if (mScoreTeamB == 50 && mScoreTeamA == 40) {
                mScoreTeamB = 40;
                setCurrentScore(R.id.team_b_score, mScoreTeamB);
            } else if (mScoreTeamA >= 30) {
                mScoreTeamA += 10;
                setCurrentScore(R.id.team_a_score, mScoreTeamA);
                if (mScoreTeamA == 60) {
                    setCurrentSetA();
                }
            } else {
                mScoreTeamA += 15;
                setCurrentScore(R.id.team_a_score, mScoreTeamA);
            }
        }
    }

    /**
     * The method "serveFailA" is called when the "SERVE FAIL" button in "Team A" is pressed.
     * It will update the "Total Fouls - foulTeamA" of Team A, and the counter of "Serve Fail A - serveFailA"
     * Also it will update the score of "Team B - scoreTeamB" with reference to score of "Team A - scoreTeamA". (Because Fault of Team A is advantage for Team B)
     */
    public void serveFailA(View Button){
        if(!mSetA3Bool) {
            mFoulTeamA = mFoulTeamA + 1;
            displayFoulA(mFoulTeamA);
            oneB();
            mServeFailA = mServeFailA + 1;
            serveFailFaultA(mServeFailA);
        }
    }
    public void serveFailFaultA(int score) {
        mServeFailATV.setText(String.valueOf(score));
    }

    /**
     * The method "netTouchA" is called when the "NET TOUCH" button in "Team A" is pressed.
     * It will update the "Total Fouls - foulTeamA" of Team A, and the counter of "Net Touch A - netTouchA"
     * Also it will update the score of "Team B - scoreTeamB" with reference to score of "Team A - scoreTeamA". (Because Fault of Team A is advantage for Team B)
     */
    public void netTouchA(View Button){
        if(!mSetA3Bool) {
            mFoulTeamA = mFoulTeamA + 1;
            displayFoulA(mFoulTeamA);
            oneB();
            mNetTouchA = mNetTouchA + 1;
            netTouchFaultA(mNetTouchA);
        }
    }
    public void netTouchFaultA(int score) {
        mNetTouchATV.setText(String.valueOf(score));
    }

    /**
     * The method "otherFaultA" is called when the "OTHER FAULT" button in "Team A" is pressed.
     * It will update the "Total Fouls - foulTeamA" of Team A, and the counter of "Other Fault A - otherFaultA"
     * Also it will update the score of "Team B - scoreTeamB" with reference to score of "Team A - scoreTeamA". (Because Fault of Team A is advantage for Team B)
     */
    public void otherFaultA(View Button) {
        if(!mSetA3Bool) {
            mFoulTeamA = mFoulTeamA + 1;
            displayFoulA(mFoulTeamA);
            oneB();
            mOtherFaultA = mOtherFaultA + 1;
            otherFaultAA(mOtherFaultA);
        }
    }
    public void otherFaultAA(int score) {
        mOtherFaultATV.setText(String.valueOf(score));
    }

    /**
     * "displayFoulA" will display total fouls done by "Team A"
     */
    public void displayFoulA(int score) {
        mDisplayFoulATV.setText(String.valueOf(score));
    }

    /**
     *  "displayForTeamA" will reset the "Total score - scoreTeamA" to zero (0).
     */
    public void displayForTeamA() {
        mDisplayScoreATV.setText(String.valueOf(0));
    }

    /**
     *  "setCurrentSetA" will update the value of the "Team A Set" with reference to "Team B Set"
     */
    private void setCurrentSetA(){
        mScoreTeamA=0;
        setCurrentScore(R.id.team_a_score, mScoreTeamA);
        mSetA1 = getCurrentScore(R.id.score_set_a_1);
        mSetA2 = getCurrentScore(R.id.score_set_a_2);
        mSetA3 = getCurrentScore(R.id.score_set_a_3);
        mSetB1 = getCurrentScore(R.id.score_set_b_1);
        mSetB2 = getCurrentScore(R.id.score_set_b_2);
        mSetB3 = getCurrentScore(R.id.score_set_b_3);
        if(!mSetA1Bool && (mSetA1<6 || (mSetB1>4 && (mSetA1>=6 && (mSetA1-2)!=mSetB1)))){
            mSetA1+=1;
            setCurrentScore(R.id.score_set_a_1,mSetA1);
            if(mSetA1>=6 && (mSetA1-2)>= mSetB1){
                mSetA1Bool = true;
                mSetB1Bool = true;
                displayForTeamA();
                displayForTeamB();
            }
        }
        else if(!mSetA2Bool && (mSetA2<6 || (mSetB2>4 && (mSetA2>=6 && (mSetA2-2)!=mSetB2)))){
            mSetA2+=1;
            setCurrentScore(R.id.score_set_a_2,mSetA2);
            if(mSetA2>=6 && (mSetA2-2)>= mSetB2){
                mSetA2Bool = true;
                mSetB2Bool = true;
                displayForTeamA();
                displayForTeamB();
            }
        }
        else if(!mSetA3Bool && (mSetA3<6 || (mSetB3>4 && (mSetA3>=6 && (mSetA3-2)!=mSetB3)))){
            mSetA3+=1;
            setCurrentScore(R.id.score_set_a_3,mSetA3);
            if(mSetA3>=6 && (mSetA3-2)>= mSetB3){
                mSetA3Bool = true;
                mSetB3Bool = true;
                displayForTeamA();
                displayForTeamB();
                setFinalWinnersMessage();
            }
        }
    }

    /**
     * Displays the given score for Team B.
     * The method "oneB" is called when the "POINT" button in "Team B" is pressed.
     * Point will update the "scoreTeamB" in reference to "scoreTeamA".
     * The rules of score update is based on Tennis game rule.
     */
    public void oneB(View Button){
        oneB();
    }
    private void oneB(){
        if(!mSetB3Bool) {
            mScoreTeamA = getCurrentScore(R.id.team_a_score);
            mScoreTeamB = getCurrentScore(R.id.team_b_score);
            if (mScoreTeamA <= 30 && mScoreTeamB == 40) {
                setCurrentSetB();
            } else if (mScoreTeamA == 40 && mScoreTeamB == 40) {
                mScoreTeamB = 50;
                setCurrentScore(R.id.team_b_score, mScoreTeamB);
            } else if (mScoreTeamA == 50 && mScoreTeamB == 40) {
                mScoreTeamA = 40;
                setCurrentScore(R.id.team_a_score, mScoreTeamA);
            } else if (mScoreTeamB >= 30) {
                mScoreTeamB += 10;
                setCurrentScore(R.id.team_b_score, mScoreTeamB);
                if (mScoreTeamB == 60) {
                    setCurrentSetB();
                }
            } else {
                mScoreTeamB += 15;
                setCurrentScore(R.id.team_b_score, mScoreTeamB);
            }
        }
    }

    /**
     * The method "serveFailB" is called when the "SERVE FAIL" button in "Team B" is pressed.
     * It will update the "Total Fouls - foulTeamB" of Team B, and the counter of "Serve Fail B - serveFailB"
     * Also it will update the score of "Team A - scoreTeamA" with reference to score of "Team B - scoreTeamB". (Because Fault of Team B is advantage for Team A)
     */
    public void serveFailB(View Button){
        if(!mSetB3Bool) {
            mFoulTeamB = mFoulTeamB + 1;
            displayFoulB(mFoulTeamB);
            oneA();
            mServeFailB = mServeFailB + 1;
            serveFailFaultB(mServeFailB);
        }
    }
    public void serveFailFaultB(int score) {
        mServeFailBTV.setText(String.valueOf(score));
    }

    /**
     * The method "netTouchB" is called when the "NET TOUCH" button in "Team B" is pressed.
     * It will update the "Total Fouls - foulTeamB" of Team B, and the counter of "Net Touch B - netTouchB"
     * Also it will update the score of "Team A - scoreTeamA" with reference to score of "Team B - scoreTeamB". (Because Fault of Team B is advantage for Team A)
     */
    public void netTouchB(View Button){
        if(!mSetB3Bool) {
            mFoulTeamB = mFoulTeamB + 1;
            displayFoulB(mFoulTeamB);
            oneA();
            mNetTouchB = mNetTouchB + 1;
            netTouchFaultB(mNetTouchB);
        }
    }
    public void netTouchFaultB(int score) {
        mNetTouchBTV.setText(String.valueOf(score));
    }

    /**
     * The method "otherFaultB" is called when the "OTHER FAULT" button in "Team B" is pressed.
     * It will update the "Total Fouls - foulTeamB" of Team B, and the counter of "Other Fault B - otherFaultB"
     * Also it will update the score of "Team A - scoreTeamA" with reference to score of "Team B - scoreTeamB". (Because Fault of Team B is advantage for Team A)
     */
    public void otherFaultB(View Button) {
        if(!mSetB3Bool) {
            mFoulTeamB = mFoulTeamB + 1;
            displayFoulB(mFoulTeamB);
            oneA();
            mOtherFaultB = mOtherFaultB + 1;
            otherFaultBB(mOtherFaultB);
        }
    }
    public void otherFaultBB(int score) {
        mOtherFaultBTV.setText(String.valueOf(score));
    }

    /**
     * "displayFoulB" will display total fouls done by "Team B"
     */
    public void displayFoulB(int score) {
        mDisplayFoulBTV.setText(String.valueOf(score));
    }
    /**
     *  "displayForTeamB" will reset the "Total score - scoreTeamB" to zero (0).
     */
    public void displayForTeamB() {
        mDisplayScoreBTV.setText(String.valueOf(0));
    }
    /**
     *  "setCurrentSetB" will update the value of the "Team B Set" with reference to "Team A Set"
     */
    private void setCurrentSetB(){
        mScoreTeamB=0;
        setCurrentScore(R.id.team_b_score, mScoreTeamB);
        mSetA1 = getCurrentScore(R.id.score_set_a_1);
        mSetA2 = getCurrentScore(R.id.score_set_a_2);
        mSetA3 = getCurrentScore(R.id.score_set_a_3);
        mSetB1 = getCurrentScore(R.id.score_set_b_1);
        mSetB2 = getCurrentScore(R.id.score_set_b_2);
        mSetB3 = getCurrentScore(R.id.score_set_b_3);
        if(!mSetB1Bool && (mSetB1<6 || (mSetA1>4 && (mSetB1>=6 && (mSetB1-2)!=mSetA1)))){
            mSetB1+=1;
            setCurrentScore(R.id.score_set_b_1,mSetB1);
            if(mSetB1>=6 && (mSetB1-2)>= mSetA1){
                mSetA1Bool = true;
                mSetB1Bool = true;
                displayForTeamA();
                displayForTeamB();
            }
        }
        else if(!mSetB2Bool && (mSetB2<6 || (mSetA2>4 && (mSetB2>=6 && (mSetB2-2)!=mSetA2)))){
            mSetB2+=1;
            setCurrentScore(R.id.score_set_b_2,mSetB2);
            if(mSetB2>=6 && (mSetB2-2)>= mSetA2){
                mSetA2Bool = true;
                mSetB2Bool = true;
                displayForTeamA();
                displayForTeamB();
            }
        }
        else if(!mSetB3Bool && (mSetB3<6 || (mSetA3>4 && (mSetB3>=6 && (mSetB3-2)!=mSetA3)))){
            mSetB3+=1;
            setCurrentScore(R.id.score_set_b_3,mSetB3);
            if(mSetB3>=6 && (mSetB3-2)>= mSetA3){
                mSetA3Bool = true;
                mSetB3Bool = true;
                displayForTeamA();
                displayForTeamB();
                setFinalWinnersMessage();
            }
        }
    }


    /**
     *  "reset" will reset all the values to zero(0) when "RESET" button is pressed.
     *  It indicates the restart of match or is pressed when one team is won and match is to be initiated again.
     */
    public void reset(View Button){
        mFoulTeamA = 0;
        mFoulTeamB = 0;
        displayFoulA(mFoulTeamA);
        displayFoulB(mFoulTeamB);
        mScoreTeamA = 0;
        mScoreTeamB = 0;
        displayForTeamA();
        displayForTeamB();
        mServeFailA = 0;
        mNetTouchA = 0;
        mOtherFaultA = 0;
        serveFailFaultA(mServeFailA);
        netTouchFaultA(mNetTouchA);
        otherFaultAA(mOtherFaultA);
        mServeFailB = 0;
        mNetTouchB = 0;
        mOtherFaultB = 0;
        serveFailFaultB(mServeFailB);
        netTouchFaultB(mNetTouchB);
        otherFaultBB(mOtherFaultB);
        mSetA1 = 0;
        setCurrentScore(R.id.score_set_a_1,mSetA1);
        mSetA2 = 0;
        setCurrentScore(R.id.score_set_a_2,mSetA2);
        mSetA3 = 0;
        setCurrentScore(R.id.score_set_a_3,mSetA3);
        mSetB1 = 0;
        setCurrentScore(R.id.score_set_b_1,mSetB1);
        mSetB2 = 0;
        setCurrentScore(R.id.score_set_b_2,mSetB2);
        mSetB3 = 0;
        setCurrentScore(R.id.score_set_b_3,mSetB3);
        mSetA1Bool = false;
        mSetA2Bool = false;
        mSetA3Bool = false;
        mSetB1Bool = false;
        mSetB2Bool = false;
        mSetB3Bool = false;
        TextView tv = findViewById(R.id.final_winner_message);
        tv.setVisibility(View.INVISIBLE);
    }

    /**
     * "getCurrentScore" will get the current value of the Score of which the id is passed.
     */
    public int getCurrentScore(int id){
        TextView tv = findViewById(id);
        return Integer.valueOf(tv.getText().toString());
    }

    /**
     * "setCurrentScore" will set the updated value of the Score of which the id is passed.
     */
    public void setCurrentScore(int id,int score){
        TextView scoreView = findViewById(id);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * "setFinalWinnerMessage" will display the winner based on the highest number of sets.
     */
    private void setFinalWinnersMessage(){
        if(mSetA1>mSetB1){
            mFinalScoreOfA+=1;
        }else{
            mFinalScoreOfB+=1;
        }
        if(mSetA2>mSetB2){
            mFinalScoreOfA+=1;
        }else{
            mFinalScoreOfB+=1;
        }
        if(mSetA3>mSetB3){
            mFinalScoreOfA+=1;
        }else{
            mFinalScoreOfB+=1;
        }
        if(mFinalScoreOfA>mFinalScoreOfB){
            TextView tv = findViewById(R.id.final_winner_message);
            tv.setText(R.string.teamAWin);
            tv.setVisibility(View.VISIBLE);
        }else{
            TextView tv = findViewById(R.id.final_winner_message);
            tv.setText(R.string.teamBWin);
            tv.setVisibility(View.VISIBLE);
        }
    }
}