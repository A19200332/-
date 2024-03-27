package com.example.backend.service.impl.Bus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backend.mapper.BusMapper;
import com.example.backend.pojo.Bus;
import com.example.backend.service.bus.GetBusListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GetBusListServiceImpl implements GetBusListService {
    @Autowired
    private BusMapper busMapper;
    private List<Bus>ans=new ArrayList<>();
    private void quiksort(int l,int r){
        if(l>=r)return ;
        int mid=(l+r+1)/2;
        int x= ans.get(mid).getId();
        int i=l-1,j=r+1;
        while(i<j) {
            do i++;while(ans.get(i).getId()<x);
            do j--;while(ans.get(j).getId()>x);
            if(i<j) {
                Bus temp;
                temp= ans.get(i);
                ans.set(i,ans.get(j));
                ans.set(j,temp);
            }
        }
        quiksort(l,i-1);
        quiksort(i,r);
    }
    @Override
    public List<Bus> getBusList(Map<String, String> data) {
        ans.clear();
        String id= data.get("id");
        String origin = data.get("origin_station");
        String destination =data.get("destination_station");


        for(int i=1;i<5;i++){
            for(int j=i+1;j<=5;j++){
                QueryWrapper<Bus>queryWrapper=new QueryWrapper<>();
                if(id!=null&&!id.equals("")){
                    queryWrapper.eq("id",Integer.parseInt(id));
                }
                String i_char = Integer.toString(i);
                String j_char = Integer.toString(j);
                if(origin!=null&&!origin.equals("")){
                    System.out.println(1);
                    queryWrapper.eq("station"+i_char,origin);
                }
                if(destination!=null&&!destination.equals("")){
                    System.out.println(2);
                    queryWrapper.eq("station"+j_char,destination);
                }
                ans.addAll(busMapper.selectList(queryWrapper));
            }
        }
        Set<Bus> set=new HashSet<>(ans);
        ans.clear();
        ans.addAll(set);
        this.quiksort(0,ans.size()-1);
        return ans;
    }
}
