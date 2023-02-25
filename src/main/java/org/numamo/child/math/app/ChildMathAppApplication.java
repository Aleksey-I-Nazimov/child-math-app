package org.numamo.child.math.app;

import org.numamo.child.math.app.view.frame.ApplicationFrameManager;
import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import static org.springframework.boot.SpringApplication.run;


@SpringBootApplication
public class ChildMathAppApplication {

	public static final String APPLICATION_FRAME_MANAGER_COMPONENT = "ApplicationFrameManagerComponent";

	public static void main(String[] args) {

		ApplicationContext context = new SpringApplicationBuilder(ChildMathAppApplication.class)
				.web(WebApplicationType.NONE)
				.headless(false)
				.bannerMode(Banner.Mode.CONSOLE)
				.run(args);
//		final ApplicationFrameManager manager = (ApplicationFrameManager)context
//				.getBean(APPLICATION_FRAME_MANAGER_COMPONENT);
//		manager.start();
	}

}
