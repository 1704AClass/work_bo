package com.health.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.alibaba.dubbo.config.annotation.Service;
import com.aliyuncs.exceptions.ClientException;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.health.entity.PageResult;
import com.health.entity.QueryPageBean;
import com.health.entity.WanNeng;
import com.health.mapper.TCheckgroupCheckitemMapper;
import com.health.mapper.TCheckgroupMapper;
import com.health.mapper.TCheckitemMapper;
import com.health.mapper.TSetmealCheckgroupMapper;
import com.health.mapper.TSetmealMapper;
import com.health.pojo.TCheckgroup;
import com.health.pojo.TCheckgroupCheckitemExample;
import com.health.pojo.TCheckgroupCheckitemKey;
import com.health.pojo.TCheckgroupExample;
import com.health.pojo.TCheckitem;
import com.health.pojo.TCheckitemExample;
import com.health.pojo.TSetmeal;
import com.health.pojo.TSetmealCheckgroupExample;
import com.health.pojo.TSetmealCheckgroupKey;
import com.health.service.CheckItemService;
import com.health.service.SetMealService;
import com.health.utils.SMSUtils;
import com.health.utils.ValidateCodeUtils;
@Service
public class SetMealServiceImpl implements SetMealService{

	@Autowired
	private TCheckitemMapper checkitemMapper;
	@Autowired
	private TSetmealMapper setmealMapper;
	@Autowired
	private TCheckgroupCheckitemMapper checkgroupCheckitemMapper;
	@Autowired
	private TCheckgroupMapper checkgroupMapper;
	@Autowired
	private TSetmealCheckgroupMapper setmealCheckgroupMapper;
	@Autowired
	private RedisTemplate redisTemplate;

	@Override
	public void add(TSetmeal setmeal, Integer[] ids) {
      
		setmealMapper.insert(setmeal);  //添加套餐管理
		System.out.println(setmeal.getId());
		setSetMealCheckGroupKey(setmeal.getId(), ids); //添加中间表
	}
	/**
	 * 添加检查组和检查项的中间表
	 * @param setMealId
	 * @param ids
	 */
	public void setSetMealCheckGroupKey(Integer setMealId,Integer[] ids){
		//System.out.println("进来");
		for (Integer id : ids) {
		//	System.out.println(id);
			TSetmealCheckgroupKey checkgroupKey = new TSetmealCheckgroupKey();
			checkgroupKey.setSetmealId(setMealId);
			checkgroupKey.setCheckgroupId(id);
		//	System.out.println(checkgroupKey);
			setmealCheckgroupMapper.insert(checkgroupKey);
		}
	}

	@Override
	public void update(TSetmeal setmeal, Integer[] ids) {
  
		setmealMapper.updateByPrimaryKey(setmeal);  //修改
		TSetmealCheckgroupExample example = new TSetmealCheckgroupExample();
		example.createCriteria().andSetmealIdEqualTo(setmeal.getId());
		setmealCheckgroupMapper.deleteByExample(example);  //删除所有id为修改的中间表
		setSetMealCheckGroupKey(setmeal.getId(), ids);//添加中间表
		
	}

	@Override
	public PageResult findPage(Integer page, Integer size, String queryString) {

		PageHelper.startPage(page, size);
		Page<TSetmeal> pages=setmealMapper.findByPage(queryString);
		return new PageResult(pages.getTotal(), pages.getResult());
	}

	@Override
	public void delete(Integer id) {
        setmealMapper.deleteByPrimaryKey(id);// 删除
		TSetmealCheckgroupExample example = new TSetmealCheckgroupExample();
		example.createCriteria().andSetmealIdEqualTo(id);
		setmealCheckgroupMapper.deleteByExample(example);  //删除所有id为修改的中间表
		
	}

	@Override
	public WanNeng findById(Integer id) {
		System.out.println("ss"+id);
		TSetmeal setmeal = setmealMapper.selectByPrimaryKey(id);
		System.out.println(setmeal.getName());
		TSetmealCheckgroupExample example = new TSetmealCheckgroupExample();
		example.createCriteria().andSetmealIdEqualTo(id);
		List list1=new ArrayList();
		List<TSetmealCheckgroupKey> list = setmealCheckgroupMapper.selectByExample(example);
		for (TSetmealCheckgroupKey tSetmealCheckgroupKey : list) {
			list1.add(tSetmealCheckgroupKey.getCheckgroupId());
		}
		return new WanNeng(setmeal, list1);
	}
	@Override
	public void addFileName(String fileName) {

		redisTemplate.boundSetOps("chuan").add(fileName);
	}
	@Override
	public void addchenggou(String fileName) {
		redisTemplate.boundSetOps("addchengguo").add(fileName);

	}
	@Override
	public List<TSetmeal> findAll() {
		return setmealMapper.selectByExample(null);
	}
	@Override
	public WanNeng findDetailById(Integer id) {
		TSetmeal setmeal = setmealMapper.selectByPrimaryKey(id);
		TSetmealCheckgroupExample example = new TSetmealCheckgroupExample();
		example.createCriteria().andSetmealIdEqualTo(id);
		List<WanNeng> list2= new ArrayList<>();
		
		List<TSetmealCheckgroupKey> list = setmealCheckgroupMapper.selectByExample(example);
		
		for (TSetmealCheckgroupKey tSetmealCheckgroupKey : list) {
			//根据中间表检查组的id进行查询检查组
			TCheckgroupExample example2 = new TCheckgroupExample();
			example2.createCriteria().andIdEqualTo(tSetmealCheckgroupKey.getCheckgroupId());
			List<TCheckgroup> selectByExample = checkgroupMapper.selectByExample(example2);
			
			
			
			//然后根据检查组id去检查项和检查组的中间表
			for (TCheckgroup tCheckgroup : selectByExample) {
				WanNeng wanNeng = new WanNeng();
				wanNeng.setData(tCheckgroup);
				TCheckgroupCheckitemExample tCheckgroupCheckitemExample = new TCheckgroupCheckitemExample();
				tCheckgroupCheckitemExample.createCriteria().andCheckgroupIdEqualTo(tCheckgroup.getId());
				List<TCheckgroupCheckitemKey> ceckgroupCheckitemlist = checkgroupCheckitemMapper.selectByExample(tCheckgroupCheckitemExample);
				
				//检查项和检查组的中间表去查询
				for (TCheckgroupCheckitemKey tCheckgroupCheckitemKey : ceckgroupCheckitemlist) {
					TCheckitemExample example3 = new TCheckitemExample();
					example2.createCriteria().andIdEqualTo(tCheckgroupCheckitemKey.getCheckitemId());
					List<TCheckitem> selectByExample2 = checkitemMapper.selectByExample(example3);
					
					wanNeng.setData1(selectByExample2);
				}
				list2.add(wanNeng);
			}
		}
		
		
		
		
		return new WanNeng(setmeal,list2);
	}
	@Override
	public Boolean sendSmsCode(String telephone) {
        Boolean flag=false;
		String code4String = ValidateCodeUtils.generateValidateCode4String(4);
		System.out.println(code4String);
		try {
			//SMSUtils.sendSms(telephone, code4String);
			System.out.println("发送短信中");
			System.out.println("发送成功");
			redisTemplate.boundValueOps(telephone).setIfAbsent(code4String);
			redisTemplate.boundValueOps(telephone).expire(1, TimeUnit.MINUTES);
			flag=true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	@Override
	public String sgetSmsCode(String telephone) {

		String telephone1=(String) redisTemplate.boundValueOps(telephone).get();
		return telephone1;
	}
	
	

}
