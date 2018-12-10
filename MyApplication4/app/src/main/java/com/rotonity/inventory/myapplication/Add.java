
package com.rotonity.inventory.myapplication;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.File;

public class Add extends AppCompatActivity {

   // public static String result = "1";
    EditText description, cost, purchased;
    Spinner partType, currentlyWith;
    String barCode = "343434";
    private static String result;

    private static void updateResult(){ result=Global.db_result;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        description = findViewById(R.id.description);
        cost = findViewById(R.id.cost);
        purchased = findViewById(R.id.purchased);
        partType = findViewById(R.id.part_type);
        currentlyWith = findViewById(R.id.member);

        //  ArrayAdapter partType = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,)

        partType.getSelectedItem().toString();

    }

    public void selectImage(View view) {

        Toast.makeText(this, "noi", Toast.LENGTH_SHORT).show();
       /* final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};


        AlertDialog.Builder builder = new AlertDialog.Builder(Add.this);

        builder.setTitle("Add Photo!");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override

            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo"))

                {

                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");

                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));

                    startActivityForResult(intent, 1);

                } else if (options[item].equals("Choose from Gallery"))

                {

                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                    startActivityForResult(intent, 2);


                } else if (options[item].equals("Cancel")) {

                    dialog.dismiss();

                }

            }

        });

        builder.show();*/

    }


    public void StartScan(View view) {
        new IntentIntegrator(Add.this).setCaptureActivity(Scanner.class).initiateScan();
        //barCode = QRscanner.multiUsed;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        updateResult();
        //We will get scan results here
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        //check for null
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Scan Cancelled", Toast.LENGTH_LONG).show();
            } else {
                //show dialogue with result
                showResultDialogue(result.getContents());
            barCode=result.getContents();
            }
        } else {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    //method to construct dialogue with scan results
    public void showResultDialogue(final String result) {
     barCode=result.toLowerCase();
       /* AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle("Scan Result")
                .setMessage("Scanned result is " + result)
                .setPositiveButton("Copy result", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                        ClipData clip = ClipData.newPlainText("Scan Result", result);
                        clipboard.setPrimaryClip(clip);
                        Toast.makeText(Add.this, "Result copied to clipboard", Toast.LENGTH_SHORT).show();

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                        dialog.dismiss();
                    }
                })
                .show();*/
    }


    public void onClick(View view) {
        try {
            String type = partType.getSelectedItem().toString();
            String current = currentlyWith.getSelectedItem().toString();
            Background_worker background_worker = new Background_worker(this);
            background_worker.execute("add", type, description.getText().toString(), cost.getText().toString(), current, purchased.getText().toString(), barCode,"");

            //TODO:
            updateResult();

            Toast.makeText(this, result + " !", Toast.LENGTH_LONG).show();
            // background_worker.execute("add", "dadsad", "dasdasdasda", "5345345345", "sasasaas", "fefefrdge", "feef2222");
            //  background_worker.execute("add", " dsad", "dasdasdasda").toString();
        }catch (Exception e){
        Toast.makeText(this, result + " !"+e.getMessage(), Toast.LENGTH_LONG).show();
    }}

    public void checkCon(View view) {

    }
}