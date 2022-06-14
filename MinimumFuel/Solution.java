package MinimumFuel;

import java.util.*;

public class Solution {
    private static class State implements Comparable<State>{
        // distance is cost distance
        public int vertex, fuel, cost;
        public State(int vertex, int fuel, int cost) {
            this.vertex = vertex;
            this.fuel = fuel;
            this.cost = cost;
        }
        @Override
        public int compareTo(State state) { return this.cost-state.cost; }
    }

    private static class TravelFuelRequirement {
        public int src, dest, fuelReq;

        public TravelFuelRequirement(int src, int dest, int fuelReq) {
            this.src = src;
            this.dest = dest;
            this.fuelReq = fuelReq;
        }
    }

    private static class RoadTo {
        public int vertex, fuelReq;
        public RoadTo(int vertex, int fuelReq){
            this.vertex = vertex;
            this.fuelReq = fuelReq;
        }
    }

//    int citiesCount, connectingNodes, fuelStations;
//    int fuelTankCapacity;
//    Map<Integer, Integer> fuelCosts;
//    Map<Integer, List<RoadTo>> roadToMap;
    private int dijkstrasAlgorithmWithStateMap(int citiesCount, int fuelTankCapacity, Map<Integer, List<RoadTo>> roadToMap, int [][]costsToReachFromTo, int [][]pathsToReachFromTo, Map<Integer, Integer> fuelCosts){
        Queue<State> pq = new PriorityQueue<>();
        pq.add(new State(0, 0, 0));
        while(!pq.isEmpty()){
            State state = pq.poll();
            if(costsToReachFromTo[state.vertex][state.fuel] < state.cost) continue;
            costsToReachFromTo[state.vertex][state.fuel] = state.cost;
            for(RoadTo roadTo: roadToMap.get(state.vertex)){
                int toVertex = roadTo.vertex, fuelReq = roadTo.fuelReq;
                int boughtFuel = fuelTankCapacity-state.fuel;
                if(!fuelCosts.containsKey(state.vertex)){ boughtFuel = 0; } // some cities don't offer refuelling as per constraints
                for(; boughtFuel>=0 && (state.fuel+boughtFuel)>=fuelReq; boughtFuel--){
                    int remainingFuelAfterReachingTo = state.fuel+boughtFuel-fuelReq;
                    int totalCost = state.cost +boughtFuel*fuelCosts.get(state.vertex);
                    if(costsToReachFromTo[toVertex][remainingFuelAfterReachingTo] > totalCost) {
                        costsToReachFromTo[toVertex][remainingFuelAfterReachingTo] = totalCost;
                        pathsToReachFromTo[toVertex][remainingFuelAfterReachingTo] = state.vertex;
                        pq.add(new State(toVertex, remainingFuelAfterReachingTo, totalCost));
                    }
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for(int dist: costsToReachFromTo[citiesCount-1]){ res = Math.min(res, dist); }
        return res;
    }

    private int getMinimumFuel(
            int citiesCount, int roadsCount, int fuelStations,
            int fuelTankCapacity,
            List<TravelFuelRequirement> travelFuelRequirements,
            Map<Integer, Integer> fuelCosts
    ){
        Map<Integer, List<RoadTo>> roadToMap = new HashMap<>(); // adj list - roads originating `from` a node, `to` a node with `amount` of fuel required to reach at `to` node from `from` node
        for(TravelFuelRequirement travelFuelRequirement: travelFuelRequirements){
//            if(fuelCosts.containsKey(travelFuelRequirement.src)) { // path still exists from this node
            if (!roadToMap.containsKey(travelFuelRequirement.src)) { roadToMap.put(travelFuelRequirement.src, new ArrayList<>()); }
            roadToMap.get(travelFuelRequirement.src).add(new RoadTo(travelFuelRequirement.dest, travelFuelRequirement.fuelReq));
            if (!roadToMap.containsKey(travelFuelRequirement.dest)) { roadToMap.put(travelFuelRequirement.dest, new ArrayList<>()); }
            roadToMap.get(travelFuelRequirement.dest).add(new RoadTo(travelFuelRequirement.src, travelFuelRequirement.fuelReq));
        }
        int[][] costsToReachFromTo = new int[citiesCount][fuelTankCapacity+1]; // minimum cost of fuel required to reach at `vertex` with `fuel`
        int[][] pathsToReachFromTo = new int[citiesCount][fuelTankCapacity+1]; // path associated with above
        for(int i=0; i<citiesCount; i++){
            Arrays.fill(costsToReachFromTo[i], Integer.MAX_VALUE);
            Arrays.fill(pathsToReachFromTo[i], -1);
        }
        Arrays.fill(costsToReachFromTo[0], 0); costsToReachFromTo[0][0] = Integer.MAX_VALUE;
        int res = dijkstrasAlgorithmWithStateMap(citiesCount, fuelTankCapacity, roadToMap, costsToReachFromTo, pathsToReachFromTo, fuelCosts);
        return res==Integer.MAX_VALUE? -1: res;
    }

    public static void main(String[] args) {
        Map<Integer, Integer> fuelCosts = new HashMap<>();
        List<TravelFuelRequirement> travelFuelRequirements = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        for(int i=0; i<n; i++){
            fuelCosts.putIfAbsent(i, scanner.nextInt());
        }
        for(int i=0; i<m; i++){
            travelFuelRequirements.add(new TravelFuelRequirement(scanner.nextInt(), scanner.nextInt(), scanner.nextInt()));
        }
        int q = scanner.nextInt();
        while(q-->0){
            System.out.println(new Solution().getMinimumFuel(n, m, n, scanner.nextInt(), travelFuelRequirements, fuelCosts));
        }
    }
}

/*
5 5
10 10 20 12 13
0 1 9
0 2 8
1 2 1
1 3 11
2 3 7
2
10
* */