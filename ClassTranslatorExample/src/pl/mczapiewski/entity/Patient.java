package pl.mczapiewski.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * The persistent class for the is_patient database table.
 * 
 */
@Entity
@Table(name = "is_patient")
public class Patient implements Model {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;

	@Column(name = "id_in_his")
	private String idInHis;

	@Column(name = "nfz_department")
	private Integer nfzDepartment;

	@Column(name = "patient_kind")
	private String patientKind;

	@Column(name = "pesel_eu")
	private String peselEu;

	private String pin;

	@Column(name = "pin_kind")
	private String pinKind;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_main_service", referencedColumnName = "id", nullable = false)
	protected MainService mainService;

	@OneToOne(mappedBy = "patient", cascade = { CascadeType.ALL }, orphanRemoval = true)
	protected PersonalData personalData;

	public Patient() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdInHis() {
		return this.idInHis;
	}

	public void setIdInHis(String idInHis) {
		this.idInHis = idInHis;
	}

	public Integer getNfzDepartment() {
		return this.nfzDepartment;
	}

	public void setNfzDepartment(Integer nfzDepartment) {
		this.nfzDepartment = nfzDepartment;
	}

	public String getPatientKind() {
		return this.patientKind;
	}

	public void setPatientKind(String patientKind) {
		this.patientKind = patientKind;
	}

	public String getPeselEu() {
		return this.peselEu;
	}

	public void setPeselEu(String peselEu) {
		this.peselEu = peselEu;
	}

	public String getPin() {
		return this.pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getPinKind() {
		return this.pinKind;
	}

	public void setPinKind(String pinKind) {
		this.pinKind = pinKind;
	}

	public MainService getMainService() {
		return mainService;
	}

	public void setMainService(MainService mainService) {
		this.mainService = mainService;
	}

	public PersonalData getPersonalData() {
		return personalData;
	}

	public void setPersonalData(PersonalData personalData) {
		this.personalData = personalData;
	}

	@Override
	public String toString() {
		return "IsPatient [id=" + id + ", idInHis=" + idInHis + ", nfzDepartment=" + nfzDepartment + ", patientKind=" + patientKind + ", peselEu=" + peselEu
				+ ", pin=" + pin + ", pinKind=" + pinKind + ", personalData=" + personalData + "]";
	}

}