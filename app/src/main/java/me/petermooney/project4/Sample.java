package me.petermooney.project4;


public class Sample {

    private int mSampleResId;
    private int mSampleTitleId;
    private int mSampleArtistId;
    private int mSampleAnswerId;
    private int mAttempts = 0;
    private boolean mCompleted = false;

    public Sample(int sampleResId, int sampleTitleId, int sampleArtistId, int sampleAnswerId) {
        mSampleResId = sampleResId;
        mSampleTitleId = sampleTitleId;
        mSampleArtistId = sampleArtistId;
        mSampleAnswerId = sampleAnswerId;
    }

    public int getTitle() {
        return mSampleTitleId;
    }

    public int getArtist() {
        return mSampleArtistId;
    }

    public int getAttempts() {
        return mAttempts;
    }

    public boolean getCompleted() {
        return mCompleted;
    }
}
