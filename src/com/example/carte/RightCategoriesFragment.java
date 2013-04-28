package com.example.carte;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class RightCategoriesFragment extends Fragment implements MainActivity.SpeechInputListener {
  protected final String TEXT_WELCOME = "Choisissez votre plat : entrée, viande, dessert ou boisson. Pour confirmer la commande, dites \"confirmer\"";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.frag_right_command, container, false);
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
    if (s.contains("entrée")) act.categorieEntrees(null);
    else if (s.contains("viande")) act.categorieViandes(null);
    else if (s.contains("dessert")) act.categorieDesserts(null);
    else if (s.contains("boisson")) act.categorieBoissons(null);
    else if (s.contains("confirm")) act.confirmer(null);
    else return false;
    return true;
  }

  @Override
  public void onSpeechInputInitialized() {
    MainActivity act = (MainActivity)getActivity();
    act.ask(TEXT_WELCOME);
  }
}