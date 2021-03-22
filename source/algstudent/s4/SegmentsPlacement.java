package algstudent.s4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class SegmentsPlacement {

	public static List<Integer> list = null;
	
	public static void main(String args[]) {
		list = readGameFile(Paths.get("").toAbsolutePath().toString() + "/source/algstudent/s4/game1.txt");
		System.out.println("Game1");
		list.remove(0);
		greedy1();
		greedy2();
		greedy3();
		list = readGameFile(Paths.get("").toAbsolutePath().toString() + "/source/algstudent/s4/game2.txt");
		System.out.println("Game2");
		greedy1();
		greedy2();
		greedy3();
	}
	
	public SegmentsPlacement(List<Integer> list) {
		SegmentsPlacement.list = list;
	}
	
	public static void greedy1() {
		double poofs = 0.0;
		int aux = 0;
		
		for(int i=0 ; i<list.size() ; i++) {
			poofs += (aux + (aux + list.get(i)))/2.0;
			aux += list.get(i);
		}
		
		//System.out.println("Cost greedy 1 = " + poofs + " pufosos");
	}
	
	public static void greedy2() {
		Collections.sort(list);	
		Collections.reverse(list);
		
		double poofs = 0.0;
		int aux = 0;
		
		for(int i=0 ; i<list.size() ; i++) {
			poofs += (aux + (aux + list.get(i)))/2.0;
			aux += list.get(i);
		}
		
		//System.out.println("Cost greedy 2 = " + poofs + " pufosos");
	}
	
	public static void greedy3() {
		Collections.sort(list);
		
		double poofs = 0.0;
		int aux = 0;
		
		for(int i=0 ; i<list.size() ; i++) {
			poofs += (aux + (aux + list.get(i)))/2.0;
			aux += list.get(i);
		}
		
		//System.out.println("Cost greedy 3 = " + poofs + " pufosos");
	}
	
	public static List<Integer> readGameFile(String fileName) {
        List<Integer> res = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) { // Streams in Java 8
            stream.forEach(line -> res.add(Integer.valueOf(line)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }
}
