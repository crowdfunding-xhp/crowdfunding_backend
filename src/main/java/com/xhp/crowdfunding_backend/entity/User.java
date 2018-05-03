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
public class User{

	/**
	 * 用户编号
	 */

	private String uid;
	/**
	 * 用户昵称
	 */

	private String unickname;
	/**
	 * 用户密码
	 */

	private String upassword;
	/**
	 * 用户名称
	 */

	private String uname;
	/**
	 * 用户性别
	 */

	private String usex;
	/**
	 * 用户身份证
	 */

	private String uidnumber;
	/**
	 * 用户电话
	 */

	private String uphone;
	/**
	 * 用户头像
	 */

	private String uimage;
	/**
	 * 用户邮箱
	 */

	private String uemail;

}
