package com.example.mathpops;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class InstructionsActivity extends Activity implements OnClickListener {

	
	ImageView left, right, instruction;
	

	/** Called when the activity is first created.
	    This creates the view and assigns all the buttons to a variable */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_instructions);
	
		//Connect buttons to the display
		left = (ImageView) findViewById(R.id.left_button);
		left.setOnClickListener(this);
		left.setVisibility(View.GONE); //Initially hidden
		
		right = (ImageView) findViewById(R.id.right_button);
		right.setOnClickListener(this);
		
		//Initially the controls page
		instruction = (ImageView) findViewById(R.id.display);
		
	}

	/** This provides the controls for every button when they are pressed */
	@Override
	public void onClick(View v) {
		
		//Check which button was clicked
		switch (v.getId()) {
		
			//Go from game mode descriptions to controls
			case R.id.left_button: 
				instruction.setImageResource(R.drawable.controls);
				right.setVisibility(View.VISIBLE);
				left.setVisibility(View.GONE);
				break;
			
			//Go from controls to game mode descriptions
			case R.id.right_button: 
				instruction.setImageResource(R.drawable.description);
				left.setVisibility(View.VISIBLE);
				right.setVisibility(View.GONE);
				break;
		
		}//end switch
	}
}