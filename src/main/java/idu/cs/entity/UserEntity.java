package idu.cs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import idu.cs.domain.User;

@Entity
@Table(name = "user_table")
public class UserEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id; 
	// database에서 sequence number, primary key 역할
	@Column(nullable=false, length=20, unique=true)
	private String userId;
	private String userPw;
	private String name;
	private String company;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public User buildDomain() {
		User user = new User();
		user.setId(id);
		user.setUserId(userId);
		user.setUserPw(userPw);
		user.setName(name);
		user.setCompany(company);
		return user;
	}
	
	public void buildEntity(User user) {
		id=user.getId();
		userId = user.getUserId();
		userPw = user.getUserPw();
		name = user.getName();
		company = user.getCompany();
	}
	
	
	
}