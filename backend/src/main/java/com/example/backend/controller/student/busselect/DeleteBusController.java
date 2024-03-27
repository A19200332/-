package com.example.backend.controller.student.busselect;

import com.example.backend.service.bus.DeleteBusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class DeleteBusController {
    @Autowired
    DeleteBusService deleteBusService;
    @PostMapping("/bus/select/delete/")
    public Map<String,String> delete(@RequestParam Map<String,String>data){
        System.out.println(data.get("id"));
        return deleteBusService.delete(data);
    }
}
