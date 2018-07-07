package com.example.demo.proxyer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

/**
 * 代理类   避免每个定时执行的方法都要事先job ,很麻烦
 * @author hzz
 *
 */
public class ProxyJob implements Job {

    private final Logger logger = LoggerFactory.getLogger(getClass());
   

	@Override
    public void execute(JobExecutionContext ctx) throws JobExecutionException {
        logger.info("Test job start...");
        JobDataMap jobDataMap = ctx.getTrigger().getJobDataMap();
        Class obj = (Class) jobDataMap.get("clazz");
        String m = (String) jobDataMap.get("method");
        ApplicationContext applicationContext=null;  
        try {  
        	//获取JobExecutionContext中的service对象 
			SchedulerContext schCtx = ctx.getScheduler().getContext();
            //获取Spring中的上下文  
			applicationContext = (ApplicationContext)schCtx.get("applicationContextKey");
			Object bean = applicationContext.getBean(obj);
			Method method = obj.getDeclaredMethod(m);
			method.invoke(bean, new Object[] {});
		    logger.info(obj+":"+m+"在执行！");
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace(); 
            logger.info("===获取spring容器中的bean失败或执行错误=====");
        }  
        logger.info("Test job end...");
    }
}
