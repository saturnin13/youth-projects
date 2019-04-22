package saturnin.service;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
    private Button mBouton = null;
    private TextView mAffichageCompteur = null;

    private int mCompteur = 0;

    public final static String EXTRA_COMPTEUR = "saturnin.compteur";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         mAffichageCompteur = (TextView) findViewById(R.id.affichage);

        mBouton = (Button) findViewById(R.id.bouton);
        mBouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ExampleIntentService.class);
                i.putExtra(EXTRA_COMPTEUR, mCompteur);

                mCompteur ++;
                mAffichageCompteur.setText("" + mCompteur);

                startService(i);
            }
        });
    }
}
