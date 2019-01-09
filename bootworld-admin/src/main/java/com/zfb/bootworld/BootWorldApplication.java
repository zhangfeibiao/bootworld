package com.zfb.bootworld;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author  zhangfeibiao
 */
@MapperScan({"com.zfb.bootworld.**.mapper"})
@SpringBootApplication
public class BootWorldApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(BootWorldApplication.class);
		application.setBannerMode(Banner.Mode.CONSOLE);
		application.run(args);

	}

}

