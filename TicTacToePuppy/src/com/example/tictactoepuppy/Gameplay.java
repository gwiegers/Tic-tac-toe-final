package com.example.tictactoepuppy;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View.OnTouchListener;
import android.widget.RelativeLayout;
import android.support.v4.app.DialogFragment;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;

import java.util.Arrays;
import java.util.Random;


public class Gameplay extends ActionBarActivity {

	private boolean userTurn = false;
	private int grid[][];
	private Random rand;
	private int placing;
	private ImageView image;
	private int turn;
	private int diffLevel;
	private int[] used = new int[9];
	private Dialog dialog;
	private RelativeLayout layout;
	private Handler mHandler = new Handler();
	private int win;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gameplay);
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		rand = new Random();
		Intent intent = getIntent();
        diffLevel  = intent.getIntExtra("diffLevel", 2);
		settingUp();
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_gameplay,
			container, false);
			return rootView;
		}
	}
	
	public void settingUp() {
		turn = 0; //rand.nextInt(2);
		for (int i = 0; i > used.length; i++) {
				used[i] = 0;
		}
		if (turn == 0 ) {
			userTurn = true;
		}
		else {
			switch (diffLevel) {
				case 1:
					compEasy();
				case 2:
					compMedium();
				case 3:
					compHard();	
			}
		}
	}
	
	public void playGame() {
		if (userTurn == true) {
			userTurn = false;
			switch (diffLevel) {
				case 1:
					compEasy();
				case 2:
					compMedium();
				case 3:
					compHard();		
			}
		}
		else {
			userTurn = true;
		}
	}
	
	public void compEasy() {
		placing = rand.nextInt(8);
		while (used[placing] != 0) {
			placing = rand.nextInt(8);
		}
		compPlace(placing);
	}
	
	public void compMedium() {
		if (diffLevel == 2) {
		StepCalculator sc = new StepCalculator();
		placing = sc.place(used);
        compPlace(placing);
		}
	}
	
	public void compHard() {
		
	}
	
	public void compPlace(int place) {
		placing = place;
		switch (placing) {
		case 0:
			image = (ImageView)findViewById(R.id.x0);
			image.setVisibility(0);
			break;
		case 1:
			image = (ImageView)findViewById(R.id.x1);
			image.setVisibility(0);
			break;
		case 2:
			image = (ImageView)findViewById(R.id.x2);
			image.setVisibility(0);
			break;
		case 3:
			image = (ImageView)findViewById(R.id.x3);
			image.setVisibility(0);
			break;
		case 4:
			image = (ImageView)findViewById(R.id.x4);
			image.setVisibility(0);
			break;
		case 5:
			image = (ImageView)findViewById(R.id.x5);
			image.setVisibility(0);
			break;
		case 6:
			image = (ImageView)findViewById(R.id.x6);
			image.setVisibility(0);
			break;
		case 7:
			image = (ImageView)findViewById(R.id.x7);
			image.setVisibility(0);
			break;
		case 8:
			image = (ImageView)findViewById(R.id.x8);
			image.setVisibility(0);
			break;
		}
		used[placing] = 2;
		//check for win
		checkWin();
	}
	
	public void clickPlace(View view) {
		if (userTurn == true) {
			switch (view.getId()) {
				case R.id.place0:
					if (used[0] == 0) {
					placing = 0;
					image = (ImageView)findViewById(R.id.o0);
					image.setVisibility(0);
					break;
					}
					return;
				case R.id.place1:
					if (used[1] == 0) {
					placing = 1;
					image = (ImageView)findViewById(R.id.o1);
					image.setVisibility(0);
					break;
					}
					return;
				case R.id.place2:
					if (used[2] == 0) {
					placing = 2;
					image = (ImageView)findViewById(R.id.o2);
					image.setVisibility(0);
					break;
					}
					return;
				case R.id.place3:
					if (used[3] == 0) {
					placing = 3;
					image = (ImageView)findViewById(R.id.o3);
					image.setVisibility(0);
					break; 
					} 
					return;
				case R.id.place4:
					if (used[4] == 0) {
					placing = 4;
					image = (ImageView)findViewById(R.id.o4);
					image.setVisibility(0);
					break;
					}
					return;
				case R.id.place5:
					if (used[5] == 0) {
					placing = 5;
					image = (ImageView)findViewById(R.id.o5);
					image.setVisibility(0);
					break;
					}
					return;
				case R.id.place6:
					if (used[6] == 0) {
					placing = 6;
					image = (ImageView)findViewById(R.id.o6);
					image.setVisibility(0);
					break;
					}
					return; 
				case R.id.place7:
					if (used[7] == 0) {
					placing = 7;
					image = (ImageView)findViewById(R.id.o7);
					image.setVisibility(0);
					break;
					}
					return; 
				case R.id.place8:
					if (used[8] == 0) {
					placing = 8;
					image = (ImageView)findViewById(R.id.o8);
					image.setVisibility(0);
					break;
					}
					return;
			}
			used[placing] = 1;
			checkWin();
		}
    }

	public void checkWin() {
		win = 0;
		
		if ((placing == 2 || placing == 5 || placing == 8) && used[placing - 1] == used[placing] && used[placing - 2] == used[placing]) {
			win = 4;
		}
		else if ((placing == 1 || placing == 4|| placing == 7) && used[placing - 1] == used[placing] && used[placing + 1] == used[placing]) {
			win = 4;
		}
		else if ((placing == 0 || placing == 3 || placing == 6) && used[placing + 1] == used[placing] && used[placing + 2] == used[placing]) { 
			win = 4;
		}
		else if ((placing == 6 || placing == 7 || placing == 8) && used[placing - 3] == used[placing] && used[placing - 6] == used[placing]) {
			win = 4;
		}
		else if ((placing == 3 || placing == 4 || placing == 5) && used[placing - 3] == used[placing] && used[placing + 3] == used[placing]) {
			win = 4;
		}
		else if ((placing == 0 || placing == 1 || placing == 2) && used[placing + 3] == used[placing] && used[placing + 6] == used[placing]) {
			win = 4;
		}
		else if (used[4] == used[placing] && used[0] == used[placing] && used[8] == used[placing]) {
			win = 4;
		}
		else if (used[4] == used[placing] && used[2] == used[placing] && used[6] == used[placing]) { 
			win = 4;
		}		
		if (win == 4) {
			userTurn = false;
			win = used[placing];
	    	mHandler.postDelayed(new Runnable() {
		    public void run() {
		    	gameOver(win);
		    	}
		    }, 3000);			
		}
     	else if (!contain(used, 0)) {
     		userTurn = false;
			win = 3;
	    	mHandler.postDelayed(new Runnable() {
		    public void run() {
		    	gameOver(win);
		        }
		    }, 3000);
		}
     	else {
     		playGame();
     	}
	}			

	public void gameOver(int win) {
		Dialog dialog = new Dialog(Gameplay.this, R.style.Dialog);
        dialog.setContentView(R.layout.customalert);
        TextView text = (TextView) dialog.findViewById(R.id.textView1);
        ImageView image = (ImageView) dialog.findViewById(R.id.image);
        if (win == 1) {
        	text.setText("You won :D!");
        	image.setImageResource(R.drawable.win);
        }
        else if(win == 2) {
        	text.setText("You lost :(");
            image.setImageResource(R.drawable.lost);
        }
        else if(win == 3) {
        	text.setText("You tied :|");
            image.setImageResource(R.drawable.tie);	
        }
        Button button = (Button) dialog.findViewById(R.id.search_mini);
        button.setOnClickListener(new OnClickListener() {
        @Override
            public void onClick(View v) {
        		Intent intent = new Intent(Gameplay.this, Reset.class);
        		intent.putExtra("diffLevel", diffLevel);
        		startActivity(intent); 	
            }
        });
		dialog.show();	
	}
	
	public void clickMenu(View view) {
		Intent intent = new Intent(this, StartMenu.class);
		startActivity(intent); 	
	}
	    
	public boolean contain(int[] array, int num){
		for (int i = 0; i < array.length; i++) {
			if (array[i] == num) {
				return true;
			}
		}
		return false;
	}
}


