package examplesAll.qrCodeScanner;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PointF;
import android.util.Log;
import android.widget.CompoundButton;

import com.dlazaro66.qrcodereaderview.QRCodeReaderView;
import com.self.demoaall.R;
import com.self.demoaall.databinding.ActivityDecoderBinding;

import controllerAll.utilsAll.BaseActivityJava;

public class DecoderActivity extends BaseActivityJava
        implements QRCodeReaderView.OnQRCodeReadListener {

    private QRCodeReaderView qrCodeReaderView;
    private PointsOverlayView pointsOverlayView;
    private ActivityDecoderBinding viewBinding;

    @Override
    protected int getLayoutById() {
        return R.layout.activity_decoder;
    }

    @Override
    protected void initView() {
        viewBinding = (ActivityDecoderBinding) viewDataBinding;

        qrCodeReaderView.setAutofocusInterval(2000L);
        qrCodeReaderView.setOnQRCodeReadListener(this);
        qrCodeReaderView.setBackCamera();

        viewBinding.flashlightCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                qrCodeReaderView.setTorchEnabled(isChecked);
            }
        });

        viewBinding.enableDecodingCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                qrCodeReaderView.setQRDecodingEnabled(isChecked);
            }
        });

        qrCodeReaderView.startCamera();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (qrCodeReaderView != null) {
            qrCodeReaderView.startCamera();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (qrCodeReaderView != null) {
            qrCodeReaderView.stopCamera();
        }
    }


    // Called when a QR is decoded
    // "text" : the text encoded in QR
    // "points" : points where QR control points are placed
    @Override
    public void onQRCodeRead(String text, PointF[] points) {
        viewBinding.resultTextView.setText(text);
        pointsOverlayView.setPoints(points);
        Log.e("DecoderActivity", "text: " + text);
        Intent intent = new Intent();
        intent.putExtra("text", text);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

}
