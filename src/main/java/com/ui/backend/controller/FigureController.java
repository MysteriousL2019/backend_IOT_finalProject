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
import java.util.HashMap;
@RestController
public class FigureController {
    ArrayList<ArrayList<datapoint>> tmp = DataManager.getInstance().lists;
    HashMap<String,Object> res=new HashMap<>();
    HashMap<String,Integer> temp=new HashMap<>();

    public FigureController() throws IOException {
    }

    @CrossOrigin
    @RequestMapping(value="/getAll",method= RequestMethod.POST)
    public String getAll(@RequestBody QueryInfo queryInfo) throws JsonProcessingException {
        String begin[]=queryInfo.getDate();
        String date_begin=begin[0].substring(0,10);
        String date_end=begin[1].substring(0,10);

        String time_begin=begin[0].substring(11,19);
        String time_end=begin[1].substring(11,19);
        for( ArrayList<datapoint>xx : tmp){
            for(datapoint xxx:xx){
                if(temp.containsKey(xxx.getId())){
                    temp.put(xxx.getId(),0);
                }else{
                    temp.put(xxx.getId(),0);
                }
            }
        }

        if(queryInfo.getDate()!=null) {
            for (ArrayList<datapoint> xx : tmp) {
                for (datapoint xxx : xx) {

                    String Date = xxx.getAt().substring(0, 10);
                    String Time = xxx.getAt().substring(11, 19);
                    if (Date.compareTo(date_begin) >= 0 && Date.compareTo(date_end) <= 0 ) {
                        if (Date.compareTo(date_begin) == 0 || Date.compareTo(date_end) == 0) {
                            if (Time.compareTo(time_begin) >= 0 && Time.compareTo(time_end) <= 0) {
//                                      number+=1;
                                if(temp.containsKey(xxx.getId())){
                                    //如果存在，就在对应的key的number+1
                                    temp.computeIfPresent(xxx.getId(),(key,value)->value+1);
                                }else{
                                    temp.put(xxx.getId(),1);
                                }
//                                temp.put(xxx.getId(),);
                            }
                        } else {
//                            number+=1;
                            if(temp.containsKey(xxx.getId())){
                                //如果存在，就在对应的key的number+1
                                temp.computeIfPresent(xxx.getId(),(key,value)->value+1);
                            }else{
                                temp.put(xxx.getId(),1);
                            }
                        }
                    }
                }

            }
        }
        System.out.println(temp.toString());
        res.put("numberOfEach",temp);
        String Res= JSON.toJSONString(res);
        return Res;
    }
}
