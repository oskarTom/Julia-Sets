
# Käyttöohje

### C:n valitseminen

C:n valitseminen onnistuu painamalla jotain pistettä mandelbrotin joukosta. Kun kursorin asettaa jollekkin pisteelle Mandelbrotin joukossa, joukon vasempaan alakulmaan tulostuu kyseistä pistettä vastaava kompleksiluku. Kun luku c on valittu, ohjelma piirtää vasemmalle lukua vastaavan Julia joukon. C tulostuu Julian joukon vasempaan alakulmaan.

Joukoissa voi liikkua myös painamalla pistettä mandelbrotin joukossa ja liikuttamalla samalla kursoria. Tämä saattaa kuitenkin olla hidasta jos iterointeja joudutaan tekemään paljon.

### Zoomaus

Zoomaus johonkin pisteeseen Julian tai Mandelbrotin joukossa onnistuu pyörittämällä hiirirullaa tai touchpadilla kahdella sormella. Zoomaaminen pisteisiin, jotka ovat joukon sisällä saattaa hidastaa ohjelmaa huomattavasti, koska tällöin iterointeja joudutaan tekemään enemmän.

Julia joukko zoomaa oletuksena kuvauksen keskipisteeseen. Tämän asetuksen voi kytkeä pois: _Edit --> Unlock Julia from center_.

Zoomauksen voi perua valitsemalla valikosta _Edit --> Reset Zoom_.
### Joukon tallentaminen kuvatiedostoksi

Julian joukon voi tallentaa kuvatiedostoksi. Tämä onnistuu valitsemalla valikosta _Files --> Save as png_. Uusi ikkuna aukeaa, jossa pyydetään valitsemaan resoluutio. Kuvan tallentamisessa voi mennä hetki, jos kuvan generointia varten joudutaan tekemään paljon iterointeja. 

_Huom: Kuvaa generoitaessa suoritetaan tavallista enemmän iterointeja, joten pisteet jotka näyttävät kuuluvan Julia joukkoon saattavatkin olla joukon ulkopuolella generoidussa kuvassa._
