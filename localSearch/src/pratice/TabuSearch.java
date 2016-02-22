package pratice;

public class TabuSearch {

	public static int[] getBestNeighbour(TabuList tabuList,
			TSPEnvironment tspEnvironment,
			int[] initSolution) {
		
		int[] bestSol = new int[initSolution.length];
		System.arraycopy(initSolution, 0, bestSol, 0, bestSol.length);
		int bestCost = tspEnvironment.getObjectiveFunctionValue(initSolution);
		int city1 = 0;
		int city2 = 0;
		boolean firstNeighbor = true;
		
		for (int i = 1; i < bestSol.length - 1; i++) {
			for (int j = 2; j < bestSol.length - 1; j ++) {
				if (i == j) {
					continue;
				}
				
				
			}
		}
	}
}
