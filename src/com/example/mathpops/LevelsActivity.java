package com.example.mathpops;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class LevelsActivity extends Activity implements OnClickListener {

	
	ImageView multiples, sum, primes;
	

	/** Called when the activity is first created.
	    This creates the view and assigns all the buttons to a variable */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_levels);
	
		//Connect buttons to the display
		sum = (ImageView) findViewById(R.id.sum_button);
		sum.setOnClickListener(this);
		
		multiples = (ImageView) findViewById(R.id.multiples_button);
		multiples.setOnClickListener(this);
		
		primes = (ImageView) findViewById(R.id.primes_button);
		primes.setOnClickListener(this);
		
	}

	/** This provides the controls for every button when they are pressed */
	@Override
	public void onClick(View v) {
		
		//Check which button was clicked
		switch (v.getId()) {
		
			//Summation/subtraction
			case R.id.sum_button: 
				Intent intent1 = new Intent (getApplicationContext(), SumActivity.class);
				startActivity(intent1);
				finish();
				break;
			
			//Multiplication
			case R.id.multiples_button: 
				Intent intent2 = new Intent (getApplicationContext(), MultiplesActivity.class);
				startActivity(intent2);
				finish();
				break;
				
			//Prime Numbers	
			case R.id.primes_button: 
				Intent intent4 = new Intent (getApplicationContext(), PrimesActivity.class);
				startActivity(intent4);
				finish();
				break;	
		
		}//end switch
	}
}