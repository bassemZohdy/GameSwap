package com.course.gameswap;

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

public class IHaveActivity extends ListActivity {
	
	private Button addWantButton;
	DatabaseHandler db;
	List<GameBean> allHaveList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ihave);
		
		db = new DatabaseHandler(this);
		
		db.addHaveGame(new GameBean("Medal Of Honor", "2010"));
		db.addHaveGame(new GameBean("GTA", "1"));
		db.addHaveGame(new GameBean("Age of Empires", "2008"));
		db.addHaveGame(new GameBean("Snake", "2000"));
		
		addWantButton=(Button) findViewById(R.id.addWantButton);
		
		addWantButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(IHaveActivity.this, "Button Clicked", Toast.LENGTH_LONG).show();
				gotoAddHave(v);
			}
		});
		
		db = new DatabaseHandler(this);
		allHaveList = db.getHaveGames();

		String[] haveNames = new String[db.getHaveCount()];

		for (int x = 0; x < db.getHaveCount(); x++) {
			haveNames[x] = allHaveList.get(x).getName();

		}

		this.setListAdapter(new ArrayAdapter<String>(this, R.layout.activity_ihave,	R.id.haveLabel, haveNames));
		ListView lv = getListView();

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
