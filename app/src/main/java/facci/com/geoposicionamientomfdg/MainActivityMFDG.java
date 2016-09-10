package facci.com.geoposicionamientomfdg;

import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.List;

public class MainActivityMFDG extends AppCompatActivity {

    LocationManager locManager;//administrar lista de proveedores
    // obtener las coordenada a travez del locationManager

    private double latitud;
    private double longitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_mfdg);
        //iniciar locManager
        locManager=(LocationManager) getSystemService(LOCATION_SERVICE);
        //METODO KE SE UTLIZA PARA EOL CONTROL DE LA INTERFAZ
        //OBTENER LA LISTA DE PROVEEDORES
        List<String> listProviders=locManager.getAllProviders();
        //Obtener el primer proveedor de la lista

        LocationProvider provider = locManager.getProvider(listProviders.get(0));//es especificamente un proveedor // el manager administra
    }
    public  void ActualizarLatLongClick(View v){

        if (ContextCompat.checkSelfPermission(
                this, android.Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED){

        }
        locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,2*60*1000,10,locationListenerGPS);
    }
    private final LocationListener locationListenerGPS =new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            longitud= location.getLongitude();
            latitud= location.getLatitude();

            double altitud=location.getAltitude();
            float velocidad= location.getSpeed();

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    EditText txtLongitud=(EditText)findViewById(R.id.txtLongitud);
                    EditText txtLatitud=(EditText)findViewById(R.id.txtLatitud);

                    txtLatitud.setText(latitud+"");
                    txtLongitud.setText(String.valueOf(longitud));
                }
            });
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

