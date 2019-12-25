package com.bobenut.learning.graphql.domain;

import java.util.List;

import lombok.Data;

@Data
public abstract class EntityPageable<T> {
	private String[] ascs;
	private String[] descs;
	private long current;
	private List<T> records;
	private long size;
	private long total;
}
