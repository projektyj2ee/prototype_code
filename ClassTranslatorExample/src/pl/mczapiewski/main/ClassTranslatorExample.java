package pl.mczapiewski.main;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import pl.mczapiewski.dto.BasicServiceDto;
import pl.mczapiewski.dto.JakisObiektDto;
import pl.mczapiewski.dto.MainServiceDto;
import pl.mczapiewski.dto.PatientDto;
import pl.mczapiewski.dto.PersonalDataDto;
import pl.mczapiewski.entity.BasicService;
import pl.mczapiewski.entity.MainService;
import pl.mczapiewski.entity.Patient;
import pl.mczapiewski.entity.PersonalData;
import pl.mczapiewski.translator.ClassTranslator;

/**
 * Przyklad uzycia klasy ClassTranslator
 * 
 * @author mczapiewski
 * 
 */
public class ClassTranslatorExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Start");

		MainService mainService = createExampleEntity();
		MainServiceDto mainServiceDto = new MainServiceDto(); // DTO utworzone z encji

		// Tworze instacje translatora dla struktury MainService
		ClassTranslator<MainService, MainServiceDto> translator = new ClassTranslator<MainService, MainServiceDto>();
		translator.parseEntityObjectToDto(mainService, mainServiceDto);

		System.out.println("Dane wejsciow/pobrane z bazy z entity:");
		printEntity(mainService);

		System.out.println("\nDto utworzone z encji:");
		printDto(mainServiceDto);

		System.out.println("\nKonwertuje przetworzone/zmodyfikowane DTO na Encje:");

		// jakas tam lekka modyfikacja
		mainServiceDto.setAccepted(true);
		mainServiceDto.setModified(new Date());
		mainServiceDto.setLifeSaving(false);
		mainServiceDto.getPatient().setNfzDepartment(55);

		MainService updateMainService = new MainService(); // nowa instancja
															// encji

		translator.mergeToDomainObject(updateMainService, mainServiceDto);

		printEntity(updateMainService);

		System.out.println("Koniec");
	}

	/**
	 * Tworzy strukture DTO wypelniona jakimis przykladowymi danymi
	 * 
	 * @return
	 */
	private static MainServiceDto createExampleDto() {
		// Tworze strukture obiektow DTO i wypelniam ja danymi
		MainServiceDto mainServiceDto = new MainServiceDto(); // dto
		mainServiceDto.setId(1L);
		mainServiceDto.setStartDate(new Date());
		mainServiceDto.setEndDate(new Date());
		mainServiceDto.setIdInHis("1234");
		mainServiceDto.setRange("range");
		mainServiceDto.setSystemId("Systemid");
		mainServiceDto.setLifeSaving(true);
		mainServiceDto.setNeedsPower(100);

		// --dodaje obiekt ktory bedzie kopiowany a nalezy do listy
		BasicServiceDto basicServiceDto = new BasicServiceDto();
		basicServiceDto.setId(2L);
		basicServiceDto.setIdInHis("4321");
		basicServiceDto.setStartDate(new Date());
		basicServiceDto.setMainService(mainServiceDto);

		mainServiceDto.getBasicServices().add(basicServiceDto);

		// --dodaje pojedynczy obiekt, ktory bedzie kopiowany

		PatientDto patientDto = new PatientDto();
		patientDto.setId(3L);
		patientDto.setIdInHis("5678");
		patientDto.setNfzDepartment(2);
		patientDto.setPatientKind("patientKindId");
		patientDto.setPeselEu("123456789");
		patientDto.setPin("123456789");
		patientDto.setPinKind("P");

		patientDto.setMainService(mainServiceDto);
		mainServiceDto.setPatient(patientDto);

		PersonalDataDto personalDataDto = new PersonalDataDto();
		personalDataDto.setId(5L);
		personalDataDto.setFirstName("Imie");
		personalDataDto.setLastName("Nazwisko");
		personalDataDto.setPatient(patientDto);
		
		patientDto.setPersonalData(personalDataDto);

		// --dodaje obiekt, ktory nie bedzie kopiowany
		JakisObiektDto jakisObiekt = new JakisObiektDto();
		jakisObiekt.setId(4L);
		jakisObiekt.setName("to jest jaksi obiekt, ktory nei bedzie kopiowany");

		mainServiceDto.setJakisPrzykladowyObiekt(jakisObiekt);
		mainServiceDto.getJakasPrzykladowaList().add(jakisObiekt);

		return mainServiceDto;
	}

	/**
	 * Tworzy strukture encji wypelniona jakimis przykladowymi danymi
	 * 
	 * @return
	 */
	private static MainService createExampleEntity() {
		// Tworze strukture obiektow encji i wypelniam ja danymi
		MainService mainService = new MainService(); // dto
		mainService.setId(1L);
		mainService.setStartDate(new Date());
		mainService.setEndDate(new Date());
		mainService.setIdInHis("EntitySystem");
		mainService.setRange("entity");
		mainService.setSystemId("EntitiySystemId");
		mainService.setLifeSaving(true);
		mainService.setNeedsPower(100);

		// --dodaje obiekt ktory bedzie kopiowany a nalezy do listy
		BasicService basicService = new BasicService();
		basicService.setId(2L);
		basicService.setIdInHis("4321");
		basicService.setStartDate(new Date());
		basicService.setMainService(mainService); // UWAGA - TEGO NIE
													// WYPELNIAMY!!! robi to za
													// nas ClassTranslator

		Set<BasicService> bsList = new HashSet<BasicService>();
		bsList.add(basicService);

		mainService.setBasicServices(bsList);

		// --dodaje pojedynczy obiekt, ktory bedzie kopiowany

		Patient patient = new Patient();
		patient.setId(3L);
		patient.setIdInHis("5678");
		patient.setNfzDepartment(2);
		patient.setPatientKind("patientKindId");
		patient.setPeselEu("123456789");
		patient.setPin("123456789");
		patient.setPinKind("P");

		patient.setMainService(mainService); // UWAGA - TEGO NIE WYPELNIAMY!!!
												// robi to za nas
												// ClassTranslator
		mainService.setPatient(patient);

		PersonalData personalData = new PersonalData();
		personalData.setId(5L);
		personalData.setFirstName("Imie");
		personalData.setLastName("Nazwisko");

		personalData.setPatient(patient);
		patient.setPersonalData(personalData);

		return mainService;
	}

	/**
	 * @param mainService
	 */
	private static void printEntity(MainService mainService) {
		System.out.println(mainService);
		System.out.println(" ->" + mainService.getPatient());
		System.out.println(" <-" + mainService.getPatient().getMainService());
		System.out.println("   ->" + mainService.getPatient().getPersonalData());
		System.out.println("   <-" + mainService.getPatient().getPersonalData().getPatient());
		System.out.println(" ->" + (mainService.getBasicServices().toArray()[0]));
		System.out.println(" <-" + ((BasicService) mainService.getBasicServices().toArray()[0]).getMainService());
	}

	/**
	 * @param mainServiceDto
	 */
	private static void printDto(MainServiceDto mainServiceDto) {
		System.out.println(mainServiceDto);
		System.out.println(" ->" + mainServiceDto.getPatient());
		System.out.println(" <-" + mainServiceDto.getPatient().getMainService());
		System.out.println("   ->" + mainServiceDto.getPatient().getPersonalData());
		System.out.println("   <-" + mainServiceDto.getPatient().getPersonalData().getPatient());
		System.out.println(" ->" + (mainServiceDto.getBasicServices().toArray()[0]));
		System.out.println(" <-" + ((BasicServiceDto) mainServiceDto.getBasicServices().toArray()[0]).getMainService());
	}
}
