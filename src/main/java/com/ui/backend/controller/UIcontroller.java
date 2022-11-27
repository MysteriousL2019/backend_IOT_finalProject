package com.ui.backend.controller;

import com.alibaba.fastjson.JSON;
import com.ui.backend.DataManager;
import com.ui.backend.entity.QueryInfo;
import com.ui.backend.entity.datapoint;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.regex.Pattern;

@RestController
public class UIcontroller {
    HashMap<String,Object> res=new HashMap<>();

    ArrayList<ArrayList<datapoint>> tmp = DataManager.getInstance().lists;

//    ArrayList<HashMap<String,Object>> Return=new ArrayList<>();
    ArrayList<datapoint> Return=null;

    public UIcontroller() throws IOException {
    }

    @CrossOrigin
    @RequestMapping(value="/getValue",method= RequestMethod.POST)
    public synchronized String getValue(@RequestBody QueryInfo queryInfo){
        Return=new ArrayList<>();
        int pageStart = (queryInfo.getPageNum()-1)*queryInfo.getPageSize();
        System.out.println("触发");
//        System.out.println(queryInfo.getDate());
        System.out.println(queryInfo.toString());

        String begin[]=queryInfo.getDate();
        String date_begin=begin[0].substring(0,10);
        String date_end=begin[1].substring(0,10);

        String time_begin=begin[0].substring(11,19);
        String time_end=begin[1].substring(11,19);
        System.out.println("date "+date_begin+"  "+time_begin);

        int number=0;
        float max=0;
        float min=999999999;
        float sum=0;
        int count_num=0;
        if(queryInfo.getDate()!=null&& queryInfo.getId()!=null){
            Pattern pattern = Pattern.compile("[0-9]*");

            for(ArrayList<datapoint> xx:tmp){
                for(datapoint xxx: xx){

                    String Date=xxx.getAt().substring(0,10);
                    String Time=xxx.getAt().substring(11,19);
                    //在其中
                    if(Date.compareTo(date_begin)>=0&& Date.compareTo(date_end)<=0 &&queryInfo.getId().equals(xxx.getId())){
                        if(Date.compareTo(date_begin)==0|| Date.compareTo(date_end)==0){
                            if(Time.compareTo(time_begin)>=0&&Time.compareTo(time_end)<=0){

                                if(pattern.matcher(xxx.getValue()).matches()){
                                    count_num++;
                                    int temp=Integer.parseInt(xxx.getValue());
                                    if(temp>max){
                                        max=temp;
                                    }
                                    if(temp<min){
                                        min=temp;
                                    }
                                    sum+=temp;
                                }
                                Return.add(xxx);
                                number+=1;
                            }
                        }else{

                            if(pattern.matcher(xxx.getValue()).matches()){
                                count_num++;
                                int temp=Integer.parseInt(xxx.getValue());
                                if(temp>max){
                                    max=temp;
                                }
                                if(temp<min){
                                    min=temp;
                                }
                                sum+=temp;
                            }
                            Return.add(xxx);
                            number+=1;
                        }
                    }

                }
            }
        }else{

        }
//        return "";
        //这里不能把res直接变
        float avg=(float)sum/count_num;
        res.put("avg",avg);
        res.put("max",max);
        res.put("min",min);

        System.out.println(Return.toString());
        Collections.reverse(Return);
        System.out.println(Return.toString());


        res.put("number",number);
        res.put("result",Return);


        String Res = JSON.toJSONString(res);
//        System.out.println("res  "+Res);
        return Res;
    }

    @CrossOrigin
    @RequestMapping(value="/getValue2",method= RequestMethod.POST)
    public synchronized String getValue2(@RequestBody QueryInfo queryInfo){
        Return=new ArrayList<>();
        int pageStart = (queryInfo.getPageNum()-1)*queryInfo.getPageSize();
        System.out.println("触发");
//        System.out.println(queryInfo.getDate());
        System.out.println(queryInfo.toString());

        String begin[]=queryInfo.getDate();
        String date_begin=begin[0].substring(0,10);
        String date_end=begin[1].substring(0,10);

        String time_begin=begin[0].substring(11,19);
        String time_end=begin[1].substring(11,19);
        System.out.println("date "+date_begin+"  "+time_begin);

        int number=0;
        float max=0;
        float min=999999999;
        float sum=0;
        int count_num=0;
        if(queryInfo.getDate()!=null&& queryInfo.getId()!=null){
            Pattern pattern = Pattern.compile("[0-9]*");

            for(ArrayList<datapoint> xx:tmp){
                for(datapoint xxx: xx){

                    String Date=xxx.getAt().substring(0,10);
                    String Time=xxx.getAt().substring(11,19);
                    //在其中
                    if(Date.compareTo(date_begin)>=0&& Date.compareTo(date_end)<=0 &&queryInfo.getId().equals(xxx.getId())){
                        if(Date.compareTo(date_begin)==0|| Date.compareTo(date_end)==0){
                            if(Time.compareTo(time_begin)>=0&&Time.compareTo(time_end)<=0){

                                if(pattern.matcher(xxx.getValue()).matches()){
                                    count_num++;
                                    int temp=Integer.parseInt(xxx.getValue());
                                    if(temp>max){
                                        max=temp;
                                    }
                                    if(temp<min){
                                        min=temp;
                                    }
                                    sum+=temp;
                                }
                                Return.add(xxx);
                                number+=1;
                            }
                        }else{

                            if(pattern.matcher(xxx.getValue()).matches()){
                                count_num++;
                                int temp=Integer.parseInt(xxx.getValue());
                                if(temp>max){
                                    max=temp;
                                }
                                if(temp<min){
                                    min=temp;
                                }
                                sum+=temp;
                            }
                            Return.add(xxx);
                            number+=1;
                        }
                    }

                }
            }
        }else{

        }
//        return "";
        //这里不能把res直接变
        float avg=(float)sum/count_num;
        res.put("avg",avg);
        res.put("max",max);
        res.put("min",min);

        System.out.println(Return.toString());
        Collections.reverse(Return);
        System.out.println(Return.toString());


        res.put("number",number);
        res.put("result",Return);


        String Res = JSON.toJSONString(res);
//        System.out.println("res  "+Res);
        return Res;
    }

    @CrossOrigin
    @RequestMapping(value="/getValue3",method= RequestMethod.POST)
    public synchronized String getValue3(@RequestBody QueryInfo queryInfo){
        Return=new ArrayList<>();
        int pageStart = (queryInfo.getPageNum()-1)*queryInfo.getPageSize();
        System.out.println("触发");
//        System.out.println(queryInfo.getDate());
        System.out.println(queryInfo.toString());

        String begin[]=queryInfo.getDate();
        String date_begin=begin[0].substring(0,10);
        String date_end=begin[1].substring(0,10);

        String time_begin=begin[0].substring(11,19);
        String time_end=begin[1].substring(11,19);
        System.out.println("date "+date_begin+"  "+time_begin);

        int number=0;
        float max=0;
        float min=999999999;
        float sum=0;
        int count_num=0;
        if(queryInfo.getDate()!=null&& queryInfo.getId()!=null){
            Pattern pattern = Pattern.compile("[0-9]*");

            for(ArrayList<datapoint> xx:tmp){
                for(datapoint xxx: xx){

                    String Date=xxx.getAt().substring(0,10);
                    String Time=xxx.getAt().substring(11,19);
                    //在其中
                    if(Date.compareTo(date_begin)>=0&& Date.compareTo(date_end)<=0 &&queryInfo.getId().equals(xxx.getId())){
                        if(Date.compareTo(date_begin)==0|| Date.compareTo(date_end)==0){
                            if(Time.compareTo(time_begin)>=0&&Time.compareTo(time_end)<=0){

                                if(pattern.matcher(xxx.getValue()).matches()){
                                    count_num++;
                                    int temp=Integer.parseInt(xxx.getValue());
                                    if(temp>max){
                                        max=temp;
                                    }
                                    if(temp<min){
                                        min=temp;
                                    }
                                    sum+=temp;
                                }
                                Return.add(xxx);
                                number+=1;
                            }
                        }else{

                            if(pattern.matcher(xxx.getValue()).matches()){
                                count_num++;
                                int temp=Integer.parseInt(xxx.getValue());
                                if(temp>max){
                                    max=temp;
                                }
                                if(temp<min){
                                    min=temp;
                                }
                                sum+=temp;
                            }
                            Return.add(xxx);
                            number+=1;
                        }
                    }

                }
            }
        }else{

        }
//        return "";
        //这里不能把res直接变
        float avg=(float)sum/count_num;
        res.put("avg",avg);
        res.put("max",max);
        res.put("min",min);

        System.out.println(Return.toString());
        Collections.reverse(Return);
        System.out.println(Return.toString());


        res.put("number",number);
        res.put("result",Return);


        String Res = JSON.toJSONString(res);
//        System.out.println("res  "+Res);
        return Res;
    }

}
