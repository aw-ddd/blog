package com.dwj.service.impl;

import com.dwj.mapper.TextMapper;
import com.dwj.pojo.Text;
import com.dwj.service.TextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TextServiceImpl implements TextService {

    @Autowired
    private TextMapper textMapper;

    @Override
    public Text save(Text text) {
        if (null == text.getTextId()){
            //新增
            text.setCreateDate(new Date());
            text.setUserId(1);
            int i = textMapper.insertSelective(text);
            if (i <= 0){
                throw new RuntimeException("保存文本失败");
            }
        }else{
            //编辑
            text.setUpdateDate(new Date());
            textMapper.updateByPrimaryKey(text);
        }
        return text;
    }

    @Override
    public Text findById(Integer textId) {
        Text text = textMapper.selectByPrimaryKey(textId);
        return text;
    }

    @Override
    public List<Text> findAll() {
        List<Text> textList = textMapper.selectAll();
        return textList;
    }
}
