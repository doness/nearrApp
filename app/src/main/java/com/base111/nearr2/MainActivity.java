package com.base111.nearr2;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    //selected item variable stores string value of spinner item(locations)
    String selectedItem;

     String[] locations = {
            "Hospital","Bank","School",
            "Airport","Police Station",
            "Supermarket"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        LocationListener ll = new MyLocationListener();

        //check for permission
        if ( ContextCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {

            ActivityCompat.requestPermissions( this, new String[] {  android.Manifest.permission.ACCESS_COARSE_LOCATION  },
                    MyLocationListener.MY_PERMISSION_ACCESS_COARSE_LOCATION );
        }
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,ll);*/

        //spinner element
        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        //adapter for spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,locations);
        adapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(adapter);

        //cannot use set on ITEM click listener with spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                //selected item
                selectedItem = adapterView.getItemAtPosition(position).toString();



                //toast to show selected spinner item
                //Toast.makeText(adapterView.getContext(),"Finding "+item+"s near you..",
                       // Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}

        });// we now have 'selected item' variable

    }//end of on create method

    //use selected item from spinner to send toast
    public void locationBtn(View view){
        //display toast for more than 3 secs
        Toast.makeText(this,"Finding "+selectedItem+"s near you..",Toast.LENGTH_LONG).show();

        switch (selectedItem){
            case "Hospital":
                Intent hospital = new Intent(Intent.ACTION_VIEW);
                hospital.setData(Uri.parse("geo:6.5749,3.3918"));
                startActivity(hospital);
                break;
            case "Bank":
                Intent bank = new Intent(Intent.ACTION_VIEW);
                bank.setData(Uri.parse("geo:6.4980,3.3439"));
                startActivity(bank);
                break;

        }

        /*switch (locationPos){
            case 0:
                Intent hospital = new Intent(Intent.ACTION_VIEW);
                hospital.setData(Uri.parse("geo:6.5749,3.3918"));
                Intent chooser = Intent.createChooser(hospital,"Launch Map");
                startActivity(chooser);
        }*/

    }//end of location btn method


    //location listener inner class
   /* private class MyLocationListener implements LocationListener {
        @Override
        public void onLocationChanged(Location location){
            if (location != null){
                double lat = location.getLatitude();
                double lon = location.getLongitude();
            }
        }

        @Override
        public void onProviderDisabled(String provider){}
        @Override
        public void onProviderEnabled(String provider){}
        @Override
        public void onStatusChanged(String provider,int status,
                                    Bundle extras){}
    }//end of my location listener class
*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return true;
    }

    //define click events on menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.about:
                Intent about = new Intent(getApplicationContext(),AboutApp.class);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}//end of main activity class
