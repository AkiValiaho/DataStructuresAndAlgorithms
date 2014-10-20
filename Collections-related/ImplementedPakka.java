import java.util.*;
public class ImplementedPakka<E> implements Pakka<E>,Iterable<E>{
	private node last = null;
	private node first = null;
	private node curr = null;
	public ImplementedPakka() {
		// TODO Auto-generated constructor stub
	}
	public ImplementedPakka(E x) {
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
		//Tarkistetaan ensin onko t‰ss‰ rakenteessa yht‰‰n mit‰‰n
		//Jos rakenne on tyhj‰ niin ensimm‰inen ja viimeinen ovat tietysti
		//t‰m‰ lis‰tt‰v‰ tapaus
		if (onkoTyhja()) {
			node toFirstAndLast = new node(x);
			this.first = toFirstAndLast;
			this.last = toFirstAndLast;
			return;
		}
		//Tehd‰‰n uusi node ‰ks‰ll‰
		node toAdd = new node(x);
		//Otetaan t‰m‰nhetkinen ensimm‰inen talteen
		node tmp = first;
		//Asetetaan uusi ensimm‰iseksi
		this.first = toAdd;
		//Asetetaan ensimm‰isen seuraavaksi
		//entinen ensimm‰inen
		this.first.next = tmp;
		//Laitetaan edelliseksi null
		this.first.previous = null;
		//Korjataan entinen osoittamaan oikeaa previousta
		tmp.previous = this.first;
	}
	@Override
	public void lisaaLoppuun(E x) {
		//Tarkistetaan ensin onko t‰ss‰ rakenteessa yht‰‰n mit‰‰n
		//Jos rakenne on tyhj‰ niin ensimm‰inen ja viimeinen ovat tietysti
		//t‰m‰ lis‰tt‰v‰ tapaus
		if (onkoTyhja()) {
			node toFirstAndLast = new node(x);
			this.first = toFirstAndLast;
			this.last = toFirstAndLast;
		}
		//Tehd‰‰n uusi node ‰ks‰ll‰
		node toAdd = new node(x);
		//T‰m‰nhetkinen talteen
		node tmpLast = this.last;
		//Asetetaan uusi viimeiseksi
		this.last = toAdd;
		//Asetetaan nulliksi seuraava
		this.last.next = null;
		//Asetetaan previous arvoksi vanha viimeinen
		this.last.previous = tmpLast;
		//Korjataan entinen viimeinen osoittamaan oikeaa nexti‰
		tmpLast.next = this.last;
	}
	@Override
	public E alku() {
		return first.dataValue;
	}
	@Override
	public E loppu() {
		// TODO Auto-generated method stub
		return last.dataValue;
	}
	@Override
	public E poistaAlusta() {
		//Muista tarkistaa onko poistettavaa!
		//Otetaan t‰m‰nhetkiset arvot muistiin
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
	public E poistaLopusta() {
		//Muista tarkistaa onko poistettavaa!
		//Otetaan t‰m‰nhetkiset arvot muistiin
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
	public Iterator<E> iterator() {
		curr = first;
		return new pakkaIterator();
	}
	class node {
		private node next = null;
		private node previous = null;
		private E dataValue = null;
		public node(E dataToInput) {
			this.dataValue = dataToInput;
		}
}
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
