package com.example.carte;

import java.util.ArrayList;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.RatingBar;


public class LeftMenuFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.frag_left_menu, container, false);
		Context c = this.getActivity();

    	LinearLayout l = (LinearLayout) v.findViewById(R.id.liste_plats);
    	
    	ArrayList<Plat> plats = Plats.getInstance().getPlats();
    	float prixTotal = 0;
    	for (int i = 0 ; i < plats.size() ; i++) {
    		float prixPlat = plats.get(i).getPrix()*plats.get(i).getQuantite();
    		prixTotal += prixPlat;
    		
    		View vuePlat = inflater.inflate(R.layout.plat, container, false);
    		((ImageView) vuePlat.findViewById(R.id.image_plat)).setImageResource(plats.get(i).getImage());
    		((TextView) vuePlat.findViewById(R.id.texte_plat)).setText(plats.get(i).getNom());
    		((TextView) vuePlat.findViewById(R.id.prix_plat)).setText(Float.toString(prixPlat) + "€");
    		((RatingBar) vuePlat.findViewById(R.id.rating_plat)).setRating(plats.get(i).getNote());
    		l.addView(vuePlat);
    	}
    	
    	((TextView) v.findViewById(R.id.prix)).setText(Float.toString(prixTotal) + "€");
		
    	return v;
	}
}


