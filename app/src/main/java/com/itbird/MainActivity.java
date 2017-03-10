package com.itbird.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.itbird.retrofit.entity.Doctor;
import com.itbird.retrofit.http.RetrofitWrapper;
import com.itbird.retrofit.subscribers.ProgressSubscriber;
import com.itbird.retrofit.subscribers.SubscriberOnNextListener;
import java.util.List;
import com.itbird.R;

public class MainActivity extends AppCompatActivity {

    private SubscriberOnNextListener getTopMovieOnNext;
    TextView resultTV;

    private RetrofitWrapper mRetrofitWrapper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTV = (TextView) findViewById(R.id.result_TV);

        mRetrofitWrapper = new RetrofitWrapper(this, true, true);

        findViewById(R.id.click_me_BN).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRetrofitWrapper.getDoctorList(new ProgressSubscriber(getTopMovieOnNext, MainActivity.this), 0, 10);
            }
        });
        getTopMovieOnNext = new SubscriberOnNextListener<List<Doctor>>() {
            @Override
            public void onNext(List<Doctor> doctors) {
                resultTV.setText(doctors.toString());
            }
        };
    }
}
