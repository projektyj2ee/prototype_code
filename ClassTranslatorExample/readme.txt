Często występuje potrzeba mapowania/wypełaniania danych z jednego obiekt na drugi.
Praktycznie zawsze występuje konieczność kopiowania danych z encji do obiektów DTO, które używane są w metodach biznesowych.
W tym celu utworzyłem translator 'ClassTranslator' wykorzystujący reflection w celu wypełnienia odpowiednich pól.

Translator jako źródło (source) pobiera klasy implementujące interfejs 'pl.mczapiewski.entity.Model' a jako klasę docelową (target) oczekuję klasy implementującej interfej pl.mczapiewski.dto.ModelDto

Warunki konieczne, które muszą być spełnione, żeby translator dobrze wypełnił wymagane pola

1. nazwy pól w obu klasach muszą być jednakowe 
2. target i source musi mieć takie same metody 'set' i 'get' - w przypadku braku wygenerowany zostanie wyjątek (chyba, że użyjemy adnotacji @Dto(type = DtoType.TRANSIENT) albo @Transient)
3. pola, które utworzone są z obiektów złożony przy metodzie 'get' muszą zawierać adnotację @Dto(type = DtoType.FIELD)
4. pola, które są listami obiektów muszą zawierać adnotację @Dto(type = DtoType.LIST) lub @Dto(type = DtoType.SET)
5. pola, które nie mają być uwzględniane w procesie translacji muszą zawierać adnotację @Dto(type = DtoType.TRANSIENT) albo @Transient

Encję z reguły zawierają w sobie relację do innych obiektów (po PK lub FK) w tym celu do obietów DTO należy dodać identyczna strukturę z tym że należy metody 'get' w klasach DTO dla tych struktur 
sygnować adnotacją @Dto(type = DtoType.TRANSIENT) a translator sam automatycznie utworzy odpowiednia strukture tak, żeby możliwe było bezproblemowe utrwalenie encji w bazie danych 
(brak tej adnotacji w klasach DTO będzie skutkowąć nieskończoną rekurencją!) 

Translator działa także z innymi typami obiektów (np zamiast encji mogą być klasy reprezentujące wiersze w gridach po stronie klienckiej itp)
 
