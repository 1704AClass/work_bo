package com.health.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.health.entity.Result;
import com.health.mapper.MemberDao;
import com.health.mapper.OrderDao;
import com.health.mapper.OrderSettingDao;
import com.health.mapper.TMemberMapper;
import com.health.mapper.TMenuMapper;
import com.health.mapper.TOrderMapper;
import com.health.mapper.TOrdersettingMapper;
import com.health.pojo.TMember;
import com.health.pojo.TMemberExample;
import com.health.pojo.TOrder;
import com.health.pojo.TOrderExample;
import com.health.pojo.TOrdersetting;
import com.health.pojo.TOrdersettingExample;
import com.health.service.OrderService;
import com.health.utils.DateUtils;

@Service
public class OrderServieImpl implements OrderService{

	@Autowired
	private OrderSettingDao orderSettingDao;
	@Autowired
	private TOrdersettingMapper ordersettingMapper;
	@Autowired
	private TMemberMapper memberMapper;
	@Autowired
	private TOrderMapper orderMapper;
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private OrderDao orderDao;
	
	/**
	 * 体检预约
	 */
	@Override
	public Result order(Map map) throws Exception {

		System.out.println("就来了");
		//检查当前日期是否进行预约设置
	    String orderDate=(String) map.get("orderDate");
	    System.out.println("日期");
		Date date = DateUtils.parseString2Date(orderDate);
		/*TOrdersettingExample ordersettingExample = new TOrdersettingExample();
		ordersettingExample.createCriteria().andOrderdateEqualTo(date);
		TOrdersetting ordersetting = ordersettingMapper.selectByExample(ordersettingExample).get(0);*/
		TOrdersetting ordersetting=ordersettingMapper.findByOrderdateId(date);
		
		System.out.println(ordersetting);
		if (ordersetting==null) {
			return new Result(false, "没有当前的预约日期");
		}
		//检查预约日期是否预约已满
		Integer number = ordersetting.getNumber(); //可预约的人数
		Integer reservations = ordersetting.getReservations();//已经预约的人数
		if (reservations >= number) {
			//当前日期的预约人数已经满了
			return new Result(false, "当前预约的人已经满了");
		}
		//根据当前手机号是否是会员
		String telephone=(String) map.get("telephone");
		System.out.println(telephone);
		//TMember member = memberDao.findByTelephone(telephone);
		TMemberExample memberExample = new TMemberExample();
		memberExample.createCriteria().andPhonenumberEqualTo(telephone);
		TMember member = memberMapper.selectByExample(memberExample).get(0);
		System.out.println(member.getName());
		if (member !=null) {
			Integer memberId = member.getId();
			int setmealId = Integer.parseInt((String)map.get("setmealId"));
			TOrder order = new TOrder(memberId, date, null,null, setmealId);
			//List<TOrder> list = orderDao.findByCondition(order);
			List<TOrder> list = orderMapper.findByCondition(order);
			if (list !=null && list.size()>0) {
				//已经完成预约了，不能重复预约啊
				return new Result(false, "不能重复预约啊");
			}
		}
		
		//可以预约，设置预约人数加一
		ordersetting.setReservations(ordersetting.getReservations()+1);;
		//orderSettingDao.editReservationsByOrderDate(ordersetting);
		ordersettingMapper.updateByPrimaryKey(ordersetting);
		
		if (member ==null) {
			//当前的手机号不是会员，需要添加会员列表
			 member = new TMember();
			 member.setName((String)map.get("name"));
			 member.setPhonenumber(telephone);
			 member.setIdcard((String)map.get("idCard"));
			 member.setSex((String)map.get("sex"));
			 member.setRegtime(new Date());
			// memberDao.add(member);
			 memberMapper.insert(member);
		}
		
		//保存预约信息到预约表
		TOrder order = new TOrder(member.getId(), date, (String)map.get("orderType"),"未就诊" , (Integer)map.get("setmealId"));
		//orderDao.add(order);
		orderMapper.insert(order);
		
		return new Result(true, "预约成功",order.getId());
	}

}
