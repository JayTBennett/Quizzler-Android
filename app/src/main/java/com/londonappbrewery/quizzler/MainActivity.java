package com.londonappbrewery.quizzler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    // TODO: Declare constants here
    private final int PROGRESS_BAR_INCREMENT = 8;

    // TODO: Declare member variables here:
    private int mScore;
    private Button mFalseButton;
    private Button mTrueButton;
    private TextView mQuestionTextView;
    private TextView mScoreTextView;
    private ProgressBar mProgressBar;
    private int mIndex;
    private int mQuestion;

    // TODO: Uncomment to create question bank
    private TrueFalse[] mQuestionBank = new TrueFalse[]{
            new TrueFalse(R.string.question_1, true),
            new TrueFalse(R.string.question_2, true),
            new TrueFalse(R.string.question_3, true),
            new TrueFalse(R.string.question_4, true),
            new TrueFalse(R.string.question_5, true),
            new TrueFalse(R.string.question_6, false),
            new TrueFalse(R.string.question_7, true),
            new TrueFalse(R.string.question_8, false),
            new TrueFalse(R.string.question_9, true),
            new TrueFalse(R.string.question_10, true),
            new TrueFalse(R.string.question_11, false),
            new TrueFalse(R.string.question_12, false),
            new TrueFalse(R.string.question_13, true)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTrueButton = findViewById(R.id.true_button);
        mFalseButton = findViewById(R.id.false_button);
        mQuestionTextView = findViewById(R.id.question_text_view);
        mProgressBar = findViewById(R.id.progress_bar);
        mScoreTextView = findViewById(R.id.score);

        mQuestion = mQuestionBank[mIndex].getQuestionID();
    }

    public void TrueClick(View view) {
        CheckAnswer(true);
        UpdateQuestion();
    }

    public void FalseClick(View view) {
        CheckAnswer(false);
        UpdateQuestion();
    }

    private void UpdateQuestion() {
        // Using modulus to reset to 1...
        mIndex = (mIndex + 1) % mQuestionBank.length;

        if (mIndex == 0){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Game Over");
            alert.setCancelable(false);
            alert.setMessage("You scored " + mScore + " points!");
            alert.setPositiveButton("Close Application", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
        }

        mQuestion = mQuestionBank[mIndex].getQuestionID();
        mQuestionTextView.setText(mQuestion);

        mProgressBar.incrementProgressBy(PROGRESS_BAR_INCREMENT);
    }

    private void CheckAnswer(boolean userSelection) {
        boolean correctAnswer = mQuestionBank[mIndex].isAnswer();
        if (userSelection == correctAnswer) {
            Toast.makeText(MainActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
            mScore++;
            mScoreTextView.setText("Score " + mScore + "/13");
        } else {
            Toast.makeText(MainActivity.this, "Sorry that's not right", Toast.LENGTH_SHORT).show();
        }
    }
}
