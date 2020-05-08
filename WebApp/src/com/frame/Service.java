package com.frame;

import java.util.ArrayList;

import org.springframework.transaction.annotation.Transactional;

public interface Service<V> {
	@Transactional
	public void insert(V v) throws Exception;
	@Transactional
	public void delete(V v) throws Exception;
	@Transactional
	public void update(V v) throws Exception;
	
	public V select(V v) throws Exception;
	public ArrayList<V> selectAll(V v) throws Exception;
}
