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
		
		Plat plat1 = new Plat("salade niçoise", "Excellente salade", 21, 3, R.drawable.gateau);
		Plat plat2 = new Plat("salade viennoise", "Excellente salade", 21, 3, R.drawable.gateau);
		Plat plat3 = new Plat("fat salade", "Excellente salade", 21, 3, R.drawable.gateau);
		Plats plats = Plats.getInstance();
		plats.addPlat(plat1);
		plats.addPlat(plat2);
		plats.addPlat(plat3);
		
		
	    if (savedInstanceState == null) {
	    	LeftWelcomeFragment leftfrag = new LeftWelcomeFragment();
	    	RightWelcomeFragment rightfrag = new RightWelcomeFragment();
	    	
           /* Fragment fragTwo = new MyFragment();
            Bundle arguments = new Bundle();
            arguments.putBoolean("shouldYouCreateAChildFragment", false);
            fragTwo.setArguments(arguments);*/

	    	getFragmentManager().beginTransaction()
	    		.add(R.id.fragment_right, rightfrag)
	    		.add(R.id.fragment_left, leftfrag)
	    		.commit();
      
	   
      
	    }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
