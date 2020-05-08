package com.frame;

import java.util.ArrayList;

public interface Dao<V> {
	public void insert(V v) throws Exception;
	public void delete(V v) throws Exception;
	public void update(V v) throws Exception;
	public V select(V v) throws Exception;
	public ArrayList<V> selectAll(V v) throws Exception;
}
