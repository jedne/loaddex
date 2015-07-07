package com.jeden.loaddex.exec;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

public class LDLoadActivity 
{
	public Activity activity = null;
	
	public void setActivity(Activity activity)
	{
		this.activity = activity;
		Log.v("jeden", "LDLoadActivity  setActivity");
	}
	
	public void onCreate(Bundle savedInstanceState) 
	{
		Log.v("jeden", "LDLoadActivity  onCreate");
	}

	protected void onDestroy() 
	{
		Log.v("jeden", "LDLoadActivity  onDestroy");
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) 
	{
		Log.v("jeden", "LDLoadActivity  onKeyDown");
		return false;
	}

	public boolean onKeyUp(int keyCode, KeyEvent event) 
	{
		Log.v("jeden", "LDLoadActivity  onKeyUp");
		return false;
	}

	protected void onPause() 
	{
		Log.v("jeden", "LDLoadActivity  onPause");
	}

	protected void onRestart() 
	{
		Log.v("jeden", "LDLoadActivity  onRestart");
	}

	protected void onResume() 
	{
		Log.v("jeden", "LDLoadActivity  onResume");
	}

	protected void onStart() 
	{
		Log.v("jeden", "LDLoadActivity  onStart");
	}

	protected void onStop()
	{
		Log.v("jeden", "LDLoadActivity  onStop");
	}

	public void onWindowFocusChanged(boolean hasFocus)
	{
		Log.v("jeden", "LDLoadActivity  onWindowFocusChanged");
	}

	public void onClick(View v)
	{
		Log.v("jeden", "LDLoadActivity  onClick");
	}
	
	public void handler(Message msg)
	{
		Log.v("jeden", "LDLoadActivity  handler");
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		Log.v("jeden", "LDLoadActivity  onActivityResult");
	}
}
