package com.example.carte;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

class MyButtonPayment {
	public int imageResId;
	public String title;
	public String comment;
	
	public MyButtonPayment(int imageResId, String title, String comment) {
		this.imageResId = imageResId;
		this.title = title;
		this.comment = comment;
	}
}

class MyAdapterPayment extends ArrayAdapter<MyButtonPayment> {
	public MyButtonPayment[] buttons;
	protected Context ctx;
	
	public MyAdapterPayment(Context ctx, MyButtonPayment[] buttons) {
		super(ctx, R.layout.button_payment, buttons);
		this.ctx = ctx;
		this.buttons = buttons;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		
		if (row == null) {
			LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(R.layout.button_payment, parent, false);
		}
		
		((ImageView)row.findViewById(R.id.image_paiement)).setImageResource(this.buttons[position].imageResId);
		((TextView)row.findViewById(R.id.texte_paiement)).setText(this.buttons[position].title);
		((TextView)row.findViewById(R.id.description_paiement)).setText(this.buttons[position].comment);
		
		return row;
	}
}


public class RightPaymentFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.frag_right_payment, container, false);
		
		Context c = this.getActivity();
		MyButtonPayment buttons[] = {
			new MyButtonPayment(R.drawable.ic_creditcard, "Carte bancaire", "Passez-la dans le lecteur."),
			new MyButtonPayment(R.drawable.ic_card, "Carte de fidélité", "Passez-la dans le lecteur"),
			new MyButtonPayment(R.drawable.ic_phone, "Vous avez l'app ?", "Scannez le code sur la table !"),
			new MyButtonPayment(R.drawable.ic_service, "Autres?", "Appel à un serveur")
		};
		((GridView)v.findViewById(R.id.list)).setAdapter(new MyAdapterPayment(c, buttons));
		
		return v;
	}
}