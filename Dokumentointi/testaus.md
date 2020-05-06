# Testausdokumentti

Automaattiset testit testaavat ohjelman sovelluslogiikkaa, eli luokkia Complex, JuliaLogic ja MandelbrotLogic. Abstrakti luokka FractalSetup tulee testattua, kun testataan luokkia JuliaLogic ja MandelbrotLogic. 

### Testikattavuus

Kun graafinen käyttöliittymä jätetään testauksen ulkopuolelle, testauksen rivikattavuus on 87% ja haarautumakattavuus 85%.

![Test Result](https://github.com/oskarTom/ot-harjoitustyo/blob/master/images/jacoco%20result.png)

## Järjestelmätestaus

Järjestelmätestaus on toteutettu manuaalisesti.

### Asennus ja konfigurointi

Sovellusta on testattu Windows ja Linux ympäristössä käyttöohjeen mukaisesti. 

### Toiminnallisuudet
Ohjelman määrittelydokumentissa listatut ominaisuudet on testattu. Ohjelma ei sisällä syöttökenttiä joihin käyttäjä voisi syöttää virheellisiä arvoja, vaan kaikki käyttäjän valitsemat arvot ovat ohjelmassa määritettyjä.
