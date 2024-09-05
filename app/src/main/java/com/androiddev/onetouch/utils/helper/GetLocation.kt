package com.androiddev.onetouch.utils.helper

import android.app.Activity
import android.content.pm.PackageManager
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class GetLocation {

    var locationData: HashMap<String, String> = HashMap()
    fun getlongitude(activity: Activity): HashMap<String, String> {

        val fusedLocationClient: FusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(activity)

        if (ActivityCompat.checkSelfPermission(
                activity,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                activity,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                100
            )
            return locationData
        } else {
            fusedLocationClient.lastLocation.addOnCompleteListener(activity) { task ->
                if (task.isSuccessful && task.result != null) {
                    val location = task.result
                    val latitude = (location)?.latitude.toString()
                    val longitude = (location)?.longitude.toString()
                    locationData.put("latitude", latitude)
                    locationData.put("longitude", longitude)

                    Log.d("MYTAG", "getLocations: " + latitude + " " + longitude)
                    Toast.makeText(
                        activity,
                        "latitude" + latitude + "\n" + "longitude " + longitude,
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Log.w("MYTAG", "getLastLocation:exception", task.exception)
                }
            }
            return locationData
        }
    }


/*
    fun getLatitude(activity: Activity): String {
        var latitude: String = ""

        val fusedLocationClient: FusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(activity)

        if (ActivityCompat.checkSelfPermission(
                activity,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                activity,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                100
            )
            Toast.makeText(activity, "Permission Denied", Toast.LENGTH_SHORT).show()
        }
        fusedLocationClient.lastLocation
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful && task.result != null) {
                    val mLastLocation = task.result
                    latitude = (mLastLocation).latitude.toString()
                    latitude = latitude
                    Log.d("MYTAG", "getLocations: " + latitude + " " + longitude)
                } else {
                    Log.w("MYTAG", "getLastLocation:exception", task.exception)
                }
            }
        return latitude
    }
*/

}