/**
*Union-find with specific canonical element. Add a method find() to the union-find data type so that 
*find(i) returns the largest element in the connected component containing i. The operations, union(), 
*onnected(), and find() should all take logarithmic time or better.
*For example, if one of the connected components is {1,2,6,9}, then the find() method should return 9
*for each of the four elements in the connected components.
*/

public class UFwithCanonicalElement {
	
	private final int[] id;
	private final int[] sizeOfTree;
	private final int[] largestElement;
	private int numberOfTree;

	public UFwithCanonicalElement(int numberOfUsers) {
		this.id = new int[numberOfUsers];
		idInitializer();
		this.sizeOfTree = new int[numberOfUsers];
		treeSizeInitializer();
		this.numberOfTree = numberOfUsers;
		this.largestElement = new int[numberOfUsers];
		largestElementInitializer();
	}

	private void idInitializer() {
		for(int i = 0; i < id.length; i++) {
			id[i] = i;
		}
	}

	private void largestElementInitializer() {
		for(int i = 0; i < largestElement.length; i++) {
			largestElement[i] = i;
		}
	}

	private void treeSizeInitializer() {
		for(int i = 0; i < sizeOfTree.length; i++) {
			sizeOfTree[i] = 1;
		}
	}

	public boolean find(int user1, int user2) {
		return root(user1) == root(user2);
	}

	public int find(int user) {
		return largestElement[root(user)];
	}

	private int root(int user) {
		while(id[user] != user) {
			user = id[user];
		}
		return user;
	}

	public boolean connected(int user1, int user2) {
		return root(user1) == root(user2);
	} 
	
	public void union(int user1, int user2) {
		int root1 = root(user1);
		int root2 = root(user2);
		if(sizeOfTree[root1] < sizeOfTree[root2]) {
			id[root1] = root2;
			sizeOfTree[root2] += sizeOfTree[root1];
			largestElementUpdater(root1, root2);
		}
		else{
			id[root2] = root1;
			sizeOfTree[root1] += sizeOfTree[root2];
			largestElementUpdater(root2, root1);
		}
	}

	public boolean allAreFriends() {
		return numberOfTree == 1;
	}

	private void largestElementUpdater(int smallerRoot, int largerRoot) {
		if(largestElement[smallerRoot] > largestElement[largerRoot]) {
			largestElement[largerRoot]  = largestElement[smallerRoot];
		}
	}
}
