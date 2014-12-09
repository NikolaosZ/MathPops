package backend;

import android.os.CountDownTimer;


public class myCountDownTimer extends CountDownTimer{
	
	//private final long startTime = 30 * 1000;
	//private final long interval = 1 * 1000;
	public myCountDownTimer(long startTime, long interval) {
		super(startTime, interval);
	}

	  @Override

	  public void onTick(long millisUntilFinished) {
		//  text.setText("" + millisUntilFinished / 1000);
	  }

	@Override
	public void onFinish() {
		// TODO Auto-generated method stub
		
	}

 }
