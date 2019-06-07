package com.rotonity.inventory.inventoryapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class Scanner extends AppCompatActivity {
        //qr code scanner object
    private IntentIntegrator qrScan;
    public static String barcode;
    TextView textView;
    String type;
    String operation;
    AlertDialog.Builder builder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);
        Intent in = getIntent();
        builder=new AlertDialog.Builder(this);
        Bundle b = in.getExtras();
        try {
            operation = b.getString("type");
        }catch (NullPointerException e){
            operation="scan";}
            qrScan = new IntentIntegrator(this);
        qrScan.setCameraId(0);
        qrScan.setBarcodeImageEnabled(true);
        qrScan.setOrientationLocked(true);
        qrScan.initiateScan();


    }


    //Getting the scan results
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if(operation.equals("Add")){
                barcode=result.getContents().toString();
                finish();
            }
            else {
                barcode=result.getContents().toString();
                DBHelper helper=new DBHelper(getApplicationContext());
                InventoryItem item= helper.getItem(barcode);
                if(item.getDescription().equals("")){
                    builder.setMessage("No item found in Inventory").setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
                    AlertDialog alertDialog=builder.create();
                    alertDialog.setTitle("Not Found");
                    alertDialog.show();
                    finish();
                }
                Intent intent = new Intent(Scanner.this,ViewPage.class);
                intent.putExtra("item",item);
                startActivity(intent);
            }


    }
    }
    @Override
    public void onResume() {
        super.onResume();

        // Register ourselves as a handler for scan results.
    }

    @Override
    public void onPause() {
        super.onPause();
        // Stop camera on pause
       // mScannerView.stopCamera();
        try{qrScan.wait();}catch (Exception e){}
    }


}
