package com.example.marci.parserv1;


import android.os.AsyncTask;

import java.net.URL;
import java.util.ArrayList;

public class RSSDownloader extends AsyncTask<String, Void, ArrayList<RSSItem>> {

    // Prosty AsyncTask, który czyta nam dane
    @Override
    protected ArrayList<RSSItem> doInBackground(String... params) {

        try {
            URL url = new URL(params[0]);
            ArrayList<RSSItem> rssItems = RSSReader.startReader(url);
            return rssItems;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}