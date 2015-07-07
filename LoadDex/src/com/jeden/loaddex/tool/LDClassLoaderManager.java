package com.jeden.loaddex.tool;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.util.Log;
import dalvik.system.DexClassLoader;

public class LDClassLoaderManager 
{
	private static final String LD_JAR_NAME = "jedenld.jar";
	
	private static final String LD_DEX_NAME = "jedenld.dex";
	
	private DexClassLoader classloader = null;
	
	public String dexpath = null;
	
	public String dexoutputpath = null;
	
	private static Context context;
	
	private static LDClassLoaderManager instance = null;
	
	private LDClassLoaderManager()
	{
		CreateClassLoader();
	}
	
	/**
	 * 获取当前类的实例(至少需要调用一次)
	 * 
	 * @param context
	 * 				上下文环境
	 * @return	实例
	 */
	public static LDClassLoaderManager getInstance(Context context)
	{
		LDClassLoaderManager.context = context;
		if(null == instance)
		{
			instance = new LDClassLoaderManager();
		}
		
		return instance;
	}
	
	/**
	 * 获取当前类的实例
	 * 
	 * @param context
	 * 				上下文环境
	 * @return	实例
	 */
	public static LDClassLoaderManager getInstance()
	{
		if(null == instance)
		{
			Log.e("jeden", "not init with context!");
			return null;
		}
		return instance;
	}
	
	/**
	 * 获取当前的classloader
	 */
	public DexClassLoader getClassLoader()
	{
		return classloader;
	}
	
	/**
	 * 创建一个新的classloader
	 */
	public boolean CreateClassLoader()
	{
		if(!getFilePath())
		{
			return false;
		}
//		ClassLoader localClassLoader = ClassLoader.getSystemClassLoader();
//		ClassLoader localClassLoader = context.getClassLoader();
		ClassLoader localClassLoader = LDClassLoaderManager.class.getClassLoader();
		DexClassLoader localDexClassLoader = new DexClassLoader(dexpath,
				dexoutputpath, null, localClassLoader);
		classloader = localDexClassLoader;
		return true;
	}
	
	/**
	 * 获取jar的路径信息
	 * 
	 * @return
	 * 		是否获取成功
	 */
	public boolean getFilePath()
	{
		String path = getSysFilePathHeader(context);
		dexpath = path+File.separator + LD_JAR_NAME;
		File file = new File(dexpath);
//		if(!file.exists())
		{
			CopyAssetsFile(context, LD_JAR_NAME, path);	
		}
		
		dexoutputpath = path+File.separator;
		file = new File(dexoutputpath + LD_DEX_NAME);
		if(file.exists())
		{
			deleteFileOnly(file);
		}
		return true;
	}
	
	/**
	 * 获取系统内部根目录路径（无论T卡是否存在）
	 */
	public String getSysFilePathHeader(Context context)
	{
		String path;
		File file =context.getApplicationContext().getFilesDir();
		if (null == file)
		{
			return File.separator;
		}
		path = file.getPath();

		return path;
	}
	
	/**
	 * 只删除文件
	 * 
	 * @param file
	 * 			文件路径（也可以是文件夹）
	 */
	public void deleteFileOnly(File file)
	{
        if(file.isFile())
        {
            file.delete();
            return;
        }
        if(file.isDirectory())
        {
            File[] childFile = file.listFiles();
            for(File f : childFile)
            {
            	deleteFileOnly(f);
            }
        }
    }
	
	/**
	 * 从assets下复制文件到指定目录
	 * 
	 * @param context
	 *            程序运行环境
	 * @param fileName
	 *            需要复制的文件
	 * @param dir
	 *            需要保存的目录
	 * @return 是否复制成功
	 */
	public static boolean CopyAssetsFile(Context context, String fileName,
			String dir)
	{
		File mWorkingPath = new File(dir);
		// 如果文件路径不存在
		if (!mWorkingPath.exists())
		{
			// 创建文件夹
			if (!mWorkingPath.mkdirs())
			{
				return false;
			}
		}

		File outFile = new File(mWorkingPath, fileName);
		if (outFile.exists())
		{
			outFile.delete();
		}

		InputStream in = null;
		OutputStream out = null;
		try
		{
			in = context.getAssets().open(fileName);
			out = new FileOutputStream(outFile);
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0)
			{
				out.write(buf, 0, len);
			}
			return true;
		}
		catch (IOException e)
		{
			return false;
		}
		finally
		{
			if (in != null)
			{
				try
				{
					in.close();
				}
				catch (IOException e)
				{
				}
			}

			if (out != null)
			{
				try
				{
					out.close();
				}
				catch (IOException e)
				{
				}
			}
		}
	}
}
