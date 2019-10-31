package com.bobenut.learning.graphql.domain;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName
@Data
public class User  extends Model<User>{
	
	private static final long serialVersionUID = -3234880730692732568L;
	private String id;
	private String username;
	private String password;
	private String nickname;
	
	@Override
	protected Serializable pkVal() {
		return this.id;
	}
}