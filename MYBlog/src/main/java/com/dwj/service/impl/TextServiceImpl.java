package com.dwj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dwj.mapper.TextMapper;
import com.dwj.pojo.Text;
import com.dwj.service.TextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TextServiceImpl implements TextService {

    @Autowired
    private TextMapper textMapper;

    @Override
    public Text save(Text text) {
        if (null == text.getTextId()){
            //新增
            text.setCreateDate(LocalDateTime.now());
            text.setUserId(1);
            Integer i = textMapper.insert(text);
            if (i <= 0){
                throw new RuntimeException("保存文本失败");
            }
        }else{
            QueryWrapper<Text> wrapper = new QueryWrapper<>();
            wrapper.eq("text_id",text.getTextId());
            //编辑
            text.setUpdateDate(LocalDateTime.now());
            textMapper.update(text,wrapper);
        }
        return text;
    }

    @Override
    public Text findById(Integer textId) {
        QueryWrapper<Text> wrapper = new QueryWrapper<>();
        wrapper.eq("text_id",textId);
        Text text = textMapper.selectOne(wrapper);
        return text;
    }

    @Override
    public List<Text> findAll() {
        QueryWrapper<Text> wrapper = new QueryWrapper();
        List<Text> textList = textMapper.selectList(wrapper);
        return textList;
    }
}
