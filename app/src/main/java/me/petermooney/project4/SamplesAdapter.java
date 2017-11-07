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
        SampleFormatter formatter = new SampleFormatter(getContext(), sample);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_sample, parent, false);
        }

        TextView sampleTitle = convertView.findViewById(R.id.item_sample_title);
        TextView sampleArtist = convertView.findViewById(R.id.item_sample_artist);
        TextView sampleAttempts = convertView.findViewById(R.id.item_sample_attempts);
        TextView sampleCompleted = convertView.findViewById(R.id.item_sample_completed);

        sampleTitle.setText(sample.getTitle());
        sampleArtist.setText(sample.getArtist());
        sampleAttempts.setText(formatter.getAttemptsText());
        sampleCompleted.setText(formatter.getCompletedText());

        return convertView;
    }


}
