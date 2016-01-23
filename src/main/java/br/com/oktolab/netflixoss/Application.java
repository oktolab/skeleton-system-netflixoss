package br.com.oktolab.netflixoss;

import io.netty.buffer.ByteBuf;

import java.util.concurrent.TimeUnit;

import netflix.karyon.Karyon;
import netflix.karyon.KaryonBootstrap;
import netflix.karyon.KaryonServer;
import netflix.karyon.archaius.ArchaiusBootstrap;
import br.com.oktolab.netflixoss.hystrix.plugins.CustomEventNotifier;
import br.com.oktolab.netflixoss.hystrix.plugins.CustomHystrixCommandExecution;
import br.com.oktolab.netflixoss.nettyrest.ApplicationBootstrap;

import com.google.inject.Module;
import com.netflix.hystrix.contrib.rxnetty.metricsstream.HystrixMetricsStreamHandler;
import com.netflix.hystrix.contrib.yammermetricspublisher.HystrixYammerMetricsPublisher;
import com.netflix.hystrix.strategy.HystrixPlugins;
import com.yammer.metrics.reporting.GraphiteReporter;

@ArchaiusBootstrap
@KaryonBootstrap(name="skeleton-system-netflixoss")
public class Application {
	
	private static KaryonServer server;
	
	public static void main(String[] args) {
		server = ApplicationBootstrap.run(Application.class, new Module[]{
//			new KaryonWebAdminModule()});
		});
//		configHystrixPlugins();
//		startHystrixDashBoardStream();
//		startGraphiteReporter();
		server.waitTillShutdown();
	}

	private static void startGraphiteReporter() {
		GraphiteReporter.enable(5000l, TimeUnit.MILLISECONDS, "127.0.0.1", 2003);
	}

	private static void configHystrixPlugins() {
		HystrixPlugins.getInstance().registerCommandExecutionHook(CustomHystrixCommandExecution.getInstance());
		HystrixPlugins.getInstance().registerEventNotifier(CustomEventNotifier.getInstance());
		HystrixPlugins.getInstance().registerMetricsPublisher(new HystrixYammerMetricsPublisher());
	}

	private static void startHystrixDashBoardStream() {
		KaryonServer metrixHandlerServer = Karyon
			.forRequestHandler(
					7760,
					new HystrixMetricsStreamHandler<ByteBuf, ByteBuf>(null), new Module[]{});
		metrixHandlerServer.start();
	}

	public static KaryonServer getServer() {
		return server;
	}
	
}
