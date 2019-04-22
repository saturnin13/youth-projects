package saturnin.test;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckedTextView;
import android.widget.TextView;
import android.app.Activity;
import android.widget.RelativeLayout;


public class MainActivity extends ActionBarActivity {

    TextView monTexte = null;
    RelativeLayout layout = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        layout = (RelativeLayout) RelativeLayout.inflate(this, R.layout.activity_main, null);

        monTexte = (TextView)layout.findViewById(R.id.textView);
        monTexte.setText("fils de pute de baiseur de lapin belge");

        setContentView(layout);
    }
}
