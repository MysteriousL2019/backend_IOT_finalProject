package com.ui.backend.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ui.backend.DataManager;
import com.ui.backend.entity.QueryInfo;
import com.ui.backend.entity.datapoint;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
@RestController
public class saveControllor {
    HashMap<String,Object> res=new HashMap<>();
    ArrayList<ArrayList<datapoint>> tmp = DataManager.getInstance().lists;
    ArrayList<datapoint> Return=null;


    public saveControllor() throws IOException {

    }

    @CrossOrigin
    @RequestMapping(value="/saveFile",method= RequestMethod.POST)
    public String saveFile(@RequestBody QueryInfo queryInfo) throws JsonProcessingException {
        String begin[]=queryInfo.getDate();
        String date_begin=begin[0].substring(0,10);
        String date_end=begin[1].substring(0,10);

        String time_begin=begin[0].substring(11,19);
        String time_end=begin[1].substring(11,19);
        int number=0;
        if(queryInfo.getDate()!=null&& queryInfo.getId()!=null){
            for(ArrayList<datapoint> xx:tmp){
                for(datapoint xxx: xx){

                    String Date=xxx.getAt().substring(0,10);
                    String Time=xxx.getAt().substring(11,19);
                    //在其中
                    if(Date.compareTo(date_begin)>=0&& Date.compareTo(date_end)<=0 &&queryInfo.getId().equals(xxx.getId())){
                        if(Date.compareTo(date_begin)==0|| Date.compareTo(date_end)==0){
                            if(Time.compareTo(time_begin)>=0&&Time.compareTo(time_end)<=0){

                                Return.add(xxx);
                                number+=1;
                            }
                        }else{

                            Return.add(xxx);
                            number+=1;
                        }
                    }

                }
            }
        }else{

        }


        System.out.println(Return.toString());
        Collections.reverse(Return);
        System.out.println(Return.toString());
//        return "";
        //这里不能把res直接变
        res.put("number",number);
        res.put("result",Return);


        ObjectMapper mapper = new ObjectMapper();
        String jsonResponse = mapper.writeValueAsString(Return);
        System.out.println("    sss  "+jsonResponse);

        String Res = JSON.toJSONString(res);
        return Res;

    }
}

