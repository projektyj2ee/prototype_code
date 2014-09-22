package pl.mczapiewski.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import pl.mczapiewski.translator.annotation.Dto;
import pl.mczapiewski.translator.annotation.Dto.DtoType;

/**
 * The persistent class for the is_main_service database table.
 * 
 */
@Entity
@Table(name = "is_main_service")
public class MainService implements Model {
	@Id
	@SequenceGenerator(name = "IS_MAIN_SERVICE_ID_GENERATOR")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IS_MAIN_SERVICE_ID_GENERATOR")
	private Long id;

	private Boolean accepted;

	private String characteristics;

	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	private Boolean deleted;

	@Column(name = "end_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;

	@Column(name = "id_in_his")
	private String idInHis;

	@Column(name = "life_saving")
	private Boolean lifeSaving;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modified;

	@Column(name = "needs_power")
	private Integer needsPower;

	private String range;

	@Column(name = "service_kind")
	private String serviceKind;

	@Column(name = "service_kind_his")
	private String serviceKindHis;

	@Column(name = "start_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;

	@Column(name = "system_id")
	private String systemId;

	@Column(name = "system_name")
	private String systemName;

	@Column(name = "transaction_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date transactionDate;

	@Column(name = "transaction_id")
	private Long transactionId;

	@OneToOne(mappedBy = "mainService", cascade = { CascadeType.ALL }, orphanRemoval = true)
	protected Patient patient;

	@OneToMany(mappedBy = "mainService", cascade = { CascadeType.ALL }, orphanRemoval = true)
	protected Set<BasicService> basicServices;

	public MainService() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getAccepted() {
		return this.accepted;
	}

	public void setAccepted(Boolean accepted) {
		this.accepted = accepted;
	}

	public String getCharacteristics() {
		return this.characteristics;
	}

	public void setCharacteristics(String characteristics) {
		this.characteristics = characteristics;
	}

	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Boolean getDeleted() {
		return this.deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getIdInHis() {
		return this.idInHis;
	}

	public void setIdInHis(String idInHis) {
		this.idInHis = idInHis;
	}

	public Boolean getLifeSaving() {
		return this.lifeSaving;
	}

	public void setLifeSaving(Boolean lifeSaving) {
		this.lifeSaving = lifeSaving;
	}

	public Date getModified() {
		return this.modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public Integer getNeedsPower() {
		return this.needsPower;
	}

	public void setNeedsPower(Integer needsPower) {
		this.needsPower = needsPower;
	}

	public String getRange() {
		return this.range;
	}

	public void setRange(String range) {
		this.range = range;
	}

	public String getServiceKind() {
		return this.serviceKind;
	}

	public void setServiceKind(String serviceKind) {
		this.serviceKind = serviceKind;
	}

	public String getServiceKindHis() {
		return this.serviceKindHis;
	}

	public void setServiceKindHis(String serviceKindHis) {
		this.serviceKindHis = serviceKindHis;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getSystemId() {
		return this.systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	public String getSystemName() {
		return this.systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public Date getTransactionDate() {
		return this.transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Long getTransactionId() {
		return this.transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}
	
	@Dto(type = DtoType.FIELD)
	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@Dto(type = DtoType.SET)
	public Set<BasicService> getBasicServices() {
		return basicServices;
	}

	public void setBasicServices(Set<BasicService> basicServices) {
		this.basicServices = basicServices;
	}

	@Override
	public String toString() {
		return "IsMainService [id=" + id + ", accepted=" + accepted + ", characteristics=" + characteristics + ", created=" + created + ", deleted=" + deleted
				+ ", endDate=" + endDate + ", idInHis=" + idInHis + ", lifeSaving=" + lifeSaving + ", modified=" + modified + ", needsPower=" + needsPower
				+ ", range=" + range + ", serviceKind=" + serviceKind + ", serviceKindHis=" + serviceKindHis + ", startDate=" + startDate + ", systemId="
				+ systemId + ", systemName=" + systemName + ", transactionDate=" + transactionDate + ", transactionId=" + transactionId + ", patient="
				+ patient + ", basicServices=" + basicServices + "]";
	}

}