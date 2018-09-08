package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
	
	public static String readFile(File file)
	{
		BufferedReader buffReader;
		StringBuilder text = new StringBuilder();
		
		try
		{
			buffReader = new BufferedReader(new FileReader(file));
			while(buffReader.ready())
			{
				text.append(buffReader.readLine());
			}
			buffReader.close();
		}
		catch(IOException ex)
		{
			System.err.println(ex.getMessage());
		}
		finally
		{
			return text.toString();
		}
	}
	
	public static boolean writeFile(File file,String texto, boolean append)
	{
		BufferedWriter buffWriter;
		boolean ret = false;
		try
		{
			
			buffWriter = new BufferedWriter(new FileWriter(file,append));
			buffWriter.write(texto);
			buffWriter.close();
			ret = true;
		}
		catch(IOException ex)
		{
			System.err.println(ex.getMessage());
		}
		finally
		{
			return ret;
		}
	}
}
