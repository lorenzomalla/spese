package com.java.dao;

import java.util.List;

import com.java.model.Spesa;

public interface SpesaDao {

	void persistSpesa(Spesa s);
	List<Spesa> getAllSpesa();
}
