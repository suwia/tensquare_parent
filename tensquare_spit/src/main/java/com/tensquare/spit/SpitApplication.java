package com.tensquare.spit;

import com.tensquare.common.utils.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @Author: sirc_hzr
 * @Date: 2019/3/25 15:10
 * @ClassName: SpitApplication
 * @Version: 1.0
 * @Description:
 */
@SpringBootApplication
public class SpitApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpitApplication.class, args);
    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker();
    }
}
