package hw4.puzzle;
import edu.princeton.cs.algs4.MinPQ;

import java.util.*;

public class Solver {
    private SearchNode currenNode;
    private MinPQ<SearchNode> pq;
    private HashSet<WorldState> sol;
    private int thingsEverEnqued;

    private class SearchNode{
        private WorldState state;
        private int movesMadeSoFar;
        private SearchNode previousNode;

        public SearchNode(WorldState current, SearchNode prev) {
            this.state = current;
            this.previousNode = prev;
        }

        public int getMovesMadeSoFar() {
            return movesMadeSoFar;
        }
        public void setMovesMadeSoFar(int moves) {
            this.movesMadeSoFar = moves;
        }

        public int getPriority() {
            return movesMadeSoFar + state.estimatedDistanceToGoal();
        }
    }
    /**
     * Constructor which solves the puzzle, computing
     * everything necessary for moves() and solution() to
     * not have to solve the problem again. Solves the
     * puzzle using the A* algorithm. Assumes a solution exists.
     * @param initial the initial state of the world
     */
    public Solver(WorldState initial) {
        Comparator<SearchNode> cmpr= new Comparator<>() {
            @Override
            public int compare(SearchNode o1, SearchNode o2) {
                if (o1.getPriority() < o2.getPriority()) {
                    return -1;
                } else if (o1.getPriority() == o2.getPriority()){
                    return 0;
                } else {
                    return 1;
                }
            }
        };
        this.pq = new MinPQ<>(cmpr);
        this.sol = new HashSet<>();
        this.currenNode = new SearchNode(initial, null);
        thingsEverEnqued += 1;
        while (!currenNode.state.isGoal()) {
            // Remove lowest priority item and add its neighbors to the priority queue.
            for (WorldState neighbor : this.currenNode.state.neighbors()) {
                if(this.currenNode.previousNode== null || !this.currenNode.previousNode.state.equals(neighbor)){
                    // Critical optimization:
                    pq.insert(new SearchNode(neighbor, this.currenNode));
                    thingsEverEnqued += 1;
                }
            }
            SearchNode prev = this.currenNode;
            this.currenNode = pq.delMin();
            this.currenNode.setMovesMadeSoFar(prev.getMovesMadeSoFar() + 1);
            this.sol.add(this.currenNode.state);
        }
    }

    public int getThingsEverEnqued() {
        return this.thingsEverEnqued;
    }


    /**
     *  Returns the minimum number of moves to solve the puzzle starting
     *  at the initial WorldState.
     */
    public int moves() {
        return this.currenNode.movesMadeSoFar;
    }


    /**
     * @return a sequence of WorldStates from the initial WorldState to the solution.
     */
    public Iterable<WorldState> solution() {
        return sol;
    }

}
