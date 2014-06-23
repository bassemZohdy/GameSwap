package com.course.gameswap;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class TabHostActivity extends TabActivity {

	private TabSpec haveSpec;
	private TabSpec wantSpec;
	private TabHost tabHost;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tab_host);
		
		 
		tabHost=getTabHost();
		// Tab I Have
        haveSpec = tabHost.newTabSpec("I Have");
        // setting Title and Icon for the Tab
        haveSpec.setIndicator("I have", getResources().getDrawable(R.drawable.ic_launcher));
        Intent haveIntent = new Intent(this, IHaveActivity.class);
        haveSpec.setContent(haveIntent);
         
        // Tab for Songs
        wantSpec = tabHost.newTabSpec("I Want");        
        wantSpec.setIndicator("I Want", getResources().getDrawable(R.drawable.ic_launcher));
        Intent wantIntent = new Intent(this, IWantActivity.class);
        wantSpec.setContent(wantIntent);
        
        tabHost.addTab(haveSpec); // Adding have tab
        tabHost.addTab(wantSpec); 
	}
}
