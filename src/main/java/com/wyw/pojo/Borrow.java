package com.wyw.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Borrow {

    private Integer rId;
    private  String rName;
    private Integer rbId;
    private Date rbDate;
    private  Date rpDate;
}
