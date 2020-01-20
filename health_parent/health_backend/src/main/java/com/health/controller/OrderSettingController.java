package com.health.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.dubbo.config.annotation.Reference;
import com.health.entity.Result;
import com.health.pojo.TOrdersetting;
import com.health.service.OrderSettingService;
import com.health.utils.POIUtils;

@RestController
@RequestMapping("/ordersetting")
public class OrderSettingController {

	@Reference
	private OrderSettingService orderSettingService;
	
	/**
	 *  Excel文件上传，并解析文件内容保存到数据库
	 */
	@RequestMapping("/upload")
	public Result upload(@RequestParam("excelFile")MultipartFile excelFile){
		try {
			//读取excel文件数据
			List<String[]> list=POIUtils.readExcel(excelFile);
			if (list !=null && list.size()>0) {
				List<TOrdersetting> ordersettingList=new ArrayList<>();
				for (String[] strings : list) {
					TOrdersetting ordersetting = new TOrdersetting(new Date(strings[0]), Integer.parseInt(strings[1]));
					ordersettingList.add(ordersetting);
				}
			}
			return new Result(true, "上传文件成功");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return new Result(false, "上传文件失败");
	}
	
	/**
	 * 根据日期查询预约设置数据(获取指定日期所在月份的预约设置数据)
	 * @param date
	 * @return
	 */
	@RequestMapping("/getOrderSettingByMonth")
	public Result getOrderSettingByMonth(String date){//参数格式为：2019‐03 
		try {
			List<Map> list = orderSettingService.getOrderSettingByMonth(date);
			return new Result(true, "成功", list);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return new Result(false, "失败");
	}
	
	/**
	 * 根据指定日期修改人数
	 * @param ordersetting
	 * @return
	 */
	@RequestMapping("/editNumberByDate")
	public Result editNumberByDate(@RequestBody TOrdersetting ordersetting){
		try {
			orderSettingService.editNumberByDate(ordersetting);
			return new Result(true, "成功");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return new Result(false, "失败");
	}
	
}
