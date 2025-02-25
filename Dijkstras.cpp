#include<iostream>
#include<climits>
using namespace std;
#define MAX 100

class Queue {
private:
    int arr[MAX];
    int front, rear;

public:
    Queue() {
        front = 0;
        rear = -1;
    }

    bool isEmpty() {
        return front > rear;                            /*if(front > rear)
                                                        return true ;
                                                        return false;*/
    }

    void enqueue(int x) {
        
            rear++;
            arr[rear] = x;
        
    }

    int dequeue() {
        int val = arr[front];
        front++;
            return val;
    }
};

class Graph
{
    private:
    int n;//number of vertices
    int adjmat[MAX][MAX]; 

    public:
    void createEmpUnidirGraph(int numv)
    {
         n=numv;
         for (int i = 0; i < n; i++)
         {
           for (int j = 0; j < n; j++)
           {
              adjmat[i][j]=-1;
           } 
         }
         
    }

    void addEdge(int v1, int v2, int weight)
    {
        adjmat[v1][v2]=weight;
        adjmat[v2][v1]=weight;

    }

  //  void printgraph()
  //   {
  //      for (int i = 0; i < n; i++)
  //      {
  //         cout<<"Neighbours of "<<i<<endl;
  //         for (int j = 0; j < n; j++)
  //         {
  //           if(adjmat[i][j]!=-1)
  //           {
  //           cout<<j<<" , weight = "<<adjmat[i][j]<<endl;
  //         }
  //         }
  //      }
  //   }
    void dijkstras(int source)
    {
      int shortest_dist[n];  // shortest distance to evry other distance
      for (int i = 0; i < n; i++)
      {
        shortest_dist[i]= INT_MAX;

      }
      shortest_dist[source]=0;
      
      Queue q;    //declare and init an empty queue
      q.enqueue(source);
      while(!q.isEmpty())
      {
          int u =q.dequeue();

          for (int v = 0; v < n; v++)
          {
            if(adjmat[u][v]!=-1)    //vertex v is a neighbour of vertex u  ...for every neighbour
            {
              int newdist =shortest_dist[u]+adjmat[u][v];//cost of u and v

              if(newdist < shortest_dist[v])
              {
                shortest_dist[v]=newdist;
                q.enqueue(v);
              }
            }
          }  
      }
     for (int i = 0; i < n; i++){
        	cout<<source<<" to "<<i<<" -> ";
        	cout << shortest_dist[i] << " ";
        	cout << endl;
		}
    }
};


int main()
{
int choice;
int numv;
Graph g;
    do {
        cout << "1. Create Graph empty undirected\n";
        cout << "2. Insert Edge\n";
        cout<<"3.Dijsktra"<<endl;
        cout << "-1. Exit\n";
        cout << "Enter your choice: ";
        cin >> choice;

        switch (choice) {
            case 1:
            //create an empty undirected graph
            
            cout<<"Enter the vertices: ";
            cin>>numv;
               g.createEmpUnidirGraph(numv);
                break;
            case 2:
           //insert an edge 
           int v1, v2, weight;
           cout<<"Enter the v1 : ";
           cin>>v1;
           cout<<"Enter the v2: ";
           cin>>v2;
           cout<<"Enter the weight: ";
           cin>>weight;
           g.addEdge(v1,v2,weight);
                break;
            case 3:
             //compute the shortesest distance
             int source;
             cout<<"Enter the source: ";
             cin>>source;
             g.dijkstras(source);
                break;
            // case 99:
            // g.printgraph();
          case -1:
                cout << "Exiting...\n";
            
                break;
            default:
                cout << "Invalid choice!\n";
        }
    } while (choice != -1);

    return 0;
}
