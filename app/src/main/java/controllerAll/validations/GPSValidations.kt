package controllerAll.validations

import android.content.Context
import android.location.LocationManager


class GPSValidations {
    companion object{
        fun isGPSStatusAvailable(context: Context): Boolean {
            val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            var gpsEnabled = false
            var networkEnabled = false
            var isGPSAvailable = false
            try {
                gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
            } catch (ex: Exception) {
            }
            try {
                networkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
            } catch (ex: Exception) {
            }
            if (gpsEnabled && networkEnabled)
                isGPSAvailable = true
            return false
        }

    }
}