package saturnin.bloc_note;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
    /* Recupération des éléments du GUI */
    private Button hideShow = null;
    private Slider slider = null;
    private RelativeLayout toHide = null;
    private EditText editer = null;
    private TextView text = null;
    private RadioGroup colorChooser = null;

    private Button bold = null;
    private Button italic = null;
    private Button underline = null;

    private ImageButton smile = null;
    private ImageButton heureux = null;
    private ImageButton clin = null;

    /* Utilisé pour planter les smileys dans le texte */
    private SmileyGetter getter = null;

    /* Couleur actuelle du texte */
    private String currentColor = "#000000";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getter = new SmileyGetter(this);

        //On récupère le bouton pour cacher/afficher le menu
        slider = (Slider) findViewById(R.id.slider);
        hideShow = (Button) findViewById(R.id.hideShow);
        //Puis, on recupère la vue racine de l'application et on change sa couleur
        slider.setBackgroundColor(getResources().getColor(R.color.background));
        //On rajoute un Listener sur le clic du bouton...
        hideShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vue) {
                //...pour afficher ou cache le menu
                if(slider.toggle())
                {
                    //Si le Slider est ouvert...
                    //... on change le texte en "Cacher"
                    hideShow.setText(R.string.hide);
                }else
                {
                    //Sinon on met "Afficher"
                    hideShow.setText(R.string.show);
                }
            }
        });

        //On récupère le menu
        toHide = (RelativeLayout) findViewById(R.id.toHide);
        //On récupère le layout principal

        //On donne le menu au layout principal
        slider.setToHide(toHide);

        //On récupère le TextView qui affiche le texte final
        text = (TextView) findViewById(R.id.text);
        //On permet au TextView de défiler
        text.setMovementMethod(new ScrollingMovementMethod());

        //On récupère l'éditeur de texte
        editer = (EditText) findViewById(R.id.edit);
        //On ajouter un Listener sur l'appui de touches
        editer.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //On récupère la position du début de la sélection dans le texte
                int cursorIndex = editer.getSelectionStart();
                //Ne réagir qu'à l'appui sur une touche (et pas le relâchement)
                if(event.getAction() == 0)
                    //S'il s'agit d'un appui sur la touche "entrée"
                    if(keyCode == 66)
                        //On insère une balise de retour à la ligne
                        editer.getText().insert(cursorIndex, "<br />");
                return true;
            }
        });
        //On ajoute un autre Listener sur le changement dans le texte cette fois
        editer.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Le Textview interprète le texte dans l'éditeur en une certaine couleur
                text.setText(Html.fromHtml("<font color=\"" + currentColor + "\">" + editer.getText().toString() + "</font>", getter, null));
            }


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        //On récupère le RadioGroup qui gère la couleur du texte
        colorChooser = (RadioGroup) findViewById(R.id.colors);
        //On rajoute un Listener sur le changement de RadioButton sélectionné
        colorChooser.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //En fonction de l'identifiant du RadioButton sélectionné...
                switch(checkedId)
                {
                    //On change la couleur actuelle pour noir
                    case R.id.black:
                        currentColor = "#000000";
                        break;
                    //On change la couleur actuelle pour bleu
                    case R.id.blue:
                        currentColor = "#0022FF";
                        break;
                    //On change la couleur actuelle pour rouge
                    case R.id.red:
                        currentColor = "#FF0000";
                }
				/*
				 * On met dans l'éditeur son texte actuel
				 * pour activer le Listener de changement de texte
				 */
                editer.setText(editer.getText().toString());
            }
        });

        smile = (ImageButton) findViewById(R.id.smile);
        smile.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //On récupère la position du début de la sélection dans le texte
                int selectionStart = editer.getSelectionStart();
                //Et on insère à cette position une balise pour afficher l'image du smiley
                editer.getText().insert(selectionStart, "<img src=\"smile\" >");
            }
        });

        heureux =(ImageButton) findViewById(R.id.heureux);
        heureux.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //On récupère la position du début de la sélection
                int selectionStart = editer.getSelectionStart();
                editer.getText().insert(selectionStart, "<img src=\"heureux\" >");
            }
        });

        clin = (ImageButton) findViewById(R.id.clin);
        clin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //On récupère la position du début de la sélection
                int selectionStart = editer.getSelectionStart();
                editer.getText().insert(selectionStart, "<img src=\"clin\" >");
            }
        });

        bold = (Button) findViewById(R.id.bold);
        bold.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View vue) {
                //On récupère la position du début de la sélection
                int selectionStart = editer.getSelectionStart();
                //On récupère la position de la fin de la sélection
                int selectionEnd = editer.getSelectionEnd();

                Editable editable = editer.getText();

                //Si les deux positions sont identiques (pas de sélection de plusieurs caractères)
                if(selectionStart == selectionEnd)
                    //On insère les balises ouvrantes et fermantes avec rien dedans
                    editable.insert(selectionStart, "<b></b>");
                else
                {
                    //On met la balise avant la sélection
                    editable.insert(selectionStart, "<b>");
                    //On rajoute la balise après la sélection (et les 3 caractères de la balise <b>)
                    editable.insert(selectionEnd + 3, "</b>");
                }

            }
        });

        italic = (Button) findViewById(R.id.italic);
        italic.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View vue) {
                //On récupère la position du début de la sélection
                int selectionStart = editer.getSelectionStart();
                //On récupère la position de la fin de la sélection
                int selectionEnd = editer.getSelectionEnd();

                Editable editable = editer.getText();

                //Si les deux positions sont identiques (pas de sélection de plusieurs caractères)
                if(selectionStart == selectionEnd)
                    //On insère les balises ouvrantes et fermantes avec rien dedans
                    editable.insert(selectionStart, "<i></i>");
                else
                {
                    //On met la balise avant la sélection
                    editable.insert(selectionStart, "<i>");
                    //On rajoute la balise après la sélection (et les 3 caractères de la balise <b>)
                    editable.insert(selectionEnd + 3, "</i>");
                }

            }
        });

        underline = (Button) findViewById(R.id.underline);
        underline.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View vue) {
                //On récupère la position du début de la sélection
                int selectionStart = editer.getSelectionStart();
                //On récupère la position de la fin de la sélection
                int selectionEnd = editer.getSelectionEnd();

                Editable editable = editer.getText();

                //Si les deux positions sont identiques (pas de sélection de plusieurs caractères)
                if(selectionStart == selectionEnd)
                    //On insère les balises ouvrantes et fermantes avec rien dedans
                    editable.insert(selectionStart, "<u></u>");
                else
                {
                    //On met la balise avant la sélection
                    editable.insert(selectionStart, "<u>");
                    //On rajoute la balise après la sélection (et les 3 caractères de la balise <b>)
                    editable.insert(selectionEnd + 3, "</u>");
                }

            }
        });
    }
}