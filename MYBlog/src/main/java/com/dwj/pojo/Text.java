package com.dwj.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

//文本
@Data
public class Text {
    private Integer textId;
    private Integer userId;
    private String title;
    private String text;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

}
