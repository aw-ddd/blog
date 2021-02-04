package com.dwj.controller;

import com.dwj.common.JsonResult;
import com.dwj.common.ResultTool;
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
    public JsonResult<Text> save(@RequestBody Text text){
        Text text1 = textService.save(text);
        return ResultTool.success(text1);
    }
    @GetMapping("/findById/{textId}")
    public JsonResult<Text> findById(@PathVariable Integer textId){
        Text text = textService.findById(textId);
        return ResultTool.success(text);
    }
    @GetMapping("/findAll")
    public JsonResult<List<Text>> findAll(){
        List<Text> textList = textService.findAll();
        return ResultTool.success(textList);
    }
}
