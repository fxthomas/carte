package com.example.carte;

import java.util.ArrayList;

import android.widget.*;
import com.example.carte.Plat.Type;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class LeftMenuFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.frag_left_menu, container, false);

		Bundle bundle = getArguments();
		String mode = "";
		if (bundle != null)
			mode = bundle.getString("mode");

      final int layout_open = mode.equals("confirme") ? R.id.layout_comment : R.id.layout_quantity;

    	LinearLayout l = (LinearLayout) v.findViewById(R.id.liste_plats);
    	
    	ArrayList<Plat> plats = Plats.getInstance().getPlats();
    	float prixTotal = 0;
    	for (int i = 0 ; i < plats.size() ; i++) {
    		int quantite = plats.get(i).getQuantite();
    		if (quantite > 0) {
	    		float prixPlat = plats.get(i).getPrix()*quantite;
          final Plat plat = plats.get(i);
	    		prixTotal += prixPlat;
	    		
	    		View vuePlat = inflater.inflate(R.layout.plat, container, false);
	    		((ImageView) vuePlat.findViewById(R.id.image_plat)).setImageResource(plats.get(i).getImage());
	    		((TextView) vuePlat.findViewById(R.id.texte_plat)).setText(plats.get(i).getNom() + " (" + quantite + ")");
	    		((TextView) vuePlat.findViewById(R.id.prix_plat)).setText(Float.toString(prixPlat) + "€");
	    		((RatingBar) vuePlat.findViewById(R.id.rating_plat)).setRating(plats.get(i).getNote());

          vuePlat.setOnClickListener(new View.OnClickListener() {
            boolean displayingComments = false;

            void hide(View v) {
              v.setBackgroundColor(Color.parseColor("#00000000"));
              v.findViewById(layout_open).setVisibility(View.GONE);
              v.findViewById(R.id.btn_cancel).setOnClickListener(null);
              v.findViewById(R.id.btn_ok).setOnClickListener(null);
              v.findViewById(R.id.btn_incr).setOnClickListener(null);
              v.findViewById(R.id.btn_decr).setOnClickListener(null);

              displayingComments = false;
            }

            void show(final View v) {
              v.setBackgroundColor(Color.parseColor("#99000000"));
              v.findViewById(layout_open).setVisibility(View.VISIBLE);
              v.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v2) {
                  hide(v);
                }
              });

              v.findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v2) {
                  hide(v);
                  Toast.makeText(getActivity().getApplicationContext(), "Commentaire envoyé!", Toast.LENGTH_LONG).show();
                }
              });

              v.findViewById(R.id.btn_decr).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v2) {
                  plat.diminuer();
                  ((TextView) v.findViewById(R.id.texte_plat)).setText(plat.getNom() + " (" + plat.getQuantite() + ")");
                  hide(v);
                }
              });
              v.findViewById(R.id.btn_incr).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v2) {
                  plat.ajouter();
                  ((TextView) v.findViewById(R.id.texte_plat)).setText(plat.getNom() + " (" + plat.getQuantite() + ")");
                  hide(v);
                }
              });

              displayingComments = true;
            }

            @Override
            public void onClick(View v) {
              if (displayingComments) hide(v);
              else show(v);
            }
          });

	    		l.addView(vuePlat);
    		}
    	}
    	((TextView) v.findViewById(R.id.prix)).setText(Float.toString(prixTotal) + "€");
		
    	if (mode.equals("confirmation")) {
    		View confirmation = inflater.inflate(R.layout.confimer, container, false);
    		l.addView(confirmation);
    	}
    	else if (mode.equals("confirme")) {
    		TextView confirme = new TextView(getActivity());
    		confirme.setText("Commande confirmée");
    		confirme.setTextColor(Color.WHITE);
    		l.addView(confirme);
    		v.findViewById(R.id.bouton_confirmer).setVisibility(View.GONE);
    		v.findViewById(R.id.bouton_payer).setVisibility(View.VISIBLE);
    	}
    	
    	return v;
	}
}


