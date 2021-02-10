package logic;

import java.io.*;
import java.util.*;

public abstract class FileUtil {
	
public static void loadFile (String fileName, List<String> list) {
		
	    String line;
	    String[] productData= null;	   
	    
	    try {
	    	   BufferedReader file = new BufferedReader(new FileReader(fileName));
	    		while (file.ready()) {
	    			line = file.readLine();
	    			productData = line.split("\t");
	    			for(String i : productData) {
	    				list.add(i);
	    			}
	    		}
	    		file.close();
	    }
	    catch (FileNotFoundException fnfe) {
	      System.out.println("File not found.");
	    }
	    catch (IOException ioe) {
	      new RuntimeException("I/O Error.");
	    } 
	  }
}
