package com.company.project.web;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.TFile;
import com.company.project.service.TFileService;
import com.company.project.utlis.HaXiUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2018/06/20.
*/
@RestController
@RequestMapping("/Tfile")
public class TFileController {

    @Resource
    private TFileService tFileService;

    @PostMapping("/add")
    public Result add(TFile tFile) {
        tFileService.save(tFile);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        tFileService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(TFile tFile) {
        tFileService.update(tFile);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        TFile tFile = tFileService.findById(id);
        return ResultGenerator.genSuccessResult(tFile);
    }

    @GetMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<TFile> list = tFileService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }



    @GetMapping("/list2")
    public Result list() throws Exception {

        return ResultGenerator.genSuccessResult (tFileService.findByhaxi("2"));
       //return ResultGenerator.genSuccessResult(haXiUtil.quiryhaxi("2"));
    }
}
