### Sovelluslogiikka
Julia joukkoja piirtäessä ohjelma noudattaa seuraavanlaista rakennetta

![Luokkakaavio](https://github.com/oskarTom/ot-harjoitustyo/blob/master/images/Luokkakaavio.png)


### Julian joukon piirtäminen
Kun käyttäjä on valinnut pisteen Mandelbrotin joukosta, ohjelman suoritus jatkuu seuraavasti:

![Sekvenssikaavio](https://github.com/oskarTom/ot-harjoitustyo/blob/master/images/Sekvenssikaavio.png)

Mandelbrotin joukon klikkaukseen reagoiva tapahtumankäsittelijä kutsuu JuliaLogic luokan oliosta draw metodia. JuliaLogic oliolle on tallennettu Canvas olio, joten se voi piirtää kuvauksen palauttamatta mitään arvoa. JuliaLogic luokan olio hakee Zoom oliosta leveyden ja korkeuden, joiden avulla voidaan päätellä kuinka tiheästi lukuja valitaan.
