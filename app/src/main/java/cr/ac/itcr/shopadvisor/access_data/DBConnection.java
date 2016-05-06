package cr.ac.itcr.shopadvisor.access_data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Mendez Soto on 3/30/2016.
 */
public class DBConnection extends SQLiteOpenHelper {
    private static final int VERSION_BOD = 1;
    private static final String NAME_BOD = "shopadvisor";


    public DBConnection(Context context){
        super(context,NAME_BOD,null,VERSION_BOD);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            StringBuilder sql = new StringBuilder();
            //Create
            String sqlCreatePlace = "CREATE TABLE IF NOT EXISTS place (id INTEGER PRIMARY KEY AUTO INCREMENT, name CHAR(200))";
            sql.append(sqlCreatePlace);
            db.execSQL(sql.toString());
        }catch(Exception error){
            Log.d("Error",error.getMessage().toString());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        StringBuilder sql = new StringBuilder();
        for(int indiceVersion = oldVersion; indiceVersion < newVersion; indiceVersion++){
            int nextVersion = indiceVersion +1;
            switch (nextVersion){
                case 1:
                    String sqlDropPlace = "DROP TABLE IF EXISTS place";
                    sql.append(sqlDropPlace);
                    break;
                case 2:
                    String sqlCreatePlace= "CREATE TABLE IF NOT EXISTS users"
                            + "(id INTEGER PRIMARY KEY AUTOINCREMENT,name CHAR(100))";
                    sql.append(sqlCreatePlace);
                    break;
                case 3:
                    String sqlCreateAdvisor = "DROP TABLE IF NOT EXISTS advisor"
                            +"(id INTEGER PRIMAR KEY AUTOINCREMENT, name CHAR(100))";
                    sql.append(sqlCreateAdvisor);
                    break;
            }
        }
    }
}
