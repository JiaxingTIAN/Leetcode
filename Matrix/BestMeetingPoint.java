public class Solution {
//BFS - TLE Worset Case All buidlings O[(MN)^2]
    public int minTotalDistance(int[][] grid) {
        //Assume people can meet at house and go through house
        if(grid == null || grid.length == 0 || grid[0].length == 0){
            return 0;
        }
        int n = grid.length;
        int m = grid[0].length;
        int build = 0;
        int[][]d = new int[n][m];
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(grid[i][j] == 1){
                    build++;
                    bfs(grid, d, i, j);
                }
            }
        }
        int min = Integer.MAX_VALUE;
        int loc = 0;
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(min > d[i][j]){
                    min = d[i][j];
                    loc = i*n + j;
                }
            }
        }
        System.out.println("X: " + loc/n + " Y: " + loc%n);
        return min == Integer.MAX_VALUE? 1:min;
    }
    
    private final int[][]dir = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
    
    public void bfs(int[][]grid, int[][]dis, int x, int y){
        Queue<int[]> queue = new LinkedList<>();
        int n = grid.length;
        int m = grid[0].length;
        boolean [][] visit = new boolean[n][m];
        queue.offer(new int[]{x, y});
        visit[x][y] = true;
        int dist = 0;
        while(!queue.isEmpty()){
            dist++;
            int size = queue.size();
            for(int i=0; i<size; i++){
                int[]cur = queue.poll();
                for(int[]d:dir){
                    int r = cur[0] + d[0];
                    int c = cur[1] + d[1];
                    if(r < 0 || r >= n || c < 0 || c >= m || visit[r][c])    
                        continue;
                    visit[r][c] = true;
                    dis[r][c] += dist;
                    queue.offer(new int[]{r, c});
                }
            }
        }
    }
}

public class Solution {
    public int minTotalDistance(int[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;
        
        List<Integer> row = new ArrayList<>();
        List<Integer> col = new ArrayList<>();
        int n = grid.length, m = grid[0].length;
        for(int i=0; i<n; i++)
            for(int j=0; j<m; j++)
                if(grid[i][j] == 1)
                    row.add(i); //For row distance convert all house to one demision
        for(int j=0; j<m; j++)
            for(int i=0; i<n; i++)
                if(grid[i][j] == 1)
                    col.add(j); 
        //Add the rows and cols according to order, no need to sort later
        return getDist(row) + getDist(col);
    }
    //Time Complexity O(NM)
    public int getDist(List<Integer> list){
        //Get equal number of houses on both side
        //If even number of house, any place in between is fine
        //If odd number of house, chose the middle house, distance to the middle house is 0
        //Collections.sort(list);
        int i = 0, j = list.size() - 1;
        int dist = 0;
        while(i < j){
            dist += list.get(j--) - list.get(i++);
        }
        return dist;
    }
}
