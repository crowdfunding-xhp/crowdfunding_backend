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
public class Project{

	/**
	 * 项目编号
	 */

	private String pid;
	/**
	 * 筹款人姓名
	 */

	private String paccountname;
	/**
	 * 筹款人身份证
	 */

	private String pidentify;
	/**
	 * 筹款人联系方式
	 */

	private String paccounttel;
	/**
	 * 项目地址
	 */

	private String ploc;
	/**
	 * 项目图片
	 */

	private String pimage;
	/**
	 * 项目名称
	 */

	private String pname;
	/**
	 * 项目内容
	 */

	private String pcontent;
	/**
	 * 项目日期
	 */

	private String pdate;
	/**
	 * 目标资金
	 */

	private Integer ptargetmoney;
	/**
	 * 筹集天数
	 */

	private Integer praisedays;
	/**
	 * 当前资金
	 */

	private Integer ppresentmoney;
	/**
	 * 标签
	 */

	private String ptags;
	/**
	 * 项目描述标题
	 */

	private String pdescriptitle;
	/**
	 * 项目详细描述
	 */

	private String pdescripcont;
	/**
	 * 支付方式
	 */

	private String prepaytype;
	/**
	 * 
	 */

	private String prepaytitle;
	/**
	 * 
	 */

	private String prepaycont;
	/**
	 * 类别编号
	 */

	private Integer pcid;
	/**
	 * 用户编号
	 */

	private Integer uid;

}
