package com.example.mathpops;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.widget.ImageView;

public class YouWinActivity extends Activity implements OnClickListener {

	ImageView play, exit;
	
	/** Called when the activity is first created.
    This creates the view and assigns all the buttons to a variable */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_youwin);
	
		//Connect buttons to the display
		play = (ImageView) findViewById(R.id.imageView2);
		play.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		
		//Check which button was clicked
		switch (v.getId()) {
		
			//Play
			case R.id.imageView2: 
		        Intent i=new Intent(this, HomeActivity.class);
		        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		        startActivity(i);
		        finish();
				break;
		
		}//end switch
	}
}