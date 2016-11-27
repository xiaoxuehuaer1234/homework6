package dev.edu.javaee.spring.aop.framework;

import dev.edu.javaee.spring.aop.support.AdvisedSupport;

import dev.edu.javaee.spring.aop.support.NameMatchMethodPointcut;
import dev.edu.javaee.spring.aop.support.TargetSource;
import dev.edu.javaee.spring.factory.FactoryBean;


//@author wangxue
//ѧϰ��http://wangxinchun.iteye.com/blog/2079585


public class ProxyFactoryBean extends AdvisedSupport implements FactoryBean<Object> {
    //����ӿ�
	private String proxyInterfaces;
	//��������
	private Object target;
	//�����������
	private String interceptorNames;
	//��־λ
	public boolean flag = false;
	
	@Override
	//�����ȡ������
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

	//��ȡ��������
	public Class<Object> getObjectType() {
		return (Class<Object>) this.getTargetSource().getTarget().getClass();
	}
	public String getProxyInterfaces() {
		return proxyInterfaces;
	}
    
	//���ô���ӿڶ���
	public void setProxyInterfaces(String proxyInterfaces) {
		this.proxyInterfaces = proxyInterfaces;
	}
    //���ض���
	public Object getTarget() {
		return target;
	}
    //��ö���
	public void setTarget(Object target) {
		this.target = target;
	}
    //���ش����������
	public String getInterceptorNames() {
		return interceptorNames;
	}
    //��ô����������
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