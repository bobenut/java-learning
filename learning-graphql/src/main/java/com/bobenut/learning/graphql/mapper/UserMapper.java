package com.bobenut.learning.graphql.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bobenut.learning.graphql.domain.User;

@Mapper
@Repository
public interface UserMapper  extends BaseMapper<User> {
	public IPage<User> getUsers(Page<User> pag);
}
