package me.petermooney.project4;

import android.os.Parcel;
import android.os.Parcelable;

public class Sample implements Parcelable {

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

    public int getSample() {
        return mSampleResId;
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
    }

    public static final Parcelable.Creator<Sample> CREATOR = new Parcelable.Creator<Sample>() {
        @Override
        public Sample createFromParcel(Parcel in) {
            int sampleResId = in.readInt();
            int sampleTitleId = in.readInt();
            int sampleArtistId = in.readInt();
            int sampleAnswerId = in.readInt();

            return new Sample(sampleResId, sampleTitleId, sampleArtistId, sampleAnswerId);
        }

        @Override
        public Sample[] newArray(int size) {
            return new Sample[size];
        }
    };
}
