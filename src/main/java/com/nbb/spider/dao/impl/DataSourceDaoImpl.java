package com.nbb.spider.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nbb.spider.dao.AbstractDao;
import com.nbb.spider.dao.DataSourceDao;
import com.nbb.spider.entity.full.DataSource;

@Repository
public class DataSourceDaoImpl extends AbstractDao<DataSource> implements
		DataSourceDao {

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<DataSource> find() {
		return this.getSession().createQuery("from DataSource").list();
	}

}
