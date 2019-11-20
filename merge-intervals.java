/*

Given a collection of intervals, merge all overlapping intervals.

Example 1:

Input: [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
Example 2:

Input: [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.

*/

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> result = new ArrayList<Interval>();
        
        //null check 
        if(intervals==null || intervals.size()==0){
            return result;
        }
        
        //sort the intervals 
        Collections.sort(intervals,new Comparator<Interval>(){
            @Override
            public int compare(Interval i1,Interval i2){
                if(i1.start!=i2.start){
                    return i1.start-i2.start;
                }else{
                    return i1.end-i2.end;
                }
            }
        });
        
        //now iterate over sorted intervals and merge if required 
        Interval pre = intervals.get(0);
        for(int i=1;i<intervals.size();i++){
            Interval curr = intervals.get(i);
            if(curr.start<=pre.end){ //overlapping so merge                
                Interval merged = new Interval(pre.start,Math.max(pre.end,curr.end));
                pre = merged;
            }else{
                result.add(pre);
                pre = curr;
            }
        }
        result.add(pre);
        return result;
    }
}
