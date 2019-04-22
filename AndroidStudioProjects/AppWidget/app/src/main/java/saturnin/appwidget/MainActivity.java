package saturnin.appwidget;

        import android.app.PendingIntent;
        import android.appwidget.AppWidgetManager;
        import android.appwidget.AppWidgetProvider;
        import android.content.Context;
        import android.content.Intent;
        import android.net.Uri;
        import android.widget.RemoteViews;

public class MainActivity extends AppWidgetProvider {
    // Les tutos que propose notre widget
    private final static Tuto TUTO_ARRAY[] = {
            new Tuto("Apprenez a creer votre site web avec HTML5 et CSS3", "http://www.siteduzero.com/tutoriel-3-13666-apprenez-a-creer-votre-site-web-avec-html5-et-css3.html"),
            new Tuto("Apprenez a programmer en C !", "http://www.siteduzero.com/tutoriel-3-14189-apprenez-a-programmer-en-c.html"),
            new Tuto("Creez des applications pour Android", "http://www.siteduzero.com/tutoriel-3-554364-creez-des-applications-pour-android.html")
    };

    // Intitule de l'extra qui contient la direction du defile
    private final static String EXTRA_DIRECTION = "extraDirection";

    // La valeur pour defiler vers la gauche
    private final static String EXTRA_PREVIOUS = "previous";

    // La valeur pour defiler vers la droite
    private final static String EXTRA_NEXT = "next";

    // Intitule de l'extra qui contient l'indice actuel dans le tableau des tutos
    private final static String EXTRA_INDICE = "extraIndice";

    // Action qui indique qu'on essaie d'ouvrir un tuto sur internet
    private final static String ACTION_OPEN_TUTO = "saturnin.appwidget.action.OPEN_TUTO";

    // Indice actuel dans le tableau des tutos
    private int indice = 0;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        // Petite astuce : permet de garder la longueur du tableau sans acceder plusieurs fois a l'objet, d'ou optimisation
        final int length = appWidgetIds.length;
        for (int i = 0 ; i < length ; i++) {
            // On recupere le RemoteViews qui correspond a l'AppWidget
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.activity_main);

            // On met le bon texte dans le bouton
            views.setTextViewText(R.id.link, TUTO_ARRAY[indice].getIntitule());

            // La prochaine section est destinee au bouton qui permet de passer au tuto suivant
            //********************************************************
            //*******************NEXT*********************************
            //********************************************************
            Intent nextIntent = new Intent(context, MainActivity.class);

            // On veut que l'intent lance la mise a jour
            nextIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);

            // On n'oublie pas les identifiants
            nextIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);

            // On rajoute la direction
            nextIntent.putExtra(EXTRA_DIRECTION, EXTRA_NEXT);

            // Ainsi que l'indice
            nextIntent.putExtra(EXTRA_INDICE, indice);

            // Les donnees inutiles mais qu'il faut rajouter
            Uri data = Uri.withAppendedPath(Uri.parse("WIDGET://widget/id/"), String.valueOf(R.id.next));
            nextIntent.setData(data);

            // On insere l'intent dans un PendingIntent
            PendingIntent nextPending = PendingIntent.getBroadcast(context, 0, nextIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            // Et on l'associe a l'activation du bouton
            views.setOnClickPendingIntent(R.id.next, nextPending);

            // La prochaine section est destinee au bouton qui permet de passer au tuto precedent
            //********************************************************
            //*******************PREVIOUS*****************************
            //********************************************************

            Intent previousIntent = new Intent(context, MainActivity.class);

            previousIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            previousIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
            previousIntent.putExtra(EXTRA_DIRECTION, EXTRA_PREVIOUS);
            previousIntent.putExtra(EXTRA_INDICE, indice);

            data = Uri.withAppendedPath(Uri.parse("WIDGET://widget/id/"), String.valueOf(R.id.previous));
            previousIntent.setData(data);

            PendingIntent previousPending = PendingIntent.getBroadcast(context, 1, previousIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            views.setOnClickPendingIntent(R.id.previous, previousPending);


            // La section suivante est destinee a l'ouverture d'un tuto dans le navigateur
            //********************************************************
            //*******************LINK*********************************
            //********************************************************
            // L'intent ouvre cette classe meme
            Intent linkIntent = new Intent(context, MainActivity.class);

            // Action l'action ACTION_OPEN_TUTO
            linkIntent.setAction(ACTION_OPEN_TUTO);
            // Et l'adresse du site a visiter
            linkIntent.setData(TUTO_ARRAY[indice].getAdresse());

            // On ajoute l'intent dans un PendingIntent
            PendingIntent linkPending = PendingIntent.getBroadcast(context, 2, linkIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            views.setOnClickPendingIntent(R.id.link, linkPending);

            // Et il faut mettre a jour toutes les vues
            appWidgetManager.updateAppWidget(appWidgetIds[i], views);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // Si l'action est celle d'ouverture du tutoriel
        if(intent.getAction().equals(ACTION_OPEN_TUTO)) {
            Intent link = new Intent(Intent.ACTION_VIEW);
            link.setData(intent.getData());
            link.addCategory(Intent.CATEGORY_DEFAULT);
            // Comme on ne se trouve pas dans une activite, on demande a creer une nouvelle tache
            link.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(link);
        } else {
            // Sinon, s'il s'agit d'une demande de mise a jour
            // On recupere l'indice passe en extra, ou -1 s'il n'y a pas d'indice
            int tmp = intent.getIntExtra(EXTRA_INDICE, -1);

            // S'il y avait bien un indice passe
            if(tmp != -1) {
                // On recupere la direction
                String extra = intent.getStringExtra(EXTRA_DIRECTION);
                // Et on calcule l'indice voulu par l'utilisateur
                if (extra.equals(EXTRA_PREVIOUS)) {
                    indice = (tmp - 1) % TUTO_ARRAY.length;
                    if(indice < 0)
                        indice += TUTO_ARRAY.length;
                } else if(extra.equals(EXTRA_NEXT))
                    indice = (tmp + 1) % TUTO_ARRAY.length;
            }
        }

        // On revient au traitement naturel du Receiver, qui va lancer onUpdate s'il y a demande de mise a jour
        super.onReceive(context, intent);
    }

}
