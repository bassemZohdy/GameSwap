package com.course.gameswap;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class IWantActivity extends Activity {
	
	private Button addWantButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_iwant);
		
		addWantButton=(Button) findViewById(R.id.addHaveButton);
		
		addWantButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(IWantActivity.this, "Number 9 is pressed", Toast.LENGTH_LONG).show();
				// TODO Auto-generated method stub
				
			}
		});
	}
}
