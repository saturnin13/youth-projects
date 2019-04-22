package saturnin.langage;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_main);

    Resources res = getResources();
    // Anais se mettra dans %1 et 22 ira dans %2, mais le reste changera en fonction de la langue du terminal !
    String chaine = res.getString(R.string.hello, "Anais", 22);
    TextView vue = (TextView)findViewById(R.id.vue);
    vue.setText(chaine);
  }
}