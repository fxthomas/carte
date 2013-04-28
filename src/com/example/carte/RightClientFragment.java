package com.example.carte;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class RightClientFragment extends Fragment implements MainActivity.SpeechInputListener {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.client_home, container, false);
	}

  @Override
  public void onStart() {
    //((MainActivity)getActivity()).registerSpeechInput(this);
    super.onStart();
  }

  @Override
  public void onStop() {
    //((MainActivity)getActivity()).unregisterSpeechInput();
    super.onStop();
  }

  @Override
  public boolean onSpeechInputFinished(String s) {
    return false;
  }

  @Override
  public void onSpeechInputInitialized() {
  }
}