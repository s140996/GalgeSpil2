package thostrup.dk.galgespil2;


import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class GalgeSpil extends AppCompatActivity implements View.OnClickListener {

    // *** Opretter variabler ***
    private ImageView iv1;
    private Button b1;
    private TextView tv1, tv2, tv3, tv4;
    private EditText et1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galge_spil);

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
        tv4 = (TextView) findViewById(R.id.textViewUsed);

        tv1.setText(StartScreen.game.getSynligtOrd());
        tv2.setText("Velkommen til Galge-Spillet");
        tv3.setText("Forkerte gæt tilbage: 6");

        // *** Sætter EditText ***
        et1 = (EditText) findViewById(R.id.editTextGuess);
        et1.setText("");
    }

    @Override
    public void onClick(View v)
    {       // *** Start spil ***
       if (b1.getText().equals("GÆT"))
       {        // *** Giver fejlbesked ved intet indtastet ***
            if (!et1.getText().toString().matches("")) {
                    // *** Opretter char variabel ***
                char c = et1.getText().toString().charAt(0);
                    // *** Checker for korrekt indtastning ***
                if (et1.getText().toString().length() == 1 && Character.isLetter(c)) {
                    // *** Tjekker om bogstav er brugt ***
                    if (!StartScreen.game.getBrugteBogstaver().contains(et1.getText().toString())) {
                        StartScreen.game.gætBogstav(et1.getText().toString());
                                // *** Ved forkert bogstav ***
                        if (StartScreen.game.erSidsteBogstavKorrekt() == false) {
                            if (StartScreen.game.getAntalForkerteBogstaver() == 1) {
                                iv1.setImageResource(R.mipmap.forkert1);
                            } else if (StartScreen.game.getAntalForkerteBogstaver() == 2) {
                                iv1.setImageResource(R.mipmap.forkert2);
                            } else if (StartScreen.game.getAntalForkerteBogstaver() == 3) {
                                iv1.setImageResource(R.mipmap.forkert3);
                            } else if (StartScreen.game.getAntalForkerteBogstaver() == 4) {
                                iv1.setImageResource(R.mipmap.forkert4);
                            } else if (StartScreen.game.getAntalForkerteBogstaver() == 5) {
                                iv1.setImageResource(R.mipmap.forkert5);
                            } else if (StartScreen.game.getAntalForkerteBogstaver() == 6) {
                                iv1.setImageResource(R.mipmap.forkert6);
                            }

                            tv2.setText("Desværre! Forkert bogstav!");
                            tv3.setText("Forkerte gæt tilbage: " + (6 - StartScreen.game.getAntalForkerteBogstaver()));
                            hideSoftKeyboard(this);
                                // *** Når spillet er tabt ***
                            if (StartScreen.game.erSpilletTabt()) {
                                tv2.setText("Desværre! Du har tabt!");
                                iv1.setImageResource(R.mipmap.tabt);
                                tv3.setText("Ordet var: " + StartScreen.game.getOrdet());
                                b1.setText("NYT SPIL");
                                et1.setVisibility(View.INVISIBLE);
                            }
                                // *** Korrekt gættet bogstav ***
                        } else if (StartScreen.game.erSidsteBogstavKorrekt() == true) {
                            tv2.setText("Flot! Godt gættet!");
                            hideSoftKeyboard(this);
                                // *** Spillet er vundet ***
                            if (StartScreen.game.erSpilletVundet()) {
                                tv2.setText("Tillykke! Du har vundet!");
                                iv1.setImageResource(R.mipmap.vundet);
                                b1.setText("NYT SPIL");
                                et1.setVisibility(View.INVISIBLE);
                            }
                        }
                        tv1.setText(StartScreen.game.getSynligtOrd());
                    } else {
                        tv2.setText("Bogstavet er brugt!");
                        hideSoftKeyboard(this);
                    }

                } else {
                    tv2.setText("Indtast venligst et bogstav");
                    hideSoftKeyboard(this);
                }

                et1.setText("");
                tv4.setText("Brugte bogstaver: " + StartScreen.game.getBrugteBogstaver());
            }
            else
            {
                tv2.setText("Indtast venligst et bogstav");
                hideSoftKeyboard(this);
            }
       }
       else
       {
           // *** Start et nyt spil funktion ***
           StartScreen.game.nulstil();
           iv1.setImageResource(R.mipmap.galge);
           tv1.setText(StartScreen.game.getSynligtOrd());
           tv2.setText("Nyt spil!");
           tv4.setText("");
           b1.setText("GÆT");
           tv3.setText("Forkerte gæt tilbage: " + (6 - StartScreen.game.getAntalForkerteBogstaver()));
           et1.setVisibility(View.VISIBLE);
       }

    }

    public void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }
}
