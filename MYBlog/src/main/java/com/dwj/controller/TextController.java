package com.dwj.controller;

import com.dwj.pojo.Text;
import com.dwj.service.TextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("text")
public class TextController {
    @Autowired
    private TextService textService;

    @PostMapping("save")
    public Text save(@RequestBody Text text){
        Text text1 = textService.save(text);
        return text1;
    }
    @GetMapping("/findById/{textId}")
    public Text findById(@PathVariable Integer textId){
        Text text = textService.findById(textId);
        return text;
    }
    @GetMapping("/findAll")
    public List<Text> findAll(){
        List<Text> textList = textService.findAll();
        return textList;
    }
}
