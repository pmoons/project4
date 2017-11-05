package me.petermooney.project4;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class SampleListActivity extends Activity {

    private Sample[] samples = new Sample[] {
        new Sample(R.raw.crept_and_we_came, R.string.sample_crept_and_we_came_title, R.string.sample_crept_and_we_came_artist, R.string.sample_crept_and_we_came_answer),
        new Sample(R.raw.lets_go, R.string.sample_lets_go_title, R.string.sample_lets_go_artist, R.string.sample_lets_go_answer),
        new Sample(R.raw.commas, R.string.sample_commas_title, R.string.sample_commas_artist, R.string.sample_commas_answer)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_list);

        SamplesAdapter adapter = new SamplesAdapter(this, samples);
        ListView listView = findViewById(R.id.sample_list);
        listView.setAdapter(adapter);
    }
}
