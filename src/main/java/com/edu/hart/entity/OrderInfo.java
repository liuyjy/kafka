package com.edu.hart.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户基本信息
 * <p>
 *
 * @author yongzhen
 * @date 2019/4/11-11:54
 */
@Data
public class OrderInfo  implements Serializable{

    private static final long serialVersionUID = 4788755594516737716L;

    private String orderNum;

    private long orderSum;

    private String address;

    private String receive;

    private Date orderTime;
}
