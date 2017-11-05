package me.petermooney.project4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class SamplesAdapter extends ArrayAdapter<Sample> {
    public SamplesAdapter(Context context, Sample[] samples) {
        super(context, 0, samples);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Sample sample = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_sample, parent, false);
        }

        TextView sampleTitle = convertView.findViewById(R.id.item_sample_title);
        TextView sampleArtist = convertView.findViewById(R.id.item_sample_artist);
        TextView sampleAttempts = convertView.findViewById(R.id.item_sample_attempts);
        TextView sampleCompleted = convertView.findViewById(R.id.item_sample_completed);

        sampleTitle.setText(sample.getTitle());
        sampleArtist.setText(sample.getArtist());
        sampleAttempts.setText(getAttemptsText(sample));
        sampleCompleted.setText(getCompletedText(sample));

        return convertView;
    }

    private String getAttemptsText(Sample sample) {
        int attempts = sample.getAttempts();
        return getContext()
                .getResources()
                .getQuantityString(R.plurals.item_sample_attempts, attempts, attempts);
    }

    private int getCompletedText(Sample sample) {
        boolean completed = sample.getCompleted();

        if (completed) {
            return R.string.item_sample_completed;
        } else {
            return R.string.item_sample_not_completed;
        }
    }
}
