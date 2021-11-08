package com.wyw.pojo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Comment {

    private  Integer cId;
    private Integer cIdentity;
    private  String cWord;
    private Date cDate;
}
