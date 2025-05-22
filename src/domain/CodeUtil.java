package domain;

import java.util.ArrayList;
import java.util.Random;

public class CodeUtil {
    public static String getCode(){
        ArrayList<Character> list=new ArrayList<>();
        for(int i=0;i<26;i++){
            list.add((char)('a'+i));
            list.add((char)('A'+i));
        }
        String result="";
        Random r=new Random();
        for(int i=0;i<4;i++){
            int index=r.nextInt(list.size());
            result+=list.get(index);
        }
        int number=r.nextInt(10);
        result=result+number;
        char[] ch=result.toCharArray();
        int index=r.nextInt(ch.length);
        char temp=ch[4];
        ch[4]=ch[index];
        ch[index]=temp;
        String code=new String(ch);
        System.out.println(code);
        return code;

    }


}
