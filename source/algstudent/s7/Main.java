package algstudent.s7;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
	
	private static String nameList;
	private static List<Song> listOfSongs = new ArrayList<Song>();
	private static int lenghtOfBlocks;
	

	public static void main(String arg []) {
		String str1 = arg[0]; //name data file "List01.txt"
		String str2 = arg[1]; //length of the blocks of songs in minutes. Ex: 20
		
		if(str1 != "1") {
			nameList = Paths.get("").toAbsolutePath().toString() + "/source/algstudent/s7/" + str1;
			listOfSongs = readFile(nameList);
			lenghtOfBlocks = Integer.valueOf(str2);
		}
		else{
			getSongsRanomly(25);
			//getSongsRanomly(50);
			//getSongsRanomly(100);
			//getSongsRanomly(200);
			//getSongsRanomly(400);
			lenghtOfBlocks = lengthOfBlocks();
		}
		
		
		
		Node node = new MyNode(listOfSongs,lenghtOfBlocks*60,0);
		BestList listBesto = new BestList(node);
		long t1 = System.currentTimeMillis(); 
		listBesto.branchAndBound(node);
		long t2 = System.currentTimeMillis();
		
		System.out.println("TIME TOT EXECUTION: " + (t2-t1));
		
		listBesto.printSolutionTrace();
		
		System.out.println("");
		System.out.println("Generated nodes: " + listBesto.getGnodes());
		System.out.println("Processed nodes: " + listBesto.getPnodes());
		System.out.println("Trimmed nodes: " + listBesto.getTnodes());
	}
	
	private static int lengthOfBlocks() {
		int time = 0;
		for(Song so : listOfSongs) {
			time += so.getDuration().getTotSec();
		}
		time = (int) (time * 0.4 / 60);
		return time;
	}
	
	private static List<Song> readFile (String fileName) {

        List<Song> songs = new ArrayList<Song>();

        try {
               BufferedReader file = new BufferedReader(new FileReader(fileName));
               int size = Integer.valueOf( file.readLine());
               for (int i = 0; i < size; i++) {
            	   String[] spliting = file.readLine().split("\t");
           			String identifier = spliting[0];
           			String[] timeSplit = spliting[1].split(":");
           			int minutes = Integer.valueOf(timeSplit[0]);
           			int seconds = Integer.valueOf(timeSplit[1]);
           			int score = Integer.valueOf(spliting[2]);
           		
           			songs.add(new Song(identifier, minutes, seconds, score));
               }
                file.close();
        }
        catch (FileNotFoundException fnfe) {
          System.out.println("File not found.");
        }
        catch (IOException ioe) {
          new RuntimeException("I/O Error.");
        } 

        return songs;
    }
	
	private static void getSongsRanomly(int n) {
		/*
		 * Generates n random songs Song time generated according a normal distribution
		 * mean 2 mins and standard distribution 1 min (> 30 secs) Scores are generated
		 * according a normal distribution mean 2000 and standard distribution 1000 (>
		 * 300)
		 */
		int t_secs, score;
		Random rand = new Random();
		for (int i = 0; i < n; i++) {
			do {
				t_secs = (int) (rand.nextGaussian() * 120 + 60);
			} while (t_secs < 30);
			do {
				score = (int) (rand.nextGaussian() * 2000 + 1000);
			} while (score < 300);
			listOfSongs.add(new Song(String.valueOf(i), t_secs, score));
		}
	}
	
}
