package usuario.dontcrashmebro;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import usuario.negocio.SessaoUsuario;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {


    private boolean local = true;
    private GoogleMap mMap;
    private static final String TAG = "Erro no Mapa";
    private Marker marker;
    private SessaoUsuario sessao;
    double latitude;
    double longitude;
    LatLng latLng;

    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        }

        if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                     latitude = location.getLatitude();
                     longitude = location.getLongitude();
                     latLng = new LatLng(latitude, longitude);
                    Geocoder geocoder = new Geocoder(getApplicationContext());
                    try {
                        List<Address> addressList = geocoder.getFromLocation(latitude, longitude, 1);
                        String string = addressList.get(0).getLocality();
                        if(isLocal()){
                            mMap.moveCamera( CameraUpdateFactory.newLatLngZoom(latLng , 17.0f) );
                            setLocal(false);
                            customizadoAddMarker(latLng);
                        }
                    } catch (IOException e) {
                        Log.e(TAG,Log.getStackTraceString(e));
                    }
                }

                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {

                }

                @Override
                public void onProviderEnabled(String s) {

                }

                @Override
                public void onProviderDisabled(String s) {

                }
            });
        } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    LatLng latLng = new LatLng(latitude, longitude);
                    Geocoder geocoder = new Geocoder(getApplicationContext());
                    try {
                        List<Address> addressList = geocoder.getFromLocation(latitude, longitude, 1);
                        String string = addressList.get(0).getLocality();
                        if(isLocal()){
                            mMap.moveCamera( CameraUpdateFactory.newLatLngZoom(latLng , 17.0f) );
                            setLocal(false);
                        }
                    } catch (IOException e) {
                        Log.e(TAG,Log.getStackTraceString(e));
                    }
                }

                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {

                }

                @Override
                public void onProviderEnabled(String s) {

                }

                @Override
                public void onProviderDisabled(String s) {

                }

            });

        }
        sessao = new SessaoUsuario(getApplicationContext());
        sessao.iniciarSessao();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        mMap.setMinZoomPreference(13.0f);


        if(mMap != null){
            mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
                @Override
                public View getInfoWindow(Marker marker) {
                    return null;
                }

                @Override
                public View getInfoContents(Marker marker) {
                    View v = getLayoutInflater().inflate(R.layout.infowindows_maps,null);

                    TextView titulo = v.findViewById(R.id.tituloId);
                    TextView infos = v.findViewById(R.id.endereçoId);

                    titulo.setText(marker.getTitle());
                    infos.setText(marker.getSnippet());
                    return v;
                }
            });
        }

        mMap.setMyLocationEnabled(true);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            return;
        }

    }

    public void customizadoAddMarker(LatLng latLng){

        MarkerOptions options = new MarkerOptions();
        options.position(latLng).title("Recomendação")
                .snippet("Cuidado, possibilidade de chuva"+"\n"+"Condição climática: Parcialmente nublado"+"\n"+"Velocidade dos ventos: 19 km/h");

        this.marker = this.mMap.addMarker(options);
    }

    public boolean isLocal() {
        return local;
    }

    public void setLocal(boolean local) {
        this.local = local;
    }
}
