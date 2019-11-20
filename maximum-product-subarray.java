/*

Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.

Example 1:

Input: [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.
Example 2:

Input: [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.

*/

/*
https://www.geeksforgeeks.org/maximum-product-subarray/ 
https://github.com/mission-peace/interview/blob/68f97392c6f6e14e2c08860684ed605a1a5e7e72/src/com/interview/array/MaxProductSubarray.java

*/
class Solution {
    public int maxProduct(int[] nums) {
        int max_ending_here = 1; //max positive product at current position
        int min_ending_here = 1; //min negative product at current position 
        int max_so_far = nums[0] ; //overall max product 
        
        for(int i=0;i<nums.length;i++){
            if(nums[i]>0){ // positive
                max_ending_here = max_ending_here * nums[i];
                min_ending_here = Math.min((min_ending_here*nums[i]),1); //since mul of +ve with -ve can lead to more -ve
                max_so_far = Math.max(max_so_far,max_ending_here);
            }else if(nums[i]==0){ //zero
                max_ending_here = 1 ; //reset 
                min_ending_here = 1; //reset 
                max_so_far = Math.max(max_so_far,0);
            }else{ //negative
                int t = max_ending_here * nums[i]; //prev max number * this (-ve number)
                max_ending_here = Math.max(1, min_ending_here*nums[i]);// max will be either prev min( -ve number) * this(-ve number) , or reset to 1 cause we dont want this number in subarray  
                max_so_far = Math.max(max_so_far, min_ending_here * nums[i]);
               
                min_ending_here = t;
                
            }
        }
        
        return max_so_far;
    }
}
