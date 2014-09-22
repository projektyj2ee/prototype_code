package pl.mczapiewski.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the is_personal_data database table.
 * 
 */
@Entity
@Table(name = "is_personal_data")
public class PersonalData implements Model {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;

	@Column(name = "date_of_birth")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateOfBirth;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "last_name_second_part")
	private String lastNameSecondPart;

	@Column(name = "second_name")
	private String secondName;

	private String sex;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_patient", referencedColumnName = "id", nullable = false)
	protected Patient patient;

	public PersonalData() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastNameSecondPart() {
		return this.lastNameSecondPart;
	}

	public void setLastNameSecondPart(String lastNameSecondPart) {
		this.lastNameSecondPart = lastNameSecondPart;
	}

	public String getSecondName() {
		return this.secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@Override
	public String toString() {
		return "IsPersonalData [id=" + id + ", dateOfBirth=" + dateOfBirth + ", firstName=" + firstName + ", lastName=" + lastName + ", lastNameSecondPart="
				+ lastNameSecondPart + ", secondName=" + secondName + ", sex=" + sex + "]";
	}

}