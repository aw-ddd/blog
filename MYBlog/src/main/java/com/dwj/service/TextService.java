package com.dwj.service;

import com.dwj.pojo.Text;

import java.util.List;


public interface TextService {
    Text save(Text text);

    Text findById(Integer textId);

    List<Text> findAll();
}
