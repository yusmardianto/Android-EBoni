package id.co.perhutani.sisdhbukuobor.Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyBackgroundService extends Service {
    public MyBackgroundService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    // Thread initialization
    Thread t = new ThreadSendToAPI(this);

    @Override
    public void onCreate() {
        super.onCreate();
        // starting the thread

        // mengaktifkan service kirim data ke server
        t.start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(!t.isAlive())
        {
            t.start();
        }
        return Service.START_REDELIVER_INTENT;
    }

}
