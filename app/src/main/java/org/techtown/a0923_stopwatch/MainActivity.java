package org.techtown.a0923_stopwatch;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.chronometer_view) Chronometer mChronometer;
    @BindView(R.id.start_button) Button startBtn;
    @BindView(R.id.stop_button) Button stopBtn;
    @BindView(R.id.reset_button) Button resetBtn;

    long baseOn = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        startBtn.setOnClickListener(this);
        stopBtn.setOnClickListener(this);
        resetBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) { //star버튼 누르면 v가 start버튼이 되고 stop버튼 누르면 v가 stop 버튼이 됨
        switch (v.getId()) {
            case R.id.start_button:
                mChronometer.setBase(SystemClock.elapsedRealtime());
                mChronometer.start();
                if (stopBtn.getText().equals("계속"))
                    stopBtn.setText("정지");
                break;
            case R.id.stop_button:
                if (stopBtn.getText().equals("정지")) {
                    stopBtn.setText("계속");
                    mChronometer.stop();
                    baseOn = mChronometer.getBase() - SystemClock.elapsedRealtime();
                } else if (stopBtn.getText().equals("계속")) {
                    stopBtn.setText("정지");
                    mChronometer.setBase(SystemClock.elapsedRealtime() + baseOn);
                    mChronometer.start();
                }
                break;
            case R.id.reset_button:
                mChronometer.stop();
                mChronometer.setBase(SystemClock.elapsedRealtime());
                if (stopBtn.getText().equals("계속"))
                    stopBtn.setText("정지");
                break;
        }
    }
}
