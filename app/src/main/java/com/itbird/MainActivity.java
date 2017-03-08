package com.itbird.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.itbird.retrofit.entity.Subject;
import com.itbird.retrofit.http.RetrofitWrapper;
import com.itbird.retrofit.subscribers.ProgressSubscriber;
import com.itbird.retrofit.subscribers.SubscriberOnNextListener;
import java.util.List;
import com.itbird.R;

public class MainActivity extends AppCompatActivity {

    private SubscriberOnNextListener getTopMovieOnNext;
    TextView resultTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTV = (TextView) findViewById(R.id.result_TV);
        findViewById(R.id.click_me_BN).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetrofitWrapper.getInstance().getTopMovie(new ProgressSubscriber(getTopMovieOnNext, MainActivity.this), 0, 10);
            }
        });
        getTopMovieOnNext = new SubscriberOnNextListener<List<Subject>>() {
            @Override
            public void onNext(List<Subject> subjects) {
                resultTV.setText(subjects.toString());
            }
        };
    }
}
