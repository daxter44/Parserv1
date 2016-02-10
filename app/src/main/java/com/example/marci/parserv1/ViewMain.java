package com.example.marci.parserv1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewMain extends AppCompatActivity  {

    // Podstawowe wykorzystywane elementy
    private ArrayList<RSSItem> data;
    private ListView listViewParser;
    private AdapterListViewMain adapterListViewMain;

    final static String FILTER = "link";

    public void onClick(View view){

        Intent intent = new Intent(this, news.class);
        String link = data.get(3).getLink();
        intent.putExtra(FILTER, link);
        startActivity(intent);


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // Nasza tablica z danymi
        data = new ArrayList<RSSItem>();

        // Przypisujemy każdy element View do zmiennej
        listViewParser = (ListView) findViewById(R.id.listView_view_main_parser);

        // Tutaj łaczymy dane z ich wyświetlaniem
        adapterListViewMain = new AdapterListViewMain(this, data);
        listViewParser.setAdapter(adapterListViewMain);

        // I testujemy nasze pobieranie
        new RSSDownloader() {

            // Najprościej będzie wykorzystać jedną z funkcji AsyncTaska
            // onPostExecute wykonuje się po wykonaniu wszystkich czynności
            @Override
            protected void onPostExecute(ArrayList<RSSItem> result) {
                try {
                    // Dodajemy do naszej tablicy nowe dane
                    data.addAll(result);
                    adapterListViewMain.notifyDataSetChanged(); // Powiadamiamy o zmianach w "łączniku"

                    // Wyświetlamy komunikat o liczbie pobranych wiadomości
                    Toast.makeText(ViewMain.this,
                            "Pobrano " + result.size() + " wiadomości",
                            Toast.LENGTH_SHORT).show();

                } catch (Exception e){
                    Toast.makeText(ViewMain.this,
                            "Brak połączenia internetowego",
                            Toast.LENGTH_SHORT).show();

                }
            }

        }.execute("http://www.uni.opole.pl/rss.php"); // Adres kanału RSS, to jeszcze zmienimy w przyszłości
    }

    // Menu na ActionBare
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_view_main, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}