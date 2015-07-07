package com.jeden.loaddex;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;

import com.jeden.loaddex.tool.LDClassLoaderManager;

import dalvik.system.DexClassLoader;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class MainActivity extends Activity implements OnClickListener
{
	private static final String LD_LOAD_ACTIVITY_NAME = "com.jeden.loaddex.exec.LDLoadActivity";
	
	private static Class localClass;
	
	private static Object instance;
	
	public static Context context;
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		context = this;
		Log.v("jeden", "TestAActivity .. oncreate");
		Bundle paramBundle = new Bundle();
		paramBundle.putBoolean("KEY_START_FROM_OTHER_ACTIVITY", true);
		LoadAPK(paramBundle);
		super.onCreate(savedInstanceState);
	}

	public void LoadAPK(Bundle paramBundle)
	{
		DexClassLoader localDexClassLoader = LDClassLoaderManager.getInstance(context)
				.getClassLoader();
		if(null == localDexClassLoader)
		{
			finish();
			return;
		}
		try 
		{
			localClass = localDexClassLoader.loadClass(LD_LOAD_ACTIVITY_NAME);
			Constructor localConstructor = localClass
					.getConstructor(new Class[] {});
			instance = localConstructor.newInstance(new Object[] {});
			
			
			Method localMethodSetActivity = localClass.getDeclaredMethod(
					"setActivity", new Class[] { Activity.class });
			localMethodSetActivity.setAccessible(true);
			localMethodSetActivity.invoke(instance, new Object[] { this });

			
			
			Method methodonCreate = localClass.getDeclaredMethod(
					"onCreate", new Class[] { Bundle.class });
			methodonCreate.setAccessible(true);
			methodonCreate.invoke(instance, new Object[] { paramBundle });
			return;
		} 
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	@Override
	protected void onDestroy() 
	{
		super.onDestroy();
		try 
		{
			Method methodonCreate = localClass.getDeclaredMethod(
					"onDestroy", new Class[] {});
			methodonCreate.setAccessible(true);
			methodonCreate.invoke(instance);
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) 
	{
		try 
		{
			Method methodonCreate = localClass.getDeclaredMethod(
					"onKeyDown", new Class[] {int.class ,KeyEvent.class});
			methodonCreate.setAccessible(true);
			boolean result = (Boolean) methodonCreate.invoke(instance, new Object[] {keyCode ,event});
			return result;
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) 
	{
		try 
		{
			Method methodonCreate = localClass.getDeclaredMethod(
					"onKeyUp", new Class[] {int.class ,KeyEvent.class});
			methodonCreate.setAccessible(true);
			boolean result = (Boolean) methodonCreate.invoke(instance, new Object[] {keyCode ,event});
			return result;
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		return super.onKeyUp(keyCode, event);
	}

	@Override
	protected void onPause() 
	{
		super.onPause();
		try 
		{
			Method methodonCreate = localClass.getDeclaredMethod(
					"onPause", new Class[] {});
			methodonCreate.setAccessible(true);
			methodonCreate.invoke(instance);
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
	}

	@Override
	protected void onRestart() 
	{
		try 
		{
			Method methodonCreate = localClass.getDeclaredMethod(
					"onRestart", new Class[] {});
			methodonCreate.setAccessible(true);
			methodonCreate.invoke(instance);
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		super.onRestart();
	}

	@Override
	protected void onResume() 
	{
		try 
		{
			Method methodonCreate = localClass.getDeclaredMethod(
					"onResume", new Class[] {});
			methodonCreate.setAccessible(true);
			methodonCreate.invoke(instance);
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		super.onResume();
	}

	@Override
	protected void onStart() 
	{
		try 
		{
			Method methodonCreate = localClass.getDeclaredMethod(
					"onStart", new Class[] {});
			methodonCreate.setAccessible(true);
			methodonCreate.invoke(instance);
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		super.onStart();
	}

	@Override
	protected void onStop()
	{
		try 
		{
			Method methodonCreate = localClass.getDeclaredMethod(
					"onStop", new Class[] {});
			methodonCreate.setAccessible(true);
			methodonCreate.invoke(instance);
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		super.onStop();
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus)
	{
		try 
		{
			Method methodonCreate = localClass.getDeclaredMethod(
					"onWindowFocusChanged", new Class[] { boolean.class });
			methodonCreate.setAccessible(true);
			methodonCreate.invoke(instance, new Object[] {hasFocus});
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		super.onWindowFocusChanged(hasFocus);
	}

	@Override
	public void onClick(View v)
	{
		try 
		{
			Method methodonCreate = localClass.getDeclaredMethod(
					"onClick", new Class[] {View.class});
			methodonCreate.setAccessible(true);
			methodonCreate.invoke(instance, new Object[] {v});
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
	}
	
	public static Handler handler = new Handler()
	{
		public void handleMessage(Message msg)
		{
			try 
			{
				Method methodonCreate = localClass.getDeclaredMethod(
						"handler", new Class[] {Message.class});
				methodonCreate.setAccessible(true);
				methodonCreate.invoke(instance, new Object[] {msg});
			} 
			catch (Exception ex) 
			{
				ex.printStackTrace();
			}
			super.handleMessage(msg);
		}
	};
	
	public static MainActivity getMainContext()
	{
		return (MainActivity) context;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		try 
		{
			Method methodonCreate = localClass.getDeclaredMethod(
					"onActivityResult", new Class[] { int.class ,int.class ,Intent.class });
			methodonCreate.setAccessible(true);
			methodonCreate.invoke(instance, new Object[] {requestCode ,resultCode ,data});
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
}
