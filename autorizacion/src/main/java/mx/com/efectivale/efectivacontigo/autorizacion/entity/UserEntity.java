package mx.com.efectivale.efectivacontigo.autorizacion.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/***
 * @author Carlos Ruiz
 *
 */
@Entity
@Table(name = "wsuser")
@Getter
@Setter
@ToString
public class UserEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private Integer userId;
	private String username;
	private String password;

	/**
	 * 
	 */
	public UserEntity() {
		super();
	}

	/**
	 * @param userId
	 * @param username
	 * @param password
	 */
	public UserEntity(Integer userId, String username, String password) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
