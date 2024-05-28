package tn.com.guru.gateway;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.Executor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@EnableDiscoveryClient
@SpringBootApplication
public class GatewayApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(GatewayApplication.class);

	@Value("${server.port}")
	private Integer port;

	@Bean(name = "threadPoolTaskExecutor")
	public Executor threadPoolTaskExecutor() {
		return new ThreadPoolTaskExecutor();
	}

	public static void main(String[] args) throws UnknownHostException {
		SpringApplication app = new SpringApplication(GatewayApplication.class);
		Environment env = app.run(args).getEnvironment();
		String protocol = "http";
		if (env.getProperty("server.ssl.key-store") != null)
			protocol = "https";
		logger.info(
				"\n----------------------------------------------------------\n\t"
						+ "Application '{}' is running! Access URLs:\n\t" + "Local: \t\t{}://localhost:{}\n\t"
						+ "External: \t{}://{}:{}\n\t"
						+ "Profile(s): \t{}\n\t"
						+ (env.getProperty("spring.profiles.active").equals("prod") ? "" : "Swagger Link: \t{}://{}:{}/webjars/swagger-ui/index.html?configUrl=%2Fv3%2Fapi-docs%2Fswagger-config&urls.primaryName=gateway \n")
						+ "----------------------------------------------------------",
				env.getProperty("spring.application.name"), protocol, 
				env.getProperty("server.port"), protocol,
				InetAddress.getLocalHost().getHostAddress(), env.getProperty("server.port"),
				env.getActiveProfiles(), protocol, 
				InetAddress.getLocalHost().getHostAddress(), env.getProperty("server.port"));
	}

}
