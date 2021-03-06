package com.rotonity.inventory.inventoryapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements fragment_ViewInventory.OnFragmentInteractionListener,NavigationView.OnNavigationItemSelectedListener {
    InventoryItem[] inventoryItem= new InventoryItem[100];
    static ArrayList<String> part_type_list,members_list;
    static SharedPreferences sp;
    SharedPreferences.Editor editor;
    String webhash="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue( getHashRequest );

        SharedPreferences saved_values = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
       editor =saved_values.edit();
        setContentView(R.layout.activity_main);
        loadFragment(new TabsFragment());
        sp = getSharedPreferences("login",MODE_PRIVATE);
        sp = getSharedPreferences("Hash",MODE_PRIVATE);
        sp=getSharedPreferences("localHash",MODE_PRIVATE);
        webhash=sp.getString("localHash","");


        String hash=sp.getString("Hash",webhash);
        if(!(hash.equals(webhash))) {
            VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(getDataRequest);
           // sp.edit().putString(webhash,"Hash").apply();
        }
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(getTableDataRequest);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment=new profile_fragment();

                switch (item.getItemId()){
                    case R.id.navigation_main:
                        fragment = new TabsFragment();
                        break;
                    case R.id.navigation_profile:
                        fragment = new profile_fragment();
                        break;

                    case R.id.navigation_add:
                        fragment = new add_fragment();
                        break;

                    case R.id.navigation_scan:
                      //  fragment = new Scanner();
                        startActivity(new Intent(MainActivity.this, Scanner.class));
                        onPause();
                        break;
                }
                return loadFragment(fragment);
            }
        });
    }
    private boolean loadFragment(Fragment fragment){
        if(fragment!=null)
        {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       /* if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    public StringRequest getHashRequest = new StringRequest(Request.Method.POST, URLs.GET_HASH,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                       // webhash=response;
                    sp.edit().putString(response,"Hash").apply();
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //  Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }) {
        @Override
        protected Map<String, String> getParams() {
            Map<String, String> params = new HashMap<>();
            return params;
        }
    };

    public StringRequest getDataRequest = new StringRequest(Request.Method.POST, URLs.GET_ALL,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {
                        JSONArray jsonarray = new JSONArray(response);
                        for (int i = 0; i < jsonarray.length(); i++) {
                            JSONObject jsonobject = jsonarray.getJSONObject(i);
                            inventoryItem[i]=new InventoryItem(jsonobject.getString("id"));//,jsonobject.getString("Name"));
                            inventoryItem[i].setPart_type(jsonobject.getString("Part_Type"));
                            inventoryItem[i].setDescription(jsonobject.getString("Description"));
                            inventoryItem[i].setCurrently_with(jsonobject.getString("Currently_With"));
                            inventoryItem[i].setAvailability(jsonobject.getString("Available"));
                            inventoryItem[i].setLast_edit(jsonobject.getString("Last_Edit"));
                            inventoryItem[i].setCost(jsonobject.getString("Cost"));

                            DBHelper helper = new DBHelper(getApplicationContext());
                            helper.insertItem(inventoryItem[i]);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //  Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }) {
        @Override
        protected Map<String, String> getParams() {
            Map<String, String> params = new HashMap<>();
            return params;
        }
    };

    public StringRequest getTableDataRequest = new StringRequest(Request.Method.POST, URLs.GET_SPINNER_LISTS,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {

                        JSONArray jsonarray = new JSONArray(response);
                        for (int i = 0; i < jsonarray.length(); i++) {
                            JSONObject jsonobject = jsonarray.getJSONObject(i);
                            part_type_list.add(jsonobject.getString("PT"+i));
                            members_list.add(jsonobject.getString("M"+i));

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //  Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }) {
        @Override
        protected Map<String, String> getParams() {
            Map<String, String> params = new HashMap<>();
            return params;
        }
    };

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.log_out){
          //  sp.edit().putBoolean("logged",false).apply();
            editor.putBoolean("logged",false);
            //sp.edit().putBoolean("log",false).commit();
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
            finish();
        }

        else if(id==R.id.yo)
        {
            Toast.makeText(getApplicationContext(),"Yo",Toast.LENGTH_SHORT).show();
        }
        /*if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }
}
