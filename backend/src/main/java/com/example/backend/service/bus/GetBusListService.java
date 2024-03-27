package com.example.backend.service.bus;

import com.example.backend.pojo.Bus;

import java.util.List;
import java.util.Map;

public interface GetBusListService {
    List<Bus>getBusList(Map<String,String> data);
}
