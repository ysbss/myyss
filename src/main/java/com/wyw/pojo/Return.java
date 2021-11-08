package com.wyw.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Return {
    private Integer brId;
    private  String brName;
    private String brbName;
    private  Float brPrice;
    private Date brDate;

}
