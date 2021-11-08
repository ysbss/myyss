package com.wyw.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private Integer sId;
    private String sName;
    private  String sPassword;
    private Integer sGender;
}
