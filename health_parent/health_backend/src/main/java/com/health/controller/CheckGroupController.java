package com.health.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.health.entity.PageResult;
import com.health.entity.QueryPageBean;
import com.health.entity.Result;
import com.health.entity.WanNeng;
import com.health.pojo.TCheckgroup;
import com.health.pojo.TCheckitem;
import com.health.service.CheckGroupService;
import com.health.service.CheckItemService;

@RestController
@RequestMapping("/checkgroup")//检查组管理
public class CheckGroupController {

    @Reference
    private CheckGroupService checkGroupService;
    /**
     * 添加
     * @param checkitem
     * @return
     */
    @RequestMapping("/add")
    public Result add(@RequestBody TCheckgroup checkgroup){
    	try {
    		checkGroupService.add(checkgroup);
			return new Result(true, "添加成功");
		} catch (Exception e) {
			// TODO: handle exception
		}
    	return new Result(false, "添加失败");
    	}
    
    /**
     * 正确添加
     * @param checkitem
     * @return
     */
    @RequestMapping("/add1")
    public Result add1(@RequestBody TCheckgroup checkgroup,Integer[] checkitemIds){
    	try {
    		checkGroupService.add1(checkgroup,checkitemIds);
			return new Result(true, "添加成功");
		} catch (Exception e) {
			// TODO: handle exception
		}
    	return new Result(false, "添加失败");
    	}
    
    /**
     * 修改
     * @param checkitem
     * @return
     */
    @RequestMapping("/update")
    public Result update(@RequestBody TCheckgroup checkgroup){
    	try {
    		checkGroupService.update(checkgroup);
			return new Result(true, "修改成功");
		} catch (Exception e) {
			// TODO: handle exception
		}
    	return new Result(false, "修改失败");
    	}
    
    /**
     * 正确修改
     * @param checkitem
     * @return
     */
    @RequestMapping("/update1")
    public Result update1(@RequestBody TCheckgroup checkgroup,Integer[] checkitemIds){
    	try {
    		checkGroupService.update2(checkgroup, checkitemIds);
			return new Result(true, "修改成功");
		} catch (Exception e) {
			// TODO: handle exception
		}
    	return new Result(false, "修改失败");
    	}
    
    /**
     * 查询 +分页 +模糊
     * @param queryPageBean
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
    	return checkGroupService.findPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize(), queryPageBean.getQueryString());
    }
    /**
     * 查询一个对象 （修改回现）
     * @param id
     * @return
     */
    @RequestMapping("/findById")
    public WanNeng findById(Integer id){
    	return checkGroupService.findById(id);
    }
    
    @RequestMapping("/deleteById")
    public Result deleteById(Integer id){
    	try {
    		System.out.println(id);
    		checkGroupService.delete(id);
			return new Result(true, "删除成功");
		} catch (Exception e) {
			// TODO: handle exception
		}
    	return new Result(false, "删除失败");
    }
}
