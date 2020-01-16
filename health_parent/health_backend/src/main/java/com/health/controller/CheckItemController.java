package com.health.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.health.entity.PageResult;
import com.health.entity.QueryPageBean;
import com.health.entity.Result;
import com.health.pojo.TCheckitem;
import com.health.service.CheckItemService;

@RestController
@RequestMapping("/checkItem")
public class CheckItemController {

    @Reference
    private CheckItemService checkItemService;
    /**
     * 添加
     * @param checkitem
     * @return
     */
    @RequestMapping("/add")
    public Result add(@RequestBody TCheckitem checkitem){
    	try {
			checkItemService.add(checkitem);
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
    public Result update(@RequestBody TCheckitem checkitem){
    	try {
			checkItemService.update(checkitem);
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
    	return checkItemService.findPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize(), queryPageBean.getQueryString());
    }
    /**
     * 查询一个对象 （修改回现）
     * @param id
     * @return
     */
    @RequestMapping("/findById")
    public TCheckitem findById(Integer id){
    	return checkItemService.findById(id);
    }
    
    @RequestMapping("/deleteById")
    public Result deleteById(Integer id){
    	try {
			checkItemService.delete(id);
			return new Result(true, "删除成功");
		} catch (Exception e) {
			// TODO: handle exception
		}
    	return new Result(false, "删除失败");
    }
}
