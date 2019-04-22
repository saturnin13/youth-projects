package saturnin.network;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.setProperty("http.keepAlive", "false");
        OutputStreamWriter writer = null;
        BufferedReader reader = null;
        URLConnection connexion = null;
        try {
            // Encodage des param�tres de la requ�te
            String donnees = URLEncoder.encode("identifiant1", "UTF-8")+ "="+URLEncoder.encode("valeur1", "UTF-8");
            donnees += "&"+URLEncoder.encode("identifiant2", "UTF-8")+ "=" + URLEncoder.encode("valeur2", "UTF-8");

            // On a envoy� les donn�es � une adresse distante
            String adresse = "https://openclassrooms.com";
            URL url = new URL(adresse);
            connexion = url.openConnection();
            connexion.setDoOutput(true);

            // On envoie la requ�te ici
            writer = new OutputStreamWriter(connexion.getOutputStream());

            // On ins�re les donn�es dans notre flux
            writer.write(donnees);

            // Et on s'assure que le flux est vid�
            writer.flush();

            // On lit la r�ponse ici
            reader = new BufferedReader(new InputStreamReader(connexion.getInputStream()));
            String ligne;

            // Tant que � ligne � n'est pas null, c'est que le flux n'a pas termin� d'envoyer des informations
            while ((ligne = reader.readLine()) != null) {
                System.out.println(ligne);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{writer.close();}catch(Exception e){}
            try{reader.close();}catch(Exception e){}
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}