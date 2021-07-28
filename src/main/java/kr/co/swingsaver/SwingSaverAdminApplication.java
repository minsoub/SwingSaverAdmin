package kr.co.swingsaver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
public class SwingSaverAdminApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
        var app = new SpringApplication(SwingSaverAdminApplication.class);
        app.addListeners(new ApplicationPidFileWriter());
        app.run(args);
	}
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SwingSaverAdminApplication.class);
    }
}
