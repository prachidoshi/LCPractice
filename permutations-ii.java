/*

Given a collection of numbers that might contain duplicates, return all possible unique permutations.

Example:

Input: [1,1,2]
Output:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]

*/
class Solution {   
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        backtrack(result, new ArrayList<Integer>(),nums,new boolean[nums.length]);
        return result;
    }
    
    public void backtrack(List<List<Integer>> result,ArrayList<Integer> tempList,int[] nums,boolean[] used){
        if(tempList.size()==nums.length){
            result.add(new ArrayList<Integer>(tempList));
        }else{
            for(int i=0;i<nums.length;i++){
                if(used[i] || (i>0 && nums[i-1]==nums[i] && !used[i-1])) continue;
                used[i] = true;
                tempList.add(nums[i]);
                backtrack(result,tempList,nums,used);
                used[i] = false;
                tempList.remove(tempList.size()-1);
            }
        }       
    }
}
