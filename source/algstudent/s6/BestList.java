package algstudent.s6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class BestList {

	private static String nameList;
	private static List<Song> listOfSongs = null;
	private static int numSongs;
	private static int lenghtOfBlocks;
	private static int totScore = 0;
	private static int counter = 0;
	
	private static List<Song> listA = null;
	private static List<Song> listB = null;
	
	public static void main(String arg []) {
		String str1 = arg[0]; //name data file "List01.txt"
		String str2 = arg[1]; //length of the blocks of songs in minutes. Ex: 20
		
		nameList = Paths.get("").toAbsolutePath().toString() + "/source/algstudent/s6/" + str1;
		listOfSongs = importSongs();
		lenghtOfBlocks = Integer.valueOf(str2);
		
		listA = new ArrayList<Song>();
		listB = new ArrayList<Song>();
		
		System.out.println("Number of songs: " + numSongs + "\n\n");
		System.out.println("List of songs:");
		
		for (Song so : listOfSongs){
			System.out.print("id: " + so.getIdentifier() + " seconds: " + so.getDuration().getMinutes() + ":");
			if(so.getDuration().getSeconds() < 10) {
				System.out.println("0" + so.getDuration().getSeconds() + " score: " + so.getScore());
			}
			else {
				System.out.println(so.getDuration().getSeconds() + " score: " + so.getScore());
			}			
		}
		
		backtracking(0);
		
		totScore = tot();
		
		System.out.println("\n\n" + "Length of the blocks: " + lenghtOfBlocks + ":0");
		System.out.println("Total score: " + totScore);
		System.out.println("Total counter: " + counter);	
		
		System.out.println("\n\n" + "Best block A:");
		printList(listA);		
		
		System.out.println("\n\n" + "Best block B:");
		printList(listB);
	}
	
	private static List<Song> importSongs() {
		List<Song> res = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(nameList))) { // Streams in Java 8
            stream.forEach(line -> res.add(parse(line)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        res.remove(0);
        return res;
	}
	
	private static void printList(List<Song> list) {
		for (Song so : list){
			System.out.print("id: " + so.getIdentifier() + " seconds: " + so.getDuration().getMinutes() + ":");
			if(so.getDuration().getSeconds() < 10) {
				System.out.println("0" + so.getDuration().getSeconds() + " score: " + so.getScore());
			}
			else {
				System.out.println(so.getDuration().getSeconds() + " score: " + so.getScore());
			}
		}
	}
	
	private static Song parse(String value) {
		try {
			if(Integer.valueOf(value) instanceof Integer) {
				numSongs = Integer.valueOf(value);
				return null;
			}
		} catch(NumberFormatException e) {
			
		}
		
		String[] spliting = value.split("\t");
		String identifier = spliting[0];
		String[] timeSplit = spliting[1].split(":");
		int minutes = Integer.valueOf(timeSplit[0]);
		int seconds = Integer.valueOf(timeSplit[1]);
		int score = Integer.valueOf(spliting[2]);
		
		return new Song(identifier, minutes, seconds, score);
	}
	
	private static int tot() {
		int a = 0;
		for (Song so : listA){
			a += so.getScore();
		}
		
		for (Song so : listB){
			a += so.getScore();
		}
		return a;
	}
	
	private static void backtracking(int level) {
		if(level == numSongs) {
			
		}
		else {
			for(int i=0 ; i<3; i++) {
				if(i == 0) {
					backtracking(level + 1);
					counter++;
				}
				else if(i == 1) {
					Song song = listOfSongs.get(level);
					
					if(!listA.contains(song) && !listB.contains(song)) {
						listA.add(song);
						
						if(minuteLimit(listA)) {
							
							int test = compareScore(listA, song);
							if(test != -1) {
								listA.remove(test);
							}
							
							else {
								listA.remove(listA.size()-1);
							}
						}
						backtracking(level + 1);
						counter++;	
					}					
				}
				else if(i == 2) {
					Song song = listOfSongs.get(level);
					
 					if(!listB.contains(song) && !listA.contains(song)) {
						listB.add(song);
						
						if(minuteLimit(listB)) {
							
							int test = compareScore(listB, song);
							if(test != -1) {
								listB.remove(test);
							}
							
							else {
								listB.remove(listB.size()-1);
							}
						}	
						backtracking(level + 1);
						counter++;
					}
				}
			}
		}
	}
	
	private static boolean minuteLimit(List<Song> list) {
		boolean res = true;
		int seconds = 0;
		for(Song so : list) {
			seconds += so.getDuration().getTotSec();
		}
		if(seconds <= (lenghtOfBlocks*60)) {
			res = false;
		}
		return res;
	}
	
//	private static int compareScore(List<Song> listN, Song song) {
//		List<Song> list = listN;
//		int initialScore = song.getScore();
//		int minNum = -1;
//		
//		for(int i=0 ; i<list.size() ;i++) {
//			if(initialScore > list.get(i).getScore()) {
//				initialScore = list.get(i).getScore();
//				minNum = i;
//			}
//		}
//		
//		if(minNum == -1) {
//			return minNum;
//		}
//		
//		Song backup = list.get(minNum);
//		
//		if(minuteLimit(list)) {
//			if(song.getScore() > backup.getScore()) {
//				list.remove(backup);
//				if(minuteLimit(list)) {
//					return -1;
//				}
//				else {
//					return minNum;
//				}
//			}
//		}
//		return -1;
//	}
//}
	
	private static int compareScore(List<Song> listN, Song song) {
		List<Song> list = listN;
		List<Song> listD = new ArrayList<Song>();
		int initialScore = song.getScore();
		
		for(int i=0 ; i<list.size() ;i++) {
			if(initialScore > list.get(i).getScore()) {
				listD.add(list.get(i));
			}
		}
		
		for(int i=0 ; i<listD.size() ;i++) {
			if(minuteLimit(list)) {
				if(song.getScore() > listD.get(i).getScore()) {
					list.remove(listD.get(i));
					if(minuteLimit(list)) {
						return -1;
					}
					else {
						return i;
					}
				}
			}
		}
		
		return -1;
	}
}
