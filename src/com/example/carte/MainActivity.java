package com.example.carte;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.*;
import android.widget.EditText;

public class MainActivity extends Activity {
	public final static String EXTRA_MESSAGE2 = "com.example.carte.CHANGER";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

    if (savedInstanceState == null) {
      LeftWelcomeFragment leftfrag = new LeftWelcomeFragment();
      RightWelcomeFragment rightfrag = new RightWelcomeFragment();

      getFragmentManager().beginTransaction()
              .replace(R.id.fragment_right, rightfrag)
              .replace(R.id.fragment_left, leftfrag)
              .commit();
    }
  }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public void changer(View v){
		Intent intent = new Intent(this, Preparation.class);
    	String price = "jump to preparation";
    	intent.putExtra(EXTRA_MESSAGE2, price);
    	startActivity(intent);
	}
}
