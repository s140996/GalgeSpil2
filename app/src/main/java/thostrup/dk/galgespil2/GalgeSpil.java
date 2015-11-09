package thostrup.dk.galgespil2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class GalgeSpil extends AppCompatActivity implements View.OnClickListener {

    private Galgelogik game;
    private ImageView iv1;
    private Button b1;
    private TextView tv1, tv2, tv3;
    private EditText et1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galge_spil);

        // *** Opretter nyt Spil-objekt ***
        game = new Galgelogik();
        game.nulstil();
// HEJ HEJ HEJ
        // *** Sætter ImageView og Startbillede ***
        iv1 = (ImageView) findViewById(R.id.imageView);
        iv1.setImageResource(R.mipmap.galge);

        // *** Sætter Knap ***
        b1 = (Button) findViewById(R.id.buttonGuess);
        b1.setOnClickListener(this);
        b1.setText("GÆT");

        // *** Sætter TextViews ***
        tv1 = (TextView) findViewById(R.id.textViewWord);
        tv2 = (TextView) findViewById(R.id.textViewInfo);
        tv3 = (TextView) findViewById(R.id.textViewCount);

        tv1.setText(game.getSynligtOrd());
        tv2.setText("Velkommen til Galge-Spillet");
        tv3.setText("Forkerte gæt tilbage: 6");

        // *** Sætter EditText ***
        et1 = (EditText) findViewById(R.id.editTextGuess);
    }

    @Override
    public void onClick(View v)
    {
       if (b1.getText().equals("GÆT"))
       {


       if (v == b1) {

           char c = et1.getText().toString().charAt(0);

           if (et1.getText().toString().length() == 1 && Character.isLetter(c)) {

               if (!game.getBrugteBogstaver().contains(et1.getText().toString())) {
                   game.gætBogstav(et1.getText().toString());

                   if (game.erSidsteBogstavKorrekt() == false) {
                       if (game.getAntalForkerteBogstaver() == 1) {
                           iv1.setImageResource(R.mipmap.forkert1);
                       } else if (game.getAntalForkerteBogstaver() == 2) {
                           iv1.setImageResource(R.mipmap.forkert2);
                       } else if (game.getAntalForkerteBogstaver() == 3) {
                           iv1.setImageResource(R.mipmap.forkert3);
                       } else if (game.getAntalForkerteBogstaver() == 4) {
                           iv1.setImageResource(R.mipmap.forkert4);
                       } else if (game.getAntalForkerteBogstaver() == 5) {
                           iv1.setImageResource(R.mipmap.forkert5);
                       } else if (game.getAntalForkerteBogstaver() == 6) {
                           iv1.setImageResource(R.mipmap.forkert6);
                       }

                       tv2.setText("Desværre! Forkert bogstav!");
                       et1.setText("");
                       tv3.setText("Forkerte gæt tilbage: " + (6 - game.getAntalForkerteBogstaver()));

                       if (game.erSpilletTabt()) {
                           tv2.setText("Desværre! Du har tabt!");
                           et1.setText("");
                           tv3.setText("Ordet var: " + game.getOrdet());
                           b1.setText("NYT SPIL");
                       }

                   } else if (game.erSidsteBogstavKorrekt() == true) {
                       tv2.setText("Flot! Godt gættet!");
                       et1.setText("");

                       if (game.erSpilletVundet()) {
                           tv2.setText("Tillykke! Du har vundet!");
                           et1.setText("");
                           iv1.setImageResource(R.mipmap.vundet);
                           b1.setText("NYT SPIL");
                       }
                   }
                   tv1.setText(game.getSynligtOrd());
               } else {
                   tv2.setText("Bogstavet er brugt!");
                   et1.setText("");
               }

           } else {
               tv2.setText("Indtast venligst et bogstav");
           }
        }

       }
       else
       {
           game.nulstil();
           iv1.setImageResource(R.mipmap.galge);
           tv1.setText(game.getSynligtOrd());
           b1.setText("GÆT");
           tv3.setText("Forkerte gæt tilbage: " + (6 - game.getAntalForkerteBogstaver()));
       }

    }
}
