//N'oubliez pas de déclarer le bon package dans lequel se trouve le fichier !
package com.example.saturnin.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class PremiereActivite extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView text = new TextView(this);
        text.setText("Bonjour, vous me devez 1 000 000€.");
        setContentView(text);
    }
}