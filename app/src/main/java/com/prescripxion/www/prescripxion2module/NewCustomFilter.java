package com.prescripxion.www.prescripxion2module;

/**
 * Created by Tasin Ishmam on 12/12/2017.
 */

import android.widget.Filter;
import java.util.ArrayList;

public class NewCustomFilter extends Filter{
    MyMainAdapter adapter;
    ArrayList<Medicine> filterList;


    public NewCustomFilter(ArrayList<Medicine> filterList,MyMainAdapter adapter)
    {
        this.adapter=adapter;
        this.filterList=filterList;
    }
    //FILTERING OCURS
    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results=new FilterResults();
        //CHECK CONSTRAINT VALIDITY
        if(constraint != null && constraint.length() > 0)
        {
            //CHANGE TO UPPER
            constraint=constraint.toString().toUpperCase();
            //STORE OUR FILTERED MedicineS
            ArrayList<Medicine> filteredMedicines=new ArrayList<>();
            for (int i=0;i<filterList.size();i++)
            {
                //CHECK
                if(filterList.get(i).getName().toUpperCase().contains(constraint) || filterList.get(i).getDetails().toUpperCase().contains(constraint))
                {
                    //ADD Medicine TO FILTERED MedicineS
                    filteredMedicines.add(filterList.get(i));
                }
            }
            results.count=filteredMedicines.size();
            results.values=filteredMedicines;
        }else
        {
            results.count=filterList.size();
            results.values=filterList;
        }
        return results;
    }
    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapter.medicineList= (ArrayList<Medicine>) results.values;
        adapter.refresh();

        //REFRESH
        adapter.notifyDataSetChanged();
    }
}