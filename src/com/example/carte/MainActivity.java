package com.example.carte;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
	public final static String EXTRA_MESSAGE2 = "com.example.carte.CHANGER";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Plat plat1 = new Plat("salade niçoise", "Excellente salade", 21, 3, 0x7f02000e);
		Plat plat2 = new Plat("salade viennoise", "Excellente salade", 21, 3, 0x7f02000e);
		Plat plat3 = new Plat("salade niçoise", "Excellente salade", 21, 3, 0x7f02000e);
		ArrayList<Plat> plats = new ArrayList<Plat>();
		plats.add(plat1);
		plats.add(plat2);
		plats.add(plat3);
		
		
    if (savedInstanceState == null) {
      LeftWelcomeFragment leftfrag = new LeftWelcomeFragment();
      RightWelcomeFragment rightfrag = new RightWelcomeFragment();

      getFragmentManager().beginTransaction()
              .replace(R.id.fragment_right_welcome, rightfrag)
              .replace(R.id.fragment_left, leftfrag)
              .commit();
      
      ImageView image = new ImageView(this);
      image.setImageResource(R.drawable.gateau);
      LinearLayout l = (LinearLayout) findViewById(R.id.text_linearlayout);
      l.addView(image);
      
    }
    
  }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
