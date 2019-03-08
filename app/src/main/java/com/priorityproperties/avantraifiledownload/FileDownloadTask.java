package com.priorityproperties.avantraifiledownload;

import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileDownloadTask extends AsyncTask<Void,Void,String> {


    ResponseListner listner;
    String pageNumber;
     static   String baseUrl="http://www.mso.anu.edu.au/~ralph/OPTED/v003/";


    public FileDownloadTask(ResponseListner listner,String pageNumber) {

        this.pageNumber=pageNumber;
        this.listner = listner;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        listner.onRespose(s);
    }

    @Override
    protected String doInBackground(Void... voids) {

        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(baseUrl+pageNumber);
        String html = "";
        try {
            HttpResponse response = client.execute(request);
            InputStream in;
            in = response.getEntity().getContent();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(in));
            StringBuilder str = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                str.append(line);
            }
            in.close();
            html = str.toString();
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return html;


    }
}
