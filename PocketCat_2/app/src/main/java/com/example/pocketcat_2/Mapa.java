package com.example.pocketcat_2;

import android.os.Bundle;

import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.tileprovider.tilesource.XYTileSource;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Polyline;
import androidx.appcompat.app.AppCompatActivity;

public class Mapa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        Configuration.getInstance().load(getApplicationContext(), PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));
        // Obtiene la referencia al componente MapView del layout.
        MapView mapView = findViewById(R.id.mapView);
        // Establece la fuente de azulejos del mapa a MAPNIK (mapa estándar).
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        // Activa los controles de zoom en el mapa.
        mapView.setBuiltInZoomControls(true);
        // Activa el control multitáctil para el mapa (zoom con dos dedos).
        mapView.setMultiTouchControls(true);

        // Coordenadas del IP Santo Tomás, Chile.
        double ipSantoTomasLatitud = -33.4493141; // Latitud del IP Santo Tomás.
        double ipSantoTomasLongitud = -70.6624069; // Longitud del IP Santo Tomás.
        GeoPoint IPsantoTomasPoint = new GeoPoint(ipSantoTomasLatitud, ipSantoTomasLongitud);
        mapView.getController().setZoom(15.0);
        mapView.getController().setCenter(IPsantoTomasPoint);

        // Crear un marcador para el IP Santo Tomás. y Creamos un marcador en el mapa
        Marker marcadorSantoTomas = new Marker(mapView);
        marcadorSantoTomas.setPosition(IPsantoTomasPoint);
        marcadorSantoTomas.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        marcadorSantoTomas.setTitle("Ip Santo Tomas, Chile");
        marcadorSantoTomas.setSnippet("Un Instituto Tomista");
        mapView.getOverlays().add(marcadorSantoTomas);

        // Coordenadas del Italiano Veloz, Chile.
        double italianoVelozLatitud = -33.4519004;
        double italianoVelozLongitud = -70.6656575;
        GeoPoint italianoVelozPoint = new GeoPoint(italianoVelozLatitud, italianoVelozLongitud);
        mapView.getController().setZoom(15.0);
        mapView.getController().setCenter(italianoVelozPoint);

        // Crear un marcador para Italiano Veloz. y Creamos un marcador en el mapa
        Marker marcadoritalianoVeloz = new Marker(mapView);
        marcadoritalianoVeloz.setPosition(italianoVelozPoint);
        marcadoritalianoVeloz.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        marcadoritalianoVeloz.setTitle("Italiano Veloz, Chile");
        marcadoritalianoVeloz.setSnippet("Comida Rapida");
        mapView.getOverlays().add(marcadoritalianoVeloz);

        // Crear una línea que conecte los 2 Marcadores
        Polyline linea = new Polyline();
        linea.addPoint(IPsantoTomasPoint);
        linea.addPoint(italianoVelozPoint);
        linea.setColor(0xFF0000FF);
        linea.setWidth(5);
        // Añadir la línea al mapa.
        mapView.getOverlayManager().add(linea);

        // Configurar el Spinner para cambiar el tipo de mapa y Obtiene la referencia al componente Spinner del id del xml.
        Spinner mapTypeSpinner = findViewById(R.id.mapTypeSpinner);
        // Define un array con los tipos de mapas.
        String[] mapTypes = {"Mapa Normal", "Mapa de Transporte", "Mapa Topografico"};

        // Crear un ArrayAdapter para poblar el Spinner con los tipos de mapas.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mapTypes);
        // Establece el layout para los elementos del Spinner.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Asigna el adaptador al Spinner.
        mapTypeSpinner.setAdapter(adapter);

        // Listener para detectar cambios en la selección del Spinner.
        mapTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {   switch (position) {
                case 0:
                    mapView.setTileSource(TileSourceFactory.MAPNIK);
                    break;
                case 1:
                    mapView.setTileSource(new XYTileSource(
                            "PublicTransport",
                            0, 18, 256, ".png", new String[]{
                            "https://tile.memomaps.de/tilegen/"}));
                    break;
                case 2:
                    mapView.setTileSource(new XYTileSource(
                            "USGS_Satellite", 0, 18, 256, ".png", new String[]{
                            "https://a.tile.opentopomap.org/",
                            "https://b.tile.opentopomap.org/",
                            "https://c.tile.opentopomap.org/"}));
                    break;
                }
            }
            // No se hace nada cuando no se selecciona ningún elemento.
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}