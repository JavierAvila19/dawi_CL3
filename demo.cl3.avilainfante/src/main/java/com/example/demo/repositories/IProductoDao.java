package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Producto;

@Repository
public interface IProductoDao extends CrudRepository<Producto,Long> {

	public List<Producto> findAll();
}
