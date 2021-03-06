package com.example.seekrim.util;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-10-17
 * Time: 下午12:08
 * To change this template use File | Settings | File Templates.
 */
public class Tools {
    public static boolean isEmpty = true;

    public static ArrayList<HashMap<String,?>> getAdapterData(String[] temp){
        ArrayList<HashMap<String, ?>> data = new ArrayList<HashMap<String,?>>();
        for(int i = 0; i <temp.length ;i ++){
            HashMap<String, Object> item = new HashMap<String, Object>();
            item.put("index_content_list_textView",temp[i] );
            data.add(item);
        }
        return data;
    }


    public static ArrayList<HashMap<String,?>> getAdapterDataWithIndex(int index,String[][] temp){
        ArrayList<HashMap<String, ?>> data = new ArrayList<HashMap<String,?>>();
        for(int i=0;i<temp[index].length;i++){
            HashMap<String, Object> item = new HashMap<String, Object>();

            item.put("index_content_list_textView",temp[index][i]);
            data.add(item);
        }
        return data;
    }

    public static ArrayList<HashMap<String,?>> getAdapterAllDataWithIndex(int index,String[][][] temp){
        ArrayList<HashMap<String, ?>> data = new ArrayList<HashMap<String,?>>();
        for(int i=0;i<temp[index].length;i++){
            for(int j = 0 ; j <temp[index][i].length ;j ++){
                HashMap<String, Object> item = new HashMap<String, Object>();
                item.put("index_content_list_textView",temp[index][i][j]);
                data.add(item);
            }
        }
        return data;
    }

    public static String getAdapterButtonDataWithIndex(int index, String[][][] temp){
        ArrayList<HashMap<String, ?>> data = new ArrayList<HashMap<String,?>>();
        String str = "";
        for(int i=0;i<temp[index].length;i++){
            for(int j = 0 ; j <temp[index][i].length ;j ++){
               str = temp[index][i][j];
            }
        }
        return  str;
    }

    public static ArrayList<HashMap<String,?>> getAdapterAllDataWithIndex(int firstIndex,int secondIndex,String[][][] temp){
        ArrayList<HashMap<String, ?>> data = new ArrayList<HashMap<String,?>>();
        for(int j=0;j<temp[firstIndex][secondIndex].length;j++){
            HashMap<String, Object> item = new HashMap<String, Object>();
            item.put("index_content_list_textView",temp[firstIndex][secondIndex][j]);
            data.add(item);

        }
        return data;
    }

    public static boolean isEmpty() {
        return isEmpty;
    }

    public static void setEmpty(boolean empty) {
        isEmpty = empty;
    }
}
