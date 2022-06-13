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
    private int dijkstrasAlgorithmWithStateMap(int citiesCount, int fuelTankCapacity, Map<Integer, List<RoadTo>> roadToMap, int [][]costsToReachFromTo, Map<Integer, Integer> fuelCosts){
        Queue<State> pq = new PriorityQueue<>();
        pq.add(new State(0, fuelTankCapacity, 0));
        while(!pq.isEmpty()){
            State state = pq.poll();
            if(costsToReachFromTo[state.vertex][state.fuel] < state.cost) continue;
            costsToReachFromTo[state.vertex][state.fuel] = state.cost;
            for(RoadTo roadTo: roadToMap.get(state.vertex)){
                int toVertex = roadTo.vertex, fuelReq = roadTo.fuelReq;
                for(int boughtFuel = fuelTankCapacity-state.fuel; boughtFuel>=0 && state.fuel+boughtFuel-fuelReq>=0; boughtFuel--){
                    if(costsToReachFromTo[toVertex][state.fuel+boughtFuel-fuelReq] > state.cost +boughtFuel*fuelCosts.get(state.vertex)) {
                        costsToReachFromTo[toVertex][state.fuel+boughtFuel-fuelReq] = state.cost +boughtFuel*fuelCosts.get(state.vertex);
                        pq.add(new State(toVertex, state.fuel + boughtFuel - fuelReq, state.cost + boughtFuel * fuelCosts.get(state.vertex)));
                    }
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for(int dist: costsToReachFromTo[citiesCount]){ res = Math.min(res, dist); }
        return res;
    }

    private int getMinimumFuel(
            int citiesCount, int roadsCount, int fuelStations,
            int fuelTankCapacity,
            List<TravelFuelRequirement> travelFuelRequirements,
            Map<Integer, Integer> fuelCosts
    ){
        Map<Integer, List<RoadTo>> roadToMap = new HashMap<>(); // adj list
        for(TravelFuelRequirement travelFuelRequirement: travelFuelRequirements){
//            if(fuelCosts.containsKey(travelFuelRequirement.src)) { // path still exists from this node
            if (!roadToMap.containsKey(travelFuelRequirement.src)) { roadToMap.put(travelFuelRequirement.src, new ArrayList<>()); }
            roadToMap.get(travelFuelRequirement.src).add(new RoadTo(travelFuelRequirement.dest, travelFuelRequirement.fuelReq));
            if (!roadToMap.containsKey(travelFuelRequirement.dest)) { roadToMap.put(travelFuelRequirement.dest, new ArrayList<>()); }
            roadToMap.get(travelFuelRequirement.dest).add(new RoadTo(travelFuelRequirement.src, travelFuelRequirement.fuelReq));
        }
//        for(int i=0; i<citiesCount; i++){
//            fuelCosts.putIfAbsent(i, 200000); // double of value provided in constraints - fuel can be refilled from this point - if added a path would be present with high costs
//        }
        int[][] costsToReachFromTo = new int[citiesCount][fuelStations+1];
        for(int []dist: costsToReachFromTo) Arrays.fill(dist, Integer.MAX_VALUE);
        int res = dijkstrasAlgorithmWithStateMap(citiesCount, fuelTankCapacity, roadToMap, costsToReachFromTo, fuelCosts);
        return res;
    }
}