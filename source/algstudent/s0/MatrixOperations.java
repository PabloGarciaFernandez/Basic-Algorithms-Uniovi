package algstudent.s0;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import logic.FileUtil;

public class MatrixOperations {
	private int n;
	private int min;
	private int max;
	private int[][] matrix;
	List<String> list;

	public MatrixOperations(int n, int min, int max) {
		this.min = min;
		this.n = n;
		this.max = max;
		fill();
	}
	
	public MatrixOperations(String fileName) {
		list = new ArrayList();
		FileUtil.loadFile(fileName, list);
		
		n = Integer.valueOf(list.get(0));
		matrix = new int[n][n];
		int num = 1;
		
		for(int i=0 ; i<n ; i++) {
			for(int j=0 ; j<n ; j++) {
				matrix[i][j] = Integer.valueOf(list.get(num));
				num++;
			}
		}
	}
	
	private void fill() {
		matrix = new int[n][n];
		for(int i=0 ; i<n ; i++) {
			for(int j=0 ; j<n ; j++) {
				matrix[i][j] = random();
			}
		}
	}
	
	private int random() {
		Random ran = new Random();
		return ran.nextInt(max + 1) + min;
	}
	
	public int getSize() {
		return n;
	}
	
	public void write() {
		for(int i=0 ; i<n ; i++) {
			for(int j=0 ; j<n ; j++) {
				System.out.print(matrix[i][j] + "\t");
				if(j == n-1) {
					System.out.println("");
				}
			}
		}
	}
	
	public int sumDiagonal1() {
		int num = 0;
		for(int i=0 ; i<n ; i++) {
			for(int j=0 ; j<n ; j++) {
				if(i == j) {
					num += matrix[i][j];
				}
			}
		}
		return num;
	}
	
	public int sumDiagonal2() {
		int num = 0;
		for(int i=0 ; i<n ; i++) {
			for(int j=0 ; j<n ; j++) {
				if(i + j == n-1) {
					num += matrix[i][j];
				}
			}
		}
		return num;
	}
	
	public void travelPath(int i, int j) {
		travel(i,j,0);
	}
	
	private int travel(int i, int j, int num) {
		int numb = num;
		if(matrix[i][j] == 1) {
			if(i-1 < 0) {
				numb = travel(i-1,j,num++);
				return numb;
			}			
		}
		if(matrix[i][j] == 2) {
			if(j+1 > n) {
				numb = travel(i,j+1,num++);
				return numb;
			}
		}
		if(matrix[i][j] == 3) {
			if(i+1 > n) {
				numb = travel(i+1,j,num++);
				return numb;
			}
		}
		if(matrix[i][j] == 4) {
			if(j-1 < 0) {
				numb = travel(i,j-1,num++);
				return numb;
			}
		}
		return numb;
	}
}
