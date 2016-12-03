/**
*Successor with delete. Given a set of N integers S={0,1,...,N−1} and a sequence of
*requests of the following form:
*Remove x from S
*Find the successor of x: the smallest y in S such that y≥x.
*design a data type so that all operations (except construction) should take logarithmic 
*ime or better.
*/
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class SuccessorWithDelete {
	
	private final int[] s;
	private final int[] root;

	// the input Array is a sorted array.
	public SuccessorWithDelete(int[] inputArray) {
		Arrays.sort(inputArray);
		this.s = initializer(inputArray);
		this.root = initializer(inputArray);
	}

	private int[] initializer(int[] inputArray) {
		int[] res = new int[inputArray.length];
		for(int i = 0; i < inputArray.length; i++) {
			res[i] = inputArray[i];
		}
		return res;	
	}

	public void remove(int num) {
		root[num] = root(num + 1);

	}

	private int root(int num) {
		Queue<Integer> pathObj = new LinkedList<>();
		while(num != root[num]) {
			pathObj.add(num);
			num = root[num];
		}
		reRoot(pathObj, num);
		return num;
	}

	private void reRoot(Queue<Integer> pathObj, int targetRoot) {
		while(!pathObj.isEmpty()) {
			int obj = pathObj.poll();
			root[obj] = targetRoot;
		}
	}


}