package algstudent.s1;

public class Vector4 {
	
	public static void main(String args[]) {
		int n = Integer.parseInt(args[0]);
		int[] v = new int[n]; //EJECUTAR VARIAS VECES y obtener una media.
		long fTime = System.currentTimeMillis();
		for(int i=0 ; i<10000000 ; i++) {
			Vector1.fillIn(v);
		}
		long feTime = System.currentTimeMillis() - fTime;
		//feTime = feTime/10000000;
		
		long sTime = System.currentTimeMillis();
		for(int i=0 ; i<10000000 ; i++) {
			Vector1.sum(v);
		}		
		long seTime = System.currentTimeMillis() - sTime;
		//seTime = seTime/10000000;
		
		long mTime = System.currentTimeMillis();
		for(int i=0 ; i<10000000 ; i++) {
			Vector1.maximum(v, new int[2]);
		}		
		long meTime = System.currentTimeMillis() - mTime;
		//meTime = meTime/10000000;
		
		System.out.println("Elapsed sum time in ms: " + seTime);
		System.out.println("Elapsed maximum time in ms: " + meTime);
		System.out.println("Elapsed fillIn time in ms: " + feTime);
	}
}
