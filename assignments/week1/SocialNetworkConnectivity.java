/**
*Social network connectivity. Given a social network containing n members and a log file containing m 
*timestamps at which times pairs of members formed friendships, design an algorithm to determine 
*the earliest time at which all members are connected (i.e., every member is a friend of a friend of a 
*riend ... of a friend). Assume that the log file is sorted by timestamp and that friendship is an 
*equivalence relation. The running time of your algorithm should be mlogn or better and use extra space 
*portional to n.
*/

public class SocialNetworkConnectivity {
	
	private final int[] id;
	private final int[] sizeOfTree;
	private int numberOfTree;

	public SocialNetworkConnectivity(int numberOfUsers) {
		this.id = new int[numberOfUsers];
		idInitializer();
		this.sizeOfTree = new int[numberOfUsers];
		treeSizeInitializer();
		this.numberOfTree = numberOfUsers;
	}

	private void idInitializer() {
		for(int i = 0; i < id.length; i++) {
			id[i] = i;
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
		}
		else{
			id[root2] = root1;
			sizeOfTree[root1] += sizeOfTree[root2];
		}
	}

	public boolean allAreFriends() {
		return numberOfTree == 1;
	}
}
