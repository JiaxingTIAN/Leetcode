public class Solution {
    public int numDecodings(String s) {
        if(s == null || s.length() == 0)
            return 0;
        int n = s.length();
        int[]dp = new int[n+1];
        dp[n] = 1;
        dp[n-1] = s.charAt(n-1)=='0'? 0:1;
        for(int i=n-2; i>=0; i--){
            if(s.charAt(i) == '0')
                continue;   //Skip if zero
            dp[i] = dp[i+1];
            if(Integer.valueOf(s.substring(i, i+2)) < 27)
                dp[i] += dp[i+2];   //Combine the two
        }
        return dp[0];
    }
}
