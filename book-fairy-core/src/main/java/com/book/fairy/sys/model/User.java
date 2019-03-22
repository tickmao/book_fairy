package com.book.fairy.sys.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class User extends BaseEntity<Long> {

	private static final long serialVersionUID = -6525908145032868837L;

	private String username;
	private String password;
	@JsonIgnore
	private String salt;
	private String nickname;
	private String headImgUrl;
	private String phone;
	private String telephone;
	private String email;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date birthday;
	private Integer sex;
	private Integer status;

	/**
	 * 用户类型（1 市级、2 区级、3 班长、4  人员）
	 */
	private Integer userType;
	/**
	 * 所属地区的ID
	 */
	private String businessAreaId;
	/**
	 * 营业厅ID
	 */
	private String businessHallId;
	/**
	 * 人员ID
	 */
	private String employeeId;


	private String adminRole;

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

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public interface Status {
		int DISABLED = 0;
		int VALID = 1;
		int LOCKED = 2;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public String getBusinessAreaId() {
		return businessAreaId;
	}

	public void setBusinessAreaId(String businessAreaId) {
		this.businessAreaId = businessAreaId;
	}

	public String getBusinessHallId() {
		return businessHallId;
	}

	public void setBusinessHallId(String businessHallId) {
		this.businessHallId = businessHallId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public void setAdminRole(String adminRole) {
		this.adminRole = adminRole;
	}

	@Override
	public String toString() {
		return "User{" +
				"username='" + username + '\'' +
				", password='" + password + '\'' +
				", salt='" + salt + '\'' +
				", nickname='" + nickname + '\'' +
				", headImgUrl='" + headImgUrl + '\'' +
				", phone='" + phone + '\'' +
				", telephone='" + telephone + '\'' +
				", email='" + email + '\'' +
				", birthday=" + birthday +
				", sex=" + sex +
				", status=" + status +
				", userType=" + userType +
				", businessAreaId='" + businessAreaId + '\'' +
				", businessHallId='" + businessHallId + '\'' +
				", employeeId='" + employeeId + '\'' +
				", adminRole='" + adminRole + '\'' +
				'}';
	}
}
