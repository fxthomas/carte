package com.example.carte;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class RightBonAppetitFragment extends Fragment implements MainActivity.SpeechInputListener {
  protected final String TEXT_WELCOME = "Bon appétit!";

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return inflater.inflate(R.layout.frag_pre_right, container, false);
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
    return false;
  }

  @Override
  public void onSpeechInputInitialized() {
    MainActivity act = (MainActivity)getActivity();
    act.ask(TEXT_WELCOME);
  }
}
