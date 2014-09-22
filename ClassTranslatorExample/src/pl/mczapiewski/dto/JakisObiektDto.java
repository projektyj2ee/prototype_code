package pl.mczapiewski.dto;

/**
 * Jakie przygladowy-testowy obiekt DTO
 * 
 * @author mczapiewski
 * 
 */
public class JakisObiektDto implements ModelDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "JakisObiektDto [id=" + id + ", name=" + name + "]";
	}

}
