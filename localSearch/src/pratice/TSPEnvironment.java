package pratice;

public class TSPEnvironment {

	public int [][] distances;
	public TSPEnvironment() {
		
	}
	
	public int getObjectiveFunctionValue(int solution[]) {
		int cost = 0;
		for (int i = 0; i < solution.length - 1; i++) {
			cost += distances[solution[i]][solution[i+1]];
		}
		return cost;
	}
}
