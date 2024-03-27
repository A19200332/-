package com.example.backend.service.impl.Bus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backend.mapper.BusMapper;
import com.example.backend.pojo.Bus;
import com.example.backend.service.bus.DeleteBusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DeleteBusServiceImpl implements DeleteBusService {
    @Autowired
    private BusMapper busMapper;
    @Override
    public Map<String, String> delete(Map<String, String> data) {
        Map<String,String>mp=new HashMap<>();
        String authority = data.get("authority");
        if(authority==null||authority.equals("0")){
            mp.put("error_massage","无权限");
            return mp;
        }
        Integer id = Integer.parseInt(data.get("id"));
        QueryWrapper<Bus>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("id",id);
        busMapper.delete(queryWrapper);

        mp.put("error_massage","success");
        return mp;
    }
}
