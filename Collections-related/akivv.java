import java.util.*;
///**
// * Teht�v� X3
// * T�m� luokka on annetun pakka-rajapinnan toteutus. Luokasta l�ytyy rajapinnassa
// * annetut metodit onkoTyhja(), lisaaAlkuun(E x), lisaaLoppuun(E x), alku(),
// * loppu(), poistaAlusta(), poistaLopusta() sek� toteutus luokan iteraattorille ja toString()-metodille
// * debuggauksen helpottamista varten.
// * 
// * Tietorakenne on dynaamisesti kahteen suuntaan linkitetty ja 
// * toimii ajatuksen tasolla fyysisen pelikorttipakan tavoin; luokasta
// * ei l�ydy operaatiota, jolla voitaisiin muokata alkioita keskelt� pakkaa. Toisinsanoen, 
// * rakenteeseen voidaan tehd� operaatioita pelk�st��n pinkan pohjalle ja p��lle. T�st� voisi
// * olla hy�ty� esimerkiksi silloin, kun halutaan varmistua ett� ohjelmoija ei tule k�ytt�neeksi
// * vahingossa indeksipohjaisia operaatioita, jotka ovat pahimmassa tapauksessa lineaarisia linkitetylle rakenteelle.
// * 
// * Kaikki t�ss� luokassa olevat operaatiot ovat vakioaikaisia (lukuunottamatta toString()-metodia, joka on lineaarinen).
// * Luokkaa voisi jatkokehitt�� kirjoittamalla esimerkiksi k�ytt�j�n haluamaan suuntaan lukevan iteraattorin.
// * Luokalle voisi kehitell� my�s jonkinlaisen addAll()-metodin. Collections-rajapintaa tuskin kannattaisi 
// * implementoida kuitenkaan suoraan, sill� silloin overridett�isiin my�s rajapinnan indeksipohjaiset operaatiot.
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
		//Tarkistetaan ensin onko t�ss� rakenteessa yht��n mit��n
		//Jos rakenne on tyhj� niin ensimm�inen ja viimeinen ovat tietysti
		//t�m� lis�tt�v� tapaus
		if (onkoTyhja()) {
			node toFirstAndLast = new node(x);
			this.first = toFirstAndLast;
			this.last = toFirstAndLast;
			return;
		}
		//Tehd��n uusi node �ks�ll�
		node toAdd = new node(x);
		//Otetaan t�m�nhetkinen ensimm�inen talteen
		node tmp = first;
		//Asetetaan uusi ensimm�iseksi
		this.first = toAdd;
		//Asetetaan ensimm�isen seuraavaksi
		//entinen ensimm�inen
		this.first.next = tmp;
		//Laitetaan edelliseksi null
		this.first.previous = null;
		//Korjataan entinen osoittamaan oikeaa previousta
		tmp.previous = this.first;
	}
	@Override
	public void lisaaLoppuun(E x) {
		//Tarkistetaan ensin onko t�ss� rakenteessa yht��n mit��n
		//Jos rakenne on tyhj� niin ensimm�inen ja viimeinen ovat tietysti
		//t�m� lis�tt�v� tapaus
		if (onkoTyhja()) {
			node toFirstAndLast = new node(x);
			this.first = toFirstAndLast;
			this.last = toFirstAndLast;
			return;
		}
		//Tehd��n uusi node �ks�ll�
		node toAdd = new node(x);
		//T�m�nhetkinen talteen
		node tmpLast = this.last;
		//Asetetaan uusi viimeiseksi
		this.last = toAdd;
		//Asetetaan nulliksi seuraava
		this.last.next = null;
		//Asetetaan previous arvoksi vanha viimeinen
		this.last.previous = tmpLast;
		//Korjataan entinen viimeinen osoittamaan oikeaa nexti�
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
			//Onko tyhj�
			if (onkoTyhja()) {
				throw new NoSuchElementException();
			}
			//Tarkistetaan onko t�m� mahdollisesti viimeinen poistettava tapaus!
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
			//Tarkistetaan onko tyhj�
			if (onkoTyhja()) {
				throw new NoSuchElementException();
			}
			//Tarkistetaan onko t�m� viimeinen poistettava tapaus
			if (last.previous == null) {
				node tmp = last;
				last = null;
				first = null;
				return tmp.dataValue;
			}
			//Otetaan t�m�nhetkiset arvot muistiin
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
	//	 * Yksitt�inen datakapseli, sis�lt�� kaksisuuntaiset
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
	 * Luokan iteraattorin toteutus. Voisi tarvittaessa jatkokehitell�
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
