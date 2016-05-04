package Entities;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Objects;

public class Doctor implements Entity  {

	Integer id;
	String name; //ФИО
	Date birtday; //День рождения
	Date workday; //День приема на работу
	Integer area; //Номер участка
	Integer salary; //Зарплата
	String specialty; //Специальность
	String sex; //Пол
	String domain_name;


	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
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

	public Date getBirtday() {
		return birtday;
	}

	public void setBirtday(Date birtday) {
		this.birtday = birtday;
	}

	public Date getWorkday() {
		return workday;
	}

	public void setWorkday(Date workday) {
		this.workday = workday;
	}

	public Integer getArea() {
		return area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	@Override
	public Class getClassName() {
		return this.getClass();
	}

	@Override
	public String getStringName() {
		return "Doctor";
	}
	
	public String getDomain_name() {
		return domain_name;
	}
	
	public void setDomain_name(String domain_name) {
		this.domain_name = domain_name;
	}

}

