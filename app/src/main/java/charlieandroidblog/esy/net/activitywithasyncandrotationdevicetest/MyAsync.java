package charlieandroidblog.esy.net.activitywithasyncandrotationdevicetest;

import android.os.AsyncTask;
import android.util.Log;

import java.io.Serializable;

/**
 * Created by user on 5/9/17.
 */

public class MyAsync extends AsyncTask<Void,Void,Void> implements Serializable{

    private static final long serialVersionUID = 7526472295622776147L;
    private static final String TAG = "Async";

    ICallBack mainUI;
    int cont;

    public MyAsync(ICallBack mainUI) {
        this.mainUI = mainUI;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        for (cont=1;cont<100;cont++){
            try {
                Thread.sleep(100);
                publishProgress();
            } catch (InterruptedException e) {
                Log.d(TAG,"Error:"+e.toString());
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        mainUI.asyncFinished();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
        mainUI.asyncProgressUpdate(cont);
    }

    public void setMainUI(ICallBack mainUI) {
        this.mainUI = mainUI;
    }

}
