import java.util.*;

class Node {
    int vertex;
    Node next;

    Node(int vertex) {
        this.vertex = vertex;
        this.next = null;
    }
}

class HMGraph {
    static final int MAX = 100;
    int n, m;
    Node[] adjlist;

    HMGraph() {
        adjlist = new Node[MAX];
        n = 0;
        m = 0;
    }

    public void createDirectedGraph(int num_v) {
        n = num_v;
        m = 0;
        for (int i = 0; i < MAX; i++) {
            adjlist[i] = null;
        }
        System.out.println("Graph created with " + num_v + " vertices.");
    }

    public void addEdge(int v1, int v2) {
        Node newNode = new Node(v2);
        newNode.next = adjlist[v1];
        adjlist[v1] = newNode;
        m++;
        System.out.println("Edge added from " + v1 + " to " + v2);
    }

    private boolean isHamiltonianUtil(int current, boolean[] visited, int count, int start, boolean checkCycle) {
        if (count == n) {
            if (checkCycle) {
                // Check if there's an edge from current to start to form a cycle
                Node temp = adjlist[current];
                while (temp != null) {
                    if (temp.vertex == start)
                        return true;
                    temp = temp.next;
                }
                return false;
            }
            return true;
        }

        Node temp = adjlist[current];
        while (temp != null) {
            if (!visited[temp.vertex]) {
                visited[temp.vertex] = true;
                if (isHamiltonianUtil(temp.vertex, visited, count + 1, start, checkCycle))
                    return true;
                visited[temp.vertex] = false;
            }
            temp = temp.next;
        }

        return false;
    }

    public void checkHamiltonianCycle() {
        boolean[] visited = new boolean[n];
        for (int start = 0; start < n; start++) {
            Arrays.fill(visited, false);
            visited[start] = true;
            if (isHamiltonianUtil(start, visited, 1, start, true)) {
                System.out.println("Hamiltonian Cycle Exists.");
                return;
            }
        }
        System.out.println("Hamiltonian Cycle Does NOT Exist.");
    }

    public void checkHamiltonianPath() {
        boolean[] visited = new boolean[n];
        for (int start = 0; start < n; start++) {
            Arrays.fill(visited, false);
            visited[start] = true;
            if (isHamiltonianUtil(start, visited, 1, start, false)) {
                System.out.println("Hamiltonian Path Exists.");
                return;
            }
        }
        System.out.println("Hamiltonian Path Does NOT Exist.");
    }

    public void checkHamiltonianGraph() {
        boolean hasPath = false;
        boolean hasCycle = false;
        boolean[] visited = new boolean[n];

        for (int start = 0; start < n; start++) {
            Arrays.fill(visited, false);
            visited[start] = true;
            if (!hasPath && isHamiltonianUtil(start, visited, 1, start, false))
                hasPath = true;

            Arrays.fill(visited, false);
            visited[start] = true;
            if (!hasCycle && isHamiltonianUtil(start, visited, 1, start, true))
                hasCycle = true;
        }

        if (hasPath && hasCycle)
            System.out.println("Graph has both Hamiltonian Path and Cycle (Hamiltonian Graph).");
        else if (hasPath)
            System.out.println("Graph has Hamiltonian Path only.");
        else
            System.out.println("Graph is NOT a Hamiltonian Graph.");
    }

    public void print() {
        System.out.println("\nAdjacency List of the Directed Graph:");
        for (int i = 0; i < n; i++) {
            System.out.print(i + " -> ");
            Node temp = adjlist[i];
            while (temp != null) {
                System.out.print(temp.vertex + " ");
                temp = temp.next;
            }
            System.out.println();
        }
    }

}

public class HamiltonianCheck {
    public static void main(String[] args) {
        int choice = 0;
        int num_v;
        Scanner scanner = new Scanner(System.in);
        HMGraph g = new HMGraph();

        while (choice != -1) {
            System.out.println("\n----- MENU -----");
            System.out.println("1. Create Empty Directed Graph");
            System.out.println("2. Insert an Edge");
            System.out.println("3. Check Hamiltonian Cycle");
            System.out.println("4. Check Hamiltonian Path");
            System.out.println("5. Check Hamiltonian Graph");
            System.out.println("-1. Exit");
            System.out.print("Enter the choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter the number of vertices: ");
                    num_v = scanner.nextInt();
                    g.createDirectedGraph(num_v);
                    break;
                case 2:
                    System.out.print("Enter source vertex: ");
                    int v1 = scanner.nextInt();
                    System.out.print("Enter destination vertex: ");
                    int v2 = scanner.nextInt();
                    g.addEdge(v1, v2);
                    break;
                case 3:
                    g.checkHamiltonianCycle();
                    break;
                case 4:
                    g.checkHamiltonianPath();
                    break;
                case 5:
                    g.checkHamiltonianGraph();
                    break;
                case -1:
                    System.out.println("Exiting...");
                case 99: //dummy case to check and print the graph
                    System.out.println(" ");
                    g.print();
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }

        scanner.close();
    }
}
