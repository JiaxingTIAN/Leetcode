public class Solution{
	public boolean palindromPermutation(String str){
		// Use bucket to count the number of appearance of each letter
		if(str==null||str.length()==0) return false;
		int[] letter = new int[26];
		char[] ch = str.toCharArray();
		for(char c:ch){
			if(c == ' ')	continue;
			letter[c-'a']++;
		}
		// IF more than one letter with odd count, return false
		boolean odd = false;
		for(int i:letter){
			if(i%2==1){
				if(odd) return false;
				else	odd = true;
			}
		}
		return true;
	}
	
	public boolean palindromPermutation1(String str){
		//Count the number of odd appearance along the way
		if(str == null || str.length() == 0) return false;
		
		int oddCount = 0;
		int[] letter = new int[26];
		char[] ch = str.toCharArray();
		for(char c:ch){
			if(c==' ') continue;
			letter[c - 'a']++;
			if(letter[c-'a']%2==1)	oddCount++;
			else	oddCount--;
		}
		return oddCount<=1;
	}
	
	public boolean palindromPermutation2(String str){
		//Flip the coin to record the odd count 
		if(str == null || str.length() == 0) return false;
		
		int count = 0;
		char[] c = str.toCharArray();
		for(char ch:c){
			if(ch == ' ') continue;
			int mask = 1<<(ch-'a');
			if((count & mask) == 0)	count |= mask;
			else 	count &= ~mask;
		}
		return (count & (count - 1)) == 0;
	}
}
