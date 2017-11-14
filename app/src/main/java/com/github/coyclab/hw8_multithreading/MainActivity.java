package com.github.coyclab.hw8_multithreading;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView mTestText;
    Button mBtnThreadUsing;
    Button mBtnAsyncTaskUsing;
    Button mBtnExecutorUsing;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTestText = (TextView) findViewById(R.id.text_view_test_text);
        mBtnThreadUsing = (Button) findViewById(R.id.button_thread_using);
        mBtnAsyncTaskUsing = (Button) findViewById(R.id.button_async_task_using);
        mBtnExecutorUsing = (Button) findViewById(R.id.button_executor_using);

        mBtnThreadUsing.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View pView) {
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        textChange("Threading");
                    }
                }).run();
            }
        });
    }

    void textChange(final String pMethodName) {
        final String result = "Text was successfully changed using " + pMethodName;
            mTestText.setText(result);
    }
}
