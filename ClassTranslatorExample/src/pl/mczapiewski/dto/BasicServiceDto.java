package pl.mczapiewski.dto;

import java.util.Date;

import pl.mczapiewski.translator.annotation.Dto;
import pl.mczapiewski.translator.annotation.Dto.DtoType;

/**
 * DTO dla encji IsBasicService
 * @author mczapiewski
 *
 */
public class BasicServiceDto implements ModelDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private Date endDate;

	private String idInHis;

	private Boolean sendToNfz;

	private String serviceKindNfz;

	private String serviceSubKindNfz;

	private Date startDate;

	private MainServiceDto mainService;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Boolean getSendToNfz() {
		return sendToNfz;
	}

	public void setSendToNfz(Boolean sendToNfz) {
		this.sendToNfz = sendToNfz;
	}

	public String getServiceKindNfz() {
		return serviceKindNfz;
	}

	public void setServiceKindNfz(String serviceKindNfz) {
		this.serviceKindNfz = serviceKindNfz;
	}

	public String getServiceSubKindNfz() {
		return serviceSubKindNfz;
	}

	public void setServiceSubKindNfz(String serviceSubKindNfz) {
		this.serviceSubKindNfz = serviceSubKindNfz;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
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
		return "BasicServiceDto [id=" + id + ", endDate=" + endDate + ", idInHis=" + idInHis + ", sendToNfz=" + sendToNfz + ", serviceKindNfz="
				+ serviceKindNfz + ", serviceSubKindNfz=" + serviceSubKindNfz + ", startDate=" + startDate + "]";
	}
}
