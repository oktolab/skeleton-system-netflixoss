package br.com.oktolab.netflixoss.hystrix.plugins;

import java.util.List;

import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties.ExecutionIsolationStrategy;
import com.netflix.hystrix.HystrixEventType;
import com.netflix.hystrix.strategy.eventnotifier.HystrixEventNotifier;

public class CustomEventNotifier extends HystrixEventNotifier {

    private static CustomEventNotifier INSTANCE = new CustomEventNotifier();

    private CustomEventNotifier() {

    }

    public static HystrixEventNotifier getInstance() {
        return INSTANCE;
    }
	// autoria de execução
	@Override
	public void markCommandExecution(HystrixCommandKey key,
			ExecutionIsolationStrategy isolationStrategy, int duration,
			List<HystrixEventType> eventsDuringExecution) {
		System.out.println("evento markCommandExecution");
		super.markCommandExecution(key, isolationStrategy, duration,
				eventsDuringExecution);
	}
	
	@Override
	public void markEvent(HystrixEventType eventType, HystrixCommandKey key) {
		System.out.println("evento markEvent");
		super.markEvent(eventType, key);
	}
}