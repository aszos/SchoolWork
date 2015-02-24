package com.aszostek.SimpleSimon.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.aszostek.SimpleSimon.SimpleSimon;

public class AndroidLauncher extends AndroidApplication 
{
	protected void onCreate (Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
	   
		//disable Accelerometer and Compass to reduce battery consumption
		config.useAccelerometer = false;
	    config.useCompass = false;
		
		initialize(new SimpleSimon(), config);
	}
}
