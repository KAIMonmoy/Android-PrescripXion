package com.prescripxion.www.prescripxion2module;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.lang.ref.WeakReference;

/**
 * Created by Tasin Ishmam on 12/12/2017.
 */


public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    //OUR VIEWS

    TextView medNameTxt,medDetailsTxt;
    ImageButton itemCheckoutButton;
     WeakReference<ClickListener> listenerRef;




    public MyHolder(View itemView, ClickListener listener) {
        super(itemView);

        listenerRef = new WeakReference<>(listener);
        this.medNameTxt= (TextView) itemView.findViewById(R.id.medNameText);
        this.medDetailsTxt= (TextView) itemView.findViewById(R.id.medDetailsText);
        this.itemCheckoutButton= (ImageButton) itemView.findViewById(R.id.itemCheckoutButton);

        itemCheckoutButton.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        if (v.getId() == itemCheckoutButton.getId()) {

            //Toast.makeText(v.getContext(), "ITEM PRESSED = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
        }
        listenerRef.get().onPositionClicked(getAdapterPosition());
    }
    /*@Override
    public void onClick(View v) {
        this.itemClickListener.onItemClick(v,getLayoutPosition());
    }
    public void setItemClickListener(ItemClickListener ic)
    {
        this.itemClickListener=ic;
    }*/
}