package com.accenture.dsg.backend.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="contatti")
@NamedQueries({
	@NamedQuery(name="Contact.findAll", query="SELECT c FROM Contact c"),
	@NamedQuery(name="Contact.getByRef", query="SELECT c FROM Contact c WHERE c.branch = :branch AND c.option = :option")
})

public class Contact implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String option;
	
	private String value;

	private String phone;
	
	private String email;
	
	private String type;
	
	private String channel;
	
	private String web;
	
	private String fax;
	
	private String bcc;
	
	private String branch;
	
	public Contact() {
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

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getChannel() {
		return this.channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
	
	public String getWeb() {
		return this.web;
	}

	public void setWeb(String web) {
		this.web = web;
	}
	
	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}
	
	public String getBcc() {
		return this.bcc;
	}

	public void setBcc(String bcc) {
		this.bcc = bcc;
	}
	
	public String getBranch() {
		return this.branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

}