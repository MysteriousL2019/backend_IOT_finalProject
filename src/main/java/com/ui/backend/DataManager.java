package com.ui.backend;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ui.backend.entity.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataManager {
    private static DataManager instance = null;
    /**
     * get the unique instance of DataManager
     * @return reference of DataManager object
     * @throws IOException
     */
    public static DataManager getInstance() throws IOException {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    public static ArrayList<ArrayList<datapoint>> lists = new ArrayList<>();
    public static ArrayList<String> IDs=new ArrayList<>();



    public static String sendGetRequest(String url,Map<String, String> params) {

        HttpURLConnection con = null;

        BufferedReader buffer = null;
        StringBuffer resultBuffer = null;

        try {
            String entireUrlStr=getRqstUrl(url,params);
            URL entireUrl = new URL(entireUrlStr);
            //得到连接对象
            con = (HttpURLConnection) entireUrl.openConnection();
            //设置请求类型
            con.setRequestMethod("GET");
            //设置请求需要返回的数据类型和字符集类型
            con.setRequestProperty("Content-Type", "application/json;charset=GBK");
            con.setRequestProperty("api-key","zEslkuvPCzLnv2t7Wy=jXWZdsHY=");
            //允许写出
            con.setDoOutput(true);
            //允许读入
            con.setDoInput(true);
            //不使用缓存
            con.setUseCaches(false);
            //得到响应码
            int responseCode = con.getResponseCode();

            System.out.println(responseCode+" "+con.getResponseMessage());
            if(responseCode == HttpURLConnection.HTTP_OK){
                //得到响应流
                InputStream inputStream = con.getInputStream();
                //将响应流转换成字符串
                resultBuffer = new StringBuffer();
                String line;
                buffer = new BufferedReader(new InputStreamReader(inputStream, "GBK"));
                while ((line = buffer.readLine()) != null) {
                    resultBuffer.append(line);
                }
                return resultBuffer.toString();
            }

        }catch(Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 拼接get请求的url请求地址
     */
    public static String getRqstUrl(String url, Map<String, String> params) {
        StringBuilder builder = new StringBuilder(url);
        boolean isFirst = true;
        for (String key : params.keySet()) {
            if (key != null && params.get(key) != null) {
                if (isFirst) {
                    isFirst = false;
                    builder.append("?");
                } else {
                    builder.append("&");
                }
                builder.append(key)
                        .append("=")
                        .append(params.get(key));
            }
        }
        return builder.toString();
    }


    public static void SaveJson(String data,String name){
        File directory = new File("");// 参数为空
        String courseFile;
        try {
            courseFile = directory.getCanonicalPath();
            System.out.println(courseFile);

            File path=new File(courseFile+"/Files/"+name+".json");
            System.out.println("path: "+path);
            System.out.println("data "+data);
            if(path.exists()){
                System.out.println("文件存在");
            }else{
                try{
                    path.createNewFile();
                }catch (IOException e){
                    e.printStackTrace();
                }
                System.out.println("文件创建成功");
            }
            FileWriter  fileWriter=null;
            try{
                fileWriter =new FileWriter (path);
                fileWriter.write(data);
            }catch (Exception e){
                e.printStackTrace();
            }finally{
                fileWriter.flush();
                fileWriter.close();
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


    public DataManager() throws IOException {

        //url中的35379643替换为自己组对应的设备ID(登录OneNet控制台查看）
        String url ="http://api.heclouds.com/devices/35379866/datapoints";

        //paras的名称和类型查看文档
        Map<String, String> params = new HashMap<>();
        params.put("start","2022-10-10T08:00:35");
        params.put("end","2022-11-07T08:00:35");
        String ret = sendGetRequest(url, params);

        JSONObject jsonObject = JSONObject.parseObject(ret);



        if(jsonObject.getString("errno").equals("0")){

//            System.out.println(jsonObject);
            String data=jsonObject.getString("data");
//            System.out.println(data);

            JSONObject data_json=JSONObject.parseObject(data);
            String count=data_json.getString("count");

            String dataSteams=data_json.getString("datastreams");
//            System.out.println(dataSteams);

            JSONArray x=data_json.getJSONArray("datastreams");
//            System.out.println(x);
//            System.out.println("-----");


            for(int i=0;i<x.size();i++){
                JSONObject ob=(JSONObject)x.get(i);
//                System.out.println(ob);
                String dataPoints=ob.getString("datapoints");
                String id=ob.getString("id");
//                System.out.println("id "+id);
//                System.out.println("datapoints "+dataPoints);
                SaveJson(dataPoints,id);
                IDs.add(id);
//                System.out.println("!!!!!!!!!!");

                ArrayList<datapoint> datapoints = (ArrayList<datapoint>) JSON.parseArray(IO.read("Files/"+id+".json"),datapoint.class);
                for(datapoint xx:datapoints){
                    xx.setId(id);
                }

                lists.add(datapoints);
            }

//            System.out.println("遍历");
            for(ArrayList<datapoint> xx:lists){
                for(datapoint xxx: xx){
                    System.out.println(xxx.getAt());
                    System.out.println(xxx.getId());
                    System.out.println(xxx.getValue());

                }
            }



            System.out.println("数据无错误");
            System.out.println("共读取"+count+"条数据");
            System.out.println("datapoints ID: "+IDs.toString());

            SaveJson(ret,"data_all");
        }else{
            System.out.println("数据出错");
        }
    }

    public static void main(String[] args) throws IOException {


    }
}
