package controllerAll.utilsAll;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;

import com.self.demoaall.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import callBacksAll.PermissionCallBack;

public abstract class BaseActivityJava extends AppCompatActivity {
    public ViewDataBinding viewDataBinding;
    private Context context;
    private int permisssionCode,  current_api_Version = android.os.Build.VERSION.SDK_INT;
    private PermissionCallBack permissionListener;
    private String permissionType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewDataBinding = DataBindingUtil.setContentView(BaseActivityJava.this, getLayoutById());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setNavigationBarColor(ContextCompat.getColor(BaseActivityJava.this, R.color.colorPrimary));
            getWindow().setStatusBarColor(ContextCompat.getColor(BaseActivityJava.this, R.color.colorPrimary));
        }
    }

    protected abstract int getLayoutById();

    protected abstract void initView();

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
    public void onRequestPermissionsResult(int requestCode,String[] permissions, int[] grantResults) {
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
