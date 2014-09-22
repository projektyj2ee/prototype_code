package pl.mczapiewski.dto;

import java.util.Date;

import pl.mczapiewski.translator.annotation.Dto;
import pl.mczapiewski.translator.annotation.Dto.DtoType;

/**
 * DTO dla encji isPersonalData
 * 
 * @author mczapiewski
 * 
 */
public class PersonalDataDto implements ModelDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private Date dateOfBirth;

	private String firstName;

	private String lastName;

	private String lastNameSecondPart;

	private String secondName;

	private String sex;

	private PatientDto patient;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastNameSecondPart() {
		return lastNameSecondPart;
	}

	public void setLastNameSecondPart(String lastNameSecondPart) {
		this.lastNameSecondPart = lastNameSecondPart;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Dto(type = DtoType.TRANSIENT) //lub @Transient
	public PatientDto getPatient() {
		return patient;
	}

	public void setPatient(PatientDto patient) {
		this.patient = patient;
	}

	@Override
	public String toString() {
		return "PersonalDataDto [id=" + id + ", dateOfBirth=" + dateOfBirth + ", firstName=" + firstName + ", lastName=" + lastName + ", lastNameSecondPart="
				+ lastNameSecondPart + ", secondName=" + secondName + ", sex=" + sex + "]";
	}
}
