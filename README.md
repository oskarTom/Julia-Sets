![Julia Set](https://github.com/oskarTom/ot-harjoitustyo/blob/master/images/Julia%202.png)

# Julian joukot
Ohjelma kuvaa syötetylle kompleksiluvulle vastaavan Julian joukon. Mielekkään Julian joukon saamiseksi on suotavaa valita kompleksiluku Mandelbrotin joukosta tai sen lähettyviltä. Iteraatioiksi kannattaa asettaa vähintään 1000.

Esimerkkejä kompleksiluvun arvolle:

(-0.4, 0.6)

(0.285, 0.01)

(-0.8, 0.15) *(vaatii enemmän iteraatioita)*

Projekti on toteutettu hakemistoon Fraktaalit.

## Dokumentaatio
[Vaatimusmäärittely](https://github.com/oskarTom/ot-harjoitustyo/blob/master/Dokumentointi/Vaatimusmaarittely.md)

[Työaikakirjanpito](https://github.com/oskarTom/ot-harjoitustyo/blob/master/Dokumentointi/tuntikirjanpito.md)

## Komentorivitoiminnot
Testien suorittaminen toteutetaan komennolla

    mvn test
Testikattavuusraportin luominen tapahtuu komennolla

    mvn test jacoco:report
Kattavuusraporttia voi tarkastella avaamalla tiedosto *target/site/jacoco/index.html* selaimessa
