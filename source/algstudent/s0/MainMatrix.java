package algstudent.s0;

import algstudent.s1.Vector1;

public class MainMatrix {
	
	private static MatrixOperations matrix = null;

	public static void main(String[] args) {
		matrix = new MatrixOperations(Integer.parseInt(args[0]),0,100);

//		long mTime = System.currentTimeMillis();
//		for(int i=0 ; i<1000000 ; i++) {
//			matrix.sumDiagonal1();
//		}
//		long meTime = System.currentTimeMillis() - mTime;
//		
//		System.out.println("Elapsed sumDiagonal1 time in ms: " + meTime);
		
		long sTime = System.currentTimeMillis();
		for(int i=0 ; i<1000000 ; i++) {
			matrix.sumDiagonal2();
		}
		long seTime = System.currentTimeMillis() - sTime;
		
		System.out.println("Elapsed sumDiagonal2 time in ms: " + seTime);
	}

}
