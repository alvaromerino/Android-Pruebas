package com.alvaromerino.expandablelistviewejemplo.activities;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.alvaromerino.expandablelistviewejemplo.R;
import com.alvaromerino.expandablelistviewejemplo.adapters.ExpandableListAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListView;

public class MainActivity extends Activity
{
	private List<String> groupList;
	private List<String> childList;
	private Map<String, List<String>> laptopColecciones;
	private ExpandableListView expandableListView;
	private ExpandableListAdapter expandableListAdapter;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        createGroupList();
        createCollection();
        
        expandableListView = (ExpandableListView) findViewById(R.id.laptop_list);
        expandableListAdapter = new ExpandableListAdapter(this, groupList, laptopColecciones);
        expandableListView.setAdapter(expandableListAdapter);
    }

    private void createGroupList() 
    {
        groupList = new ArrayList<String>();
        groupList.add("HP");
        groupList.add("Dell");
        groupList.add("Lenovo");
        groupList.add("Sony");
        groupList.add("HCL");
        groupList.add("Samsung");
    }

    private void createCollection() 
    {
    	String[] hpModels = { "HP Pavilion G6-2014TX", "ProBook HP 4540", "HP Envy 4-1025TX" };
        String[] hclModels = { "HCL S2101", "HCL L2102", "HCL V2002" };
        String[] lenovoModels = { "IdeaPad Z Series", "Essential G Series", "ThinkPad X Series", "Ideapad Z Series" };
        String[] sonyModels = { "VAIO E Series", "VAIO Z Series", "VAIO S Series", "VAIO YB Series" };
        String[] dellModels = { "Inspiron", "Vostro", "XPS" };
        String[] samsungModels = { "NP Series", "Series 5", "SF Series" };
 
        laptopColecciones = new LinkedHashMap<String, List<String>>();
 
        for (String laptop : groupList) 
        {
        	if (laptop.equals("HP")) loadChild(hpModels);
            else if (laptop.equals("Dell")) loadChild(dellModels);
            else if (laptop.equals("Sony")) loadChild(sonyModels);
            else if (laptop.equals("HCL")) loadChild(hclModels);
            else if (laptop.equals("Samsung")) loadChild(samsungModels);
            else loadChild(lenovoModels);
 
            laptopColecciones.put(laptop, childList);
        }
    }
    
    private void loadChild(String[] laptopModels)
    {
        childList = new ArrayList<String>();
        for (String model : laptopModels)
            childList.add(model);
    }
    

}
