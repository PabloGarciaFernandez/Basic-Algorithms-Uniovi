package algstudent.s32;

import java.util.List;

public class InversionsQuadratic {
	
	List<Integer> ranking = null;
	
	public InversionsQuadratic(List<Integer> ranking) {
		this.ranking = ranking;
	}

	public String start() {
		long counter = 0;
		for(int i=0; i<ranking.size(); i++) {
			for(int j=0; j<ranking.size(); j++) {
				if(i<j && ranking.get(j)<ranking.get(i)) {
					int save = ranking.get(j);
					ranking.set(j, ranking.get(i));
					ranking.set(i, save);
					counter++;
				}
			}
		}
		return String.valueOf(counter);
	}

}
