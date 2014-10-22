import java.util.*;
///**
// * Tehtävä X3
// * Tämä luokka on annetun pakka-rajapinnan toteutus. Luokasta löytyy rajapinnassa
// * annetut metodit onkoTyhja(), lisaaAlkuun(E x), lisaaLoppuun(E x), alku(),
// * loppu(), poistaAlusta(), poistaLopusta() sekä toteutus luokan iteraattorille ja toString()-metodille
// * debuggauksen helpottamista varten.
// * 
// * Tietorakenne on dynaamisesti kahteen suuntaan linkitetty ja 
// * toimii ajatuksen tasolla fyysisen pelikorttipakan tavoin; luokasta
// * ei löydy operaatiota, jolla voitaisiin muokata alkioita keskeltä pakkaa. Toisinsanoen, 
// * rakenteeseen voidaan tehdä operaatioita pelkästään pinkan pohjalle ja päälle. Tästä voisi
// * olla hyötyä esimerkiksi silloin, kun halutaan varmistua että ohjelmoija ei tule käyttäneeksi
// * vahingossa indeksipohjaisia operaatioita, jotka ovat pahimmassa tapauksessa lineaarisia linkitetylle rakenteelle.
// * 
// * Kaikki tässä luokassa olevat operaatiot ovat vakioaikaisia (lukuunottamatta toString()-metodia, joka on lineaarinen).
// * Luokkaa voisi jatkokehittää kirjoittamalla esimerkiksi käyttäjän haluamaan suuntaan lukevan iteraattorin.
// * Luokalle voisi kehitellä myös jonkinlaisen addAll()-metodin. Collections-rajapintaa tuskin kannattaisi 
// * implementoida kuitenkaan suoraan, sillä silloin overridettäisiin myös rajapinnan indeksipohjaiset operaatiot.
// * @author Aki
// * @param <E>
// */
public class akivv<E> implements Pakka<E>,Iterable<E>{
	@Override
	public String toString() {
		Iterator<E> iterator = iterator();
		StringBuilder theString = new StringBuilder();
		while (iterator.hasNext()) {
			E e = iterator.next();
			theString.append(e);
			theString.append(" ");
		}
		return theString.toString();	
	}
	private node last = null;
	private node first = null;
	private node curr = null;
	public akivv() {
		// TODO Auto-generated constructor stub
	}
	public akivv(E x) {
		lisaaAlkuun(x);
	}
	@Override
	public boolean onkoTyhja() {
		if (last == null || first == null) {
			return true;
		} else {
			return false;
		}
	}
	@Override
	public void lisaaAlkuun(E x) {
		//Tarkistetaan ensin onko tässä rakenteessa yhtään mitään
		//Jos rakenne on tyhjä niin ensimmäinen ja viimeinen ovat tietysti
		//tämä lisättävä tapaus
		if (onkoTyhja()) {
			node toFirstAndLast = new node(x);
			this.first = toFirstAndLast;
			this.last = toFirstAndLast;
			return;
		}
		//Tehdään uusi node äksällä
		node toAdd = new node(x);
		//Otetaan tämänhetkinen ensimmäinen talteen
		node tmp = first;
		//Asetetaan uusi ensimmäiseksi
		this.first = toAdd;
		//Asetetaan ensimmäisen seuraavaksi
		//entinen ensimmäinen
		this.first.next = tmp;
		//Laitetaan edelliseksi null
		this.first.previous = null;
		//Korjataan entinen osoittamaan oikeaa previousta
		tmp.previous = this.first;
	}
	@Override
	public void lisaaLoppuun(E x) {
		//Tarkistetaan ensin onko tässä rakenteessa yhtään mitään
		//Jos rakenne on tyhjä niin ensimmäinen ja viimeinen ovat tietysti
		//tämä lisättävä tapaus
		if (onkoTyhja()) {
			node toFirstAndLast = new node(x);
			this.first = toFirstAndLast;
			this.last = toFirstAndLast;
			return;
		}
		//Tehdään uusi node äksällä
		node toAdd = new node(x);
		//Tämänhetkinen talteen
		node tmpLast = this.last;
		//Asetetaan uusi viimeiseksi
		this.last = toAdd;
		//Asetetaan nulliksi seuraava
		this.last.next = null;
		//Asetetaan previous arvoksi vanha viimeinen
		this.last.previous = tmpLast;
		//Korjataan entinen viimeinen osoittamaan oikeaa nextiä
		tmpLast.next = this.last;
	}
	@Override
	public E alku() throws NoSuchElementException {
		if (onkoTyhja()) {
			throw new NoSuchElementException();
		}
		return first.dataValue;
	}
	@Override
	public E loppu() throws NoSuchElementException {
		if (onkoTyhja()) {
			throw new NoSuchElementException();
		}
		return last.dataValue;
	}
	@Override
	public E poistaAlusta() throws NoSuchElementException {
			//Onko tyhjä
			if (onkoTyhja()) {
				throw new NoSuchElementException();
			}
			//Tarkistetaan onko tämä mahdollisesti viimeinen poistettava tapaus!
			if (last.previous == null) {
				node tmp = first;
				last = null;
				first = null;
				return tmp.dataValue;
			}
			node newFirst = first.next;
			node returnableFirst = first;
			first = newFirst;
			returnableFirst.next = null;
			returnableFirst.previous = null;
			first.previous = null;
			return returnableFirst.dataValue;
	}
	@Override
	public E poistaLopusta() throws NoSuchElementException{
			//Tarkistetaan onko tyhjä
			if (onkoTyhja()) {
				throw new NoSuchElementException();
			}
			//Tarkistetaan onko tämä viimeinen poistettava tapaus
			if (last.previous == null) {
				node tmp = last;
				last = null;
				first = null;
				return tmp.dataValue;
			}
			//Otetaan tämänhetkiset arvot muistiin
			node newLast = this.last.previous;
			node currentLast = this.last;
			//asetetaan uudeksi lastiksi newLast
			this.last = newLast;
			//Korjataan next osoittamaan nullia
			this.last.next = null;
			//palautetaan poistetun dataValue
			return currentLast.dataValue;
	}
	@Override
	public Iterator<E> iterator() throws NoSuchElementException{
		if (onkoTyhja()) {
			throw new NoSuchElementException();
		}
		curr = first;
		return new pakkaIterator();
	}
	//	/**
	//	 * Yksittäinen datakapseli, sisältää kaksisuuntaiset
	//	 * viittaukset edelliseen ja seuraavaan kapseliin tietorakenteessa
	//	 * @author Aki
	//	 */
	class node {
		private node next = null;
		private node previous = null;
		private E dataValue = null;
		public node(E dataToInput) {
			this.dataValue = dataToInput;
		}
	}
	/**
	 * Luokan iteraattorin toteutus. Voisi tarvittaessa jatkokehitellä
	 * esimerkiksi toiseenkin suuntaan toimivaksi? 
	 * @author Aki
	 *
	 */
	class pakkaIterator implements Iterator<E> {
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return curr!=null;
		}
		@Override
		public E next() {
			// TODO Auto-generated method stub
			node tmp = curr;
			curr = curr.next;
			return tmp.dataValue;
		}
	}
}
