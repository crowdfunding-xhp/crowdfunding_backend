package com.xhp.crowdfunding_backend.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author yuchu
 * @email 
 * @date 2018-05-03 10:00:35
 */
@Data
public class Order{

	/**
	 * 订单编号
	 */

	private String oid;
	/**
	 * 项目编号
	 */

	private String pid;
	/**
	 * 用户编号
	 */

	private String uid;
	/**
	 * 订单状态
	 */

	private String ostate;
	/**
	 * 资助金额
	 */

	private Integer omoney;

}
