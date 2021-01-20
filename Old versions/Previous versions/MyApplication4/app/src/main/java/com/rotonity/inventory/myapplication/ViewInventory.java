package com.rotonity.inventory.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ViewInventory extends AppCompatActivity {
    public static TextView t12;
    //public static String getData;
    Spinner part_type, currently_with;
    String[] parts = {"1", "2"};
    RecyclerView dataList;
    Background_worker background_worker;
    //ProgressDialog dialog = new ProgressDialog(this);
    static String barCode = "68";
   // Thread te;
    public static String rawData = "connection success: motor, description1, more, four, fixe, 2525421515: motor, description1, more, four, fixe, 66: motor, description1, more, four, fixe, six: motor, description1, more, four, fixe, six: motor, description1, more, four, fixe, six: motor, description1, more, four, fixe, six: motor, description1, more, four, fixe, six";

    public String[] getRawData(String rawData)

    {
       /* Background_worker background_worker = new Background_worker(this);
        background_worker.execute("getList");
*/
        String[] proccessed = rawData.split(": ");
        List<String> list = new ArrayList<String>(Arrays.asList(proccessed));
        list.remove(proccessed[0]);
        proccessed = list.toArray(new String[0]);

        return proccessed;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
          ///  te= new Thread();
        setContentView(R.layout.activity_view_inventory);

        part_type = (findViewById(R.id.part_type));

        dataList = findViewById(R.id.dataList);
        //dataList= new RecyclerView(this);

        //background_worker=new Background_worker()// TODO: DOnt unCOmment this
        /*dialog.setMessage("message");
        dialog.setCancelable(false);
        dialog.setInverseBackgroundForced(false);
*/
        //t12 = findViewById(R.id.textView12);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //We will get scan results here
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        //check for null
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Scan Cancelled", Toast.LENGTH_LONG).show();
            } else {
                barCode = result.getContents();


                   update();
                updateDataSet();
                update();
                updateDataSet();


                //show dialogue with result
                //showResultDialogue(result.getContents());
            }
        } else {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void onRefresh(View view)

    {
      //  dialog.show();
        try {
            background_worker = new Background_worker(this);
            background_worker.execute("view", "", "", "", "", "", "", "all");
            rawData=Global.db_result;}
        catch (Exception e){
            Toast.makeText(this,e.getMessage()+"66666",Toast.LENGTH_LONG).show();}

       // while(background_worker.getStatus().toString().equals("RUNNING")){Toast.makeText(this,"running",Toast.LENGTH_SHORT).show();}
        updateDataSet();


    }

    public void Scan(View view) {


        IntentIntegrator intentIntegrator = new IntentIntegrator(ViewInventory.this);
        intentIntegrator.setCaptureActivity(Scanner.class).initiateScan();
        /* intentIntegrator.notifyAll();
         */
    }

    public void update() {
       // t12.setText(barCode);
        try {
            background_worker = new Background_worker(this);
            background_worker.execute("view", "", "", "", "", "", barCode, "id");
            rawData=Global.db_result;}
            catch (Exception e){
                Toast.makeText(this,e.getMessage()+"/////Exception in BG_worker",Toast.LENGTH_LONG).show();}
                updateDataSet();
    }

    public void status(View view){
        Toast.makeText(this,background_worker.getStatus().toString(),Toast.LENGTH_LONG).show();
    }
    public void cancel(View view){
        background_worker.cancel(true);
        Toast.makeText(this,"isCANNED",Toast.LENGTH_LONG).show();
    }

    private void updateDataSet(){
        try {
            // background_worker.execute("view","","","","","","","all");
            ///rawData=Global.db_result;
            dataList = findViewById(R.id.dataList);
            String[] data = getRawData(rawData);
            //t12.setText(data[2]);
            dataList.setLayoutManager(new LinearLayoutManager(this));
            dataList.setAdapter(new ProgrammingAdaptor(data, this));
            //dataList.getLayoutManager();}
        }
        catch (Exception r) {
            Toast.makeText(this, r.getMessage()+"65165156!", Toast.LENGTH_SHORT).show();
        }
    }




}

