package cr.ac.itcr.shopadvisor;

import android.app.AlertDialog;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.jar.Manifest;

public class MapsActivity extends FragmentActivity
        implements OnMapReadyCallback, View.OnClickListener {

    private GoogleMap mMap;
    private Button bmapa;
    private Button bterreno;
    private Button bhibrido;
    private Button binterior;

    private Button datos;
    private TextView latidud;
    private TextView longitud;

    private Location location;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private AlertDialog alert = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        bmapa = (Button) findViewById(R.id.bmapa);
        bterreno = (Button)findViewById(R.id.bterreno);
        bhibrido = (Button)findViewById(R.id.bhibrido);
        binterior = (Button)findViewById(R.id.binterior);

        bmapa.setOnClickListener(this);
        bterreno.setOnClickListener(this);
        bhibrido.setOnClickListener(this);
        binterior.setOnClickListener(this);

        //El pedacito de mapa del fragment tiene que ser asyncronico
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        latidud = (TextView) findViewById(R.id.latitud);
        longitud = (TextView) findViewById(R.id.longitud);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            //AlertGPS();
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) !=
            PackageManager.PERMISSION_GRANTED && checkSelfPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED){
                return;
            }
            else{
                location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            }
        }
        else {
            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }

        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        //String mejor= locationManager.getBestProvider();
        MostrarLocalizacion(location);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                MostrarLocalizacion(location);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

    }

    public void MostrarLocalizacion(Location l){
        if (l!=null){
            latidud.setText(l.getLatitude() + "");
            latidud.setText(l.getLongitude() + "");
            LatLng tec = new LatLng(l.getLatitude(), l.getLongitude());

//            if (nMap!=null)
//                nMap.addMarker(new MarkerOptions().position(tec).title("Marcas"));
        }

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    public boolean marcadorInicial = true;
    Marker markerActual;
    Marker markerAnterior;
    String textoMarcador = "Inicio";
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng tec = new LatLng(10.3652250,-84.509969);
        mMap.addMarker(new MarkerOptions().position(tec).title("Marker in TEC"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tec, 19));
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                MarkerOptions markerOptions = new MarkerOptions()
//                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_shopadvisor3))
//                        .icon(BitmapDescriptorFactory.defaultMarker())
//                        .anchor(0.0f, 1.0f)
                        .title(textoMarcador)
                        .position(latLng);
//                mMap.addMarker(markerOptions);
                markerActual = mMap.addMarker(markerOptions);
                if (marcadorInicial == false){
                    markerAnterior.remove();
                    Log.d("marcador inicial", ( String.valueOf(marcadorInicial) ) );
                }
                if (markerAnterior != null){
                    PolylineOptions options = new PolylineOptions()
                            .add(markerActual.getPosition())
                            .add(markerAnterior.getPosition());
                    mMap.addPolyline(options);
                    marcadorInicial = false;
                    textoMarcador = "Final";
                }
                markerAnterior = markerActual;
            }
        });

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Toast.makeText(getApplicationContext(),"has pulsado una marca",Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }



    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.bmapa:
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                break;
            case R.id.bhibrido:
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                break;
            case R.id.bterreno:
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                break;
            case R.id.binterior:
                //Algunos edificio tienen mapa de interior, hay que ponerse sobre ellos y dire
                //Lets see the map from the interior with those coordinates
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                        new LatLng(-33.86997,151.2089),18));
                break;
            default:
                break;
        }
    }
}
