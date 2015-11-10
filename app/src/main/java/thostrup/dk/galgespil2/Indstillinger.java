package thostrup.dk.galgespil2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Indstillinger extends AppCompatActivity {

    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indstillinger);

        list = (ListView) findViewById(R.id.listView);

        list.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,  android.R.id.text1, StartScreen.game.getMuligeOrd()));
    }
}
