package com.bobenut.learning.graphql.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bobenut.learning.graphql.domain.User;

@Mapper
@Repository
public interface UserMapper  extends BaseMapper<User> {
	public List<User> getUsers();
}
