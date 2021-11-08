package com.wyw.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private Integer bId;
    private String bName;
    private String bIsbn;
    private String bAuthor;
    private String bPublisher;
    private Date bDate;
    private Float bPrice;
    private Integer bNum;
}
