package dev.edu.javaee.spring.aop.framework;

import dev.edu.javaee.spring.aop.support.AdvisedSupport;

import dev.edu.javaee.spring.aop.support.NameMatchMethodPointcut;
import dev.edu.javaee.spring.aop.support.TargetSource;
import dev.edu.javaee.spring.factory.FactoryBean;


//@author wangxue
//学习自http://wangxinchun.iteye.com/blog/2079585


public class ProxyFactoryBean extends AdvisedSupport implements FactoryBean<Object> {
    //代理接口
	private String proxyInterfaces;
	//缓存所用
	private Object target;
	//代理对象名称
	private String interceptorNames;
	//标志位
	public boolean flag = false;
	
	@Override
	//对象获取的输入
	public Object getObject() throws Exception {
		TargetSource targetSource = new TargetSource();
		targetSource.setTarget(this.getTarget());
		this.setTargetSource(targetSource);
		this.setInterfaces(Class.forName(proxyInterfaces));
		
		this.setMethodInterceptor(this.getMethodInterceptor());
		NameMatchMethodPointcut nameMatchMethodPointcut = new NameMatchMethodPointcut();
		
		this.setMethodMatcher(nameMatchMethodPointcut);
		return this.getProxy();
	}

	//获取对象类型
	public Class<Object> getObjectType() {
		return (Class<Object>) this.getTargetSource().getTarget().getClass();
	}
	public String getProxyInterfaces() {
		return proxyInterfaces;
	}
    
	//设置代理接口对象
	public void setProxyInterfaces(String proxyInterfaces) {
		this.proxyInterfaces = proxyInterfaces;
	}
    //返回对象
	public Object getTarget() {
		return target;
	}
    //获得对象
	public void setTarget(Object target) {
		this.target = target;
	}
    //返回代理对象名称
	public String getInterceptorNames() {
		return interceptorNames;
	}
    //获得代理对象名称
	public void setInterceptorNames(String interceptorNames) {
		this.interceptorNames = interceptorNames;
	}

	

	@Override
	public boolean isSingleton() {
		return false;
	}
	
	public Object getProxy() {
		return new JdkDynamicAopProxy(this).getProxy();
	}

}