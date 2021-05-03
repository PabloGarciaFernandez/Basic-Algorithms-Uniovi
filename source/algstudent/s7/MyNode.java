package algstudent.s7;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MyNode extends Node{

	private List<Song> song;
	private List<Song> listA = new ArrayList<Song>();
	private List<Song> listB = new ArrayList<Song>();
	private int maxSecs;
	
	public MyNode(List<Song> song, List<Song> listA, List<Song> listB, int maxSecs, int level, UUID parentID) {
		this.song = song;
		this.listA = listA;
		this.listB = listB;
		this.maxSecs = maxSecs;
		this.depth = level;
		this.parentID = parentID;
		
		calculateHeuristicValue();
	}
	
	public MyNode(List<Song> song,int maxSecs, int level) {
		this.song = song;
		this.maxSecs = maxSecs;
		this.depth = level;		
	}
	
	@Override
	public void calculateHeuristicValue() {
		if(prune()) {
			heuristicValue = Integer.MAX_VALUE;
			
		}
		else {
			heuristicValue = -totScore();
		}
	}

	@Override
	public ArrayList<Node> expand() {
		ArrayList<Node> result=new ArrayList<Node>();
		
		if(depth < song.size()) {
			for(int i=0 ; i<3 ; i++) {
				if(i==0) {
					result.add(new MyNode(new ArrayList<Song>(song), new ArrayList<Song>(listA), new ArrayList<Song>(listB), maxSecs, depth+1, this.getID()));
				}
				if(i==1) {
					listA.add(song.get(depth));
					result.add(new MyNode(new ArrayList<Song>(song), new ArrayList<Song>(listA), new ArrayList<Song>(listB), maxSecs, depth+1, this.getID()));
					listA.remove(song.get(depth));
				}
				if(i==2) {
					listB.add(song.get(depth));
					result.add(new MyNode(new ArrayList<Song>(song), new ArrayList<Song>(listA), new ArrayList<Song>(listB), maxSecs, depth+1, this.getID()));
					listB.remove(song.get(depth));
				}
			}
		}		
		
		return result;
	}
	
	private int totScore() {
		int point = 0;
		
		if(listA != null) {
			for(Song so : listA){
				point += so.getScore();
			}
		}
		
		if(listB != null) {
			for(Song so : listB){
				point += so.getScore();
			}
		}
		
		return point;
	}
	
	public String toString() {
		String res = "Lenght of the blocks: " + maxSecs/60 + ":0\n";
		res += "Total score: " + totScore() + "\n\n\n";
		res += "Best block A:\n";
		for(Song so : listA) {
			res += "id: " + so.getIdentifier() + " seconds: " + getMinutes(so) + " score: " + so.getScore() + "\n";
		}
		
		res += "\n\n";
		res += "Best block B:\n";
		
		for(Song so : listB) {
			res += "id: " + so.getIdentifier() + " seconds: " + getMinutes(so) + " score: " + so.getScore() + "\n";
		}
		
		res += "\n";
		
		return res;
    }
	
	private String getMinutes(Song so) {
		String res = "";
		if(so.getDuration().getSeconds() < 10) {
			res += "0" + so.getDuration().getSeconds();
		}
		else {
			res += so.getDuration().getSeconds();
		}
		
		return res;
	}
	
	private boolean prune() {
		double a = 0;
		double b = 0;
		
		if(listA != null) {
			for(Song so : listA){
				a += so.getDuration().getTotSec();
			}
		}
		
		if(listB != null) {
			for(Song so : listB){
				b += so.getDuration().getTotSec();
			}
		}	
		
		if(a > maxSecs || b > maxSecs) {
			return true;
		}
		
		return false;
	}

	@Override
	public boolean isSolution() {
		return depth == (song.size()-1);
	}

}
