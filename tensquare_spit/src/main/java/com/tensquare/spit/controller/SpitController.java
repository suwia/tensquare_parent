package com.tensquare.spit.controller;

import com.tensquare.common.domain.PageResult;
import com.tensquare.common.domain.Result;
import com.tensquare.common.domain.StatusCode;
import com.tensquare.spit.pojo.Spit;
import com.tensquare.spit.service.SpitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: sirc_hzr
 * @Date: 2019/3/25 15:45
 * @ClassName: SpitController
 * @Version: 1.0
 * @Description:
 */

@RequestMapping("/spit")
@CrossOrigin
@RestController
public class SpitController {

    @Autowired
    private SpitService spitService;

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping
    public Result findAll() {
        return new Result(true, StatusCode.OK, "查询成功", spitService.findAll());
    }

    @GetMapping("/{spitId}")
    public Result findById(@PathVariable String spitId) {
        return new Result(true, StatusCode.OK, "查询成功", spitService.findById(spitId));
    }

    @PutMapping("/{spitId}")
    public Result update(@PathVariable String spitId, @RequestBody Spit spit) {
        spit.set_id(spitId);
        spitService.update(spit);
        return new Result(true, StatusCode.OK, "更新成功");
    }

    @PostMapping
    public Result add(@RequestBody Spit spit) {
        spitService.save(spit);
        return new Result(true, StatusCode.OK, "保存成功");
    }

    @DeleteMapping("/{spitId}")
    public Result delete(@PathVariable String spitId) {
        spitService.deleteById(spitId);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    @GetMapping("/comment/{parentid}/{page}/{size}")
    public Result findByParentId(@PathVariable String parentid, @PathVariable int page, @PathVariable int size) {
        Page<Spit> byParentId = spitService.findByParentId(parentid, page, size);
        return new Result(true, StatusCode.OK, "查询成功",  new PageResult<Spit>(byParentId.getTotalElements(), byParentId.getContent()));
    }

    @PutMapping("/thumbup/{spitId}")
    public Result thumbup(@PathVariable String spitId) {
        String userid = "111";
        if (redisTemplate.opsForValue().get("thumbup_" + userid) != null) {
            return new Result(false, StatusCode.REPERROR, "不能重复点赞");
        }
        redisTemplate.opsForValue().set("thumbup_" + userid, 1);
        spitService.thumbup(spitId);
        return new Result(true, StatusCode.OK, "点赞成功");
    }

}
