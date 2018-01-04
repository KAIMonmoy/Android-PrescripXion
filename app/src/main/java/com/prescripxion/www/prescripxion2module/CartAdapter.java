package com.prescripxion.www.prescripxion2module;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

/**
 * Created by Tasin Ishmam on 12/14/2017.
 */


public class CartAdapter extends RecyclerView.Adapter<CartViewHolder> implements View.OnClickListener{
    Context c;

    ArrayList<MyPair> medicineList;




    public CartAdapter(Context ctx,  ArrayList< MyPair > medicineList)
    {
        this.c=ctx;

        this.medicineList=medicineList;



    }
    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_card_model,null);

        CartViewHolder holder=new CartViewHolder(v);
        holder.itemCancelButton.setTag(holder);
        holder.itemCancelButton.setOnClickListener(this);
        return holder;
    }

    //DATA BOUND TO VIEWS
    @Override
    public void onBindViewHolder(CartViewHolder holder, int position) {
        //BIND DATA



        holder.medNameTxt.setText(medicineList.get(position).medicine.getName());

        MyPair temp = medicineList.get(position);

        Double unitPrice = temp.medicine.getPrice();
        int amount = temp.amount;


        Double totalPrice = unitPrice * amount;
        totalPrice = BigDecimal.valueOf(totalPrice).setScale(3, RoundingMode.HALF_UP).doubleValue();

        String amountTemp = "Amount: "  + Integer.toString(amount);
        String priceTemp = "Price: " + Double.toString(totalPrice);
        holder.medPriceTxt.setText(priceTemp);

        holder.medAmountTxt.setText(amountTemp);




    }


    @Override
    public int getItemCount() {
        return medicineList.size();
    }

    @Override
    public void onClick(View view) {
CartViewHolder holder = (CartViewHolder) view.getTag();

        if(view.getId() == holder.itemCancelButton.getId())
        {
            removeItemAt(holder.getAdapterPosition());

        }
    }

    private void removeItemAt(int position) {
        medicineList.remove(position);
        this.notifyItemRemoved(position);
        this.notifyItemRangeChanged(position, medicineList.size());
        ((CartAdapterDataTransferInterface)c).onCartDataChanged( medicineList);
    }



    interface CartAdapterDataTransferInterface
    {
        public void onCartDataChanged(ArrayList<MyPair> updatedCartArrayList);
    }


}
