package com.health.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.dubbo.config.annotation.Reference;
import com.health.entity.PageResult;
import com.health.entity.QueryPageBean;
import com.health.entity.Result;
import com.health.entity.WanNeng;
import com.health.pojo.TCheckgroup;
import com.health.pojo.TCheckitem;
import com.health.pojo.TSetmeal;
import com.health.service.CheckGroupService;
import com.health.service.CheckItemService;
import com.health.service.SetMealService;
import com.health.utils.QiniuUtils;

@RestController
@RequestMapping("/setmeal")//检查组管理
public class SetMealController {

    @Reference
    private SetMealService setMealService;
    
    @RequestMapping("/upload")
    public Result upload(@RequestParam("imgFile")MultipartFile imgFile){
    	try {
			//获取原始用户名
    		String originalFilename = imgFile.getOriginalFilename();
    		int lastIndexOf = originalFilename.lastIndexOf(".");
    		//获取文件后缀
    		String substring = originalFilename.substring(lastIndexOf-1);
    		//使用随机上次文件名 使用uuid
    		String fileName=UUID.randomUUID().toString()+substring;
    		QiniuUtils.upload2Qiniu(imgFile.getBytes(), fileName);
    		//图片上传成功
    		setMealService.addFileName(fileName);
    		return new Result(true, fileName);
		} catch (Exception e) {
			// TODO: handle exception
		}
    	return new Result(false, "上传失败");
    }
    
    /**
     * 正确添加
     * @param setmeal
     * @return
     */
    @RequestMapping("/add")
    public Result add(@RequestBody TSetmeal setmeal,Integer[] checkgroupIds){
    	try {
    		setMealService.add(setmeal, checkgroupIds);
    		setMealService.addchenggou(setmeal.getImg());
			return new Result(true, "添加成功");
		} catch (Exception e) {
			// TODO: handle exception
		}
    	return new Result(false, "添加失败");
    	}
    
    /**
     * 修改
     * @param setmeal
     * @return
     */
    @RequestMapping("/update")
    public Result update(@RequestBody TSetmeal setmeal,Integer[] checkgroupIds){
    	try {
    		setMealService.update(setmeal, checkgroupIds);
			return new Result(true, "修改成功");
		} catch (Exception e) {
			// TODO: handle exception
		}
    	return new Result(false, "修改失败");
    	}
    
    /**
     * 分页 + 查询 +精确
     * @param pageBean
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean pageBean){
       return setMealService.findPage(pageBean.getCurrentPage(), pageBean.getPageSize(), pageBean.getQueryString());
    }
    
    /**
     * 
     * @param 删除
     * @return
     */
    @RequestMapping("/deleteById")
    public Result deleteById(Integer id){
    	try {
    		setMealService.delete(id);
			return new Result(true, "删除成功");
		} catch (Exception e) {
			// TODO: handle exception
		}
    	return new Result(false, "删除失败");
    	}
}
