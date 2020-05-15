package com.dustin.springbootblog.config;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;
import org.springframework.stereotype.Component;

/**
 * 	override 用意,使Quartz job class 
 *	註冊至springIOC容器中(beanFactory.autowireBean(obj))
 *
 *	@Autowired service 能成功注入
 *	
 *
 */
//public class MyAdaptableJobFactory extends AdaptableJobFactory {
@Component
public class MyAdaptableJobFactory extends SpringBeanJobFactory {

	//	可以將一個對象添加到SpringIOC容器中,並完成對象注入
	@Autowired
	AutowireCapableBeanFactory beanFactory;
	
	/**
	 * 	該方法需要將實例化的任務對象
	 * 	手動的添加到springIOC容器中並且完成對象的注入
	 */
	@Override
	protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
		Object obj = super.createJobInstance(bundle);
		
		//	將OBJ對象添加到SpringIOC容器中,並完成注入
		beanFactory.autowireBean(obj);
		return obj;
	}
	
}
