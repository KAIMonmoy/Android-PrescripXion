package com.prescripxion.www.prescripxion2module;

import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeMap;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;





public class MainActivity extends AppCompatActivity implements DataTransferInterface, PurchaseDialog.FragmentDataTransferInterface {


    private RecyclerView mRecyclerView;
    public MyMainAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<String> mItems;
    private  ArrayList<Medicine> currentFilteredState;
    TreeMap<Medicine, Integer > currentCartSelection =new TreeMap<Medicine, Integer> ();
    //public Medicine[] medicines = new Medicine[NUMBER_OF_MEDICINES];
    ArrayList<Medicine> medicines = new ArrayList<Medicine>();
    public static ArrayList<String> addedToCart = new ArrayList<String>();
    Medicine lastConfirmedOnCart;
    SearchView searchView;
    MenuItem cartmenu, cameramenu;
    public static final int NUMBER_OF_MEDICINES=11;

    int a = 3;

    View view;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        currentCartSelection = new TreeMap<Medicine, Integer> ((HashMap<Medicine , Integer>) data.getSerializableExtra("updatedAddedToCartMap"));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Reading Medicine Names From Excel File
        getMedNamesData(view);

        //AddtoCart Codes


        //adding toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);






        ///RecyclerView Codes:
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);


        Collections.sort(medicines);


        mAdapter = new MyMainAdapter(this, medicines, this ,  new ClickListener() {
            @Override
            public void onPositionClicked(int position) {


                lastConfirmedOnCart = currentFilteredState.get(position);
                FragmentManager manager = getFragmentManager();
                PurchaseDialog purchaseDialog = new PurchaseDialog();

                purchaseDialog.show(manager, "TAG");

               // Toast.makeText(getApplicationContext(),currentFilteredState.get(position).getName() ,Toast.LENGTH_SHORT).show();

            }
        } );
        mRecyclerView.setAdapter(mAdapter);






        //SearchBar Codes Start here

        searchView = (SearchView) findViewById(R.id.searchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String query) {


                mAdapter.getFilter().filter(query);
                return false;
            }
        });



        //TODO: Searchbar Using Searchview and AutoCompletion


        ///Bottom Navigation Bar Codes:
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.actionbar_buttons, menu);



        cartmenu = menu.findItem(R.id.button_checkout);
        cameramenu = menu.findItem(R.id.button_camera);




        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        //noinspection SimplifiableIfStatement
        switch (item.getItemId())
        {
            case R.id.button_checkout:{
                Intent intentCart = new Intent(MainActivity.this, CartActivity.class);
                final int result = 1;


                intentCart.putExtra("currentCartSelection" , currentCartSelection);

                startActivityForResult(intentCart, result);

                break;
            }

            case R.id.button_camera: {
                Intent intentCamera = new Intent(MainActivity.this, CameraActivity.class);

                startActivity(intentCamera);

                break;



            }



        }









        return super.onOptionsItemSelected(item);
    }

    public void getMedNamesData(View view)
    {

        try {
            AssetManager am=getAssets();
            InputStream is=am.open("new.xls");
            Workbook workbook = Workbook.getWorkbook(is);
            Sheet s=workbook.getSheet(0);
            int row= s.getRows();
            

            for(int r = 0; r < row; r++)
            {

                Cell temp1=s.getCell(0,r);
                Cell temp2=s.getCell(1,r);
                Cell temp3=s.getCell(2,r);

               String name = temp1.getContents();
               String details = temp2.getContents();
               String  priceString=temp3.getContents().trim();

               if(details == null || details.equals("")) continue;

                try {
                    Double price = Double.parseDouble(priceString);
                    Medicine temp = new Medicine(name, details, price);
                    medicines.add(temp);
                }
                catch (NumberFormatException nfe){}









            }



        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),"No Xls File" + e.toString(),Toast.LENGTH_LONG).show();

        }
    }







    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //TODO: GO TO HOME ACTIVITY;
                    return true;
                case R.id.navigation_medication:
                    //TODO: GO TO MEDICINE ACTIVITY;
                    Intent medicineIntent = new Intent(MainActivity.this, MedicationActivity.class);
                    startActivity(medicineIntent);
                    finish();
                    return true;
                case R.id.navigation_profile:
                    //TODO: GO TO PROFILE ACTIVITY;
                    Intent profileIntent = new Intent(MainActivity.this, ProfileActivity.class);
                    startActivity(profileIntent);
                    finish();
                    return true;
            }
            return false;
        }

    };


    @Override
    public void setValues(ArrayList<Medicine> dataList) {
        currentFilteredState = dataList;
    }

    @Override
    public void onPurchaseConfirm(String message) {

        int amountPurchased = Integer.parseInt(message);

        if(amountPurchased > 0) {

            int previousAmount = 0;

            if(currentCartSelection.get(lastConfirmedOnCart) != null)
            {
                previousAmount = currentCartSelection.get(lastConfirmedOnCart);
            }


            currentCartSelection.put(lastConfirmedOnCart, amountPurchased + previousAmount);

            Toast.makeText(getApplicationContext(), message + " of " + lastConfirmedOnCart.getName() + " added to cart", Toast.LENGTH_SHORT).show();
        }
    }
}
