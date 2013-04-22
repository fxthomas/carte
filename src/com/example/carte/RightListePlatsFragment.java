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


public class RightListePlatsFragment extends Fragment implements MainActivity.SpeechInputListener {
  ArrayList<Plat> plats;

  protected final String TEXT_WELCOME = "Que voulez vous commander? ";
  protected final String TEXT_REPEAT = "Faites une autre commande, ou dites retour pour revenir au menu précédent.";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.frag_right_liste_plats, container, false);
		Context c = this.getActivity();
		
		Bundle bundle = getArguments();
		int type = Type.ENTREE.ordinal();
		if (bundle != null)
			type = bundle.getInt("type");

    	LinearLayout l = (LinearLayout) v.findViewById(R.id.liste_plats_categorie);
    	
    	plats = Plats.getInstance().getPlatType(type);
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

  @Override
  public void onStart() {
    ((MainActivity)getActivity()).registerSpeechInput(this);
    super.onStart();
  }

  @Override
  public void onStop() {
    ((MainActivity)getActivity()).unregisterSpeechInput();
    super.onStop();
  }

  @Override
  public boolean onSpeechInputFinished(String s) {
    MainActivity act = (MainActivity)getActivity();

    // Check the recognized string for a menu item name
    for (Plat p: plats) {

      // If we found one, select it and ask again
      if (s.contains(p.getNom().toLowerCase())) {
        Plats.getInstance().incrementer(p.getId());
        getFragmentManager().beginTransaction()
          .replace(R.id.fragment_left, new LeftMenuFragment())
          .commit();

        act.ask(TEXT_REPEAT);
        return true;
      }
    }

    // If the user said "back", do it
    if (s.contains("retour")) {
      act.getFragmentManager().popBackStack();
      return true;
    }

    // If we didn't recognize what the user said, start STT again
    return false;
  }

  @Override
  public void onSpeechInputInitialized() {
    MainActivity act = (MainActivity)getActivity();

    // Builds a list of menu items
    String TEXT_PLATS = "";
    for (Plat p: plats) TEXT_PLATS = TEXT_PLATS + p.getNom() + ", ";

    // Asks the user to say the name of a menu item
    act.ask(TEXT_WELCOME + TEXT_PLATS + "ou dites retour pour retourner au menu précédent");
  }
}
