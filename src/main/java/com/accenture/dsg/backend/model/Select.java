package com.accenture.dsg.backend.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="select")
@NamedQuery(name="Select.findAll", query="SELECT s FROM Select s")
public class Select implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String option;
	
	private String value;

	private String phone;
	
	private String email;
	
	public Select() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getoOption() {
		return this.option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

}