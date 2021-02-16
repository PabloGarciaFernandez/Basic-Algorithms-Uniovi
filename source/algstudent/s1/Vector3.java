package algstudent.s1;

public class Vector3 {
	
	public static void main(String args[]) {
		int n = Integer.parseInt(args[0]);
		int[] v = new int[n*5];
		Vector1.fillIn(v);
		
		long sTime = System.currentTimeMillis();
		Vector1.sum(v);
		long eTime = System.currentTimeMillis() - sTime;
		
		System.out.println("Elapsed time in ms: " + eTime);
	}
}
