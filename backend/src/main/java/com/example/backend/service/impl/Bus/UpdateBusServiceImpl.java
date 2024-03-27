package com.example.backend.service.impl.Bus;

import com.example.backend.mapper.BusMapper;
import com.example.backend.pojo.Bus;
import com.example.backend.service.bus.UpdateBusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UpdateBusServiceImpl implements UpdateBusService {
    @Autowired
    private BusMapper busMapper;
    @Override
    public Map<String, String> updateBusService(Map<String, String> data) {
        String id =data.get("id");
        String []stations=data.get("stations").split("_");
        int blankStation=0;
        Map<String,String>mp=new HashMap<>();
        String authority = data.get("authority");
        if(authority==null||authority.equals("0")){
            mp.put("error_massage","无权限");
            return mp;
        }
        for(String s:stations){
            if(s.equals("#"))blankStation=blankStation+1;
        }
        if(blankStation>3){
            mp.put("error_massage","至少要有两个站点");
            return mp;
        }
        if(stations.length>5){
            mp.put("error_massage","最多五个站点");
            return mp;
        }
        boolean flag=true;
        for (String station : stations) {
            if (station != null && !station.equals("")) {
                flag = false;
                break;
            }
        }
        if(flag){
            mp.put("error_massage","站点不能全为空");
            return mp;
        }
        for(int i=0;i<stations.length;i++){
            for(int j=i+1;j<stations.length;j++){
                if(stations[i]!=null&&(stations[i].equals(stations[j])&&!stations[i].equals("#"))){
                    mp.put("error_massage","不能存在两个相同站点");
                    return mp;
                }
            }
        }
        Bus bus = new Bus(Integer.parseInt(id),stations[0].substring(1),stations[1].substring(1),stations[2].substring(1),stations[3].substring(1),stations[4].substring(1));
        busMapper.updateById(bus);

        mp.put("error_massage","success");
        return mp;
    }
}
