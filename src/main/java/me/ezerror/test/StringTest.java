package me.ezerror.test;

public class StringTest {
    public static void main(String[] args) {
        String a="/usr/bin/bash /usr/local/bin/svnfilterco.sh ${sourcesvnurl} source ${sourcesvnusername} ${sourcesvnpassword}\n" +
                "/usr/bin/bash /usr/local/bin/svnfilterco.sh ${targetsvnurl} target ${targetsvnusername} ${targetsvnpassword}\n"+
                "asdasdasdasdas";
        String targetCmd = a.substring(a.lastIndexOf("/usr/bin/bash"), a.indexOf("${targetsvnpassword}")+"${targetsvnpassword}".length());
        System.out.println(targetCmd);
        System.out.println(a.replace(targetCmd, ""));
        System.out.println("430121000000".substring(4,12));
    }
}
