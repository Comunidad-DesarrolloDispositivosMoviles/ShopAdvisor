package cr.ac.itcr.shopadvisor.access_data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import cr.ac.itcr.shopadvisor.entity.Place;

/**
 * Created by Mendez Soto on 3/30/2016.
 */
public class PlaceRepository implements IRepositroy<Place> {
    private DBConnection connexion;

    public PlaceRepository(Context context){
        connexion = new DBConnection(context);
    }



    @Override
    public boolean Save(Place place) {
        try{
            SQLiteDatabase db = connexion.getWritableDatabase();
            if(db != null){
                ContentValues newData = new ContentValues();
                newData.put("id",place.getId());
                newData.put("name", place.getName());
                db.insert("place", null, newData);
                connexion.close();
                return false;//Sin errores
            }
        }catch(Exception error){
            Log.d("Error",error.getMessage());
        }
        return true;
    }

    @Override
    public boolean Update(Place place) {
        try{
            SQLiteDatabase db = connexion.getWritableDatabase();
            if(db != null){
                ContentValues updateData = new ContentValues();
                updateData.put("name",place.getName());

                db.update("place", updateData, "id=?", new String[]{String.valueOf(place.getId())});
                connexion.close();
                return false;//Sin errores
            }
        }catch(Exception error){
            Log.d("Error",error.getMessage());
        }
        return true;
    }

    @Override
    public boolean Delete(Place place) {
        try{
            SQLiteDatabase db = connexion.getWritableDatabase();
            if(db != null){
                String[] args = new String[]{String.valueOf(place.getId())};
                db.delete("place","id=?",args);

                connexion.close();
                return false;//Sin errores
            }
        }catch(Exception error){
            Log.d("Error",error.getMessage());
        }
        return true;
    }

    @Override
    public ArrayList<Place> GetAll() {
        ArrayList<Place> listPlace = new ArrayList<Place>();
        try{
            SQLiteDatabase db = connexion.getWritableDatabase();
            if(db!=null){
                Cursor c = db.query("place",new String[]{"id","name"},
                        null,null,null,null, "id desc",null);
                if(c.moveToFirst()){
                    do{
                        int id = c.getInt(0);
                        String nombre = c.getString(1);

                        Place place = new Place();
                        place.setId(id);
                        place.setName(nombre);

                        listPlace.add(place);
                    }while(c.moveToNext());
                }
                connexion.close();
                return listPlace;
            }
        }catch (Exception error){
            Log.d("Error",error.getMessage());
        }
        return listPlace;
    }

    @Override
    public ArrayList<Place> GetBy(Place place) {
        ArrayList<Place> listPlace = new ArrayList<Place>();
        try{
            SQLiteDatabase db = connexion.getWritableDatabase();
            if(db!=null){
                String[] args = new String[]{String.valueOf(place.getId())};
                Cursor c = db.query("place",new String[]{"id","name"},
                        "id=?",args,null,null, "id desc",null);
                if(c.moveToFirst()){
                    do{
                        int id = c.getInt(0);
                        String nombre = c.getString(1);

                        Place tempPlace = new Place();
                        tempPlace.setId(id);
                        tempPlace.setName(nombre);

                        listPlace.add(tempPlace);
                    }while(c.moveToNext());
                }
                connexion.close();
                return listPlace;
            }
        }catch (Exception error){
            Log.d("Error",error.getMessage());
        }
        return listPlace;
    }
}
