# Käyttöohje

## Käynnistäminen

Kun olet asentanut ohjelman [asennusohjeiden](https://github.com/korolainenriikka/ohtu_miniprojekti_2020/blob/master/dokumentaatio/asennus.md) opastamalla tavalla, avaa se komennolla 

```java -jar build/libs/KäpistelyKirjasto.jar```

## Päävalikko

Sovelluksen käynnistyessa avautuu valikko:  
![valikko](https://raw.githubusercontent.com/korolainenriikka/ohtu_miniprojekti_2020/master/dokumentaatio/valikko.png)  

Valitse haluamasi toiminto valikosta.  

* Valitsemalla "1" voit lisätä lukuvinkkejä.  
	* Sovellus kysyy haluatko lisätä kirjan vai videon. Valitse sopiva vaihtoehto ja vastaa lisäkysymyksiin.  
![kirja](https://raw.githubusercontent.com/korolainenriikka/ohtu_miniprojekti_2020/master/dokumentaatio/addbook.png)   
![video](https://raw.githubusercontent.com/korolainenriikka/ohtu_miniprojekti_2020/master/dokumentaatio/addvideo.png)  

* Valitsemalla "2" sovellus listaa kaikki jo kirjastossa olevat lukuvinkit.  
  
* Valitsemalla "3" voi poistaa haluamasi lukuvinkin.  
	* Valitse avautuvasta listasta sen lukuvinkin numero, jonka haluat poistaa.    
![delete](https://raw.githubusercontent.com/korolainenriikka/ohtu_miniprojekti_2020/master/dokumentaatio/delete.png)  

* Valitsemalla "4" voit muokata jo lisäämiäsi lukuvinkkejä.  
	* Valitse muokattava lukuvinkki ja vastaa lisäkysymyksiin.  
![edit](https://raw.githubusercontent.com/korolainenriikka/ohtu_miniprojekti_2020/master/dokumentaatio/edit.png)  

* Syöteellä "0" sovellus tulostaa valikon uudestaan.  

* Syöteellä "X" sovellus sulkeutuu.

## Lisää lukuvinkki

1. Valitse päävalikossa toiminto ```1```. 

1. Valitse kirja (```1```) tai video (```2```).

	1. Kirjan tapauksessa syötä vuorollaan kirjalle nimi, kirjoittaja, ISBN ja kommentti. Kommentin voi myös jättää tyhjäksi.
	1. Videon tapauksessa syötä vuorollaan videolle nimi, URL, kesto ja kommentti. Keston ja kommentin voi myös jättää tyhjäksi.

1. Voit valita videoon liittyvät kurssit syöttämällä niiden numerot pilkuilla erotettuina tai jättää liittämättä lukuvinkkiä mihinkään kurssiin antamalla tyhjän syötteen. Tarvittaessa voit lisätä ensin uuden kurssin valitsemalla ```X```.

### Uuden kurssin lisääminen

Jos valitsit lisätä uuden kurssin, syötä kurssille ensin tunnuskoodi ja sitten nimi. Ohjelma palaa kurssien valintaan, ja voit valita nyt myös luomasi kurssin.


## Näytä lukuvinkit

Valitse päävalikossa toiminto ```2```. 

Ohjelma listaa kaikki tallennetut lukuvinkit.

### Lukuvinkkien suodattaminen

Voit suodattaa lukuvinkkejä näyttämällä pelkästään luetut (```1```), lukemattomat (```2```) tai tiettyyn kurssiin liittyvät (```3```) lukuvinkit.

#### Kurssiin liittyvät lukuvinkit

Valitse se kurssi, johon liittyvät lukuvinkit haluat nähdä.




