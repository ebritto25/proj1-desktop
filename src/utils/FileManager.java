package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileManager {
	
    
	private static ObjectInputStream binaryReader(File file)
	{
		ObjectInputStream inputStream = null;

		try
		{
			FileInputStream fis = new FileInputStream(file);
			inputStream = new ObjectInputStream(fis);
		}
		catch(IOException ex)
		{
			System.out.println(ex.getMessage());
		}

		return inputStream;  
	}

	private static ObjectOutputStream binaryWriter(File file,boolean append)
	{
		ObjectOutputStream outputStream = null;

		try
		{
			FileOutputStream fos = null;

			if(file.exists())
			{
				fos = new FileOutputStream(file,append);
				outputStream = new ObjectOutputStream(fos)
				{
					@Override
					protected void writeStreamHeader()
					{
					}
				};
			}
			else
			{
				fos = new FileOutputStream(file,append);
				outputStream = new ObjectOutputStream(fos);
			}

		}
		catch(IOException ex)
		{
			System.out.println(ex.getMessage());
		}

		return outputStream;  
	}
    
	public static ArrayList readFile(File file)
	{
		ObjectInputStream in = null;
		in = binaryReader(file);

		ArrayList objs = new ArrayList();

		try
		{
			try{
				do{
					objs.add(in.readObject());
				}while(true);
			}
			catch(java.io.EOFException ex)
			{
				System.err.println("Fim do arquivo " + ex);
			}
		}
		catch(ClassNotFoundException | IOException ex)
		{
			System.err.println(ex.getMessage());   
		}
		finally
		{
			return objs;
		}
	}
	
	public static boolean writeFile(File file,Object obj, boolean append)
	{
		ObjectOutputStream out = null;
		out = binaryWriter(file,append);

		boolean ret = true;
		try
		{
			out.writeObject(obj);

			//if(flush) out.flush();

		}
		catch(IOException ex)
		{
			System.err.println(ex.getMessage());
			ret = false;
		}
		finally
		{
			try
			{
				out.close();
			}
			catch(IOException ex)
			{
				System.err.println(ex.getMessage());
			}
			return ret;
		}
	}
	
	public static boolean deleteFile(File file)
	{
		if(file.exists())
		{
			try
			{
				file.delete();
				file.createNewFile();
			}
			catch(IOException ex)
			{
				System.err.println(ex.getMessage());
				return false;
			}
			return true;
		}
		return false;
	}
	
}
