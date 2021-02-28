package algstudent.s2;

/* This program can be used to sort n elements with 
 * a "bad" algorithm (quadratic). 
 * It is the BUBBLE or DIRECT EXCHANGE */
public class Bubble extends Vector {
	public Bubble(int nElements) {
		super(nElements);
	}

	@Override
	public void sort() {
		int i = 1;
		boolean hasChange = true;
		
		while(hasChange && (i < this.elements.length)){
			hasChange = false;
			for(int j=this.elements.length-1 ; j >= i ; j-- ) {
				if(elements[j-1] > elements[j]) {
					super.interchange(j-1, j);
					hasChange = true;
				}
			}
			i++;
		}
	}  
	
	@Override
	public String getName() {
		return "Bubble";
	} 
} 

