package com.course.gameswap;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class IHaveActivity extends Activity {
	
	private Button addHaveButton;
	DatabaseHandler db;
	List<GameBean> allHaveList;
	
	private void fillTestData(){
		db.addGame(new GameBean(1, "Medal Of Honor", "2010"));
		db.addGame(new GameBean(2, "GTA", "1"));
		db.addGame(new GameBean(3, "Age of Empires", "2008"));
		db.addGame(new GameBean(4, "Snake", "2000"));
		db.addGame(new GameBean(5, "Call of duty", "2000"));
		db.addGame(new GameBean(6, "Fifa 2014", "2000"));
		db.addGame(new GameBean(7, "PES 2014", "2000"));
		db.addGame(new GameBean(8, "PES 2013", "2000"));
		db.addGame(new GameBean(9, "PES 2012", "2000"));
		
		db.addHaveGame(new GameBean(1, "Medal Of Honor", "2010"));
		db.addHaveGame(new GameBean(2, "GTA", "1"));
		db.addHaveGame(new GameBean(3, "Age of Empires", "2008"));
		db.addHaveGame(new GameBean(4, "Snake", "2000"));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ihave);
		
		db = new DatabaseHandler(this);
		
		fillTestData();
		
		addHaveButton=(Button) findViewById(R.id.addHaveButton);
		
		addHaveButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(IHaveActivity.this, "Button Clicked", Toast.LENGTH_LONG).show();
				gotoAddHave(v);
			}
		});
		
		db = new DatabaseHandler(this);
		allHaveList = db.getHaveGames();
		ArrayList<String> haveNames = new ArrayList<String>();
		
		for (int x = 0; x < db.getHaveCount(); x++) {
			haveNames.add(allHaveList.get(x).getName());
		}

		ListView lv = (ListView)findViewById(android.R.id.list);
//		ListView lv = getListView();
		lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, haveNames));
//		lv.setListAdapter(new ArrayAdapter<String>(this, R.layout.activity_ihave,	R.id.haveLabel, haveNames));
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int pos,
					long arg) {
				// TODO Auto-generated method stub
//				GameBean contacts = new GameBean();
//				contacts = db.getContact(pos + 1);
//
//				Intent intent = new Intent(getApplicationContext(),	SingleContact.class);
//				intent.putExtra("id", contacts.getID());
//				intent.putExtra("name", contacts.getName());
//				intent.putExtra("phone", contacts.getPhoneNumber());
//
//				startActivity(intent);

			}
		});
		
	}
	
	public void gotoAddHave(View view){
		Intent intent = new Intent(this, AddHaveActivity.class);
		startActivity(intent);
	}
	
}
