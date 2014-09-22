package pl.mczapiewski.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;

import pl.mczapiewski.translator.annotation.Dto;
import pl.mczapiewski.translator.annotation.Dto.DtoType;
import pl.mczapiewski.translator.annotation.TransientDto;

/**
 * DTO dla encji IsMainService
 * 
 * @author mczapiewski
 * 
 */
public class MainServiceDto implements ModelDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private Boolean accepted;

	private String characteristics;

	private Date created;

	private Boolean deleted;

	private Date endDate;

	private String idInHis;

	private Boolean lifeSaving;

	private Date modified;

	private Integer needsPower;

	private String range;

	private String serviceKind;

	private String serviceKindHis;

	private Date startDate;

	private String systemId;

	private String systemName;

	private Date transactionDate;

	private Long transactionId;

	private PatientDto patient;

	private Collection<BasicServiceDto> basicServices;

	private JakisObiektDto jakisPrzykladowyObiekt;

	private List<JakisObiektDto> jakasPrzykladowaList;

	private String poleTesotwe;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getAccepted() {
		return accepted;
	}

	public void setAccepted(Boolean accepted) {
		this.accepted = accepted;
	}

	public String getCharacteristics() {
		return characteristics;
	}

	public void setCharacteristics(String characteristics) {
		this.characteristics = characteristics;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getIdInHis() {
		return idInHis;
	}

	public void setIdInHis(String idInHis) {
		this.idInHis = idInHis;
	}

	public Boolean getLifeSaving() {
		return lifeSaving;
	}

	public void setLifeSaving(Boolean lifeSaving) {
		this.lifeSaving = lifeSaving;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public Integer getNeedsPower() {
		return needsPower;
	}

	public void setNeedsPower(Integer needsPower) {
		this.needsPower = needsPower;
	}

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}

	public String getServiceKind() {
		return serviceKind;
	}

	public void setServiceKind(String serviceKind) {
		this.serviceKind = serviceKind;
	}

	public String getServiceKindHis() {
		return serviceKindHis;
	}

	public void setServiceKindHis(String serviceKindHis) {
		this.serviceKindHis = serviceKindHis;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	// ---
	@TransientDto
	public JakisObiektDto getJakisPrzykladowyObiekt() {
		return jakisPrzykladowyObiekt;
	}

	public void setJakisPrzykladowyObiekt(JakisObiektDto jakisPrzykladowyObiekt) {
		this.jakisPrzykladowyObiekt = jakisPrzykladowyObiekt;
	}

	@TransientDto
	public List<JakisObiektDto> getJakasPrzykladowaList() {
		if (this.jakasPrzykladowaList == null)
			return new ArrayList<JakisObiektDto>();
		else
			return this.jakasPrzykladowaList;
	}

	public void setJakasPrzykladowaList(List<JakisObiektDto> jakasPrzykladowaList) {
		this.jakasPrzykladowaList = jakasPrzykladowaList;
	}

	@TransientDto
	public String getPoleTesotwe() {
		return poleTesotwe;
	}

	public void setPoleTesotwe(String poleTesotwe) {
		this.poleTesotwe = poleTesotwe;
	}

	@Dto(type = DtoType.FIELD)
	public PatientDto getPatient() {
		return patient;
	}

	public void setPatient(PatientDto patient) {
		this.patient = patient;
	}

	
	@Dto(type = DtoType.SET)
	public Collection<BasicServiceDto> getBasicServices() {
		if (this.basicServices == null) {
			this.basicServices = new LinkedHashSet<BasicServiceDto>();
		}
		return this.basicServices;
	}

	public void setBasicServices(Collection<BasicServiceDto> basicServices) {
		this.basicServices = basicServices;
	}

	@Override
	public String toString() {
		return "MainServiceDto [id=" + id + ", accepted=" + accepted + ", characteristics=" + characteristics + ", created=" + created + ", deleted=" + deleted
				+ ", endDate=" + endDate + ", idInHis=" + idInHis + ", lifeSaving=" + lifeSaving + ", modified=" + modified + ", needsPower=" + needsPower
				+ ", range=" + range + ", serviceKind=" + serviceKind + ", serviceKindHis=" + serviceKindHis + ", startDate=" + startDate + ", systemId="
				+ systemId + ", systemName=" + systemName + ", transactionDate=" + transactionDate + ", transactionId=" + transactionId + ", patient="
				+ patient + 
				", basicServices=" + basicServices + 
				", jakisPrzykladowyObiekt=" + jakisPrzykladowyObiekt + 
				", jakasPrzykladowaList="+ jakasPrzykladowaList + 
				", poleTesotwe=" + poleTesotwe + "]";
	}

	// --
}
