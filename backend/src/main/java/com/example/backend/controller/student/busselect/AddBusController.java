package com.example.backend.controller.student.busselect;

import com.example.backend.service.bus.AddBusService;
import com.example.backend.service.impl.Bus.AddBusServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AddBusController {
    @Autowired
    private AddBusService addBusService;
    @PostMapping("/bus/select/add/")
    public Map<String,String> add(@RequestParam Map<String,String>data){
        return addBusService.add(data);
    }
}
