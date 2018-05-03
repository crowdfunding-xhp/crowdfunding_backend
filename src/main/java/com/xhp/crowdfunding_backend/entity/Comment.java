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
public class Comment{

	/**
	 * 评论编号
	 */

	private String cid;
	/**
	 * 评论内容
	 */

	private String ccontext;
	/**
	 * 评论回复编号
	 */

	private String creplyid;
	/**
	 * 用户编号
	 */

	private String uid;
	/**
	 * 项目编号
	 */

	private String pid;

}
