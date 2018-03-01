package com.sobey;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sobey.dao.DepartmentDao;
import com.sobey.dao.JobDao;
import com.sobey.dao.SiteDao;
import com.sobey.model.DepartmentBean;
import com.sobey.model.JobBean;
import com.sobey.model.SiteBean;
import com.sobey.model.UserBean;
import com.sobey.dao.UserDao;
import com.sobey.util.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KakaAppMserviceApplicationTests {

	@Autowired
	private UserDao templateDao;

	@Autowired
	private SiteDao siteDao;

	@Autowired
	private DepartmentDao departmentDao;

	@Autowired
	private JobDao jobDao;

	@Autowired
	private RedisUtil redisUtil;

	@Test
	public void contextLoads() throws Exception {
		UserBean userBean = new UserBean();
//		userBean.setUserid("2a2c94a9f30b4ce698fe886477af94e7");
//		userBean.setUserid(UUID.randomUUID().toString().replace("-" , ""));
//		userBean.setAppversion("1.0.0");
//		userBean.setDepartmentname("可视化");
//		userBean.setDeviceId("uudb20817jliyuyt");
//		userBean.setDevicemodel("Apple 7");
//		userBean.setDevicetype("ios");
//		userBean.setDisksize(10240L);
//		userBean.setEmil("616823670@qq.com");
//		userBean.setHeadimg("http://172.16.145.51:8088/MHQ/reception/static/imgs/clue/2.jpg");
//		userBean.setJobname("后台研发");
//		userBean.setLastlogintime("2018-02-28 15:40:43");
//		userBean.setLoginstatus(1);
//		userBean.setLoginnumber(1L);
//		userBean.setPhone("13088094976");
//		userBean.setPwd("616823670");
//		userBean.setRegisttime("2018-02-28 15:40:43");
//		userBean.setStatus(0);
//		userBean.setToken("token_" + UUID.randomUUID().toString().replace("-" , ""));
//		userBean.setUsername("谭帅");
//		templateDao.updateById(userBean);

//		DepartmentBean bean = new DepartmentBean();
//		bean.setDepartmentid(UUID.randomUUID().toString().replace("-" , ""));
//		bean.setDepartmentname("可视化与移动产品研发部");
//		bean.setDetails("这个是部门描述");
//		bean.setSiteid("02716c8de1fc4c3381c8ef7ae1fd37bb");
//		departmentDao.save(bean);

//		JobBean jobBean = new JobBean();
//		jobBean.setJobid(UUID.randomUUID().toString().replace("-" , ""));
//		jobBean.setJobname("后端研发");
//		jobBean.setDetails("员工描述");
//		jobBean.setSiteid("02716c8de1fc4c3381c8ef7ae1fd37bb");
//		jobDao.save(jobBean);

//		SiteBean siteBean = new SiteBean();
//		siteBean.setSiteid(UUID.randomUUID().toString().replace("-" , ""));
//		siteBean.setCreatetime("2018-02-28 16:12:25");
//		siteBean.setDescribe("可视化与移动产品研发部，主要专注大数据挖掘，及移动产品的设计研发");
//		siteBean.setSitecode("SCTV");
//		siteBean.setSitename("四川电视台");
//		siteBean.setStatus(0);
//		siteDao.save(siteBean);
//		templateDao.updateBySQL("update t_user set age = '10' where uuid = '5'");
//		templateDao.updateBySQLRequire("age = '15' where uuid = '5'" , UserBean.class);
//		templateDao.updateById(userBean);
//		templateDao.save(userBean);
//		templateDao.delete(userBean);
//		templateDao.deleteById("" , UserBean.class);
//		UserBean bean = templateDao.findById("3d555a78fc284deba234d08e6570e0e5" , UserBean.class);
//		UserBean model = templateDao.findBySQLToBean("uuid = 2" , UserBean.class);
//		List list = templateDao.findBySQLToList("username = '谭帅'" , UserBean.class );
//		Object obj = templateDao.findBySQLToBean("select * from t_user where username = '谭帅'");
//		System.out.println(JSONArray.toJSON(bean));
//		UserBean userbean = templateDao.findBySQLToBean("select u.* , s.sitename as sitename , s.sitecode as sitecode , j.jobname as jobname , d.departmentname as departmentname from t_user u , t_site s , t_user_site us , t_job j , t_department d where us.userid = u.userid and us.siteid = s.siteid and j.jobid = us.jobid and us.departmentid = d.departmentid");
//		userbean.setToken(UUID.randomUUID().toString().replace("-" , ""));
//		redisUtil.add(userbean.getUserid() , userbean);

//		UserBean userbean = (UserBean) redisUtil.get("2a2c94a9f30b4ce698fe886477af94e7");

//		UserBean userbean = templateDao.findBySQLToBean("select * from t_user where userid = '2a2c94a9f30b4ce698fe886477af94e7' ");

		System.out.println(JSONObject.toJSON(redisUtil.get("2a2c94a9f30b4ce698fe886477af94e7")));
	}

}
