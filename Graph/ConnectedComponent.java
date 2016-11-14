/**
 * Definition for Undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     ArrayList<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
public class Solution {
    /**
     * @param nodes a array of Undirected graph node
     * @return a connected set of a Undirected graph
     */
     
    public class UnionFind{
        private Map<Integer, Integer> root;
        
        public UnionFind(Set<Integer> set){
            root = new HashMap<>();
            for(Integer num:set){
                root.put(num, num);
            }
        }
        
        public int find(int num){
            int parent = root.get(num);
            while(parent!=root.get(parent)){
                parent = root.get(parent);
            }
            while(num!=root.get(num)){
                int next = root.get(num);
                root.put(num, parent);
                num = next;
            }
            return parent;
        }
        
        public void union(int x, int y){
            int root_x = find(x);
            int root_y = find(y);
            if(root_x!=root_y)
                root.put(root_x, root_y);
        }
    }
    public List<List<Integer>> connectedSet(ArrayList<UndirectedGraphNode> nodes) {
        // Write your code here
        Set<Integer> set = new HashSet<Integer>();
        for(UndirectedGraphNode node:nodes){
            set.add(node.label);
            for(UndirectedGraphNode nei:node.neighbors)
                set.add(nei.label);
        }
        
        UnionFind uf = new UnionFind(set);
        for(UndirectedGraphNode node:nodes)
            for(UndirectedGraphNode nei:node.neighbors)
                uf.union(node.label, nei.label);
        
        List<List<Integer>> ans = new ArrayList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int num:set){
            int root = uf.find(num);
            if(!map.containsKey(root))
                map.put(root, new ArrayList<Integer>());
            map.get(root).add(num);
        }
        
        for(List<Integer> list:map.values()){
            Collections.sort(list);
            ans.add(list);
        }
        
        return ans;
    }
    
}
