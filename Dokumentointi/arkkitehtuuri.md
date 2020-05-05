# Arkkitehtuurikuvaus
## Rakenne
Ohjelman graafinen käyttöliittymä on toteutettu _fraktaalit.ui_ pakkaukseen. Graafinen käyttöliittymä on toteutetty JavaFX:llä. Sovelluslogiikka on toteutettu pakkaukseen _fraktaalit.logic_. Sovelluslogiikan käyttämät graafiset ominaisuudet sijaitsevat pakkauksessa _fraktaalit.ui.graphics_.

## Sovelluslogiikka
Julia joukkoja piirtäessä ohjelma noudattaa seuraavanlaista rakennetta

![Luokkakaavio](https://github.com/oskarTom/ot-harjoitustyo/blob/master/images/Luokkakaavio.png)


## Julian joukon piirtäminen
Kun käyttäjä on valinnut pisteen Mandelbrotin joukosta, ohjelman suoritus jatkuu seuraavasti:

![Sekvenssikaavio](https://github.com/oskarTom/ot-harjoitustyo/blob/master/images/Sekvenssikaavio.png)

Mandelbrotin joukon klikkaukseen reagoiva tapahtumankäsittelijä kutsuu JuliaLogic luokan oliosta draw metodia. JuliaLogic oliolle on tallennettu Canvas olio, joten se voi piirtää kuvauksen palauttamatta mitään arvoa. JuliaLogic luokan olio hakee Zoom oliosta leveyden ja korkeuden, joiden avulla voidaan päätellä kuinka tiheästi lukuja valitaan.

## Ohjelman rakenteen heikkoudet

### Käyttöliittymä

Graafinen käyttöliittymä on toteutettu lähes kokonaan _fraktaalit.ui.UI_ luokkaan. Käyttöliittymän määrittely olisi vähemmän sekava jos rakenne olisi määritelty FXML-määrittelyllä.

### Sovelluslogiikka

Graafiset ominaisuudet piti jättää testien ulkopuolelle, joten erittelin sovelluslogiikan graafiset ominaisuudet abstraktiin luokkaan _FractalDrawer_. Luokkien _MandelbrotLogic_ ja _JuliaLogic_ _escapeTest()_ metodit sisälsivät eri määrän argumentteja, mikä hankaloitti metodin _draw()_ määrittelemisen yksikäsitteisesti molemmille luokille. Ratkaisuni oli luoda _FractalSettings_ luokka, jonka _draw()_ metodi ottaa argumentikseen. Tämä tuntui jälkeenpäin hieman monimutkaiselta ratkaisulta.
