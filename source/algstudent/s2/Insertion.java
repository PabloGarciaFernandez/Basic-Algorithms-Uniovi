package algstudent.s2;


/* This program can be used to sort n elements with 
 * a "bad" algorithm (quadratic). 
 * It is the DIRECT INSERTION */
public class Insertion extends Vector {	
	public Insertion(int nElements) {
		super(nElements);
	}

	@Override
	public void sort(){
		int j;
		int pivot;
		
		for(int i=0 ; i<this.elements.length ; i++) {
			pivot = this.elements[i];
			j = i-1;
			
			while(j >= 0 && pivot < this.elements[j]) {
				this.elements[j+1] = elements[j];
				j--;
			}
			
			elements[j+1] = pivot;
		}
	} 
	
	@Override
	public String getName() {
		return "Insertion";
	} 
} 
