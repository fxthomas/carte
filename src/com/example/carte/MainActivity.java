package com.example.carte;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.example.carte.Plat.Type;

import android.speech.tts.UtteranceProgressListener;
import android.speech.tts.TextToSpeech;
import android.speech.RecognizerIntent;
import android.content.Intent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class MainActivity extends Activity implements TextToSpeech.OnInitListener {

  /**
   * Speech input interface
   */
  public interface SpeechInputListener {
    public boolean onSpeechInputFinished(String s);
    public void onSpeechInputInitialized();
  }

  protected SpeechInputListener listener = null;

  /**
   * Registers a new speech input listener
   * @param f
   */
  public void registerSpeechInput(SpeechInputListener f) {
    listener = (SpeechInputListener)f;
    if (_isTTSEnabled) listener.onSpeechInputInitialized();
  }

  /**
   * Removes the current speech input listener
   */
  public void unregisterSpeechInput() {
    listener = null;
  }

  /**
   * Says the `s` string with the current TTS engine,
   * then waits for the user to say something.
   * @param s
   */
  public void ask(String s) {
    HashMap<String, String> map = new HashMap<String, String>();
    map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, s);
    tts.speak(s, TextToSpeech.QUEUE_ADD, map);
  }

	public final static String EXTRA_MESSAGE2 = "com.example.carte.CHANGER";

  /**
   * `true` if TTS/STT features are enabled
   */
  boolean _isTTSEnabled = false;

  /**
   * Construit la liste des plats
   */
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

  /**
   * Speech stuff
   */
  TextToSpeech tts = null;
  private static final int VOICE_RECOGNITION_REQUEST_CODE = 1001;

  class UPL extends UtteranceProgressListener {
    /**
     * Called when the TTS engine started saying something
     * @param s
     */
    @Override
    public void onStart(String s) {  }

    /**
     * Called when the TTS engine finished saying something
     * @param s
     */
    @Override
    public void onDone(String s) {
      runOnUiThread(new Runnable() {
        @Override
        public void run() { stt_ask(); }
      });
    }

    /**
     * Called when the TTS engine stopped saying something
     * @param s
     */
    @Override
    public void onError(String s) {
      showToastMessage("Error...");
      stt_ask();
    }
  }

  /**
   * Returns the current TTS engine
   * @return a TTS engine
   */
  TextToSpeech getTts() {
    if (tts == null) {
      tts = new TextToSpeech(this, this);
    }
    return tts;
  }

  /**
   * Start the TTS engine
   */
  public void tts_start() {
    tts = getTts();
  }

  /**
   * Stop the TTS engine
   */
  public void tts_stop() {
    tts.shutdown();
    tts = null;
  }

  /**
   * Ask the user to say something!
   */
  public void stt_ask() {
    Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

    // Specify the calling package to identify your application
    intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getClass()
            .getPackage().getName());

    // Display an hint to the user about what he should say.
    intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Parlez!");

    // Given an hint to the recognizer about what the user is going to say
    //There are two form of language model available
    //1.LANGUAGE_MODEL_WEB_SEARCH : For short phrases
    //2.LANGUAGE_MODEL_FREE_FORM  : If not sure about the words or phrases and its domain.
    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);

    // Default language is FR
    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.FRENCH.toString());

    // Start the activity
    startActivityForResult(intent, VOICE_RECOGNITION_REQUEST_CODE);
  }

  /**
   * Called when the TTS engine finished its initialization
   * @param i
   */
  @Override
  public void onInit(int i) {
    tts.setLanguage(Locale.FRENCH);
    tts.setOnUtteranceProgressListener(new UPL());
    _isTTSEnabled = true;
    if (listener != null) listener.onSpeechInputInitialized();
  }

  /**
   * Called when the speech recognition activity ends
   * @param requestCode
   * @param resultCode
   * @param data
   */
  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == VOICE_RECOGNITION_REQUEST_CODE)

      //If Voice recognition is successful then it returns RESULT_OK
      if(resultCode == Activity.RESULT_OK) {

        ArrayList<String> textMatchList = data
                .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

        if (!textMatchList.isEmpty()) {
          // Log results
          Log.i("Speech-to-text", "Got result " + textMatchList.get(0));

          // Test on the first match
          if (listener == null || !listener.onSpeechInputFinished(textMatchList.get(0)))
            stt_ask();
        }

      } else if (resultCode == RecognizerIntent.RESULT_AUDIO_ERROR){
        showToastMessage("Audio Error");
        stt_ask();
      } else if (resultCode == RecognizerIntent.RESULT_CLIENT_ERROR){
        showToastMessage("Client Error");
        stt_ask();
      } else if (resultCode == RecognizerIntent.RESULT_NETWORK_ERROR){
        showToastMessage("Network Error");
        stt_ask();
      } else if (resultCode == RecognizerIntent.RESULT_NO_MATCH){
        showToastMessage("No Match");
        stt_ask();
      } else if (resultCode == RecognizerIntent.RESULT_SERVER_ERROR){
        showToastMessage("Server Error");
        stt_ask();
      }

    super.onActivityResult(requestCode, resultCode, data);
  }

  /**
   * Show a new toast message
   * @param message
   */
  public void showToastMessage(String message){
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
  }

  /**
   * Called when the activity is created
   * @param savedInstanceState
   */
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

  /**
   * Sélectionne la catégorie "Entrées"
   * @param view
   */
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

  /**
   * Sélectionne la catégorie "Desserts"
   * @param view
   */
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

  /**
   * Sélectionne la catégorie "Viandes"
   * @param view
   */
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

  /**
   * Sélectionne la catégorie "Boissons"
   * @param view
   */
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

  /**
   * Demande à l'utilisteur de confirmer la commande
   * @param view
   */
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

  /**
   * Annule la confirmation
   * @param v
   */
	public void confirmationNon(View v) {
		LeftMenuFragment left_frag = new LeftMenuFragment();
		
		getFragmentManager().beginTransaction()
			.replace(R.id.fragment_left, left_frag)
			.commit();
	}

  /**
   * Confirme la commande
   * @param v
   */
	public void confirmationOui(View v) {
		LeftMenuFragment left_frag = new LeftMenuFragment();
		Bundle args = new Bundle();
		args.putString("mode", "confirme");
		left_frag.setArguments(args);
		
		getFragmentManager().beginTransaction()
			.replace(R.id.fragment_left, left_frag)
			.commit();
	}

  public void miam(View v) {
    RightBonAppetitFragment rf = new RightBonAppetitFragment();

    getFragmentManager().beginTransaction()
            .replace(R.id.fragment_right, rf)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .addToBackStack("Bon appétit")
            .commit();
  }

  /**
   * Permet de sélectionner d'autres convives pour le paiement
   * @param view
   */
  public void selectionner_autres(View view) {
    RightOthersFragment rf = new RightOthersFragment();

    getFragmentManager().beginTransaction()
            .replace(R.id.fragment_right, rf)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .addToBackStack("Autres convives")
            .commit();
  }

  /**
   * Affiche la page de paiement
   * @param view
   */
	public void payer(View view) {
		RightPaymentFragment right_frag = new RightPaymentFragment();
		
		getFragmentManager().beginTransaction()
			.replace(R.id.fragment_right, right_frag)
			.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
		    .addToBackStack("Paiement")
			.commit();
	}

  /**
   * Crée le menu de l'application
   * @param menu
   * @return
   */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

  /**
   * Does something when a menu item is selected
   * @param item
   * @return
   */
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_tts:
        _isTTSEnabled = !_isTTSEnabled;
        if (_isTTSEnabled) tts_start();
        else tts_stop();
        return true;
    }

    return false;
  }

  public void userlogin(View v) {
    FragmentManager fm = getFragmentManager();

    FragmentTransaction ft = fm.beginTransaction();
    ft.replace(R.id.fragment_right, new RightCategoriesFragment());
    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
    ft.addToBackStack("Commander");
    ft.commit();
  }
}
