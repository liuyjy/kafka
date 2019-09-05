package com.edu.hart.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.edu.hart.constant.Constant;
import com.edu.hart.entity.OrderInfo;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * kafka生产者控制层
 *
 * @author yongzhen
 * @date 2019/4/10-15:45
 */
@RestController
@RequestMapping("kafka")
public class ProducerController {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final Logger logger = LoggerFactory.getLogger(ProducerController.class);

    @RequestMapping("producer")
    public void kafkaProducer() {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderNum("HART201904111312");
        orderInfo.setOrderSum(588);
        orderInfo.setAddress("朝阳区中海广场");
        orderInfo.setReceive("周休");
        orderInfo.setOrderTime(new Date());
        String sendData = JSONObject.toJSONString(orderInfo);
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(Constant.TOPIC_NAME, sendData);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable throwable) {
                logger.warn("Sending message to Kafka failed: topic {}, content: {}", Constant.TOPIC_NAME, JSON.toJSONString(sendData));
            }

            @Override
            public void onSuccess(SendResult<String, String> producerData) {
                ProducerRecord<String, String> producerRecord = producerData.getProducerRecord();
                logger.info("Sending message to Kafka finished: topic {}, content: {}", Constant.TOPIC_NAME, producerRecord.value());
            }
        });
    }
}