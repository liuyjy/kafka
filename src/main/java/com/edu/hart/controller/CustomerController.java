package com.edu.hart.controller;

import com.alibaba.fastjson.JSONObject;
import com.edu.hart.constant.Constant;
import com.edu.hart.entity.OrderInfo;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * kafka消费者控制层
 *
 * @author yongzhen
 * @date 2019/4/10-15:47
 */
@RestController
public class CustomerController {

    @KafkaListener(topics = Constant.TOPIC_NAME)
    public void kafkaCustomer(String receiveData) {
        OrderInfo orderInfo = JSONObject.parseObject(receiveData, OrderInfo.class);
        System.err.println("我接收到消息:" + orderInfo.getAddress());
    }
}










