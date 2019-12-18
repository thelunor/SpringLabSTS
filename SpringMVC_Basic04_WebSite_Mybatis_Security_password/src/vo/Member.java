package vo;

import java.util.Date;

public class Member {
	private String userid;
	private String pwd;
	private String name;
	private String gender;
	private String birth;
	private String isLunar;
	private String cPhone;
	private String email;
	private String habit;
	private Date   regDate;
	
	public Member() {}
	
		
	public Member(String userid, String pwd, String name, String gender, String birth, String isLunar, String cPhone,
			String email, String habit, Date regDate) {
		
		this.userid = userid;
		this.pwd = pwd;
		this.name = name;
		this.gender = gender;
		this.birth = birth;
		this.isLunar = isLunar;
		this.cPhone = cPhone;
		this.email = email;
		this.habit = habit;
		this.regDate = regDate;
	}




	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getcPhone() {
		return cPhone;
	}
	public void setcPhone(String cPhone) {
		this.cPhone = cPhone;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getIsLunar() {
		return isLunar;
	}
	public void setIsLunar(String isLunar) {
		this.isLunar = isLunar;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHabit() {
		return habit;
	}
	public void setHabit(String habit) {
		this.habit = habit;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}


	@Override
	public String toString() {
		return "Member [userid=" + userid + ", pwd=" + pwd + ", name=" + name + ", gender=" + gender + ", birth="
				+ birth + ", isLunar=" + isLunar + ", cPhone=" + cPhone + ", email=" + email + ", habit=" + habit
				+ ", regDate=" + regDate + "]";
	}
	
	
}
