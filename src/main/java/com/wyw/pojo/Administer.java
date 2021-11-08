package com.wyw.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Administer {
    private Integer aId;
    private String aName;
    private  String aPassword;
    private Integer aGender;
}
