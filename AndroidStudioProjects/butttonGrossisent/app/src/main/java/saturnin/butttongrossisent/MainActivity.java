package saturnin.butttongrossisent;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity implements View.OnTouchListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Button b = (Button) findViewById(R.id.bouton);

        b.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        Button bouton = (Button) view;

        int largeur = bouton.getWidth();

        int hauteur = bouton.getHeight();

        float x = event.getX();

        float y = event.getY();

        bouton.setTextSize((Math.abs(x - largeur / 2) + Math.abs(y - hauteur / 2))/4);

        return true;
    }
}

