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


public class LeftMenuFragment extends Fragment{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.frag_left_menu, container, false);
		Context c = this.getActivity();

    	LinearLayout l = (LinearLayout) v.findViewById(R.id.liste_plats);
    	
    	ArrayList<Plat> plats = Plats.getInstance().getPlats();
    	for (int i = 0 ; i < plats.size() ; i++) {
    		View inflatedView = inflater.inflate(R.layout.plat, container, false);
    		((ImageView) inflatedView.findViewById(R.id.image_plat)).setImageResource(plats.get(i).getImage());
    		((TextView) inflatedView.findViewById(R.id.texte_plat)).setText(plats.get(i).getNom());
    		l.addView(inflatedView);
    	}
		
    	return v;
	}
}


