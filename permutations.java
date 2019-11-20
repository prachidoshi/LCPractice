/*
Given a collection of distinct integers, return all possible permutations.

Example:

Input: [1,2,3]
Output:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
*/


class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
        //sanity check on input 
        if(nums == null || nums.length == 0){
            return result;
        }
        
        List<Integer> tempList = new ArrayList<Integer>();
        
        dfs(nums,tempList,result);
        return result;
    }
    
    public void dfs(int[] nums,List<Integer> tempList,List<List<Integer>> result){
        //base condition 
        if(tempList.size() == nums.length){
            result.add(new ArrayList<Integer>(tempList));
        }
        
        for(int i=0;i<nums.length;i++){
            if(tempList.contains(nums[i])) continue;
            tempList.add(nums[i]);
            dfs(nums,tempList,result);
            tempList.remove(tempList.size()-1);
        }
        
    }
}
