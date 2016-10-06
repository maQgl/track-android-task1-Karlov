package task1.maq.app;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class FirstActivity extends AppCompatActivity {

    private boolean taskStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        Log.i("FirstActivity:", "onCreate");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!taskStarted) {
            taskStarted = true;
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... params) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    Log.i("FirstActivity", "afterSleep");
                    Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                    startActivity(intent);
                }
            }.execute();
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        taskStarted = savedInstanceState.getBoolean("taskStarted");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("taskStarted", taskStarted);
    }
}
