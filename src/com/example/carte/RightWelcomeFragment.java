package com.example.carte;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.GridView;
import android.widget.ArrayAdapter;

class MyButton {
	public int imageResId;
	public String title;
	
	public MyButton(int imageResId, String title) {
		this.imageResId = imageResId;
		this.title = title;
	}
}

class MyAdapter extends ArrayAdapter<MyButton> {
	public MyButton[] buttons;
	protected Context ctx;
	
	public MyAdapter(Context ctx, MyButton[] buttons) {
		super(ctx, R.layout.button_large, buttons);
		this.ctx = ctx;
		this.buttons = buttons;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		
		if (row == null) {
			LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(R.layout.button_large, parent, false);
		}
		
		((ImageView)row.findViewById(R.id.image)).setImageResource(this.buttons[position].imageResId);
		((TextView)row.findViewById(R.id.text)).setText(this.buttons[position].title);
		
		return row;
	}
}

public class RightWelcomeFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.frag_right_welcome, container, false);
		
		Context c = this.getActivity();
		MyButton buttons[] = {
			new MyButton(R.drawable.ic_order, "Commander"),
			new MyButton(R.drawable.ic_card, "Client régulier?")
		};
		((GridView)v.findViewById(R.id.list)).setAdapter(new MyAdapter(c, buttons));
		
		return v;
	}
}