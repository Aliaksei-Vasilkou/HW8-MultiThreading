package com.github.coyclab.hw8_multithreading;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private TextView mTestText;
    private final TextMaker mTextMaker = new TextMaker();
    private final ExecutorService mExecutorService = Executors.newSingleThreadExecutor();
    private final Handler uiHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTestText = (TextView) findViewById(R.id.text_view_test_text);

        // TreadUsing button OnClickListener
        final Button btnThreadUsing = (Button) findViewById(R.id.button_thread_using);
        btnThreadUsing.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View pView) {
                changeTextUsingTread();
            }
        });

        // AsyncTask button OnClickListener
        final Button btnAsyncTaskUsing = (Button) findViewById(R.id.button_async_task_using);
        btnAsyncTaskUsing.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View pView) {
                changeTextUsingAsynkTask();
            }
        });

        // ExecutorUsing button OnClickListener
        final Button btnExecutorUsing = (Button) findViewById(R.id.button_executor_using);
        btnExecutorUsing.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View pView) {
                changeTextUsingExecutor();
            }
        });
    }

    private void changeTextUsingTread() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                mTestText.post(new Runnable() {

                    @Override
                    public void run() {
                        mTestText.setText(mTextMaker.makeText("Threading"));
                    }
                });
            }
        }).start();
    }

    private void changeTextUsingAsynkTask() {
        new TextChangerAsynkTask().execute();
    }

    private void changeTextUsingExecutor() {
        final Runnable runnable = new Runnable() {

            @Override
            public void run() {
                final String resultText = mTextMaker.makeText("Executor");
                uiHandler.post(new Runnable() {

                    @Override
                    public void run() {
                        mTestText.setText(resultText);
                    }
                });
            }
        };
        mExecutorService.execute(runnable);
    }

    // Inner AsynkTask class
    class TextChangerAsynkTask extends AsyncTask<Void, Void, Void> {

        private final String METOD_NAME = "AsynkTask";
        private String resultText = "";

        @Override
        protected Void doInBackground(final Void... pVoids) {
            resultText = mTextMaker.makeText(METOD_NAME);
            return null;
        }

        @Override
        protected void onPostExecute(final Void pVoid) {
            super.onPostExecute(pVoid);
            mTestText.setText(resultText);
        }
    }
}
