package com.example.backend.controller.student.busselect;

import com.example.backend.service.bus.UpdateBusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UpdateBusController {
    @Autowired
    UpdateBusService updateBusService;
    @PostMapping("/bus/select/update/")
    public Map<String,String> update(@RequestParam Map<String,String>data){
        System.out.println(data);
        return updateBusService.updateBusService(data);
    }
}
