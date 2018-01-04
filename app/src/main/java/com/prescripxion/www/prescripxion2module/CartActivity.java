package com.prescripxion.www.prescripxion2module;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class CartActivity extends AppCompatActivity implements CartAdapter.CartAdapterDataTransferInterface {


    ///Declaration Of Recycler Variables For Cart
    private RecyclerView cartRecyclerView;
    private RecyclerView.Adapter cartAdapter;
    private RecyclerView.LayoutManager cartLayoutManager;

    TextView cartPriceText, cartItemSelectedText;



    ArrayList<Medicine> medicines;
    TreeMap<Medicine , Integer> addedToCartMap;
    ArrayList<MyPair>  addedToCartArrayList = new ArrayList<MyPair>();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartItemSelectedText = (TextView) findViewById(R.id.cartItemSelectedTextView);
        cartPriceText = (TextView) findViewById(R.id.cartTotalPriceTextView);

        ///RecyclerView Codes For Cart:
        cartRecyclerView = (RecyclerView) findViewById(R.id.recyclerCart);
        cartLayoutManager = new LinearLayoutManager(this);
        cartRecyclerView.setLayoutManager(cartLayoutManager);



        Intent activityThatCalled = getIntent();


        addedToCartMap = new TreeMap<Medicine, Integer> ((HashMap<Medicine , Integer>) activityThatCalled.getSerializableExtra("currentCartSelection"));





        for(Map.Entry<Medicine , Integer> entry : addedToCartMap.entrySet())
        {
            MyPair temp = new MyPair(entry.getKey(), entry.getValue());




            addedToCartArrayList.add(temp);

        }

        if (!addedToCartArrayList.isEmpty()) {
            updateCartText();
        }







        cartAdapter = new CartAdapter(this, addedToCartArrayList) ;

        cartRecyclerView.setAdapter(cartAdapter);


    }


    @Override
    public void onBackPressed() {

        TreeMap<Medicine, Integer> updatedAddedToCartMap = new TreeMap<>();

        for(MyPair e : addedToCartArrayList)
        {
            updatedAddedToCartMap.put(e.medicine, e.amount);

        }

        Intent goingBack = new Intent();

        goingBack.putExtra("updatedAddedToCartMap" , updatedAddedToCartMap);

        setResult(RESULT_OK, goingBack);

        finish();


    }

    void updateCartText()
    {
        int items = addedToCartArrayList.size();
        double totalPrice = 0;

        for(MyPair e : addedToCartArrayList)
        {
            double unitPrice = e.medicine.getPrice() * e.getAmount();

            totalPrice += unitPrice;
        }

        totalPrice = BigDecimal.valueOf(totalPrice).setScale(3, RoundingMode.HALF_UP).doubleValue();

       String priceText = "Price: " + Double.toString(totalPrice);
       String itemText = "Items Selected: " + Integer.toString(items);

       cartPriceText.setText(priceText);
       cartItemSelectedText.setText(itemText);


    }


    @Override
    public void onCartDataChanged(ArrayList<MyPair> updatedCartArrayList) {
        addedToCartArrayList = updatedCartArrayList;

        if(!addedToCartArrayList.isEmpty())
        {
            updateCartText();
        }
        else
        {
            cartPriceText.setText("Price: 0");
            cartItemSelectedText.setText("Items Selected: 0");
        }
    }
}
