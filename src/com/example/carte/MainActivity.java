package com.example.carte;

import java.util.ArrayList;

import com.example.carte.Plat.Type;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
	public final static String EXTRA_MESSAGE2 = "com.example.carte.CHANGER";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Plat plat1 = new Plat("salade niçoise", "Excellente salade", 21, 3, R.drawable.gateau, Type.ENTREE);
		Plat plat2 = new Plat("salade viennoise", "Excellente salade", 21, 3, R.drawable.gateau, Type.DESSERT);
		Plat plat3 = new Plat("fat salade", "Excellente salade", 21, 3, R.drawable.gateau, Type.ENTREE);
		Plats plats = Plats.getInstance();
		plat1.ajouter();
		plat2.ajouter();
		plat3.ajouter();
		plat1.ajouter();
		plats.addPlat(plat1);
		plats.addPlat(plat2);
		plats.addPlat(plat3);
		
		
	    if (savedInstanceState == null) {
	    	LeftWelcomeFragment leftfrag = new LeftWelcomeFragment();
	    	RightWelcomeFragment rightfrag = new RightWelcomeFragment();

	    	getFragmentManager().beginTransaction()
	    		.add(R.id.fragment_right, rightfrag)
	    		.add(R.id.fragment_left, leftfrag)
	    		.commit();
	    }
	}
	
	public void categorieEntrees(View view) {
		ArrayList<Plat> entrees = Plats.getInstance().getPlatType(Type.ENTREE);
		
		getFragmentManager().beginTransaction()
		.replace(R.id.fragment_right, new RightListePlatsFragment())
		.replace(R.id.fragment_left, new LeftMenuFragment())
		.commit();
	}
	
	public void categorieDesserts(View view) {
		getFragmentManager().beginTransaction()
		.add(R.id.fragment_right, new RightListePlatsFragment())
		.commit();
	}
	
	public void categorieViandes(View view) {
		getFragmentManager().beginTransaction()
		.add(R.id.fragment_right, new RightListePlatsFragment())
		.commit();
	}
	
	public void confirmer(View view) {
		Log.d("OK", "confirmer");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
