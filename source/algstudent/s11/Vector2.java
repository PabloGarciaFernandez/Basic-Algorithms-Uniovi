/*
JAVA is sensitive to upper and lower case.
A class should begin with a capital letter.
Methods and all kinds of variables should begin with lower case.
 
Java classes are stored in files with the same as the class, 
(to which we add the .java extension). Vector1.java in this case.

Packages must be in a directory path with the same name, that is,
package alg77777777.s1 should be in the directory alg77777777/s1.
*/
package algstudent.s11;

/**
 * This program is for working with vectors and see how Java programs work
 */
public class Vector2 {
	
	public static void main(String args[]) {
		int n = Integer.parseInt(args[0]);
		int[] v = new int[n];
		Vector1.fillIn(v);
		
		long sTime = System.currentTimeMillis();
		Vector1.sum(v);
		long eTime = System.currentTimeMillis() - sTime;
		
		System.out.println("Elapsed time in ms: " + eTime);
	}

}
