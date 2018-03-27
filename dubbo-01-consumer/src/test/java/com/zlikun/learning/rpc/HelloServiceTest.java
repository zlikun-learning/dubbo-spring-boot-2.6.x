package com.zlikun.learning.rpc;

import com.alibaba.dubbo.config.annotation.Reference;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * @author zlikun <zlikun-dev@hotmail.com>
 * @date 2018-03-20 11:00
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloServiceTest {

    @Reference
    private HelloService helloService;

    @Test
    public void say() throws InterruptedException {
        log.info(helloService.say("Helen"));
        log.info(helloService.say("Ashe"));
        log.info(helloService.say("Jane"));

        // 每隔一秒调用一次，观察消费者执行情况
        for (int i = 0; i < 15; i++) {
            TimeUnit.SECONDS.sleep(1L);
            log.info(helloService.say(String.format("user_%02d", i)));
        }
    }

}