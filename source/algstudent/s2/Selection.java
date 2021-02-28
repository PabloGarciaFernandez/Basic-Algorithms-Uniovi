package algstudent.s2;

/* This program can be used to sort n elements with 
 * a "bad" algorithm (quadratic). 
 * It is the SELECTION */
public class Selection extends Vector {
	public Selection(int nElements) {
		super(nElements);
	}
	
	@Override
	public void sort() {
		int posMin;
		
		for(int i=0 ; i<this.elements.length-1 ; i++) {
			posMin = findPosMin(i);
			super.interchange(i, posMin);
		}
	}  
	
	private int findPosMin(int init) {
		int index = init;
		int max = Integer.MAX_VALUE;
		for(int i=init ; i<elements.length ; i++) {
			if(elements[i] < max) {
				max = elements[i];
				index = i;
			}
		}
		return index;
	}
	
	@Override
	public String getName() {
		return "Selection";
	} 
} 
