package notme.leetcode;

import java.util.List;

public class _1202 {
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        char[] cs = s.toCharArray();
        init(cs.length);//初始化并查集
        for(List<Integer> list:pairs)
            add(is,list.get(0),list.get(1));//设置根节点关联
        over();//结束并查集
        //相同根节点的进行排序，就是字典序最小的字符串
        //字符串已限制只有小写英文字母，可以使用桶排序，统计每个字符数量
        int[][] map = new int[cs.length][26];//统计根节点字符数量
        int[] ts = new int[cs.length];//记录每个根节点最小字符下标
        for(int i=0;i<cs.length;i++)
            map[is[i]][cs[i]-'a']++;//根据根节点，字符统计
        for(int i=0;i<cs.length;i++){
            for(int j=ts[is[i]];j<26;j++){//根据记录的最小下标开始遍历
                if(map[is[i]][j]>0){//如果某字符不为空
                    map[is[i]][j]--;//记录的字符数量减一
                    cs[i] = (char)('a'+j);
                    ts[is[i]] = j;//记录新的最小值记录
                    break;
                }
            }
        }
        return new String(cs);
    }
    int[] is;
    public void init(int len){
        is = new int[len];
        for(int i=0;i<is.length;i++)
            is[i] = i;
    }
    public void add(int[] is,int a,int b){
        is[get(is,a)] = get(is,b);
    }
    public int get(int[] is,int a){
        if(is[a]!=a)
            is[a] = get(is,is[a]);
        return is[a];
    }
    public void over(){
        for(int i=0;i<is.length;i++)
            get(is,i);
    }
}
