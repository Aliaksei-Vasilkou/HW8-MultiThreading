package com.github.coyclab.hw8_multithreading;

import android.os.AsyncTask;
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
    TextMaker mTextMaker = new TextMaker();

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
                changeTextUsingTread();
            }
        });

        mBtnAsyncTaskUsing.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View pView) {
                new TextChangerAsynkTask().execute();
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


    // Inner AsynkTask class
    class TextChangerAsynkTask extends AsyncTask<Void, Void, Void> {

        private final String METOD_NAME = "AsynkTask";
        private String  resultText = "";

        @Override
        protected Void doInBackground(final Void... pVoids) {
           resultText = new TextMaker().makeText(METOD_NAME);
           return null;
        }

        @Override
        protected void onPostExecute(final Void pVoid) {
            super.onPostExecute(pVoid);
            mTestText.setText(resultText);
        }
    }

}
