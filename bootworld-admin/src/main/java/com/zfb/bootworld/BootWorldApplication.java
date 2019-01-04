package com.zfb.bootworld;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author  zhangfeibiao
 */
@SpringBootApplication
public class BootWorldApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(BootWorldApplication.class);
		application.setBannerMode(Banner.Mode.CONSOLE);
		application.run(args);

	}

}

