package com.prescripxion.www.prescripxion2module;
/**
 * Created by Tasin Ishmam on 12/12/2017.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;

public class MyMainAdapter extends RecyclerView.Adapter<MyHolder> implements Filterable{
    Context c;
    ClickListener listener;
    ArrayList<Medicine> medicineList ,filterList;
    NewCustomFilter filter;
    DataTransferInterface dtInterface;



    public MyMainAdapter(Context ctx, ArrayList<Medicine> medicineList,  DataTransferInterface dtInterface, ClickListener listener)
    {
        this.c=ctx;
        this.listener = listener;
        this.medicineList=medicineList;
        this.filterList=medicineList;
        this.dtInterface = dtInterface;
        dtInterface.setValues(medicineList);
    }
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.model,null);
        //HOLDER
        MyHolder holder=new MyHolder(v, listener);
        return holder;
    }
    //DATA BOUND TO VIEWS
    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        //BIND DATA
        holder.medNameTxt.setText(medicineList.get(position).getName());
        holder.medDetailsTxt.setText(medicineList.get(position).getDetails());
       // holder.itemCheckoutButton.setImageResource(medicineList.get(position).getImg());
        //IMPLEMENT CLICK LISTENET

    }


    @Override
    public int getItemCount() {
        return medicineList.size();
    }
    //RETURN FILTER OBJ
    @Override
    public Filter getFilter() {
        if(filter==null)
        {
            filter=new NewCustomFilter(filterList,this);
        }


        return filter;
    }

    public void refresh() {
        dtInterface.setValues(medicineList);
    }
}