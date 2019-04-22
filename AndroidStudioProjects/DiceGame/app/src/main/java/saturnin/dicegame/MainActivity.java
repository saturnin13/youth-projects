package saturnin.dicegame;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.ListIterator;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button layoutPlayer2Dice1 = (Button) findViewById(R.id.layoutPlayer2Dice1);
        Button layoutPlayer2Dice2 = (Button) findViewById(R.id.layoutPlayer2Dice2);
        Button layoutPlayer2Dice3 = (Button) findViewById(R.id.layoutPlayer2Dice3);
        Button layoutPlayer2Dice4 = (Button) findViewById(R.id.layoutPlayer2Dice4);
        Button layoutPlayer2Dice5 = (Button) findViewById(R.id.layoutPlayer2Dice5);
        Button layoutPlayer2Dice6 = (Button) findViewById(R.id.layoutPlayer2Dice6);
        Button layoutPlayer1Dice1 = (Button) findViewById(R.id.layoutPlayer1Dice1);
        Button layoutPlayer1Dice2 = (Button) findViewById(R.id.layoutPlayer1Dice2);
        Button layoutPlayer1Dice3 = (Button) findViewById(R.id.layoutPlayer1Dice3);
        Button layoutPlayer1Dice4 = (Button) findViewById(R.id.layoutPlayer1Dice4);
        Button layoutPlayer1Dice5 = (Button) findViewById(R.id.layoutPlayer1Dice5);
        Button layoutPlayer1Dice6 = (Button) findViewById(R.id.layoutPlayer1Dice6);

        TextView layoutPlayer2SelectedDice = (TextView) findViewById(R.id.layoutPlayer2SelectedDice);
        TextView layoutPlayer1SelectedDice = (TextView) findViewById(R.id.layoutPlayer1SelectedDice);

        Player player2 = new Player();
        Player player1 = new Player();

        List<Dice> player2Dices = player2.getDices();
        ListIterator<Dice> player2DicesIterator = player2Dices.listIterator();
        layoutPlayer2Dice1.setText(String.valueOf(player2DicesIterator.next().getValue()));
        layoutPlayer2Dice2.setText(String.valueOf(player2DicesIterator.next().getValue()));
        layoutPlayer2Dice3.setText(String.valueOf(player2DicesIterator.next().getValue()));
        layoutPlayer2Dice4.setText(String.valueOf(player2DicesIterator.next().getValue()));
        layoutPlayer2Dice5.setText(String.valueOf(player2DicesIterator.next().getValue()));
        layoutPlayer2Dice6.setText(String.valueOf(player2DicesIterator.next().getValue()));

        List<Dice> player1Dices = player1.getDices();
        ListIterator<Dice> player1DicesIterator = player1Dices.listIterator();
        layoutPlayer1Dice1.setText(String.valueOf(player1DicesIterator.next().getValue()));
        layoutPlayer1Dice2.setText(String.valueOf(player1DicesIterator.next().getValue()));
        layoutPlayer1Dice3.setText(String.valueOf(player1DicesIterator.next().getValue()));
        layoutPlayer1Dice4.setText(String.valueOf(player1DicesIterator.next().getValue()));
        layoutPlayer1Dice5.setText(String.valueOf(player1DicesIterator.next().getValue()));
        layoutPlayer1Dice6.setText(String.valueOf(player1DicesIterator.next().getValue()));

    }

}
