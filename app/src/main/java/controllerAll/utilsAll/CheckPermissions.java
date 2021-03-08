package controllerAll.utilsAll;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import com.self.demoaall.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import callBacksAll.PermissionCallBack;

public  class CheckPermissions extends AppCompatActivity {
    private Context context;
    private int permisssionCode,  current_api_Version = android.os.Build.VERSION.SDK_INT;
    private PermissionCallBack permissionListener;
    private String permissionType;

    public boolean requestAllPermissions(Context context, String permission, String permissionType, int permisssionCode, PermissionCallBack permissionListener) {
        this.context = context;
        this.permisssionCode = permisssionCode;
        this.permissionType = permissionType;
        this.permissionListener = permissionListener;
        if (current_api_Version >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            if (isAllowed(permission)) {
                this.permissionListener.getPermissionResults(permissionType);
            } else {
                requestPermission(permission);
            }
        } else {
            this.permissionListener.getPermissionResults(permissionType);
        }
        return false;
    }

    private boolean isAllowed(String permission) {
        //  Getting the permission status
        int result = ContextCompat.checkSelfPermission(context, permission);
        //If permission is granted returning true
        if (result == PackageManager.PERMISSION_GRANTED)
            return true;
        //If permission is not granted returning false
        return false;
    }

    //Requesting permission
    private void requestPermission(String permission) {
        if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, permission)) {
        }
        ActivityCompat.requestPermissions((Activity) context, new String[]{permission}, permisssionCode);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        //Checking the request code of our request
        if (requestCode == permisssionCode) {
            //If permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                permissionListener.getPermissionResults(permissionType);
            }else {
                permissionListener.getPermissionResults(getString(R.string.deny_runtime_permission));
            }
        }
    }
}
