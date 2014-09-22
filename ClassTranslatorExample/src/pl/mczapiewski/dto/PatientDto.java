package pl.mczapiewski.dto;

import pl.mczapiewski.translator.annotation.Dto;
import pl.mczapiewski.translator.annotation.Dto.DtoType;

/**
 * DTO dla encji IsPatient
 * 
 * @author mczapiewski
 * 
 */
public class PatientDto implements ModelDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String idInHis;

	private Integer nfzDepartment;

	private String patientKind;

	private String peselEu;

	private String pin;

	private String pinKind;

	private PersonalDataDto personalData;

	private MainServiceDto mainService;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdInHis() {
		return idInHis;
	}

	public void setIdInHis(String idInHis) {
		this.idInHis = idInHis;
	}

	public Integer getNfzDepartment() {
		return nfzDepartment;
	}

	public void setNfzDepartment(Integer nfzDepartment) {
		this.nfzDepartment = nfzDepartment;
	}

	public String getPatientKind() {
		return patientKind;
	}

	public void setPatientKind(String patientKind) {
		this.patientKind = patientKind;
	}

	public String getPeselEu() {
		return peselEu;
	}

	public void setPeselEu(String peselEu) {
		this.peselEu = peselEu;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getPinKind() {
		return pinKind;
	}

	public void setPinKind(String pinKind) {
		this.pinKind = pinKind;
	}

	@Dto(type = DtoType.FIELD)
	public PersonalDataDto getPersonalData() {
		return personalData;
	}

	public void setPersonalData(PersonalDataDto personalData) {
		this.personalData = personalData;
	}

	@Dto(type = DtoType.TRANSIENT) //lub @Transient
	public MainServiceDto getMainService() {
		return mainService;
	}

	public void setMainService(MainServiceDto mainService) {
		this.mainService = mainService;
	}

	@Override
	public String toString() {
		return "PatientDto [id=" + id + ", idInHis=" + idInHis + ", nfzDepartment=" + nfzDepartment + ", patientKind=" + patientKind + ", peselEu=" + peselEu
				+ ", pin=" + pin + ", pinKind=" + pinKind + ", personalData=" + personalData + "]";
	}

}
