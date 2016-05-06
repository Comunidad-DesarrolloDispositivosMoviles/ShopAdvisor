package cr.ac.itcr.shopadvisor.api;

import android.os.AsyncTask;
import android.util.StringBuilderPrinter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import cr.ac.itcr.shopadvisor.entity.Place;

/**
 * Created by car_e on 5/4/2016.
 */
public class PlaceApi implements IApi<Place>{
    @Override
    public boolean Save(Place o) {
        return false;
    }

    @Override
    public boolean Update(Place o) {
        return false;
    }

    @Override
    public boolean Delete(Place o) {
        return false;
    }

    @Override
    public ArrayList<Place> GetAll() {
        return null;
    }

    public class PlaceAipGet extends AsyncTask<String,Void,List<Place>>{

        @Override
        protected List<Place> doInBackground(String... params) {
            String cadena = params[0];
            URL url = null;
            String devolver = "";
            ArrayList<Place> places = new ArrayList<Place>();
            try {
                if (params[1] == ConstantApi.get){
                    url = new URL(cadena);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestProperty("Content-Type", "application/json");
                    connection.setRequestProperty("accept", "application/json");
                    int respuesta = connection.getResponseCode();
                    StringBuilder result = new StringBuilder();
                    if (respuesta == HttpURLConnection.HTTP_OK){
                        InputStream in = new BufferedInputStream(connection.getInputStream());
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                        String line;
                        while ((line = reader.readLine()) != null){
                            result.append(line);
                        }

                        JSONObject respuestaJSON = new JSONObject(result.toString());
                        for (int i=0; i<respuestaJSON.length(); i++){
                            Place place = new Place();
                            place.setId(respuestaJSON.getInt("id"));
                            place.setId(respuestaJSON.getInt("id"));

                        }
                    }
                }
            } catch (MalformedURLException e){
                e.printStackTrace();
            } catch (IOException e){
                e.printStackTrace();
            } catch (JSONException e){
                e.printStackTrace();
            }
            return places;
        }

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate (Void... values) {super.onProgressUpdate(values);}
    }

}
