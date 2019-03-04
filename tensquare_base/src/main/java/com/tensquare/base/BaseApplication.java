package com.tensquare.base;

import com.tensquare.common.utils.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @Author: sirc_hzr
 * @Date: 2019/3/4 15:50
 * @ClassName: BaseApplication
 * @Version: 1.0
 * @Description:springboot启动类
 */

@SpringBootApplication
public class BaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(BaseApplication.class, args);
    }

    /**
     * @Author sirc_hzr
     * @Description  TODO 自动id生成器
     * @Date 15:53 2019/3/4
     * @Param []
     * @return com.tensquare.common.utils.IdWorker
     **/
    @Bean
    public IdWorker idWorker() {
        return new IdWorker(1, 1);
    }
}
