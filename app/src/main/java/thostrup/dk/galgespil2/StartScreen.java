package thostrup.dk.galgespil2;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StartScreen extends AppCompatActivity {

    public static Galgelogik game;
    private TextView tv;
    private Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        // *** Opretter nyt Spil-objekt ***
        game = new Galgelogik();
        game.nulstil();

        // *** Sætter TextView ***
        tv = (TextView) findViewById(R.id.DR);

        // *** Sætter Knap ***
        b = (Button) findViewById(R.id.buttonNew);
        b.setVisibility(View.INVISIBLE);

        // *** Hent ord fra DR ***
        getDRwords();

        findViewById(R.id.buttonNew).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartScreen.this, GalgeSpil.class));
            }
        });
        findViewById(R.id.buttonIndstillinger).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartScreen.this, Indstillinger.class));
            }
        });
        findViewById(R.id.buttonHelp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartScreen.this, Help.class));
            }
        });
    }

    public void getDRwords()
    {
        tv.setText("Henter ord fra DRs server....");
        new AsyncTask() {
            @Override
            protected Object doInBackground(Object... arg0) {
                try {
                    StartScreen.game.hentOrdFraDr();
                    return "Ordene blev korrekt hentet fra DR's server.";
                } catch (Exception e) {
                    e.printStackTrace();
                    return "Ordene blev ikke hentet korrekt: " + e;
                }
            }

            @Override
            protected void onPostExecute(Object resultat) {
                if (resultat.equals("Ordene blev korrekt hentet fra DR's server."))
                {
                    tv.setText("" + resultat);
                    b.setVisibility(View.VISIBLE);
                }
                else {
                    tv.setText("" + resultat);
                }
            }
        }.execute();
    }
}
