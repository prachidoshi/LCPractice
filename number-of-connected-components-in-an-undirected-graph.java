/*
Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to find the number of connected components in an undirected graph.

Example 1:

Input: n = 5 and edges = [[0, 1], [1, 2], [3, 4]]

     0          3
     |          |
     1 --- 2    4 

Output: 2
Example 2:

Input: n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]]

     0           4
     |           |
     1 --- 2 --- 3

Output:  1
Note:
You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.

*/

class Solution {
    public int countComponents(int n, int[][] edges) {
        int[] roots = new int[n];
        
        // each node is an island
        for(int i=0;i<n;i++){
            roots[i] = i;
        } 
        
        for(int[] e:edges){
            int x = e[0];
            int y = e[1];
            int xRoot = getRoot(roots,x);
            int yRoot = getRoot(roots,y);
            if(xRoot!=yRoot){  //both have different parent 
                n--;
                roots[xRoot]=yRoot;
            }
        }
        return n;
    }
    
    public int getRoot(int[] roots,int id){
        while(id!=roots[id]){
            roots[id] = roots[roots[id]];
            id = roots[id];
        }
        return id;
    }
}
