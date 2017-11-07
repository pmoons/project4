package me.petermooney.project4;

import android.content.Context;

public class SampleFormatter {

    private Context mContext;
    private Sample mSample;

    public SampleFormatter(Context context, Sample sample) {
        mContext = context;
        mSample = sample;
    }

    public String getAttemptsText() {
        int attempts = mSample.getAttempts();
        return mContext.getResources()
                .getQuantityString(R.plurals.item_sample_attempts, attempts, attempts);
    }

    public String getCompletedText() {
        boolean completed = mSample.getCompleted();

        if (completed) {
            return mContext.getString(R.string.item_sample_completed);
        } else {
            return mContext.getString(R.string.item_sample_not_completed);
        }
    }

    public String getPlayCountText() {
        int playCount = mSample.getPlayCount();
        return mContext.getResources()
                .getQuantityString(R.plurals.sample_plays, playCount, playCount);
    }
}
