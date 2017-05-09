package charlieandroidblog.esy.net.activitywithasyncandrotationdevicetest;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ICallBack {

    MyAsync async;
    ProgressDialog progressDialog;
    private final String TAG_ASYNC = "asyncThread";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null){
            async = (MyAsync) savedInstanceState.getSerializable(TAG_ASYNC);
            startProgressDialog(0);
            async.setMainUI(this);
        }

        setContentView(R.layout.activity_main);
    }

    public void btnStartAsyncTaskClicked(View v){
        startProgressDialog(0);
        async = new MyAsync(this);
        async.execute();
    }

    @Override
    public void asyncProgressUpdate(int i) {
        progressDialog.setProgress(i);
    }

    @Override
    public void asyncFinished() {
        Toast.makeText(this,"async finished",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(TAG_ASYNC,async);
        super.onSaveInstanceState(outState);
    }

    public void startProgressDialog(int progress){
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Descargando.");
        progressDialog.setIndeterminate(false);
        progressDialog.setMax(100);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setProgress(progress);
        progressDialog.show();
    }
}
