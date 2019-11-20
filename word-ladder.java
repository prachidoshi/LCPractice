/*
Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

Only one letter can be changed at a time.
Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
Note:

Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.

Example 1:

Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

Output: 5

Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.
Example 2:

Input:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

Output: 0

Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
*/


class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
    
        //sanity check on input 
        if(beginWord == null || beginWord.length() == 0 
           ||endWord == null || endWord.length() == 0
           ||wordList == null || wordList.size() == 0
           ||!wordList.contains(endWord)) return 0;
        
        //Two hashSet for bidirectional bfs on graph 
        HashSet<String> beginSet = new HashSet<String>();
        HashSet<String> endSet = new HashSet<String>();
        
        beginSet.add(beginWord);
        endSet.add(endWord);
        int len = 1;
        
        //hashset to assist in bfs
        HashSet<String> visited = new HashSet<String>();
        
        while(!beginSet.isEmpty() && !endSet.isEmpty()){
            //optimization to reduce bidirectional 
            if(beginSet.size()>endSet.size()){
                HashSet<String> set = beginSet;
                beginSet = endSet;
                endSet = set;
            }
       
            //to assist in bfs just like a queue ( here we dont care about sequence but care about nodes at next level )
            HashSet<String> temp = new HashSet<String>();
            
            for(String w:beginSet){
                char[] chs = w.toCharArray();
                for(int i =0;i<chs.length;i++){
                    for(char c = 'a';c<='z';c++){
                       char old = chs[i];
                       chs[i] = c;
                       String target = String.valueOf(chs);
                       if(endSet.contains(target)) {
                           return len+1;
                       }
                       if(!visited.contains(target) && wordList.contains(target)){
                               temp.add(target);
                               visited.add(target);
                       }  
                       chs[i] = old; 
                  }
                }
            }
            
            len++;
            beginSet = temp;
        }
        return 0;
    }
}
