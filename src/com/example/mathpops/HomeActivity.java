package com.example.mathpops;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.os.Bundle;
import android.widget.ImageView;

public class HomeActivity extends Activity implements OnClickListener {

	ImageView play, instructions;
	
	/** Called when the activity is first created.
    This creates the view and assigns all the buttons to a variable */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
	
		//Connect buttons to the display
		play = (ImageView) findViewById(R.id.imageView2);
		play.setOnClickListener(this);
		
		instructions = (ImageView) findViewById(R.id.imageView3);
		instructions.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		
		//Check which button was clicked
		switch (v.getId()) {
		
			//Play
			case R.id.imageView2: 
				Intent intent1 = new Intent (getApplicationContext(), LevelsActivity.class);
				startActivity(intent1);
				break;
			
			//Instructions
			case R.id.imageView3: 
				Intent intent2 = new Intent (getApplicationContext(), InstructionsActivity.class);
				startActivity(intent2);
				break;
		}//end switch
	}
}
