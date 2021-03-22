package algstudent.s4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SegmentsPlacementTimes {
	
	private static int nTimes = 100000;
	private static int[] n = {100,200,400,800,1600,3200,6400,12800};
	private static List<Integer> list = null;
	
	public static void main(String args[]) {
		Random random = new Random();
		for(int i=0 ; i<n.length ; i++) {
			System.out.println("SIZE OF N = " + n[i]);
			
			list = new ArrayList();
			
			for(int j=0 ; j<n[i] ; j++) {
				list.add(random.nextInt(101));
			}
			SegmentsPlacement seg = new SegmentsPlacement(list);
			System.out.println("=========================");
			
			System.out.println("***GREEDY 1***");
			long start = System.currentTimeMillis();
			for(int j=0 ; j<nTimes ; j++) {
				seg.greedy1();
			}
			long end = System.currentTimeMillis();
			System.out.println("Greedy1 Time = " + (end-start));
			
			System.out.println("***GREEDY 2***");
			start = System.currentTimeMillis();
			for(int j=0 ; j<nTimes ; j++) {
				seg.greedy2();
			}
			end = System.currentTimeMillis();
			System.out.println("Greedy2 Time = " + (end-start));
			
			System.out.println("***GREEDY 3***");
			start = System.currentTimeMillis();
			for(int j=0 ; j<nTimes ; j++) {
				seg.greedy3();
			}
			end = System.currentTimeMillis();
			System.out.println("Greedy3 Time = " + (end-start));
			System.out.println("=========================");
		}
	}
}
