package com.example.carte;

import java.util.ArrayList;

import com.example.carte.Plat.Type;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.RatingBar;
import android.view.View.OnClickListener;


public class RightListePlatsFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.frag_right_liste_plats, container, false);
		Context c = this.getActivity();
		
		Bundle bundle = getArguments();
		int type = Type.ENTREE.ordinal();
		if (bundle != null)
			type = bundle.getInt("type");

    	LinearLayout l = (LinearLayout) v.findViewById(R.id.liste_plats_categorie);
    	
    	ArrayList<Plat> plats = Plats.getInstance().getPlatType(type);
    	for (int i = 0 ; i < plats.size() ; i++) {
    		View vuePlat = inflater.inflate(R.layout.details_plat, container, false);
    		((ImageView) vuePlat.findViewById(R.id.image_details_plat)).setImageResource(plats.get(i).getImage());
    		((TextView) vuePlat.findViewById(R.id.texte_details_plat)).setText(plats.get(i).getNom());
    		((TextView) vuePlat.findViewById(R.id.description_details_plat)).setText(plats.get(i).getDescription());
    		((TextView) vuePlat.findViewById(R.id.prix_details_plat)).setText(Float.toString(plats.get(i).getPrix()) + "€");
    		((RatingBar) vuePlat.findViewById(R.id.rating_details_plat)).setRating(plats.get(i).getNote());
    		((TextView) vuePlat.findViewById(R.id.id_details_plat)).setText(Integer.toString(plats.get(i).getId()));
    		
    		vuePlat.setOnClickListener(new OnClickListener() {
    	    	@Override
                public void onClick(View v) {
    		        Plats.getInstance().incrementer(Integer.parseInt(((TextView)v.findViewById(R.id.id_details_plat)).getText().toString()));
    		        getFragmentManager().beginTransaction()
	    				.replace(R.id.fragment_left, new LeftMenuFragment())
	    				.commit();
    	    	}
    	    });
    		l.addView(vuePlat);
    	}
    	
    	return v;
	}
}


