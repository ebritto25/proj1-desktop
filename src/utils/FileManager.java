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
    
	public static Object readFile(File file)
	{
            ObjectInputStream in = null;
            in = binaryReader(file);
            
            Object readObj = null;
            
            try
            {
                readObj = in.readObject();
            }
            catch(ClassNotFoundException ex)
            {
                System.err.println(ex.getMessage());   
            }
            catch(java.io.EOFException ex)
            {
                System.err.println(ex.getMessage());   
            }
            catch(IOException ex)
            {
                System.err.println(ex.getMessage());
            }
            finally
            {
                    return readObj;
            }
	}
	
	public static boolean writeFile(File file,Object obj, boolean append, boolean flush)
	{
            ObjectOutputStream out = null;
            out = binaryWriter(file,append);

            boolean ret = false;
            try
            {
                out.writeObject(obj);

                if(flush) out.flush();

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
