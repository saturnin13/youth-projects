package saturnin.notification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    public int ID_NOTIFICATION = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b = (Button) findViewById(R.id.launch);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // L'ic�ne sera une petite loupe
                int icon = R.mipmap.ic_launcher;
                // Le premier titre affich�
                CharSequence tickerText = "Titre de la notification";
                // Dat� de maintenant
                long when = (System.currentTimeMillis() + 22000000);

                // La notification est cr��e
                Notification notification = new Notification(icon, tickerText, when);

                Intent notificationIntent = new Intent(MainActivity.this, MainActivity.class);
                notificationIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                PendingIntent contentIntent = PendingIntent.getActivity(MainActivity.this, 0, notificationIntent, 0);

                notification.setLatestEventInfo(MainActivity.this, "Titre", "Texte", contentIntent);

                // R�cup�ration du Notification Manager
                NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                manager.notify(ID_NOTIFICATION, notification);
            }
        });
    }
}
