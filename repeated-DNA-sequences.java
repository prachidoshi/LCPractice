/*
All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.

Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.

Example:

Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"

Output: ["AAAAACCCCC", "CCCCCAAAAA"]

*/


public class Solution {
    /*Algorithm : Since the string contains only 4 characters, we could encode the characters using 2 bits each, i.e, 
       'A' -- 00
       'C' -- 01
       'G' -- 10
       'T' -- 11
       And since the length of the substring is only 10 characters, the total number of bits needed are only 20. Therefore we could encode a string into a integer. 
       
       
    */
    /*Algorithm : Since the string contains only 4 characters, we could encode the characters using 2 bits each, i.e, 
       'A' -- 00
       'C' -- 01
       'G' -- 10
       'T' -- 11
       And since the length of the substring is only 10 characters, the total number of bits needed are only 20. Therefore we could encode a string into a integer. 
       
       
    */
   private Map<Character, Integer> codingMap = new HashMap<Character, Integer>();
     
    private int encode(String s) {
        int value = 0;
        for (int i = 0; i < s.length(); i++) {
            value = (value << 2) + codingMap.get(s.charAt(i));
        }
         
        return value;
    }
     
    private void fill() {
        codingMap.put('A', 0);
        codingMap.put('C', 1);
        codingMap.put('G', 2);
        codingMap.put('T', 3);
    }
     
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> result = new ArrayList<String>();
        if (s == null || s.length() < 10) {
            return result;
        }
         
        fill();
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
         
        int lo = 0;
        int hi = 9;
         
        while (hi < s.length()) {
            String substring = s.substring(lo, hi + 1);
            int hashCode = encode(substring);
            if (map.containsKey(hashCode) && map.get(hashCode) == 1) {
                result.add(substring);
                map.put(hashCode, map.get(hashCode) + 1);
            } else {
                if (!map.containsKey(hashCode)) {
                    map.put(hashCode, 1);
                } else {
                    map.put(hashCode, map.get(hashCode) + 1);
                }
            }
             
            lo++;
            hi++;
        }
         
        return result;
    }
}
