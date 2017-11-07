package me.petermooney.project4;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class SampleDetailActivity extends Activity {

    private static final String EXTRA_SAMPLE = "me.petermooney.project4.sample";
    private static final String KEY_RESULT_TEXT = "me.petermooney.project4.result_text";

    private Sample mSample;
    private SampleComparator mComparator;
    private SampleFormatter mSampleFormatter;
    private MediaPlayer mMediaPlayer;
    private ImageButton mPlayStopButton;
    private EditText mAnswerInput;
    private TextView mResultText;
    private TextView mAttemptsText;
    private TextView mCompletionStatus;
    private TextView mPlayCount;
    private Intent mIntent = new Intent();

    public static Intent newIntent(Context packageContext, Sample sample) {
        Intent intent = new Intent(packageContext, SampleDetailActivity.class);
        intent.putExtra(EXTRA_SAMPLE, sample);
        return intent;
    }

    public static Sample getSample(Intent result) {
        return result.getParcelableExtra(EXTRA_SAMPLE);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        String resultText = mResultText.getText().toString();
        savedInstanceState.putString(KEY_RESULT_TEXT, resultText);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_detail);

        mSample = getIntent().getParcelableExtra(EXTRA_SAMPLE);
        mSampleFormatter = new SampleFormatter(this, mSample);
        mComparator = new SampleComparator(getString(mSample.getAnswer()));
        mPlayStopButton = findViewById(R.id.play);
        mAnswerInput = findViewById(R.id.answer_input);
        mResultText = findViewById(R.id.result);
        mAttemptsText = findViewById(R.id.attempts);
        mCompletionStatus = findViewById(R.id.completion_status);
        mPlayCount = findViewById(R.id.play_count);

        if (savedInstanceState != null) {
            String resultText = savedInstanceState.getString(KEY_RESULT_TEXT, "");
            mResultText.setText(resultText);
        }

        Button checkAnswerBtn = findViewById(R.id.check_answer);
        TextView sampleTitleTextView = findViewById(R.id.sample_detail_title);
        TextView sampleArtistTextView = findViewById(R.id.sample_detail_artist);

        sampleTitleTextView.setText(mSample.getTitle());
        sampleArtistTextView.setText(mSample.getArtist());
        mCompletionStatus.setText(mSampleFormatter.getCompletedText());
        mAttemptsText.setText(mSampleFormatter.getAttemptsText());
        mPlayCount.setText(mSampleFormatter.getPlayCountText());

        mMediaPlayer = MediaPlayer.create(this, mSample.getSample());
        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mPlayStopButton.setImageResource(R.drawable.ic_play);
            }
        });

        mPlayStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mMediaPlayer.isPlaying()) {
                    // MediaPlayer API sucks.  Need to pause and seek to beginning. stop() kills the object
                    mMediaPlayer.pause();
                    mMediaPlayer.seekTo(0);
                    mPlayStopButton.setImageResource(R.drawable.ic_play);
                } else {
                    mSample.incrementPlayCount();
                    mPlayCount.setText(mSampleFormatter.getPlayCountText());
                    mMediaPlayer.start();
                    mPlayStopButton.setImageResource(R.drawable.ic_stop);

                    setIntentResult();
                }
            }
        });

        checkAnswerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = mComparator.compare(mAnswerInput.getText().toString());
                mResultText.setText(getString(R.string.results, result));
                mSample.incrementAttempts();
                mAttemptsText.setText(mSampleFormatter.getAttemptsText());

                if (result >= 90) {
                    mSample.setCompleted(true);
                    mCompletionStatus.setText(mSampleFormatter.getCompletedText());
                }

                setIntentResult();
            }
        });
    }

    private void setIntentResult() {
        mIntent.putExtra(EXTRA_SAMPLE, mSample);
        setResult(RESULT_OK, mIntent);
    }
}
