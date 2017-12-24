package com.example.aladin_bureau.kademiatn;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.app.AlertDialog;
import android.widget.Button;

import com.google.zxing.Result;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class qrscannerActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;
     Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscanner);
        btn=(Button)findViewById(R.id.qrbtn);
        btn.callOnClick();
    }

    public void QrScanner(View view){


        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);

        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();         // Start camera

    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(final Result rawResult) {
        // Do something with the result here

        Log.e("handler", rawResult.getText()); // Prints scan results
        Log.e("handler", rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode)

        // show the scanner result into dialog box.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Scan Result");
        builder.setMessage(rawResult.getText());

        builder.setPositiveButton("ouvrir le lien", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent extrait=new Intent(getApplicationContext(),MenyNavigation.class);
                extrait.putExtra("qrlink",rawResult.getText().toString());
                startActivity(extrait);


            }
        });
        AlertDialog alert1 = builder.create();

        alert1.show();
        builder.show();

        // If you would like to resume scanning, call this method below:
        // mScannerView.resumeCameraPreview(this);
    }
}
