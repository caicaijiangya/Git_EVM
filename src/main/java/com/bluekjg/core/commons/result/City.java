package com.bluekjg.core.commons.result;

import java.util.List;

public class City {

	private Integer id;
	private Integer pid;
	private String name;
	private String code;
	private List<City> sub;

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<City> getSub() {
		return sub;
	}

	public void setSub(List<City> sub) {
		this.sub = sub;
	}

}
