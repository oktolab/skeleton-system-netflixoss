package br.com.oktolab.netflixoss.hystrix.plugins;

import com.netflix.hystrix.HystrixInvokable;
import com.netflix.hystrix.strategy.executionhook.HystrixCommandExecutionHook;

public class CustomHystrixCommandExecution extends HystrixCommandExecutionHook {

    private static CustomHystrixCommandExecution INSTANCE = new CustomHystrixCommandExecution();

    private CustomHystrixCommandExecution() {

    }

    public static HystrixCommandExecutionHook getInstance() {
        return INSTANCE;
    }

    @Override
    public <T> Exception onExecutionError(
    		HystrixInvokable<T> commandInstance, Exception e) {
    	System.out.println("ERRO!! ENVIAR E-MAIL");
    	return super.onExecutionError(commandInstance, e);
    }
    
}
