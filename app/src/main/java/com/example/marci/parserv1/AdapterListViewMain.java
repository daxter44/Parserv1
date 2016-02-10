package com.example.marci.parserv1;

/**
 * Created by marci on 01.02.2016.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterListViewMain extends BaseAdapter {

    // Podstawowe zmienne do wykorzystania
    private ArrayList<RSSItem> data;
    private Context listContext;
    private LayoutInflater layoutInflater;

    /*public void onClick(View view){
        // Uri uri = Uri.parse("http://google.pl");
        //Intent i = new Intent(view.getContext(), news.class );
        //listContext.startActivity(i);
        Intent i = new Intent(this, news.class );
        startActivity(i);
    }*/
    public AdapterListViewMain(Context context, ArrayList<RSSItem> data) {
        this.listContext = context;
        this.data = data;
        layoutInflater = LayoutInflater.from(listContext);
    }

    // Ta funkcja liczy nam ile elementów ma pojawić się na liście
    // Na testy dla tego przykładu damy ich 20
    public int getCount() {
        if (data != null) {
            return data.size();
        } else {
            return 0;
        }
    }




    // Pobranie danych dla jednego elementu
    // To zostawiamy puste
    public Object getItem(int position) {
        return position;
    }

    // Jak wyżej, tylko tutaj występuje identyfikator
    // To zostawiamy puste
    public long getItemId(int position) {
        return 0;
    }

    // Holder do cachowania elementów
    // Poprawia znacząco płynność
    private class CustomHolder {
        TextView tvTitle;
        TextView tvSite;
        TextView tvDescription;
        ImageView ivImage;
    }

    // Pojedynczy element na liście
    public View getView(int position, View convertView, ViewGroup parent) {

        CustomHolder viewCache;
        RSSItem actualItem = data.get(position);

        // ConvertView - czy da się wykorzystać ponownie ostatnio usunięty
        // element na liście?
        if (convertView == null) {
            // Jest pusty, więc definiujemy podstawę
            convertView = layoutInflater.inflate(
                    R.layout.item_view_main_listview, null);

            viewCache = new CustomHolder();

            // Cachujemy kolejne elementy
            viewCache.tvTitle = (TextView) convertView
                    .findViewById(R.id.textView_title_item_view_main_listview);
            viewCache.tvSite = (TextView) convertView
                    .findViewById(R.id.textView_site_item_view_main_listview);
            viewCache.tvDescription = (TextView) convertView
                    .findViewById(R.id.textView_description_item_view_main_listview);
            viewCache.ivImage = (ImageView) convertView
                    .findViewById(R.id.imageView_photo_item_view_main_listview);

            convertView.setTag(viewCache);
        } else {
            viewCache = (CustomHolder) convertView.getTag();
        }

        // Wypełnienie!
        viewCache.tvTitle.setText(actualItem.getTitle());
        viewCache.tvSite.setText(actualItem.getDescription());
        viewCache.tvDescription.setText(actualItem.getLink());

        return convertView;
    }

}
