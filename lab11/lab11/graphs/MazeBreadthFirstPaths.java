package lab11.graphs;

import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
        public int[] distTo;
        public int[] edgeTo;
        public boolean[] marked;
        */
    private int src;
    private int target;
    private Maze maze;
    private static final int INFINITY = Integer.MAX_VALUE;




    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        // Add more variables here!
        maze = m;
        src = maze.xyTo1D(sourceX, sourceY);
        target = maze.xyTo1D(targetX, targetY);
        distTo[src] = 0;
        edgeTo[src] = src;

    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        // Your code here. Don't forget to update distTo, edgeTo, and marked, as well as call announce()
        Queue<Integer> q = new ArrayDeque<>();
        for (int v = 0; v < maze.V(); v++) {
            distTo[v] = INFINITY;
        }
        distTo[src] = 0;
        marked[src] = true;
        announce();
        q.add(src);

        while (!q.isEmpty()) {
            int v = q.remove();
            for (int w : maze.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    announce();
                    q.add(w);
                    if (w == target) {
                        return;
                    }
                }
            }
        }
    }


    @Override
    public void solve() {
        bfs();
    }
}

