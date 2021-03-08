package examplesAll.qrCodeScanner;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.self.demoaall.R;
import com.self.demoaall.databinding.ActivityQrcodeScannerBinding;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import callBacksAll.PermissionCallBack;
import controllerAll.utilsAll.AppUtils;
import controllerAll.utilsAll.CheckPermissions;

public class QRCodeScanner extends CheckPermissions {
    private ActivityQrcodeScannerBinding viewBinding;
    private int OPEN_CAMERA = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = DataBindingUtil.setContentView(QRCodeScanner.this, R.layout.activity_qrcode_scanner);

        initView();
    }

    private void initView() {
        viewBinding.qrCodeImageScanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestAllPermissions(QRCodeScanner.this, Manifest.permission.CAMERA, getString(R.string.accessCamera), 1, new PermissionCallBack() {
                    @Override
                    public void getPermissionResults(Object... args) {
                            Log.e("runTimeString", "checkString: " + getString(R.string.accessCamera));
                            Intent intent = new Intent(QRCodeScanner.this, DecoderActivity.class);
                            startActivityForResult(intent, OPEN_CAMERA);
                    }
                });
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == OPEN_CAMERA) {
            if (resultCode == Activity.RESULT_OK)
                AppUtils.Companion.showToast(QRCodeScanner.this, "Code Scan Successfully");
            else if (resultCode == Activity.RESULT_CANCELED) {

            }
        }
    }
}
