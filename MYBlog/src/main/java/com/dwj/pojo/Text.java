package com.dwj.pojo;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
public class Text {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer textId;
    private Integer userId;
    private String title;
    private String text;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

}
