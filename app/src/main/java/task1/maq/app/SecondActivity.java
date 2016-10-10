package task1.maq.app;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private TextView mTextView;

    private Button mButton;

    private int mCounter = 1;

    private boolean isCounterActive;

    private CounterAsyncTask mCounterAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mTextView = (TextView) findViewById(R.id.textView);
        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCounterActive) {
                    stopCounter();
                } else {
                    startCounter();
                }
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("counter", mCounter);
        outState.putBoolean("isCounterActive", isCounterActive);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        isCounterActive = savedInstanceState.getBoolean("isCounterActive");
        if (savedInstanceState.getInt("counter") > 1) {
            mCounter = savedInstanceState.getInt("counter");
            mTextView.setText((new MyOwnConverter()).convertNumber(mCounter));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isCounterActive) {
            startCounter();
        }
    }

    private void startCounter() {
        mButton.setText(R.string.stop);
        isCounterActive = true;
        mCounterAsyncTask = new CounterAsyncTask();
        mCounterAsyncTask.execute(mCounter);
    }

    private void stopCounter() {
        if (!mCounterAsyncTask.isCancelled()) {
            mCounterAsyncTask.cancel(true);
        }
        mCounter = 1;
        mButton.setText(R.string.start);
        isCounterActive = false;
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mCounterAsyncTask != null) {
            mCounterAsyncTask.cancel(true);
        }
    }

    class CounterAsyncTask extends AsyncTask<Integer, Integer, Void> {

        @Override
        protected Void doInBackground(Integer... params) {
            for (int i = params[0]; i < 1001; i++) {
                publishProgress(i);
                if (isCancelled()) break;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            mCounter = values[0];
            mTextView.setText((new MyOwnConverter()).convertNumber(mCounter));
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            stopCounter();
        }
    }
}
