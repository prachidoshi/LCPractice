/*
Given n nodes labeled from 0 to n-1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.

Example 1:

Input: n = 5, and edges = [[0,1], [0,2], [0,3], [1,4]]
Output: true
Example 2:

Input: n = 5, and edges = [[0,1], [1,2], [2,3], [1,3], [1,4]]
Output: false
Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0,1] is the same as [1,0] and thus will not appear together in edges.
*/

class Solution {
    
/*
Solution 1 : DFS ( To be a avalid tree should not have a cycle and all vertices should be connected )


*/
    public boolean validTree(int n, int[][] edges) {
        //initialize adjacency matrix 
        List<List<Integer>> adjList = new ArrayList<List<Integer>>();
        
        //initialize vertices 
        for(int i=0;i<n;i++){
            adjList.add(i,new ArrayList<Integer>());
        }
        
        //add edges 
        for(int i=0;i<edges.length;i++){
            int u = edges[i][0];
            int v = edges[i][1];
            
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }
        
        boolean[] visited = new boolean[n];
        
        //check for cycle 
        if(hasCycle(adjList,visited,0,-1)){
            return false;
        }
        
        //check for connectedness
        for(int i=0;i<n;i++){
            if(!visited[i]){
                return false;
            }
        }
        return true;
    }
    
    public boolean hasCycle(List<List<Integer>> adjList,boolean[] visited, int u, int parent){
      visited[u] = true;
        
      //get neighbors 
      for(int i=0;i<adjList.get(u).size();i++){
          int v = adjList.get(u).get(i);
          if((visited[v] && parent!=v)||(!visited[v] && hasCycle(adjList,visited,v,u))){
              return true;
          }
      } 
        return false;
    }
}
