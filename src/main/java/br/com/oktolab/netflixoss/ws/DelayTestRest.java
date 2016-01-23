package br.com.oktolab.netflixoss.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommand.Setter;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

@Path("/example/{tempo}/delay")
public class DelayTestRest {

	@PathParam("tempo")
	private String tempo;

	@GET
	public Boolean hello() throws InterruptedException {
		long tempoooo = Long.parseLong(tempo);
		Thread.sleep(tempoooo);
		return true;
	}
	
	@GET
	@Path("/tes")
	public String messageResource() {
//		return MessageResources.getString("teste.mensagem");
		return "OK";
	}
	
	@GET
	@Path("/command")
	public Boolean helloCommand() throws InterruptedException {
		final long tempoooo = Long.parseLong(tempo);
		return (new HystrixCommand<Boolean>(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("testPool")).andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                .withExecutionTimeoutInMilliseconds(240000))) {

			@Override
			protected Boolean run() throws Exception {
				Thread.sleep(tempoooo);
				return true;
			}
			
			@Override
			protected Boolean getFallback() {
				return false;
			}
		}).execute();
	}
	
	public static void main(String[] args) throws InterruptedException {
		DelayTestRest delayTestRest = new DelayTestRest();
		delayTestRest.tempo = "5000";
		delayTestRest.hello();
		System.out.println("OK");
	}
	
	
}
