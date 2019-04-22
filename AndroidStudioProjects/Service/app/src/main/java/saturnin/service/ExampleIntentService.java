package saturnin.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class ExampleIntentService extends IntentService {
    private final static String TAG = "ExampleIntentService";

    public ExampleIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "Le compteur valait : " + intent.getIntExtra(MainActivity.EXTRA_COMPTEUR, -1));
        int i = 0;
        // Cette boucle permet de rajouter artificiellement du temps de traitement
        while(i < 1000000000)
            i++;
    }
}
