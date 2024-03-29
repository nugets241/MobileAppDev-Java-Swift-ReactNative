package com.example.geoqrnavigator;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.Task;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

// This is the main activity for the application.
public class MainActivity extends AppCompatActivity {

    // These are the request codes used for permission requests and activity results.
    private static final int REQUEST_LOCATION_PERMISSION = 1;

    // This is the client used to get the device's current location.
    private FusedLocationProviderClient fusedLocationClient;

    // This is the current location of the device.
    private Location currentLocation;

    // These TextViews display the QR code content, current location, and distance.
    private TextView tvQrCode;
    private TextView tvCurrentLocation;
    private TextView tvDistance;

    // These variables save the QR code content, current location, and distance
    private String qrCodeLocation;
    private String cLocation;
    private String distance;

    // This method is called when the activity is created. It initializes the TextViews and FusedLocationProviderClient,
    // and checks if location permissions are granted. If not, it requests them. If they are, it gets the location.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the TextViews and FusedLocationProviderClient.
        tvQrCode = findViewById(R.id.tv_qr_code);
        tvCurrentLocation = findViewById(R.id.tv_current_location);
        tvDistance = findViewById(R.id.tv_distance);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        // Check if location permissions are granted. If not, request them.
        System.out.println("About to check coarse and fine location permissions");
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            System.out.println("Coarse and fine location permissions not granted!");
            // If permissions are not granted, request them.
            String[] permissions = {
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
            };
            ActivityCompat.requestPermissions(this, permissions, REQUEST_LOCATION_PERMISSION);
        } else {
            // If permissions are granted, get the location and start the QR code scan.
            System.out.println("Permission check succeeded");
            getLocation();
        }

        // Set an OnClickListener for the scan button to start the QR code scan.
        findViewById(R.id.btn_scan).setOnClickListener(v -> {
            // Check again if location permissions are granted.
            System.out.println("About to check coarse and fine location permissions");
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                System.out.println("Coarse and fine location permissions not granted!");
                // If permissions are not granted, request them.
                String[] permissions = {
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION
                };
                ActivityCompat.requestPermissions(this, permissions, REQUEST_LOCATION_PERMISSION);
            } else {
                // If permissions are granted, get the location and start the QR code scan.
                System.out.println("Permission check succeeded");
                getLocation();
                startQrCodeScan();
            }
        });
    }

    // This method saves the state of the activity when it's stopped. It saves the QR code content, current location, and distance.
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("QR_CODE_LOCATION", qrCodeLocation);
        outState.putString("CURRENT_LOCATION", cLocation);
        outState.putString("DISTANCE", distance);
    }

    // This method restores the state of the activity when it's restarted. It restores the QR code content, current location, and distance.
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        qrCodeLocation = savedInstanceState.getString("QR_CODE_LOCATION","Lat: --\nLon: --");
        cLocation = savedInstanceState.getString("CURRENT_LOCATION", "Lat: --\nLon: --");
        distance = savedInstanceState.getString("DISTANCE", "-- km");

        tvQrCode.setText(qrCodeLocation);
        tvCurrentLocation.setText(cLocation);
        tvDistance.setText(distance);
    }


    // This method gets the device's current location. It checks if location permissions are granted. If not, it returns.
    // If they are, it gets the last known location of the device and updates the currentLocation variable and TextView.
    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Permissions are not granted
            return;
        }
        Task<Location> locationTask = fusedLocationClient.getLastLocation();
        locationTask.addOnSuccessListener(location -> {
            if (location != null) {
                currentLocation = location;
                cLocation = "Lat: " + location.getLatitude() + "\n Lon: " + location.getLongitude();
                tvCurrentLocation.setText(cLocation);
            } else {
                Toast.makeText(this, "Unable to get current location", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // This method starts the QR code scan. It sets up ScanOptions and launches a new ScanContract with these options.
    private void startQrCodeScan() {
        ScanOptions options = new ScanOptions();
        options.setPrompt("Scan a QR Code");
        options.setBeepEnabled(true);
        options.setOrientationLocked(false);
        options.setCaptureActivity(ScanActivity.class);
        barLauncher.launch(options);
    }

    // This method handles the result of the QR code scan. If there's no result, it shows a toast message.
    // If there is a result, it shows an AlertDialog with the result and calls handleQrCodeResult() with this result.
    ActivityResultLauncher<ScanOptions> barLauncher = registerForActivityResult(new ScanContract(), result -> {
        if (result.getContents() == null) {
            Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Result");
            builder.setMessage(result.getContents());
            builder.setPositiveButton("OK", (dialogInterface, i) -> dialogInterface.dismiss()).show();
            handleQrCodeResult(result.getContents());
        }
    });


    // This method handles the QR code result. It parses a geo: URI from the QR code,
    // updates the qrCodeLocation variable and TextView, calculates the distance to this location,
    // and updates the distance variable and TextView.
    private void handleQrCodeResult(String qrCode) {
        String[] parts = qrCode.split(":");
        if (parts.length == 2 && parts[0].equals("geo")) {
            String[] coordinates = parts[1].split(",");
            if (coordinates.length == 2) {
                try {
                    double lat = Double.parseDouble(coordinates[0]);
                    double lon = Double.parseDouble(coordinates[1]);

                    qrCodeLocation = "Lat: " + lat + "\n Lon: " + lon;
                    tvQrCode.setText(qrCodeLocation);

                    Location qrLocation = new Location("");
                    qrLocation.setLatitude(lat);
                    qrLocation.setLongitude(lon);

                    float distanceInMeters = currentLocation.distanceTo(qrLocation);
                    float distanceInKilometers = distanceInMeters / 1000;

                    distance = distanceInKilometers + " km";
                    tvDistance.setText(distance);
                } catch (NumberFormatException e) {
                    Toast.makeText(this, "Invalid geo: URI", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    // This method handles the result of the location permission request. If permission was granted,
    // it gets the location. If permission was denied, it shows a toast message.
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        System.out.println("onRequestPermissionsResult: requestCode = " + requestCode);
        for (int i = 0; i < permissions.length; i++){
            System.out.println(permissions[i] + " : " + grantResults[i]);
        }

        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // If permission was granted, get the location and start the QR code scan.
                System.out.println("Permission granted! Getting location and starting QR Code scan...");
                getLocation();
            } else {
                // If permission was denied, show a toast.
                System.out.println("Permission denied. The app needs location permission to function properly.");
                Toast.makeText(this, "Permission denied. The app needs location permission to function properly.", Toast.LENGTH_LONG).show();
            }
        }
    }
}
