import java.util.*;
public class Harjoitus4Tehtava21 {
	public Harjoitus4Tehtava21() {
		Collection<Character> randomList = new ArrayList<>();
		for (int i = 0; i < 11; i++) {
			Random ran = new Random();
			/*    Pseudosatunnainen integer rangella [min,max],
			    ekslusiivinen topvaluen suhteen joten lisätään 1
			    että oikea max saadaan, esim. (max = 6, viimeinen
			      saatava luku = 5). Pelkkä nextInt antaa siis rangen
			    0-(arg-1)*/
			int luku = ran.nextInt((122- 97) + 1) + 97;
			randomList.add((char) luku);
		}
		System.out.println(randomList+"\n");
		Character biggest = poistaSuurinAlkio(randomList);
		System.out.println(biggest);
		Collection<Integer> randomListInteger = new ArrayList<>();
		for (int i = 0; i < 11; i++) {
			Random ran = new Random();
			/*    Pseudosatunnainen integer rangella [min,max],
			    ekslusiivinen topvaluen suhteen joten lisätään 1
			    että oikea max saadaan, esim. (max = 6, viimeinen
			      saatava luku = 5). Pelkkä nextInt antaa siis rangen
			    0-(arg-1)*/
			int luku = ran.nextInt((122- 97) + 1) + 97;
			randomListInteger.add(luku);
		}
		System.out.println(randomListInteger+"\n");
		Integer biggest1 = poistaSuurinAlkio(randomListInteger);
		System.out.println(biggest1);
	}
	public static void main(String[] args) {
		Harjoitus4Tehtava21 asdf = new Harjoitus4Tehtava21();
	}
	public <E extends Comparable<? super E>> E poistaSuurinAlkio(Collection<E> etsittavaCollection) {
		Iterator<E> it = etsittavaCollection.iterator();
		E biggest = it.next();
		while (it.hasNext()) {
			E next = it.next();
			if (next.compareTo(biggest) > 0) {
				biggest = next;
			}
		}
		it = etsittavaCollection.iterator();
		while (it.hasNext()) {
			E next = it.next();
			if (next.equals(biggest)) {
				it.remove();
			}
		}
		return biggest;
	}
}
