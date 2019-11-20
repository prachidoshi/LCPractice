/*

Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.

 

Example:

Input: [1,2,3,4,5]
  
          1
         / \
        2   3
       / \     
      4   5    

Output: [[4,5,3],[2],[1]]

*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        helper(root,result);
        return result;
    }
    
    public int helper(TreeNode root,List<List<Integer>> result){
        if(root == null) return -1;
        int lHeight = helper(root.left, result);
        int rHeight = helper(root.right,result);
        int level = 1+Math.max(lHeight,rHeight);
        //no entry at this level 
        if(result.size()<level+1){
            result.add(new ArrayList<Integer>());
        }
        result.get(level).add(root.val);
        return level;
    }
}
