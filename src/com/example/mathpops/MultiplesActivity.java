package com.example.mathpops;


import java.util.Timer;
import java.util.TimerTask;

import backend.Calculations;
import backend.RNG;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MultiplesActivity extends Activity implements OnClickListener {
	
	/** Generate the base number*/
	int number = RNG.randInt(2, 20);
	
	/** Generate range of possible balloon values*/
	int [] balloonValues = generateValues(number);
	
	//Initialize balloons
	Balloon b1 = new Balloon(1, pickValue(balloonValues));//Top left balloon
	Balloon b2 = new Balloon(2, pickValue(balloonValues));//Center balloon
	Balloon b3 = new Balloon(3, pickValue(balloonValues));//Top right balloon
	Balloon b4 = new Balloon(4, pickValue(balloonValues));//Bottom left balloon
	Balloon b5 = new Balloon(5, pickValue(balloonValues));//Bottom right balloon
	
	//Timer Variables
	Timer myTimer = new Timer();
	int i=30;
	TextView time, timeTxt;
	final Handler myHandler = new Handler();
	
	//Score Variables
	int score = 0;
	int inc = 10;
	int winningScore= 100;
	TextView dispScore;

	
	//Declare ImageView, Button, and TextView variables
	TextView baseNo, text1, text2, text3, text4, text5;
	TextView feedbackmsg;
	Button reset;

	//Initialize effect variables
	ImageView img_pop1, img_pop2, img_pop3, img_pop4, img_pop5;	
	Handler delay;
	MediaPlayer pop_sound;
	int pop_id1, pop_id2, pop_id3, pop_id4, pop_id5;
	int id1, id2, id3, id4, id5;
	
	
	
	/** Called when the activity is first created.
	    This creates the view and assigns all the buttons to a variable */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_multiples);
		
		
		//Assign base number to TextView
		baseNo = (TextView) findViewById(R.id.baseNumber);
		baseNo.setText(Integer.toString(number));
		
		//Assign each image variable to an TextView
		text1 = (TextView) findViewById(R.id.value1); //Center balloon
		text2 = (TextView) findViewById(R.id.value2); //Top left balloon
		text3 = (TextView) findViewById(R.id.value3); //Top right balloon
		text4 = (TextView) findViewById(R.id.value4);; //Bottom left balloon
		text5 = (TextView) findViewById(R.id.value5);; //Bottom right balloon
		
		//Make each TextView a button
		text1.setOnClickListener(this);
		text2.setOnClickListener(this);
		text3.setOnClickListener(this);
		text4.setOnClickListener(this);
		text5.setOnClickListener(this);
		
		//Set values displayed on TextViews/balloons
		text1.setText(Integer.toString(b1.getValue()));
		text2.setText(Integer.toString(b2.getValue()));
		text3.setText(Integer.toString(b3.getValue()));
		text4.setText(Integer.toString(b4.getValue()));
		text5.setText(Integer.toString(b5.getValue()));
		
		//Connect reset button to the display
		reset = (Button) findViewById(R.id.button1);
		reset.setOnClickListener(this);
		
		//Connect Feedback msg to the display
		feedbackmsg = (TextView) findViewById(R.id.textView1);

		//Connect Score to the Display
		dispScore = (TextView) findViewById(R.id.score);
		
		//Setup Timer
		time=(TextView)findViewById(R.id.timer);
		timeTxt=(TextView)findViewById(R.id.timer_txt);
	    myTimer.schedule(new TimerTask() {
	         @Override
	         public void run() {UpdateGUI();}
	      }, 0, 1000);
		
		//Effect stuff
		//Set up pop delay and popping sound
		delay = new Handler();
		Context appContext = getApplicationContext();
		pop_sound = MediaPlayer.create(appContext, R.raw.pop_sound);
		
		//Assign each popping animation to an ImageView
		img_pop1 = (ImageView) findViewById(R.id.imageView1); //Center balloon
		img_pop1.setVisibility(View.GONE);
		img_pop2 = (ImageView) findViewById(R.id.imageView2); //Top left balloon
		img_pop2.setVisibility(View.GONE);
		img_pop3 = (ImageView) findViewById(R.id.imageView3); //Top right balloon
		img_pop3.setVisibility(View.GONE);
		img_pop4 = (ImageView) findViewById(R.id.imageView4); //Bottom left balloon
		img_pop4.setVisibility(View.GONE);
		img_pop5 = (ImageView) findViewById(R.id.imageView5); //Bottom right balloon
		img_pop5.setVisibility(View.GONE);
		
		//Get identifiers for later use
		id1 = getResources().getIdentifier(b1.get_color(), "drawable", getPackageName());
		id2 = getResources().getIdentifier(b2.get_color(), "drawable", getPackageName());
		id3 = getResources().getIdentifier(b3.get_color(), "drawable", getPackageName());
		id4 = getResources().getIdentifier(b4.get_color(), "drawable", getPackageName());
		id5 = getResources().getIdentifier(b5.get_color(), "drawable", getPackageName());
		pop_id1 = getResources().getIdentifier(b1.get_pop_color(), "drawable", getPackageName());
		pop_id2 = getResources().getIdentifier(b2.get_pop_color(), "drawable", getPackageName());
		pop_id3 = getResources().getIdentifier(b3.get_pop_color(), "drawable", getPackageName());
		pop_id4 = getResources().getIdentifier(b4.get_pop_color(), "drawable", getPackageName());
		pop_id5 = getResources().getIdentifier(b5.get_pop_color(), "drawable", getPackageName());
	}

	
	
	//Set Timer
		private void UpdateGUI() {
		      i--;
		      myHandler.post(myRunnable);
		   }

		final Runnable myRunnable = new Runnable() {
		      public void run() {
		    	  if (i==0) //once time's up
		    	  {
		    		  if (score >= winningScore)
		    		  {
		    			  time.setVisibility(View.INVISIBLE);
		    			  timeTxt.setText("You Win!");    		  
		    			  myHandler.removeCallbacksAndMessages(myRunnable);
		    			  myTimer.cancel();
		    			  myTimer.purge();
		    			  Intent intent1 = new Intent (getApplicationContext(), YouWinActivity.class);
		    			  startActivity(intent1);
		    			  finish();
		    		  }
		    		  else //if score is less than winning score 
		    		  {
		    			  time.setVisibility(View.INVISIBLE);
		    			  timeTxt.setText("Time's Up");    		  
		    			  myHandler.removeCallbacksAndMessages(myRunnable);
		    			  myTimer.cancel();
		    			  myTimer.purge();
		    			  Intent intent2 = new Intent (getApplicationContext(), GameOverActivity.class);
		    			  startActivity(intent2);
		    			  finish();
		    		  }
		    	  }
		    	  
		    	  else	  
		         time.setText(String.valueOf(i));
		      }
		   };

	
	
	//Set delays for each balloon instance
	Runnable rpop1 = new Runnable() {
	    @Override
	    public void run(){
			img_pop1.setVisibility(View.GONE);
			pop_sound.pause();
			pop_sound.seekTo(0);
	    }
	};
	Runnable rpop2 = new Runnable() {
	    @Override
	    public void run(){
			img_pop2.setVisibility(View.GONE);
			pop_sound.pause();
			pop_sound.seekTo(0);
	    }
	};
	Runnable rpop3 = new Runnable() {
	    @Override
	    public void run(){
			img_pop3.setVisibility(View.GONE);
			pop_sound.pause();
			pop_sound.seekTo(0);
	    }
	};
	Runnable rpop4 = new Runnable() {
	    @Override
	    public void run(){
			img_pop4.setVisibility(View.GONE);
			pop_sound.pause();
			pop_sound.seekTo(0);
	    }
	};
	Runnable rpop5 = new Runnable() {
	    @Override
	    public void run(){
			img_pop5.setVisibility(View.GONE);
			pop_sound.pause();
			pop_sound.seekTo(0);
	    }
	};
	Runnable r1 = new Runnable() {
	    @Override
	    public void run(){
			b1.updateValue(RNG.randInt(1,200));
			text1.setBackgroundResource(id1);
			text1.setText(Integer.toString(b1.getValue()));
			text1.setVisibility(View.VISIBLE);
	    }
	};
	Runnable r2 = new Runnable() {
	    @Override
	    public void run(){
			b2.updateValue(RNG.randInt(1,200));
			text2.setBackgroundResource(id2);
			text2.setText(Integer.toString(b2.getValue()));
			text2.setVisibility(View.VISIBLE);
	    }
	};
	Runnable r3 = new Runnable() {
	    @Override
	    public void run(){
			b3.updateValue(RNG.randInt(1,200));
			text3.setBackgroundResource(id3);
			text3.setText(Integer.toString(b3.getValue()));
			text3.setVisibility(View.VISIBLE);
	    }
	};
	Runnable r4 = new Runnable() {
	    @Override
	    public void run(){
			b4.updateValue(RNG.randInt(1,200));
			text4.setBackgroundResource(id4);
			text4.setText(Integer.toString(b4.getValue()));
			text4.setVisibility(View.VISIBLE);
	    }
	};
	Runnable r5 = new Runnable() {
	    @Override
	    public void run(){
			b5.updateValue(RNG.randInt(1,200));
			text5.setBackgroundResource(id5);
			text5.setText(Integer.toString(b5.getValue()));
			text5.setVisibility(View.VISIBLE);
	    }
	};
	
	
	
	/** This provides the controls for every button when they are pressed */
	@Override
	public void onClick(View v) {
		
		/*Generate new balloon values for when they are popped*/
		balloonValues = generateValues(number);
		
		//Pop balloons and check if their value is a multiple
		switch (v.getId()) {
		case R.id.value1: {
			text1.setVisibility(View.GONE);
			//Check if value of balloon is a multiple of base number
			if (b1.getValue()% number == 0)
			{
				feedbackmsg.setText("Correct!");
				score += inc;
				dispScore.setText(Integer.toString(score));
			}
			else //value of balloon is not a multiple
				feedbackmsg.setText("Wrong!");
			
			//Popping Animation
			img_pop1 = (ImageView) findViewById(R.id.imageView1); 
			img_pop1.setVisibility(View.VISIBLE);
			pop_sound.start();
			//update balloon value and make it reappear
			delay.postDelayed(rpop1, 159);
			delay.postDelayed(r1, 500);
			break;
		}
		case R.id.value2: {
			text2.setVisibility(View.GONE);
			if (b2.getValue()% number == 0)
			{
				feedbackmsg.setText("Correct!");
				score += inc;
				dispScore.setText(Integer.toString(score));
			}
			else //value of balloon is not a multiple
				feedbackmsg.setText("Wrong!");
			
			//Popping Animation
			img_pop2 = (ImageView) findViewById(R.id.imageView2); 
			img_pop2.setVisibility(View.VISIBLE);
			pop_sound.start();
			//update balloon value and make it reappear
			delay.postDelayed(rpop2, 159);
			delay.postDelayed(r2, 500);
			break;
		}
		case R.id.value3: {
			text3.setVisibility(View.GONE);
			if (b3.getValue()% number == 0)
			{
				feedbackmsg.setText("Correct!");
				score += inc;
				dispScore.setText(Integer.toString(score));
			}
			else //value of balloon is not a multiple
				feedbackmsg.setText("Wrong!");
			
			//Popping Animation
			img_pop3 = (ImageView) findViewById(R.id.imageView3); 
			img_pop3.setVisibility(View.VISIBLE);
			pop_sound.start();
			//update balloon value and make it reappear
			delay.postDelayed(rpop3, 159);
			delay.postDelayed(r3, 500);
			break;
		}
		case R.id.value4: {
			text4.setVisibility(View.GONE);
			if (b4.getValue()% number == 0)
			{
				feedbackmsg.setText("Correct!");
				score += inc;
				dispScore.setText(Integer.toString(score));
			}
			else //value of balloon is not a multiple
				feedbackmsg.setText("Wrong!");
			
			//Popping Animation
			img_pop4 = (ImageView) findViewById(R.id.imageView4); 
			img_pop4.setVisibility(View.VISIBLE);
			pop_sound.start();
			//update balloon value and make it reappear
			delay.postDelayed(rpop4, 159);
			delay.postDelayed(r4, 500);
			break;
		}
		case R.id.value5: {
			text5.setVisibility(View.GONE);
			if (b5.getValue()% number == 0)
			{
				feedbackmsg.setText("Correct!");
				score += inc;
				dispScore.setText(Integer.toString(score));
			}
			else //value of balloon is not a multiple
				feedbackmsg.setText("Wrong!");
			
			//Popping Animation
			img_pop5 = (ImageView) findViewById(R.id.imageView5); 
			img_pop5.setVisibility(View.VISIBLE);
			pop_sound.start();
			//update balloon value and make it reappear
			delay.postDelayed(rpop5, 159);
			delay.postDelayed(r5, 500);
			break;
		}
		
		//Controls for the reset button
		case R.id.button1: {
			// Generate a new base number
			number = RNG.randInt(2, 20);
			baseNo.setText(Integer.toString(number));
			
			//Generate new range of possible balloon values
			balloonValues = generateValues(number);
			
			//Update balloon objects 
			b1.updateValue(pickValue(balloonValues));
			b2.updateValue(pickValue(balloonValues));
			b3.updateValue(pickValue(balloonValues));
			b4.updateValue(pickValue(balloonValues));
			b5.updateValue(pickValue(balloonValues));
			
			//Reset values displayed on balloons
			text1.setText(Integer.toString(b1.getValue()));
			text2.setText(Integer.toString(b2.getValue()));
			text3.setText(Integer.toString(b3.getValue()));
			text4.setText(Integer.toString(b4.getValue()));
			text5.setText(Integer.toString(b5.getValue()));
			
			//Reset value of feedback message
			feedbackmsg.setText("");
			
			//Restore balloons to the View
			text1.setVisibility(View.VISIBLE);
			text2.setVisibility(View.VISIBLE);
			text3.setVisibility(View.VISIBLE);
			text4.setVisibility(View.VISIBLE);
			text5.setVisibility(View.VISIBLE);
			break;
		}
		}
	}
	
	/** Goes back to home activity when back button is pressed*/
	public void onBackPressed()
	{
		myTimer.cancel();
		myTimer.purge();
		myHandler.removeCallbacks(myRunnable);
		finish();
	}
	
	
	/** Generates possible sets of balloon values*/ 
	public static int[] generateValues(int x){
		/*Generate values that are multiples of the base number*/
		int val1 = Calculations.generateMultiple(x, RNG.randInt(2,6));
		int val2 = Calculations.generateMultiple(x, RNG.randInt(2,6));
		int val3 = Calculations.generateMultiple(x, RNG.randInt(2,6));
		int val4 = Calculations.generateMultiple(x, RNG.randInt(2,6));
		int val5 = Calculations.generateMultiple(x, RNG.randInt(2,6));
		int val6 = RNG.randInt(20, 400);
		int val7 = RNG.randInt(20, 400);
		int val8 = RNG.randInt(20, 400);
		int val9 = RNG.randInt(20, 400);
		int val10 = RNG.randInt(20, 400);

		int [] values = {val1, val10, val2, val9, val3, val8, val4, val7, val5, val6};
		
		return values;
	}
	
	/*Chooses a random element from an array of possible values*/
	public static int pickValue(int [] values){
		int index = RNG.randInt(0, 9);
		int value = values[index];
		return value;
	}
	
}