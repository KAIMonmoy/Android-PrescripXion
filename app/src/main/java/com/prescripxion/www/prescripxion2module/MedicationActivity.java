package com.prescripxion.www.prescripxion2module;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import java.io.InputStream;
import java.util.ArrayList;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class MedicationActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);


        ArrayList<String> medicine = new ArrayList<String>();
        medicine.add("Ace\n500 mg Paracetamol");
        medicine.add("Ace Plus\n500 mg Paracetamol");
        medicine.add("Napa\n500 mg Paracetamol");
        medicine.add("Napa Extra\n500 mg Paracetamol");
        medicine.add("Ace\n500 mg Paracetamol");
        medicine.add("Ace Plus\n500 mg Paracetamol");
        medicine.add("Napa\n500 mg Paracetamol");
        medicine.add("Napa Extra\n500 mg Paracetamol");
        medicine.add("Ace\n500 mg Paracetamol");
        medicine.add("Ace Plus\n500 mg Paracetamol");
        medicine.add("Napa\n500 mg Paracetamol");
        medicine.add("Napa Extra\n500 mg Paracetamol");
        medicine.add("Ace\n500 mg Paracetamol");
        medicine.add("Ace Plus\n500 mg Paracetamol");
        medicine.add("Napa\n500 mg Paracetamol");
        medicine.add("Napa Extra\n500 mg Paracetamol");

        try {
            AssetManager am = getAssets();
            InputStream is = am.open("new.xls");
            medicine.add("8888");
            Workbook wb = Workbook.getWorkbook(is);
            Sheet s = wb.getSheet(0);
            int row = s.getRows();
            int col = s.getColumns();

            for (int r = 0; r < row; r++)
                for (int c = 0; c < col; c++) {
                    Cell z = s.getCell(c, r);
                    medicine.add(z.getContents());
                }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String[] medicineList = medicine.toArray(new String[medicine.size()]);

        mRecyclerView = (RecyclerView) findViewById(R.id.myRecyclerView);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MedicationAdapter(medicineList);
        mRecyclerView.setAdapter(mAdapter);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_medication:
                    //TODO: GO TO MEDICINE ACTIVITY;
                    return true;
                case R.id.navigation_home:
                    //TODO: GO TO HOME ACTIVITY;
                    Intent mainIntent = new Intent(MedicationActivity.this, MainActivity.class);
                    startActivity(mainIntent);
                    finish();
                    return true;
                case R.id.navigation_profile:
                    //TODO: GO TO PROFILE ACTIVITY;
                    Intent profileIntent = new Intent(MedicationActivity.this, ProfileActivity.class);
                    startActivity(profileIntent);
                    finish();
                    return true;
            }
            return false;
        }

    };

}
