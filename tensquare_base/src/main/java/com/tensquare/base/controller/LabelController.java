package com.tensquare.base.controller;

import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.LabelService;
import com.tensquare.common.domain.PageResult;
import com.tensquare.common.domain.Result;
import com.tensquare.common.domain.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: sirc_hzr
 * @Date: 2019/3/5 08:53
 * @ClassName: LabelController
 * @Version: 1.0
 * @Description:
 */
@RestController
@RequestMapping("/label")
@CrossOrigin
public class LabelController {

    @Autowired
    private LabelService labelService;


    /*
     * @Author sirc_hzr
     * @Description  TODO 查询全部标签
     * @Date 9:07 2019/3/5
     * @Param 
     * @return 
     **/
    
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        return new Result(true, StatusCode.OK, "查询成功", labelService.findAll());
    }

    /**
     * @Author sirc_hzr
     * @Description  TODO 根据id查询标签
     * @Date 9:10 2019/3/5
     * @Param [labelId]
     * @return com.tensquare.common.domain.Result
     **/

    @RequestMapping(value = "/{labelId}", method = RequestMethod.GET)
    public Result findById(@PathVariable String labelId) {
        return new Result(true, StatusCode.OK, "查询成功", labelService.findById(labelId));
    }


    /**
     * @Author sirc_hzr
     * @Description  TODO 增加标签
     * @Date 9:13 2019/3/5
     * @Param [label]
     * @return com.tensquare.common.domain.Result
     **/

    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Label label) {
        labelService.add(label);
        return new Result(true, StatusCode.OK, "增加成功");
    }


    /**
     * @Author sirc_hzr
     * @Description  TODO 根据id更新标签
     * @Date 9:17 2019/3/5
     * @Param [label, labelId]
     * @return com.tensquare.common.domain.Result
     **/

    @RequestMapping(value = "/{labelId}", method = RequestMethod.PUT)
    public Result update(@RequestBody Label label, @PathVariable String labelId) {
        label.setId(labelId);
        labelService.update(label);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /*
     * @Author sirc_hzr
     * @Description  TODO 根据id删除标签
     * @Date 9:22 2019/3/5
     * @Param 
     * @return 
     **/
    
    @RequestMapping(value = "/{labelId}", method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable String labelId) {
        labelService.deleteById(labelId);
        return new Result(true, StatusCode.OK, "删除成功");
    }


    /*
     * @Author sirc_hzr
     * @Description  TODO 根据条件查询标签内容
     * @Date 10:07 2019/3/6
     * @Param
     * @return
     **/

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result findBySearch(@RequestBody Label label) {
        List<Label> list = labelService.findBySearch(label);
        return new Result(true, StatusCode.OK, "查询成功", list);
    }


    /**
     * @Author sirc_hzr
     * @Description  TODO 根据条件+分页查询标签内容
     * @Date 10:52 2019/3/6
     * @Param [label, page, size]
     * @return com.tensquare.common.domain.Result
     **/

    @RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.POST)
    public Result findBySearch(@RequestBody Label label, @PathVariable int page, @PathVariable int size) {
        Page pageData = labelService.findBySearch(label, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<Label>(pageData.getTotalElements(), pageData.getContent()));
    }



}
