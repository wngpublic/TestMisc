import java.util.*;

public class SDynamic {
	public static void p(String f, Object ...a) {
		System.out.printf(f, a);
	}
	public List<Integer> findMaxSubArray(List<Integer> l) {
		if(l.size() == 0) {
			return null;
		}
		int idxStart = 0;
		int idxEnd = 0;
		int idxStartBest = 0;
		int idxEndBest = 0;
		int sum = l.get(idxStart);
		int maxsum = sum;
		for(int i = 1; i < l.size(); i++) {
			int v = l.get(i);
			int tmpsum = sum + v;
			p("i:%2d v:%3d sum:%3d tmpsum:%3d ", i,v,sum,tmpsum);
			if(v >= tmpsum) {
				idxStart = i;
				idxEnd = i;
				sum = v;
			}
			else {
				sum = tmpsum;
			}
			if(maxsum < sum) {
				maxsum = sum;
				idxEnd = i;
				idxStartBest = idxStart;
				idxEndBest = idxEnd;
			}
			p("maxsum:%3d ibeg:%2d iend:%2d\n", maxsum,idxStart,idxEnd);
		}
		List<Integer> listresult = new ArrayList<>();
		for(int i = idxStartBest; i <= idxEndBest; i++) {
			listresult.add(l.get(i));
		}
		return listresult;
	}
	public Integer findMaxSubArrayValue(List<Integer> l) {
		if(l.size() == 0) {
			return null;
		}
		int sum = l.get(0);
		int maxsum = sum;
		for(int i = 1; i < l.size(); i++) {
			int v = l.get(i);
			if(v > (sum + v)) {
				sum = v;
			}
			else {
				sum += v;
			}
			if(sum > maxsum) {
				maxsum = sum;
			}
		}
		return maxsum;
	}
}
