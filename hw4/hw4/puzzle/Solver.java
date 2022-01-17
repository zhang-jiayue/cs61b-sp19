package hw4.puzzle;
import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayDeque;
import java.util.ArrayList;


public class Solver {
    private WorldState initialState;
    private SearchNode currenNode;
    private MinPQ<SearchNode> pq;
    private int thingsEverEnqued;
    private ArrayDeque<WorldState> sol;

    private class SearchNode<T> implements Comparable<T> {
        private WorldState state;
        private int movesMadeSoFar;
        private SearchNode previousNode;

        SearchNode(WorldState currentState, SearchNode prevNode, int moves) {
            this.state = currentState;
            this.previousNode = prevNode;
            this.movesMadeSoFar = moves;
        }

        public int getPriority() {
            return movesMadeSoFar + state.estimatedDistanceToGoal();
        }

        public int compareTo(T other) {
            if (this.getPriority() < ((SearchNode) other).getPriority()) {
                return -1;
            } else if (this.getPriority() == ((SearchNode) other).getPriority()) {
                return 0;
            } else {
                return 1;
            }
        }
    }

    /**
     * Constructor which solves the puzzle, computing
     * everything necessary for moves() and solution() to
     * not have to solve the problem again. Solves the
     * puzzle using the A* algorithm. Assumes a solution exists.
     *
     * @param initial the initial state of the world
     */
    public Solver(WorldState initial) {
        this.initialState = initial;
        this.pq = new MinPQ<>();
        this.sol = new ArrayDeque<>();
        this.pq.insert(new SearchNode(initial, null, 0));
        this.currenNode = pq.delMin();
        if (!currenNode.state.isGoal()) {
            for (WorldState neighbor : this.currenNode.state.neighbors()) {
                enqueNeighbor(neighbor);
            }
            this.currenNode = pq.delMin();

        }
        while (!currenNode.state.isGoal()) {
        // Remove lowest priority item and add its neighbors to the priority queue.
            for (WorldState neighbor : this.currenNode.state.neighbors()) {
                if (this.currenNode.previousNode != null
                        && !this.currenNode.previousNode.state.equals(neighbor)
                        && !this.currenNode.equals(neighbor)) {
                    // Critical optimization:
                    enqueNeighbor(neighbor);
                }
            }
            this.currenNode = pq.delMin();
        }
    }
    private void enqueNeighbor(WorldState neighbor) {
        pq.insert(new SearchNode(neighbor, this.currenNode,
                this.currenNode.movesMadeSoFar + 1));
        thingsEverEnqued += 1;
    }


    private int getThingsEverEnqued() {
        return this.thingsEverEnqued;
    }

    private Iterable<WorldState> getPQ() {
        ArrayList<WorldState> newPQ = new ArrayList<>();
        for (SearchNode n : this.pq) {
            newPQ.add(n.state);
        }
        return newPQ;
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
        SearchNode n = this.currenNode;
        sol.addFirst(n.state);
        while (n.previousNode != null) {
            this.sol.addFirst(n.previousNode.state);
            n = n.previousNode;
        }
        return sol;
    }

}
