package me.petermooney.project4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class SampleListActivity extends Activity {

    private static final int REQUEST_CODE_SAMPLE_DETAIL = 0;

    private int mActivePosition = -1;
    private SamplesAdapter mAdapter;
    private Sample[] samples = new Sample[] {
        new Sample(R.raw.crept_and_we_came, R.string.sample_crept_and_we_came_title, R.string.sample_crept_and_we_came_artist, R.string.sample_crept_and_we_came_answer),
        new Sample(R.raw.lets_go, R.string.sample_lets_go_title, R.string.sample_lets_go_artist, R.string.sample_lets_go_answer),
        new Sample(R.raw.commas, R.string.sample_commas_title, R.string.sample_commas_artist, R.string.sample_commas_answer)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_list);

        mAdapter = new SamplesAdapter(this, samples);
        ListView listView = findViewById(R.id.sample_list);
        listView.setAdapter(mAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = SampleDetailActivity.newIntent(SampleListActivity.this, samples[position]);
                mActivePosition = position;
                startActivityForResult(intent, REQUEST_CODE_SAMPLE_DETAIL);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == REQUEST_CODE_SAMPLE_DETAIL) {
            if (data == null) {
                return;
            }
            Sample sample = SampleDetailActivity.getSample(data);
            samples[mActivePosition] = sample;
            mAdapter.notifyDataSetChanged();
        }
    }
}
