package com.example.carte;

import java.util.ArrayList;

import com.example.carte.Plat.Type;

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


public class RightListePlatsFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.frag_right_liste_plats, container, false);
		Context c = this.getActivity();

    	LinearLayout l = (LinearLayout) v.findViewById(R.id.liste_plats_categorie);
    	
    	ArrayList<Plat> plats = Plats.getInstance().getPlatType(Type.ENTREE);
    	for (int i = 0 ; i < plats.size() ; i++) {
    		View vuePlat = inflater.inflate(R.layout.details_plat, container, false);
    		((ImageView) vuePlat.findViewById(R.id.image_details_plat)).setImageResource(plats.get(i).getImage());
    		((TextView) vuePlat.findViewById(R.id.texte_details_plat)).setText(plats.get(i).getNom());
    		((TextView) vuePlat.findViewById(R.id.prix_details_plat)).setText(Float.toString(plats.get(i).getPrix()) + "€");
    		((RatingBar) vuePlat.findViewById(R.id.rating_details_plat)).setRating(plats.get(i).getNote());
    		l.addView(vuePlat);
    	}
    	
    	return v;
	}
}


