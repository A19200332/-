package com.example.backend.controller.student.busselect;

import com.example.backend.pojo.Bus;
import com.example.backend.service.bus.GetBusListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class GetBusController {
    @Autowired
    GetBusListService getBusListService;
    @PostMapping("/bus/select/getlist/")
    public List<Bus> getlist(@RequestParam Map<String,String>data){
        return getBusListService.getBusList(data);
    }
}
