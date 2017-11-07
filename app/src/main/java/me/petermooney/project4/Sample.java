package me.petermooney.project4;

import android.os.Parcel;
import android.os.Parcelable;

public class Sample implements Parcelable {

    private int mSampleResId;
    private int mSampleTitleId;
    private int mSampleArtistId;
    private int mSampleAnswerId;
    private int mAttempts = 0;
    private int mPlayCount = 0;
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

    public int getAnswer() {
        return mSampleAnswerId;
    }

    public int getSample() {
        return mSampleResId;
    }

    public int getPlayCount() {
        return mPlayCount;
    }

    public int incrementAttempts() {
        mAttempts++;
        return mAttempts;
    }

    public void setAttempts(int attempts) {
        mAttempts = attempts;
    }

    public int incrementPlayCount() {
        mPlayCount++;
        return mPlayCount;
    }

    public void setCompleted(boolean completed) {
       mCompleted = completed;
    }

    public void setPlayCount(int playCount) {
        mPlayCount = playCount;
    }

    //
    // Parcelable Methods
    //

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(mSampleResId);
        out.writeInt(mSampleTitleId);
        out.writeInt(mSampleArtistId);
        out.writeInt(mSampleAnswerId);
        out.writeInt(mAttempts);
        out.writeInt(mPlayCount);
        out.writeByte((byte) (mCompleted ? 1 : 0));
    }

    public static final Parcelable.Creator<Sample> CREATOR = new Parcelable.Creator<Sample>() {
        @Override
        public Sample createFromParcel(Parcel in) {
            int sampleResId = in.readInt();
            int sampleTitleId = in.readInt();
            int sampleArtistId = in.readInt();
            int sampleAnswerId = in.readInt();
            int attempts = in.readInt();
            int playCount = in.readInt();
            boolean completed = in.readByte() != 0;

            Sample sample = new Sample(sampleResId, sampleTitleId, sampleArtistId, sampleAnswerId);
            sample.setAttempts(attempts);
            sample.setPlayCount(playCount);
            sample.setCompleted(completed);
            return sample;
        }

        @Override
        public Sample[] newArray(int size) {
            return new Sample[size];
        }
    };
}
