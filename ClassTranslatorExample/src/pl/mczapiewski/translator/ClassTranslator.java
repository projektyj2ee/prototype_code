package pl.mczapiewski.translator;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;

import org.apache.log4j.Logger;

import pl.mczapiewski.dto.ModelDto;
import pl.mczapiewski.entity.Model;
import pl.mczapiewski.translator.annotation.Dto;
import pl.mczapiewski.translator.annotation.Dto.DtoType;
import pl.mczapiewski.translator.annotation.TransientDto;
import pl.mczapiewski.translator.utils.ClasspathUtil;

/**
 * Klasa umozliwiajaca konwersje obiektów impelementujacych interfejs 'ModelDto'
 * na obiekty implementujace interfejs 'Model' i na odwrot
 * 
 * @author mczapiewski
 * 
 * @param <S>
 *            - source implements Model
 * @param <T>
 *            - target implements ModelDto
 */
public class ClassTranslator<S extends Model, T extends ModelDto> {
	private static final Logger logger = Logger.getLogger(ClassTranslator.class);

	/**
	 * Metoda wypelnia w sposób standardowy przekazany obiekt encji. Przepisuje
	 * jeden-do-jeden wartosci pol posiadajace zgodne nazwy
	 * 
	 * @param entity
	 *            encja
	 */
	public void mergeToDomainObject(S entity, T dto) {
		mergeToDomainObject(entity, dto, false, false);
	}

	/**
	 * Metoda wypelnia w sposób standardowy przekazany obiekt encji. Przepisuje
	 * jeden-do-jeden wartosci pol posiadajace zgodne nazwy
	 * 
	 * @param entity
	 * @param dto
	 * @param mergePlain
	 */
	public void mergeToDomainObject(S entity, T dto, Boolean mergePlain) {
		mergeToDomainObject(entity, dto, mergePlain, false);
	}

	/**
	 * Metoda wypelnia w sposób standardowy przekazany obiekt encji. Przepisuje
	 * jeden-do-jeden wartosci pol posiadajace zgodne nazwy
	 * 
	 * @param entity
	 *            encja
	 * @param mergePlain
	 *            czy ominąć pola z adnotacją OtherDto (jeśli true to omija)
	 */
	public void mergeToDomainObject(S entity, T dto, boolean mergePlain, boolean skipNullValues) {

		Method[] methods = dto.getClass().getMethods();
		// ze wszystkich metod z dto
		for (Method method : methods) {
			// wybieramy gettery
			if (method.getName().length() > 3 && "get".equals(method.getName().substring(0, 3)) && !"getClass".equals(method.getName())) {

				// pobranie adnotacji 'Dto' z pola
				Dto adnotationDto = method.getAnnotation(Dto.class);

				if (method.getAnnotation(TransientDto.class) != null || (adnotationDto != null && adnotationDto.type().equals(DtoType.TRANSIENT))) { // dla
																																						// adnotacji
																																						// TransientDto
																																						// lub
																																						// DtoType.TRANSIENT
																																						// przerywa
																																						// mapowanie
					continue;
				}

				String methodName = method.getName();

				try {
					// pobranie wartości z dto
					Object fieldValue = method.invoke(dto);

					if (skipNullValues && fieldValue == null)
						continue;

					// pobrane metody wyciągające zagnieżdżoną encję
					Method entityGetter = entity.getClass().getMethod(methodName);

					if (!mergePlain && adnotationDto != null && fieldValue != null) {

						if (adnotationDto.type().equals(DtoType.FIELD)) { // przetwarzam
																			// pola
							// pobranie zagnieżdżoną encję
							Object entityValue = entityGetter.invoke(entity);

							// jeśli null to tworzymy nowy obiekt
							if (entityValue == null) {
								entityValue = entityGetter.getReturnType().newInstance();
							}

							// pobranie metody mergeToDomainObject z
							// zagnieżdżonego dto
							Method mergeMethod = this.getClass().getMethod("mergeToDomainObject", Model.class, ModelDto.class);
							// wywołanie metody

							mergeMethod.invoke(this, entityValue, fieldValue);
							// ustawienie referencji zwrotnej
							// jesli targetObject ma metodę set + nazwaKlasyDto
							// to ja wywolujemy z biezacym obiektem
							try {
								Method setDtoMethod = entityValue.getClass().getMethod("set" + entity.getClass().getSimpleName(), entity.getClass());
								setDtoMethod.invoke(entityValue, entity);
							} catch (NoSuchMethodException e) {
								System.err.println("Blad podczas wywolania metody: " + method.getName() + " " + e);
								logger.error(e.getMessage());
							}

							Method domainSetter = entity.getClass().getMethod("set" + method.getName().substring(3), entityGetter.getReturnType());
							domainSetter.invoke(entity, entityValue);

						} else if (adnotationDto.type().equals(DtoType.LIST) || adnotationDto.type().equals(DtoType.SET)) {

							Class<?> dtoClass = ClasspathUtil.getGenericType(method.getGenericReturnType());
							if (dtoClass == null) {
								throw new IllegalArgumentException("Kolekcja powinna być generyczna");
							}

							Class<?> entityClass = ClasspathUtil.getGenericType(entityGetter.getGenericReturnType());
							if (entityClass == null) {
								throw new IllegalArgumentException("Obiekt dto powinien być generyczny");
							}

							Collection<Object> entityCollection = null;
							if (adnotationDto.type().equals(DtoType.LIST)) {
								entityCollection = new LinkedList<Object>();
							} else {
								entityCollection = new LinkedHashSet<Object>();
							}

							for (Object o : (Collection<?>) fieldValue) {
								// utworzenie nowego obiektu encji
								Object entityObject = entityClass.newInstance();
								// wywołanie metody merge
								Method parseMethod = this.getClass().getMethod("mergeToDomainObject", Model.class, ModelDto.class);
								parseMethod.invoke(this, entityObject, o);
								// dodanie do listy
								entityCollection.add(entityObject);
								// ustawienie referencji zwrotnej
								// jesli targetObject ma metodę set +
								// nazwaKlasyDto to ja wywolujemy z biezacym
								// obiektem
								try {
									Method setDtoMethod = entityObject.getClass().getMethod("set" + entity.getClass().getSimpleName(), entity.getClass());
									setDtoMethod.invoke(entityObject, entity);
								} catch (NoSuchMethodException e) {
									System.err.println("Blad podczas wywolania metody: " + method.getName() + " " + e);
									logger.error(e.getMessage());
								}
							}

							Method domainSetter = entity.getClass().getMethod("set" + method.getName().substring(3), entityGetter.getReturnType());
							domainSetter.invoke(entity, entityCollection);
						}
					} else {
						Method domainSetter = entity.getClass().getMethod("set" + method.getName().substring(3), entityGetter.getReturnType());
						domainSetter.invoke(entity, fieldValue);
					}

				} catch (Exception e) {
					// błąd w tym przypadku zostaje tylko zalogowany
					System.err.println("Blad podczas wywolania metody: " + method.getName() + " " + e);
					logger.error("Nie udało się wypełnić pola " + method.getName().substring(3) + "; " + e.getClass().getName() + ": " + e.getMessage(), e);
				}
			}
		}
	}

	/**
	 * Metoda wypelnia w sposób standardowy obiekt dto na podstawie encji.
	 * Przepisuje jeden-do-jeden wartosci pol posiadajace zgodne nazwy
	 * 
	 * @param entity
	 *            encja
	 */
	public void parseEntityObjectToDto(S entity, T dto) {
		parseEntityObjectToDto(entity, dto, false);
	}

	/**
	 * Metoda wypelnia w sposób standardowy obiekt dto na podstawie encji.
	 * Przepisuje jeden-do-jeden wartosci pol posiadajace zgodne nazwy
	 * 
	 * @param entity
	 *            encja
	 * @param boolean parsePlain czy ominąć adnotację OtherDto (jeśli true to
	 *        omija)
	 */
	public void parseEntityObjectToDto(S entity, T dto, boolean parsePlain) {

		if (entity == null) {
			return;
		}

		Method[] methods = dto.getClass().getMethods();

		// ze wszystkich metod z dto
		for (Method method : methods) {

			// wybieramy gettery
			if (method.getName().length() > 3 && "get".equals(method.getName().substring(0, 3)) && !"getClass".equals(method.getName())) {

				// pobranie adnotacji 'Dto' z pola
				Dto adnotationDto = method.getAnnotation(Dto.class);

				if (method.getAnnotation(TransientDto.class) != null || (adnotationDto != null && adnotationDto.type().equals(DtoType.TRANSIENT))) { // dla
																																						// adnotacji
																																						// TransientDto
																																						// lub
																																						// DtoType.TRANSIENT
																																						// przerywa
																																						// mapowanie
					continue;
				}

				String methodName = method.getName();

				try {
					// pobranie wartości analogicznego pola w encji
					Object fieldValue = entity.getClass().getMethod(methodName, method.getParameterTypes()).invoke(entity);

					if (!parsePlain && adnotationDto != null) {

						if (adnotationDto.type().equals(DtoType.FIELD)) {
							// obsługa zagnieżdżonych dto
							if (fieldValue == null) {
								continue;
							}
							// pobranie metody parseDomainObjectToDto z
							// zagnieżdżonego dto
							Method parseMethod = this.getClass().getMethod("parseEntityObjectToDto", Model.class, ModelDto.class);
							// utworzenie nowego obiektu dto
							Object targetObject = method.getReturnType().newInstance();
							// wywołanie metody
							parseMethod.invoke(this, fieldValue, targetObject);

							// pobranie metody ustawiającej (settera) dla
							// zagnieżdżonego dto
							Method domainSetter = dto.getClass().getMethod("set" + methodName.substring(3), method.getReturnType());
							// wywołanie settera
							domainSetter.invoke(dto, targetObject);

							// ustawienie referencji zwrotnej
							// jesli targetObject ma metodę set + nazwaKlasyDto
							// to ja wywolujemy z biezacym obiektem
							try {
								Method setDtoMethod = targetObject.getClass().getMethod("set" + entity.getClass().getSimpleName(), dto.getClass());
								setDtoMethod.invoke(targetObject, dto);
							} catch (NoSuchMethodException e) {
								System.err.println("Blad podczas wywolania metody: " + method.getName() + " " + e);
							}

						} else if (adnotationDto.type().equals(DtoType.LIST) || adnotationDto.type().equals(DtoType.SET)) {

							if (fieldValue != null) {
								Class<?> dtoClass = ClasspathUtil.getGenericType(method.getGenericReturnType());
								if (dtoClass == null) {
									throw new IllegalArgumentException("Kolekcja powinna być generyczna");
								}

								Collection<Object> dtoCollection = null;
								if (adnotationDto.type().equals(DtoType.LIST)) {
									dtoCollection = new ArrayList<Object>();
								} else {
									dtoCollection = new LinkedHashSet<Object>();
								}

								for (Object o : (Collection<?>) fieldValue) {
									if (o == null) {
										continue;
									}
									// utworzenie nowego dto
									Object embdto = dtoClass.newInstance();

									// wywołanie metody parse
									Method parseMethod = this.getClass().getMethod("parseEntityObjectToDto", Model.class, ModelDto.class);
									parseMethod.invoke(this, o, embdto);

									// dodanie do listy
									dtoCollection.add(embdto);

									// ustawienie referencji zwrotnej
									// jesli targetObject ma metodę set +
									// nazwaKlasyDto to ja wywolujemy z biezacym
									// obiektem
									try {
										Method setDtoMethod = embdto.getClass().getMethod("set" + entity.getClass().getSimpleName(), dto.getClass());
										setDtoMethod.invoke(embdto, dto);
									} catch (NoSuchMethodException e) {
										System.err.println("Blad podczas wywolania metody: " + method.getName() + " " + e);
									}
								}

								// pobranie metody ustawiającej (settera) dla
								// zagnieżdżonego dto
								Method domainSetter = dto.getClass().getMethod("set" + methodName.substring(3), method.getReturnType());
								// wywołanie settera
								domainSetter.invoke(dto, dtoCollection);
							}
						}
					} else {
						// obsługa typow zwykłych

						// pobranie settera
						Method domainSetter = dto.getClass().getMethod("set" + methodName.substring(3), method.getReturnType());
						// wywołanie settera
						domainSetter.invoke(dto, fieldValue);
					}
				} catch (Exception e) {
					System.err.println("Blad podczas wywolania metody: " + method.getName() + " " + e);
					// błąd w tym przypadku zostaje tylko zalogowany
					logger.warn("Nie udało się wypełnić pola " + methodName.substring(3) + "; " + e.getClass().getName() + ": " + e.getMessage(), e);
				}
			}
		}
	}
}
