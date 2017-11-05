package me.petermooney.project4;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class SampleDetailActivity extends Activity {

    private static final String EXTRA_SAMPLE = "me.petermooney.project4.sample";

    private Sample mSample;
    private MediaPlayer mMediaPlayer;

    public static Intent newIntent(Context packageContext, Sample sample) {
        Intent intent = new Intent(packageContext, SampleDetailActivity.class);
        intent.putExtra(EXTRA_SAMPLE, sample);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_detail);

        mSample = getIntent().getParcelableExtra(EXTRA_SAMPLE);
        TextView sampleTitleTextView = findViewById(R.id.sample_detail_title);
        TextView sampleArtistTextView = findViewById(R.id.sample_detail_artist);
        final ImageButton playStopButton = findViewById(R.id.play);

        sampleTitleTextView.setText(mSample.getTitle());
        sampleArtistTextView.setText(mSample.getArtist());

        mMediaPlayer = MediaPlayer.create(this, mSample.getSample());
        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                playStopButton.setImageResource(R.drawable.ic_play);
            }
        });

        playStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mMediaPlayer.isPlaying()) {
                    // MediaPlayer API sucks.  Need to pause and seek to beginning. stop() kills the object
                    mMediaPlayer.pause();
                    mMediaPlayer.seekTo(0);
                    playStopButton.setImageResource(R.drawable.ic_play);
                } else {
                    mMediaPlayer.start();
                    playStopButton.setImageResource(R.drawable.ic_stop);
                }
            }
        });

//        TODO: Implement screen rotation state handling
//        if (savedInstanceState != null) {
//            boolean answerShown = savedInstanceState.getBoolean(KEY_ANSWER_SHOWN, false);
//            setAnswerShownResult(answerShown);
//            showAnswer(mAnswerIsTrue);
//        }

//        mShowAnswerButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showAnswer(mAnswerIsTrue);
//                setAnswerShownResult(true);
//            }
//        });
    }
}
