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
public class Projectclassification{

	/**
	 * 分类编号
	 */

	private String pcid;
	/**
	 * 分类名称
	 */

	private String pcname;
	/**
	 * 分类数量
	 */

	private Integer pcnumber;
	/**
	 * 分类图片
	 */

	private String pcimage;

}
