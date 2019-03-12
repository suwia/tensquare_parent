package com.tensquare.qa.controller;
import java.util.List;
import java.util.Map;

import com.tensquare.common.domain.PageResult;
import com.tensquare.common.domain.Result;
import com.tensquare.common.domain.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tensquare.qa.pojo.Problem;
import com.tensquare.qa.service.ProblemService;


/**
 * 控制器层
 * @author Administrator
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/problem")
public class ProblemController {

	@Autowired
	private ProblemService problemService;


	/*
	 * @Author sirc_hzr
	 * @Description  TODO 查询有最新回答的问题
	 * @Date 11:09 2019/3/7
	 * @Param
	 * @return
	 **/

	@RequestMapping(value = "/newlist/{labelid}/{page}/{size}", method = RequestMethod.GET)
	public Result newlist(@PathVariable String labelid, @PathVariable int page, @PathVariable int size) {
		Page<Problem> newlist = problemService.newlist(labelid, page-1, size);
		PageResult<Problem> pageResult = new PageResult(newlist.getTotalElements(), newlist.getContent());
		return new Result(true, StatusCode.OK, "查询成功", pageResult);
	}

	/*
	 * @Author sirc_hzr
	 * @Description  TODO 查询回答数量最多的热门问题
	 * @Date 10:52 2019/3/7
	 * @Param
	 * @return
	 **/

	@RequestMapping(value = "/hotlist/{labelid}/{page}/{size}", method = RequestMethod.GET)
	public Result hotlist(@PathVariable String labelid, @PathVariable int page, @PathVariable int size) {
		Page<Problem> hotlist = problemService.hotlist(labelid, page-1, size);
		PageResult<Problem> pageResult = new PageResult(hotlist.getTotalElements(), hotlist.getContent());
		return new Result(true, StatusCode.OK, "查询成功", pageResult);
	}

	/*
	 * @Author sirc_hzr
	 * @Description  TODO 查询待回答的问题（回答数为0）
	 * @Date 10:53 2019/3/7
	 * @Param
	 * @return
	 **/

	@RequestMapping(value = "/waitlist/{labelid}/{page}/{size}", method = RequestMethod.GET)
	public Result waitlist(@PathVariable String labelid, @PathVariable int page, @PathVariable int size) {
		Page<Problem> waitlist = problemService.waitlist(labelid, page-1, size);
		PageResult<Problem> pageResult = new PageResult(waitlist.getTotalElements(), waitlist.getContent());
		return new Result(true, StatusCode.OK, "查询成功", pageResult);
	}
	
	
	/**
	 * 查询全部数据
	 * @return
	 */
	@RequestMapping(method= RequestMethod.GET)
	public Result findAll(){
		return new Result(true,StatusCode.OK,"查询成功",problemService.findAll());
	}
	
	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public Result findById(@PathVariable String id){
		return new Result(true,StatusCode.OK,"查询成功",problemService.findById(id));
	}


	/**
	 * 分页+多条件查询
	 * @param searchMap 查询条件封装
	 * @param page 页码
	 * @param size 页大小
	 * @return 分页结果
	 */
	@RequestMapping(value="/search/{page}/{size}",method=RequestMethod.POST)
	public Result findSearch(@RequestBody Map searchMap , @PathVariable int page, @PathVariable int size){
		Page<Problem> pageList = problemService.findSearch(searchMap, page, size);
		return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<Problem>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",problemService.findSearch(searchMap));
    }
	
	/**
	 * 增加
	 * @param problem
	 */
	@RequestMapping(method=RequestMethod.POST)
	public Result add(@RequestBody Problem problem  ){
		problemService.add(problem);
		return new Result(true,StatusCode.OK,"增加成功");
	}
	
	/**
	 * 修改
	 * @param problem
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@RequestBody Problem problem, @PathVariable String id ){
		problem.setId(id);
		problemService.update(problem);		
		return new Result(true,StatusCode.OK,"修改成功");
	}
	
	/**
	 * 删除
	 * @param id
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public Result delete(@PathVariable String id ){
		problemService.deleteById(id);
		return new Result(true,StatusCode.OK,"删除成功");
	}
	
}
