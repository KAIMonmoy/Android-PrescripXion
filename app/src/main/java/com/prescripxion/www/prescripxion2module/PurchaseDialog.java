package com.prescripxion.www.prescripxion2module;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Tasin Ishmam on 12/13/2017.
 */

public class PurchaseDialog extends DialogFragment implements View.OnClickListener {


    Button cancel, confirm, add, subtract;
    EditText amount;



    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.dialog_cancel_button) {

            dismiss();
        }
        else if(view.getId() == R.id.dialog_confirm_buttom){
            ((FragmentDataTransferInterface)getActivity()).onPurchaseConfirm( amount.getText().toString());
            dismiss();
        }
        else if(view.getId() == R.id.dialog_add){
            int temp = Integer.parseInt(amount.getText().toString());
            temp++;



            amount.setText(Integer.toString(temp));

        }

        else if(view.getId() == R.id.dialog_subtract){
            int temp = Integer.parseInt(amount.getText().toString());


                if(temp > 0) {
                    temp--;
                    amount.setText(Integer.toString(temp));

                }
        }
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.purchase_dialog, null  );

        cancel = (Button) view.findViewById(R.id.dialog_cancel_button);
        confirm = (Button) view.findViewById(R.id.dialog_confirm_buttom);
        amount = (EditText) view.findViewById(R.id.dialog_amount_editText);

        add = (Button) view.findViewById(R.id.dialog_add);
        subtract = (Button) view.findViewById(R.id.dialog_subtract);


        add.setOnClickListener(this);
        subtract.setOnClickListener(this);
        cancel.setOnClickListener(this);
        confirm.setOnClickListener(this);

        setCancelable(false);

        return view;
    }


    interface FragmentDataTransferInterface
    {
        public void onPurchaseConfirm(String message);
    }
}
