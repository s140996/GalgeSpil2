package thostrup.dk.galgespil2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Help extends AppCompatActivity {

    private TextView tvHelp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        tvHelp = (TextView) findViewById(R.id.textViewHelp);
        tvHelp.setText("Galgespil:\n" +
                "Spillet går ud på at gætte et skjult ord, hvor personen kun kender længden på ordet. Spilleren gætter på bogstaver, og får svar på om bogstavet er korrekt eller forkert (om bogstavet optræder i ordet). \n" +
                "Korrekte bogstaver bliver placeret på de respektive placer i ordet. \n" +
                "Hver gang der bliver gættet på et forkert bogstav, bliver der tegnet en ekstra ting på galgen/personen. \n" +
                "Spilleren har tabt når denne har brugt 7 forkerte gæt, uden ordet er gættet. \n" +
                "Spillet er vundet hvis hele ordet bliver gættet.");
    }
}
