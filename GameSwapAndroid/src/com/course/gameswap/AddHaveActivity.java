package com.course.gameswap;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;
import android.os.Build;

public class AddHaveActivity extends Activity {
	
	DatabaseHandler db;
	List<GameBean> allGamesList;
	private Button addHaveButton;
	private AutoCompleteTextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_have);
		
		db = new DatabaseHandler(this);
		allGamesList = db.getAllGames();
		ArrayList<String> gamesNames = new ArrayList<String>();
		
		for (int x = 0; x < db.getGamesCount(); x++) {
			gamesNames.add(allGamesList.get(x).getName());
		}
		
		// Get a reference to the AutoCompleteTextView in the layout
		textView = (AutoCompleteTextView) findViewById(R.id.autocomplete_gameslist);

		// Create the adapter and set it to the AutoCompleteTextView 
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, gamesNames);
		textView.setAdapter(adapter);
		
		addHaveButton=(Button) findViewById(R.id.addGameToHaveBtn);
		
		addHaveButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String gameEntered = textView.getText().toString();
				//Toast.makeText(AddHaveActivity.this, gameEntered, Toast.LENGTH_LONG).show();
				if ( db.isGamedAlreadyAddedToHave(gameEntered) )
					Toast.makeText(AddHaveActivity.this, "Game already exist in your list", Toast.LENGTH_LONG).show();
				else {
					db.addHaveGame(new GameBean( db.getHaveCount(), gameEntered, "" ));
					Toast.makeText(AddHaveActivity.this, "Game Added to your list", Toast.LENGTH_LONG).show();
				}
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_have, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}


}
