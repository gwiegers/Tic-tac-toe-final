package com.example.tictactoepuppy;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.os.Build;
import android.widget.Button;

public class StartMenu extends ActionBarActivity {

	private int diffLevel;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_start_menu);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.start_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    public void onClickEasy(View view) {
        // Do something in response to button click
    	Intent intent = new Intent(this, Gameplay.class);
		intent.putExtra("diffLevel", 1);
		startActivity(intent); 	     	 
    }

    public void onClickMedium(View view) {
        // Do something in response to button click
    	Intent intent = new Intent(this, Gameplay.class);
		intent.putExtra("diffLevel", 2);
		startActivity(intent); 	     	 
    }

    public void onClickHard(View view) {
        // Do something in response to button click
    	Intent intent = new Intent(this, Gameplay.class);
		intent.putExtra("diffLevel", 3);
		startActivity(intent); 		     	 
    }
}



