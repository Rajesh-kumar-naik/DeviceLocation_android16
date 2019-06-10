package com.example.iteradmin.devicelocation_android16

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val text=findViewById<TextView>(R.id.text1)
        val but=findViewById<Button>(R.id.but1)
        val fuse:FusedLocationProviderClient=LocationServices.getFusedLocationProviderClient(this)
        but.setOnClickListener{
            if (ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)
            == PackageManager.PERMISSION_GRANTED){
                fuse.lastLocation.addOnSuccessListener { location: Location? ->
                    if (location == null){
                        Toast.makeText(this,"Loaction not found",Toast.LENGTH_SHORT).show()
                    }else{
                        val s:String="Longitude:"+location.longitude+"\n Latitude:"+location.latitude
                        text1.text=s

                        Toast.makeText(this,"Loaction Found",Toast.LENGTH_SHORT).show()
                    }
                }

            }else{
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),100)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode==100){
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"Permission granted",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"Permission denied",Toast.LENGTH_SHORT).show()
            }
        }
    }
}
