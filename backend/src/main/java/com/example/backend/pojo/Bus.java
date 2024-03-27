package com.example.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Data
public class Bus {
    @TableId(type= IdType.AUTO)
    private Integer id;
    private String station1;
    private String station2;
    private String station3;
    private String station4;
    private String station5;
}
