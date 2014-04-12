package com.alvaromerino.expandablelistviewejemplo.adapters;

import java.util.List;
import java.util.Map;

import com.alvaromerino.expandablelistviewejemplo.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ExpandableListAdapter extends BaseExpandableListAdapter 
{
	private Activity context;
	private Map<String, List<String>> laptopColecciones;
	private List<String> laptops;
	
	public ExpandableListAdapter() 
	{
		super();
	}

	public ExpandableListAdapter(Activity context, List<String> laptops,
			Map<String, List<String>> laptopColecciones) 
	{
		super();
		this.context = context;
		this.laptops = laptops;
		this.laptopColecciones = laptopColecciones;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) 
	{
		return laptopColecciones.get(laptops.get(groupPosition)).get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition)
	{
		return childPosition;
	}

	@Override
	public int getChildrenCount(int groupPosition) 
	{
		return laptopColecciones.get(laptops.get(groupPosition)).size();
	}

	@Override
	public View getChildView(final int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent)
	{
		String laptop = (String) getChild(groupPosition, childPosition);
		LayoutInflater inflater = context.getLayoutInflater();
		
		if (convertView == null) 
		{
			convertView = inflater.inflate(R.layout.child_item, null);
		}
		
		TextView item = (TextView) convertView.findViewById(R.id.laptop);
        ImageView delete = (ImageView) convertView.findViewById(R.id.delete);
        
        delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				AlertDialog.Builder builder = new AlertDialog.Builder(context);
				builder.setMessage("¿Quieres eliminar?");
				builder.setCancelable(false);
				builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int id) {
						List<String> child = laptopColecciones.get(laptops.get(groupPosition));
						child.remove(childPosition);
						notifyDataSetChanged();
					}
				});
				
				builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
				
				AlertDialog alertDialog = builder.create();
				alertDialog.show();
			}
		});
        
        item.setText(laptop);
        return convertView;
	}

	@Override
	public Object getGroup(int groupPosition) 
	{
		return laptops.get(groupPosition);
	}

	@Override
	public int getGroupCount() 
	{
		return laptops.size();
	}

	@Override
	public long getGroupId(int groupPosition)
	{
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent)
	{
		String laptopName = (String) getGroup(groupPosition);
		if (convertView == null) 
		{
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.group_item, null);
		}
		TextView item = (TextView) convertView.findViewById(R.id.laptop);
        item.setTypeface(null, Typeface.BOLD);
        item.setText(laptopName);
        return convertView;
	}

	@Override
	public boolean hasStableIds()
	{
		return true;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) 
	{
		return true;
	}
}
