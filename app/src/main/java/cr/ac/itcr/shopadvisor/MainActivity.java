package cr.ac.itcr.shopadvisor;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onStop() {
        Log.d("Method", "onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("Method", "onDestroy");
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        Log.d("Method", "onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("Method", "onPause");
        super.onPause();
    }

    @Override
    protected void onStart() {
        Log.d("Method", "onStart");
        super.onStart();
    }

    @Override
    protected void onRestart() {
        Log.d("Method", "onRestart");
        super.onRestart();
    }


    public void aceptar() {
        Toast t=Toast.makeText(this,"Bienvenido a probar el programa.", Toast.LENGTH_SHORT);
        t.show();
    }

    public void cancelar() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Method", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        TextView lblTitle = (TextView) findViewById(R.id.lbltitle);
        lblTitle.setText("Hello TEC");

        final Button btn =
                (Button) findViewById(R.id.buttonPush);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                btn.setText("Successful");

                /*Simple
               /* AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                builder.setTitle("Importante");
                builder.setMessage("Este es un programa solo de prueba y no la versión completa");
                builder.setPositiveButton("OK",null);
                builder.create();
                builder.show();*/



                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(getApplicationContext());
                dialogo1.setTitle("Importante");
                dialogo1.setMessage("¿ Acepta la ejecución de este programa en modo prueba ?");
                dialogo1.setCancelable(false);
                dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        aceptar();
                    }
                });
                dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        cancelar();
                    }
                });
                dialogo1.show();




                Toast toastS = Toast.makeText(getApplicationContext(), "Mensaje corto",
                        Toast.LENGTH_SHORT);
                toastS.show();





                Toast toastL = Toast.makeText(getApplicationContext(), "Mensaje largo",
                        Toast.LENGTH_SHORT);
                toastL.show();
            }
        });
















        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent i = new Intent(MainActivity.this,DashboardTestActivity.class);
                i.putExtra("messaje", "data1");
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       /* if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }
}
