package com.cosercon.cosercon;

import android.app.Activity;
import android.content.Context;
import com.loopj.android.http.*;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;


public class HomeScreen extends Activity {

    TextView textLat;
    TextView textLong;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        textLat = (TextView) findViewById(R.id.tvLatitude);
        textLong = (TextView) findViewById(R.id.tvLongitude);

        LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        LocationListener ll = new myLocationListener();
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,ll);
        lm.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER,0,0,ll);


    }

    String counter;
    Button click;
    TextView display;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_screen, menu);
        click = (Button) findViewById(R.id.bClick);
        display = (TextView) findViewById(R.id.tvDisplay);
        click.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                counter = "Hello World !";
                display.setText("I have SentData" + counter);
            }
        });
        return true;
    }

    @Override
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


    private class myLocationListener implements LocationListener {
        @Override
        public void onLocationChanged(Location location) {
            if (location != null){
                double pLong = location.getLongitude();
                double pLat = location.getLatitude();

                textLat.setText(Double.toString(pLat));
                textLong.setText(Double.toString(pLong));

                /*new MyAsyncTask().execute(Double.toString(pLat),Double.toString(pLong));*/

                Toast.makeText(getApplicationContext(), "Your Location is"+ pLong+";"+ pLat, Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {
            Toast.makeText(getApplicationContext(),"Gps Disabled",Toast.LENGTH_SHORT).show();
        }
    }

    private class MyAsyncTask extends AsyncTask<String, Integer, Double> {

        @Override
        protected Double doInBackground(String... params) {
            // TODO Auto-generated method stub
            postData(params[0],params[1]);
            return null;
        }

        protected void onPostExecute(Double result){

            Toast.makeText(getApplicationContext(), "command sent", Toast.LENGTH_LONG).show();
        }
        protected void onProgressUpdate(Integer... progress){

        }

        public void postData(String latitude , String longitude) {
            // Create a new HttpClient and Post Header
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://local.imoplaza.ro/SEPIA_development/public/gps");

            try {
                // Add your data
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("Latitude", latitude));
                nameValuePairs.add(new BasicNameValuePair("Longitude", longitude));
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                // Execute HTTP Post Request
                HttpResponse response = httpclient.execute(httppost);

            } catch (ClientProtocolException e) {
                // TODO Auto-generated catch block
            } catch (IOException e) {
                // TODO Auto-generated catch block
            }
        }

    }
   /* public void sendCoords(double lat, double longi ) throws UnsupportedEncodingException {
        BufferedReader reader =null;
        try{
            URL url = new URL("http://local.imoplatza.ro/SEPIA_development/public/gps");
            String data = Double.toString(lat) + Double.toString(longi);

            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();
        }catch (Exception e){
            e.printStackTrace();
        }

    }*/


}
