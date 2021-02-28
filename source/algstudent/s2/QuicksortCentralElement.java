package algstudent.s2;


/* This program can be used to sort n elements with 
 * the best algorithm of this lab. It is the QUICKSORT */
public class QuicksortCentralElement extends Vector {
	
	public QuicksortCentralElement(int nElements) {
		super(nElements);
	}
	
	private void quickSort(int left, int right) {
		int i = left;
		int j = right-1;
		int pivot;
		
		if(left < right) {
			int center = median_of_three(left , right);
			
			if((right - left) >= 3) {
				pivot = elements[center];
				super.interchange(center, right);
				
				do {
					while(elements[i] <= pivot && i < right) i++;
					while(elements[j] >= pivot && j > left) j--;
					if(i<j) super.interchange(i, j);
				} while(i<j);
				
				super.interchange(i, right);
				quickSort(left, i-1);
				quickSort(i+1, right);
			}
		}
	}
	
	private int median_of_three(int left, int right) { 
		int center = (left + right) / 2;
		if (elements[left] > elements[center])
			interchange(left, center);
		if (elements[left] > elements[right])
			interchange(left, right);
		if (elements[center] > elements[right])
			interchange(center, right);
		return center;
	}

	@Override
	public void sort() {
		quickSort(0, elements.length-1);		
	}
	
	@Override
	public String getName() {
		return "Quicksort - Central element";
	} 
} 
