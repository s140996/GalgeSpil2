package thostrup.dk.galgespil2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Indstillinger extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView list;
    private ArrayList<String> ord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indstillinger);

        list = (ListView) findViewById(R.id.listView);

        ord = StartScreen.game.getMuligeOrd();

        Collections.sort(ord, String.CASE_INSENSITIVE_ORDER);

        list.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, ord));

        list.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(), "Valgt ord: " + list.getItemAtPosition(position), Toast.LENGTH_LONG).show();
    }
}
