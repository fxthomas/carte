package com.example.carte;

import java.util.ArrayList;

import com.example.carte.Plat.Type;

import android.os.Bundle;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
	public final static String EXTRA_MESSAGE2 = "com.example.carte.CHANGER";
	
	private void chargerPlats() {
		Plats plats = Plats.getInstance();
		plats.clear();
		
		Plat plat1 = new Plat("Salade niçoise", "Excellente salade niçoise", 21, 3, R.drawable.salad_platter, Type.ENTREE);
		Plat plat2 = new Plat("Salade viennoise", "Salade viennoise excellente", 21, 2, R.drawable.salad_platter, Type.ENTREE);
		Plat plat3 = new Plat("Fat salade", "Fat Fat salade", 21, 4, R.drawable.salad_platter, Type.ENTREE);
		
		Plat plat4 = new Plat("Gateau", "Un énorme gâteau caramel-choco", 2.5f, 5, R.drawable.gateau, Type.DESSERT);
		Plat plat5 = new Plat("Gateau2", "Un énorme gâteau caramel-choco", 5, 3, R.drawable.gateau, Type.DESSERT);
		Plat plat6 = new Plat("Gateau3", "Un énorme gâteau caramel-choco", 9.99f, 3, R.drawable.gateau, Type.DESSERT);
		Plat plat10= new Plat("Gateau", "Un énorme gâteau caramel-choco", 9.99f, 3, R.drawable.gateau, Type.DESSERT);
		Plat plat11 = new Plat("Gateau2", "Un énorme gâteau caramel-choco", 9.99f, 3, R.drawable.gateau, Type.DESSERT);
		Plat plat12 = new Plat("Gateau3", "Un énorme gâteau caramel-choco", 9.99f, 3, R.drawable.gateau, Type.DESSERT);
		
		Plat plat7 = new Plat("Dinde", "Dinde aux marrons", 50, 4, R.drawable.turkey, Type.VIANDE);
		Plat plat8 = new Plat("Turkey", "Thanksgiving is coming", 70, 5, R.drawable.turkey, Type.VIANDE);
		Plat plat9 = new Plat("Dindon", "Dindon", 25, 1, R.drawable.turkey, Type.VIANDE);

    Plat plat13 = new Plat("Cocktail 1", "Un délicieux cocktail", 8, 4, R.drawable.drink, Type.BOISSON);
    Plat plat14 = new Plat("Cocktail 2", "Un cocktail", 7, 4, R.drawable.drink, Type.BOISSON);
    Plat plat15 = new Plat("Cocktail 3", "Amazing cocktail!", 9, 4, R.drawable.drink, Type.BOISSON);
    Plat plat16 = new Plat("Cocktail 4", "Un autre cocktail", 5, 4, R.drawable.drink, Type.BOISSON);

		plats.addPlat(plat1);
		plats.addPlat(plat2);
		plats.addPlat(plat3);
		plats.addPlat(plat4);
		plats.addPlat(plat5);
		plats.addPlat(plat6);
		plats.addPlat(plat7);
		plats.addPlat(plat8);
		plats.addPlat(plat9);
		plats.addPlat(plat10);
		plats.addPlat(plat11);
		plats.addPlat(plat12);
    plats.addPlat(plat13);
    plats.addPlat(plat14);
    plats.addPlat(plat15);
    plats.addPlat(plat16);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// On charge les plats
		chargerPlats();

	    if (savedInstanceState == null) {
	    	LeftWelcomeFragment leftfrag = new LeftWelcomeFragment();
	    	RightWelcomeFragment rightfrag = new RightWelcomeFragment();

	    	getFragmentManager().beginTransaction()
	    		.add(R.id.fragment_left, leftfrag)
	    		.add(R.id.fragment_right, rightfrag)
	    		.commit();
	    }
	}
	
	public void categorieEntrees(View view) {
		RightListePlatsFragment frag_right = new RightListePlatsFragment();
		Bundle args = new Bundle();
		args.putInt("type", Type.ENTREE.ordinal());
		frag_right.setArguments(args);
		
		getFragmentManager().beginTransaction()
			.replace(R.id.fragment_left, new LeftMenuFragment())
			.replace(R.id.fragment_right, frag_right)
			.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
		    .addToBackStack("Entrees")
			.commit();
	}
	
	public void categorieDesserts(View view) {
		RightListePlatsFragment frag_right = new RightListePlatsFragment();
		Bundle args = new Bundle();
		args.putInt("type", Type.DESSERT.ordinal());
		frag_right.setArguments(args);
		
		getFragmentManager().beginTransaction()
			.replace(R.id.fragment_left, new LeftMenuFragment())
			.replace(R.id.fragment_right, frag_right)
			.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
		    .addToBackStack("Desserts")
			.commit();
	}
	
	public void categorieViandes(View view) {
		RightListePlatsFragment frag_right = new RightListePlatsFragment();
		Bundle args = new Bundle();
		args.putInt("type", Type.VIANDE.ordinal());
		frag_right.setArguments(args);
		
		getFragmentManager().beginTransaction()
			.replace(R.id.fragment_left, new LeftMenuFragment())
			.replace(R.id.fragment_right, frag_right)
			.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
		    .addToBackStack("Viandes")
			.commit();
	}

  public void categorieBoissons(View view) {
    RightListePlatsFragment frag_right = new RightListePlatsFragment();
    Bundle args = new Bundle();
    args.putInt("type", Type.BOISSON.ordinal());
    frag_right.setArguments(args);

    getFragmentManager().beginTransaction()
            .replace(R.id.fragment_left, new LeftMenuFragment())
            .replace(R.id.fragment_right, frag_right)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .addToBackStack("Boissons")
            .commit();
  }

	public void confirmer(View view) {
		LeftMenuFragment left_frag = new LeftMenuFragment();
		Bundle args = new Bundle();
		args.putString("mode", "confirmation");
		left_frag.setArguments(args);
		
		getFragmentManager().beginTransaction()
			.replace(R.id.fragment_left, left_frag)
			.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
		    .addToBackStack("Confirmation")
			.commit();
	}
	
	public void confirmationNon(View v) {
		LeftMenuFragment left_frag = new LeftMenuFragment();
		
		getFragmentManager().beginTransaction()
			.replace(R.id.fragment_left, left_frag)
			.commit();
	}
	
	public void confirmationOui(View v) {
		LeftMenuFragment left_frag = new LeftMenuFragment();
		Bundle args = new Bundle();
		args.putString("mode", "confirme");
		left_frag.setArguments(args);
		
		getFragmentManager().beginTransaction()
			.replace(R.id.fragment_left, left_frag)
			.commit();
	}
	
	public void payer(View view) {
		RightPaymentFragment right_frag = new RightPaymentFragment();
		
		getFragmentManager().beginTransaction()
			.replace(R.id.fragment_right, right_frag)
			.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
		    .addToBackStack("Paiement")
			.commit();
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
