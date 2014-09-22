package pl.mczapiewski.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the is_basic_service database table.
 * 
 */
@Entity
@Table(name = "is_basic_service")
public class BasicService implements Model {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;

	@Column(name = "end_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;

	@Column(name = "id_in_his")
	private String idInHis;

	@Column(name = "send_to_nfz")
	private Boolean sendToNfz;

	@Column(name = "service_kind_nfz")
	private String serviceKindNfz;

	@Column(name = "service_sub_kind_nfz")
	private String serviceSubKindNfz;

	@Column(name = "start_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;

	// bi-directional many-to-one association to IsMainService
	@ManyToOne
	@JoinColumn(name = "id_main_service")
	private MainService mainService;

	public BasicService() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Boolean getSendToNfz() {
		return this.sendToNfz;
	}

	public void setSendToNfz(Boolean sendToNfz) {
		this.sendToNfz = sendToNfz;
	}

	public String getServiceKindNfz() {
		return this.serviceKindNfz;
	}

	public void setServiceKindNfz(String serviceKindNfz) {
		this.serviceKindNfz = serviceKindNfz;
	}

	public String getServiceSubKindNfz() {
		return this.serviceSubKindNfz;
	}

	public void setServiceSubKindNfz(String serviceSubKindNfz) {
		this.serviceSubKindNfz = serviceSubKindNfz;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public MainService getMainService() {
		return mainService;
	}

	public void setMainService(MainService mainService) {
		this.mainService = mainService;
	}

	@Override
	public String toString() {
		return "IsBasicService [id=" + id + ", endDate=" + endDate + ", idInHis=" + idInHis + ", sendToNfz=" + sendToNfz + ", serviceKindNfz=" + serviceKindNfz
				+ ", serviceSubKindNfz=" + serviceSubKindNfz + ", startDate=" + startDate + "]";
	}
	
	

}