/*Design a class which receives a list of words in the constructor, and implements a method that takes two words word1 and word2 and return the shortest distance between these two words in the list. Your method will be called repeatedly many times with different parameters. 

Example:
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Input: word1 = “coding”, word2 = “practice”
Output: 3
Input: word1 = "makes", word2 = "coding"
Output: 1
*/

class WordDistance {
    HashMap<String,List<Integer>> map;
    HashMap<String,Integer> cache;
    
    public WordDistance(String[] words) {
        map = new HashMap<String,List<Integer>>();
        cache = new HashMap<String,Integer>();
        for(int i=0;i<words.length;i++){
            String w = words[i];
            if(map.containsKey(w)){
                map.get(w).add(i);
            }else{
                List<Integer> index = new ArrayList<Integer>();
                index.add(i);
                map.put(w,index);
            }
        }
        
    }
    
    public int shortest(String word1, String word2) {
        String key = word1+"-"+word2;
        String revKey = word2+"-"+word1;
        
        //check if its in cache 
        if(cache.containsKey(key)) return cache.get(key);
        if(cache.containsKey(revKey)) return cache.get(revKey);
        
        int min= Integer.MAX_VALUE;
        int i=0,j=0;
        
        List<Integer> list1 = map.get(word1);
        List<Integer> list2 = map.get(word2);
        
        while(i<list1.size() && j<list2.size()){
            int index1 = list1.get(i);
            int index2 = list2.get(j);
            if(index1<index2){
                min = Math.min(min,(index2-index1));
                i++;
            }else{
                min = Math.min(min,(index1-index2));
                j++;
            }
            if(min == 1){
                break;
            }
            
        }
        cache.put(key,min);
        return min;
    }
}
    
