package com.example.carte;



import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class Preparation extends Activity {
	public  final static String EXTRA_MESSAGE1 = "com.example.carte.PAYER";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_preparation);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.preparation, menu);
		return true;
	}
	
    public void payer(View view){
    	Intent intent = new Intent(this, RightWelcomeFragment.class);
    	EditText  prix= (EditText) findViewById(R.id.prix);
    	String price = prix.getText().toString();
    	intent.putExtra(EXTRA_MESSAGE1, price);
    	startActivity(intent);
    }

}
