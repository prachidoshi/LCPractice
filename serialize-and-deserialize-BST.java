/*
Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary search tree can be serialized to a string and this string can be deserialized to the original tree structure.

The encoded string should be as compact as possible.

Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
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
public class Codec {

    public String serialize(TreeNode root) {
        //sanity check on input plus edge case 
        if(root == null) return "";
        StringBuilder sb = new StringBuilder();
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode t = queue.poll();
            if(t!=null){
                sb.append(t.val+",");
                queue.offer(t.left);
                queue.offer(t.right);
            }else{
                sb.append("#"+",");
            }
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        //input data check and edge case 
        if(data == null || data.length() == 0) return null;
        String[] input = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(input[0]));
        int i=1;
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode current = queue.poll();
            if(current == null) continue;
            if(!input[i].equals("#")){
                current.left = new TreeNode(Integer.parseInt(input[i]));
                queue.offer(current.left);
            }else{
                current.left = null;
                queue.offer(current.left);
            }
            i++;
            if(!input[i].equals("#")){
                current.right = new TreeNode(Integer.parseInt(input[i]));
                queue.offer(current.right);
            }else{
                current.right = null;
                queue.offer(current.right);
            }
            i++;          
        }
        return root;
    }
}
