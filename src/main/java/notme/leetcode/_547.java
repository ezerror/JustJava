package notme.leetcode;

public class _547 {

    // 1 1 0
    // 1 1 0
    // 0 0 1

    public static void main(String[] args) {
        int[][] a = new int[3][3];
        a[0][0] = 1;
        a[0][1] = 1;
        a[0][2] = 0;
        a[1][0] = 1;
        a[1][1] = 1;
        a[1][2] = 0;
        a[2][0] = 0;
        a[2][1] = 0;
        a[2][2] = 1;
        System.out.println(new _547().findCircleNum(a));

    }

    int[] visited;
    public int findCircleNum(int[][] isConnected) {
        int length = isConnected.length;
        visited = new int[length];
        int numFriCir = 0;
        for (int i = 0; i < length; i++) {
            if (visited[i] == 0) {
                visited[i] = 1;
                dfs(i, isConnected, visited);
                numFriCir++;
            }

        }
        return numFriCir;
    }

    public void dfs(int col, int[][] M, int[] visited) {
        for (int i = 0; i < M[0].length; i++) {
            if (M[col][i] == 1 && visited[i] == 0) {
                visited[i] = 1;
                dfs(i, M, visited);
            }
        }
    }


}
