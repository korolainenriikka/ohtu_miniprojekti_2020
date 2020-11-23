# Asennusohje

Ennen asennusta tarvitset välineet git-repositorioiden käsittelyyn, ne voi ladata [täältä](https://git-scm.com/downloads).

Sovelluksen suorittamiseen tarvitaan gradle työkalua, käytä "gradle" sanan tilalla sanaa ./gradlew Linuxilla ja gradlew.bat Windowsilla.  

* Kloonaa sovelluksen repositorio omalle koneellesi komennolla  
`git clone https://github.com/korolainenriikka/ohtu_miniprojekti_2020.git`  

* Siirry projektin juurihakemistoon eli kansioon ohtu_miniprojekti_2020 ja suorita komento  
`gradle jar`  

* Jar-tiedosto on nyt luotu hakemistoon ohtu_miniprojekti_2020/build/ ja voit suorittaa sen komennolla  
`java -jar build/libs/KäpistelyKirjasto`
