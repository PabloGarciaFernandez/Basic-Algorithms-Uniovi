package algstudent.s32;

import java.util.ArrayList;
import java.util.List;

public class Inversions {
	
	List<Integer> ranking = null;
	
	//a=2;b=2;k=1 //DIVISION
	
	public Inversions(List<Integer> ranking) {
		this.ranking = ranking;
	}

	public String start() {
		long counter = invertNlogN(ranking,0, ranking.size());
		return String.valueOf(counter);		
	}
	
	private long invertNlogN(List<Integer> list,int left, int right) {
		long counter = 0;
		if(left<right) {
			int pivot = (right+left)/2;
			counter += invertNlogN(list,left, pivot);
			counter += invertNlogN(list,pivot+1, right);
			counter += merge(list,left,pivot,pivot+1,right);
		}
		return counter;
	}
	
	
	
	private long merge(List<Integer> list, int x1, int x2, int y1, int y2)
    {
		long counter = 0;
		
		int sizeX = x2-x1+1;
		int sizeY = y2-y1+1;
		
		List<Integer> x = new ArrayList<Integer>();
		List<Integer> y = new ArrayList<Integer>();
		
		for(int i=0 ; i<sizeX ; i++) {
			x.add(list.get(x1+i));
		}
		
		for(int i=0 ; i<sizeY-1 ; i++) {
			y.add(list.get(y1+i));
		}
		
		int k = 0;
		int r = 0;
		while(!x.isEmpty() && !y.isEmpty()) {
			if(x.get(0) > y.get(0)) {
				list.set(x1+k, y.remove(0));
				r++;
				counter += (y1 + r) - (x1 + k);
			}
			else {
				list.set(x1+k, x.remove(0));
			}
			k++;
		}  
		
		
		while(!x.isEmpty()) {
			list.set(x1+k, x.remove(0));
			k++;
		}

		while(!y.isEmpty()) {
			list.set(x1+k, y.remove(0));
			k++;
		}
        
        return counter;
    }

}
