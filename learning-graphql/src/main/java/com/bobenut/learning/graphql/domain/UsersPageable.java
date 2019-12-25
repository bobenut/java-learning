package com.bobenut.learning.graphql.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Data
public class UsersPageable extends EntityPageable<User> {

}