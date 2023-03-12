package org.numamo.child.math.app;

import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;


@SpringBootApplication
public class ChildMathAppApplication {

	public static final String APPLICATION_FRAME_MANAGER_COMPONENT = "ApplicationFrameManagerComponent";

	public static void main(String[] args) {

		new SpringApplicationBuilder(ChildMathAppApplication.class)
				.web(WebApplicationType.NONE)
				.headless(false)
				.bannerMode(Banner.Mode.CONSOLE)
				.run(args);
	}

}
