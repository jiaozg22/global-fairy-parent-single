package org.global.fairy.web.interceptors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect  
@Component
public class TimeInterceptor {
	// 日志记录
	private static Log logger = LogFactory.getLog(TimeInterceptor.class);
	
	 // 一分钟，即60000ms
    private static final long ONE_MINUTE = 60000;
    // 超时方法的统计耗时切面，类型必须为final String类型的,注解里要使用的变量只能是静态常量类型的
    public static final String POINT = "execution (* org.global.fairy..*(..))";
//    // controller层的统计耗时切面，类型必须为final String类型的,注解里要使用的变量只能是静态常量类型的
//    public static final String CONTROLLER_POINT = "execution (* org.global.fairy.*.controller.*.*(..))";
//    // service层的统计耗时切面，类型必须为final String类型的,注解里要使用的变量只能是静态常量类型的
//    public static final String SERVICE_POINT = "execution (* org.global.fairy.*.service.*.*(..))";
//    // dao层的统计耗时切面，类型必须为final String类型的,注解里要使用的变量只能是静态常量类型的
//    public static final String DAO_POINT = "execution (* org.global.fairy.*.DAO.*.*(..))";
    
	/**
	 * 统计Service中方法调用的时间
	 * 
	 * @param joinPoint
	 * @throws Throwable
	 */
	@Around(POINT)
	public Object logControllerMethodAccess(ProceedingJoinPoint joinPoint)
			throws Throwable {
		long start = System.currentTimeMillis();
		Object object = joinPoint.proceed();
		long end = System.currentTimeMillis();
		long t = end - start;
		if (t >= 1000) {
			String tmp = joinPoint.getSignature().toString();
			logger.info(String.format("class:%s,invoke_time:%s", tmp, t));
		}
		return object;
	}
//	
//	/**
//	 * 统计Service中方法调用的时间
//	 * 
//	 * @param joinPoint
//	 * @throws Throwable
//	 */
//	@Around(SERVICE_POINT)
//	public Object logServiceMethodAccess(ProceedingJoinPoint joinPoint)
//			throws Throwable {
//		long start = System.currentTimeMillis();
//		Object object = joinPoint.proceed();
//		long end = System.currentTimeMillis();
//		long t = end - start;
//		if (t >= 1000) {
//			String tmp = joinPoint.getSignature().toString();
//			logger.info(String.format("class:%s,invoke_time:%s", tmp, t));
//		}
//		return object;
//	}
//	
//	/**
//	 * 统计Dao中方法调用的时间
//	 * 
//	 * @param joinPoint
//	 * @throws Throwable
//	 */
//	@Around(DAO_POINT)
//	public Object logDaoMethodAccess(ProceedingJoinPoint joinPoint)
//			throws Throwable {
//		long start = System.currentTimeMillis();
//		Object object = joinPoint.proceed();
//		long end = System.currentTimeMillis();
//		long t = end - start;
//		if (t >= 1000) {
//			String tmp = joinPoint.getSignature().toString();
//			logger.info(String.format("class:%s,invoke_time:%s", tmp, t));
//		}
//		return object;
//	}

//    /**
//     * 打印方法执行耗时的信息，如果超过了一定的时间，才打印
//     * @param methodName
//     * @param startTime
//     * @param endTime
//     */
//    @Around(POINT)
//    private void printExecTime(String methodName, long startTime, long endTime) {
//        long diffTime = endTime - startTime;
//        if (diffTime > ONE_MINUTE) {
//            logger.warn("-----" + methodName + " 方法执行耗时：" + diffTime + " ms");
//        }
//    }
}
