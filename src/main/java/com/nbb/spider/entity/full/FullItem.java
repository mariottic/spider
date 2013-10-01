package com.nbb.spider.entity.full;

import java.util.List;

import com.nbb.spider.entity.Item;
import com.nbb.spider.entity.Task;

public class FullItem implements Item {
	private Long id;
	private String title;
	private Integer rank;
	private Integer index;// 收视指数 日报周报和月报分别不一样，但都共用该字段
	private List<Person> actors;
	private Person director;
	private String category;
	private String keywords;
	private DataSource dataSource;
	private Task task;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	@Override
	public int compareRankto(Item paramItem) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public List<Person> getActors() {
		return actors;
	}

	public void setActors(List<Person> actors) {
		this.actors = actors;
	}

	public Person getDirector() {
		return director;
	}

	public void setDirector(Person director) {
		this.director = director;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	@Override
	public String toString() {
		return "FullItem [id=" + id + ", title=" + title + ", rank=" + rank
				+ ", index=" + index + ", actors=" + actors + ", director="
				+ director + ", category=" + category + ", keywords="
				+ keywords + ", dataSource=" + dataSource + ", task=" + task
				+ "]";
	}

}