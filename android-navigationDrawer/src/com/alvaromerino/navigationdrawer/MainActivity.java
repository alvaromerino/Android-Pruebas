package com.alvaromerino.navigationdrawer;

import java.util.Locale;

import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.SearchManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private DrawerLayout drawerLayout;
	private ListView drawerList;
	private ActionBarDrawerToggle drawerToggle;
	
	private CharSequence title;
	private CharSequence drawerTitle;
	private String[] sports;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		title = drawerTitle = getTitle();
		
		sports = getResources().getStringArray(R.array.sports);
		drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawerList = (ListView) findViewById(R.id.left_drawer);
		
		drawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, sports));
		drawerList.setOnItemClickListener(new ListView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {

				selectItem(position);
			}
		});
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        /**
         * Usaremos un ActionBarDrawerToggle como DrawerListener ya que ActionBarDrawerToggle
         * implementa DrawerListener
         */
        drawerToggle = new ActionBarDrawerToggle(
        		this, 
        		drawerLayout, 
        		R.drawable.ic_drawer, 
        		R.string.drawer_open, 
        		R.string.drawer_close) {
        	
        	@Override
        	public void onDrawerOpened(View drawerView) {
        		getActionBar().setTitle(drawerTitle);
        		//Llamamos a este método para recrear el action Bar
                invalidateOptionsMenu();
        	}
        	
        	@Override
			public void onDrawerClosed(View drawerView) {
        		getActionBar().setTitle(title);
                //Llamamos a este método para recrear el action Bar
        		invalidateOptionsMenu();
        	}
        };
		
        drawerLayout.setDrawerListener(drawerToggle);

        // La primera vez seleccionamos la opcion del indice 0
        if (savedInstanceState == null) 
        	selectItem(0);
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		boolean drawerOpen = drawerLayout.isDrawerOpen(drawerList);
		// Establecemos la visibilidad al contrario de la visibilidad del drawerLayout.
        menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }  
		
		switch(item.getItemId()) {
        case R.id.action_websearch:
            Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
            intent.putExtra(SearchManager.QUERY, getActionBar().getTitle());
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            } else {
                Toast.makeText(this, R.string.app_not_available, Toast.LENGTH_LONG).show();
            }
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
	}

	private void selectItem(int position) {
		Fragment fragment = new SportFragment();
		Bundle bundle = new Bundle();
		bundle.putInt(SportFragment.SPORT_NUMBER, position);
		fragment.setArguments(bundle);
		
		FragmentManager fm = getFragmentManager();
		fm.beginTransaction().replace(R.id.content_frame, fragment).commit();
		
		drawerList.setItemChecked(position, true);
		setTitle(sports[position]);
		drawerLayout.closeDrawer(drawerList);
	}
	
	@Override
	public void setTitle(CharSequence title) {
		this.title = title;
		getActionBar().setTitle(this.title);
	}
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		drawerToggle.syncState();
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		drawerToggle.onConfigurationChanged(newConfig);
	}
	
	/*
	 * Fragment que muestra un deporte y que aparecerá en el content_frame
	 */
	public static class SportFragment extends Fragment {
		
		public static final String SPORT_NUMBER = "sport_number";
		
		public SportFragment() {
		}
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {

			View rootView = inflater.inflate(R.layout.fragment_sport, container, false);
			int index = getArguments().getInt(SPORT_NUMBER);
			String sport = getResources().getStringArray(R.array.sports)[index];
			
			int imageId = getResources().getIdentifier(sport.toLowerCase(Locale.getDefault()), "drawable", getActivity().getPackageName());
			((ImageView) rootView.findViewById(R.id.image)).setImageResource(imageId);
			
			getActivity().setTitle(sport);
			
			return rootView;
		}
	}

}
