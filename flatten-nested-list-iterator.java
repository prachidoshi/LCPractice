/*

Given a nested list of integers, implement an iterator to flatten it.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Example 1:

Input: [[1,1],2,[1,1]]
Output: [1,1,2,1,1]
Explanation: By calling next repeatedly until hasNext returns false, 
             the order of elements returned by next should be: [1,1,2,1,1].
Example 2:

Input: [1,[4,[6]]]
Output: [1,4,6]
Explanation: By calling next repeatedly until hasNext returns false, 
             the order of elements returned by next should be: [1,4,6].
             
*/

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {
    Iterator<Integer> iter;
    public NestedIterator(List<NestedInteger> nestedList) {
        //sanity check 
        if(nestedList == null) return;
        List<Integer> flatenedList = new ArrayList<Integer>();
        LinkedList<NestedInteger> stack = new LinkedList<NestedInteger>();
        Collections.reverse(nestedList);
        for(NestedInteger n:nestedList){
            stack.push(n);
        }
        while(!stack.isEmpty()){
            NestedInteger n = stack.pop();
            if(n.isInteger()) flatenedList.add(n.getInteger());
            else{
                List<NestedInteger> nList = n.getList();
                Collections.reverse(nList);
                for(NestedInteger ni:nList){
                    stack.push(ni);
                }
            }
        }
        iter = flatenedList.iterator();
    }

    @Override
    public Integer next() {
        return iter.next();
    }

    @Override
    public boolean hasNext() {
        return iter.hasNext();
    }
}
