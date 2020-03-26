package com.wldemo.demo.controller;

import com.wldemo.demo.dto.CommentDTO;
import com.wldemo.demo.dto.QuestionDTO;
import com.wldemo.demo.service.CommentService;
import com.wldemo.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private CommentService commentService;
    @GetMapping("/question/{id}")
    public String question(@PathVariable(name="id")Long id,
                           Model model){
        QuestionDTO questionDTO = questionService.getById(id);
        List<CommentDTO> commentDTOList = commentService.listByQuestionId(id);
        questionService.incView(id);//累加阅读数
        model.addAttribute("question",questionDTO);
        model.addAttribute("comments", commentDTOList);
        return "question";
    }
}
