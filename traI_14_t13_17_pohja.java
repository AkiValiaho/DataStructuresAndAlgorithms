
// traI_14_t13_17_pohja.java SJ
// P‰‰ohjelma viikon 3 teht‰viin 13-17

import java.util.*;

public class traI_14_t13_17_pohja {
    // P‰‰ohjelman k‰yttˆ:
    // java traI_14_t13_17_pohja [N] [N2] [S] [eri]
    // miss‰ N on alkioiden m‰‰r‰, N2 on alkoiden m‰‰r‰ toisessa taulukossa
    // ja S on satunnaislukusiemen
    // jos eri on olemassa, taulukoissa ei ole samoja alkioita
    public static void main(String[] args) {
        // taulukoiden koko
        int N1 = 10;
        if (args.length > 0)
            N1 = Integer.valueOf(args[0]);
        int N2 = N1;
        if (args.length > 0)
            N2 = Integer.valueOf(args[1]);
        // satunnaislukusiemen
        int siemen = 42;
        if (args.length > 2)
            siemen = Integer.valueOf(args[2]);
        // saako olla samoja alkioita
        int eri = 0;
        if (args.length > 3)
            eri = 1;
        // teht‰vien 13 ja 14 testaus, j‰rjestetty lista

        Integer[] T = new Integer[N1];
        // t‰ytet‰‰n alkioilla ja lajitellaan
        java.util.Random r = new java.util.Random(siemen);
        for (int i = 0; i < N1; i++) {
            T[i] = r.nextInt(N1/2 + N1*eri);
        }
        java.util.Arrays.sort(T);

        // tulostetaan taulukko jos alkioita ei ole paljoa
        if (N1 <= 20) {
            System.out.print("T: ");
            for (int i = 0; i < N1; i++)
                System.out.print(" " + T[i]);
            System.out.println();
        }

        // muodostetaan taulukosta LinkedList ja ArrayList
        LinkedList<Integer> LL = new LinkedList<Integer>();
        ArrayList<Integer> AL = new ArrayList<Integer>(N1);
        for (Integer x : T) {
            LL.add(x);
            AL.add(x);
        }
        LL.add(6);

        // kutsutaan teht‰v‰‰ 13
        duplikaatitPois(LL);
        System.out.print("Teht‰v‰ 13:");
        if (N1 <= 20) {
            for (Integer x : LL)
                System.out.print(" " + x);
            System.out.println();
        } else
            System.out.println(LL.size());

        // kutsutaan teht‰v‰‰ 14
        duplikaatitPois(AL);
        System.out.print("Teht‰v‰ 14:");
        if (N1 <= 20) {
            for (Integer x : AL)
                System.out.print(" " + x);
            System.out.println();
        } else
            System.out.println(AL.size());


        // teht‰v‰t 16-17: listan k‰‰ntˆ
        kaannaLista(AL);
        System.out.print("Teht‰v‰ 16:");
        if (N1 <= 20) {
            for (Integer x : AL)
                System.out.print(" " + x);
            System.out.println();
        } else
            System.out.println(AL.size());

        kaannaLista(LL);
        System.out.print("Teht‰v‰ 17:");
        if (N1 <= 20) {
            for (Integer x : LL)
                System.out.print(" " + x);
            System.out.println();
        } else
            System.out.println(LL.size());


        // teht‰v‰ 15: j‰rjest‰m‰ttˆmien listojen yhdiste

        // luodaan esimerkkitaulukot
        Integer[] T1 = new Integer[N1];
        Integer[] T2 = new Integer[N2];

        // t‰ytet‰‰n alkioilla
        for (int i = 0; i < N1; i++) {
            T1[i] = r.nextInt(N1);
        }

        for (int i = 0; i < N2; i++) {
            T2[i] = r.nextInt(N2*2) + eri * N1;
        }

        // tulostetaan taulukot jos alkioita ei ole paljoa
        if (N1 <= 20 && N2 <= 20) {
            System.out.print("T1: ");
            for (int i = 0; i < N1; i++)
                System.out.print(" " + T1[i]);
            System.out.println();

            System.out.print("T2: ");
            for (int i = 0; i < N2; i++)
                System.out.print(" " + T2[i]);
            System.out.println();
        }

        // Muodostetaan taulukoista LinkedList:t

        LinkedList<Integer> L1 = new LinkedList<Integer>();
        LinkedList<Integer> L2 = new LinkedList<Integer>();
        for (Integer x : T1)
            L1.add(x);
        for (Integer x : T2)
            L2.add(x);
        Collections.sort(L1);
        Collections.sort(L2);
        // kutsutaan teht‰v‰‰ 15
        LinkedList<Integer> Y15 = yhdiste15(L1, L2);

        System.out.print("Teht‰v‰ 15, yhdiste = ");
        if (N1 <= 20 && N2 <= 20) {
            for (Integer i : Y15)
                System.out.print(" " + i);
            System.out.println();
        } else {
            System.out.println(Y15.size() + " alkiota");
        }

    } // main()
    // 13: duplikaattien poisto j‰rjestetyst‰ LinkedList listasta
    // siis poistaa listasta alkioita
    public static <E extends Comparable<? super E>> 
    	void duplikaatitPois(LinkedList<E> L) {
    	ListIterator<E> it = L.listIterator();
    	E poistettava = null;
    	while (it.hasNext()) {
    		E seuraava=it.next();
			if (seuraava.equals(poistettava)) {
				it.remove();
			}
			else {
				poistettava=seuraava;
			}
			}
    	}
//		System.out.print(BodyRun+"\n");
//		System.out.print(whileRun+"\n");
            // TODO
    // 14: duplikaattien poisto j‰rjestetyst‰ ArrayList listasta
    // poistaa listasta alkioita
    public static <E extends Comparable<? super E>> 
    	void duplikaatitPois(ArrayList<E> L) {
    	LinkedHashSet<E> asdf = new LinkedHashSet<E>();
    	asdf.addAll(L);
    	L.clear();
    	L.addAll(asdf);
    	
    }
    /**
     * 15.
     * Palauttaa j‰rjestettyjen listojen yhdisteen ilman duplikaatteja
     * @param L1 ensimm‰inen lista kasvavassa j‰rjestyksess‰
     * @param L2 toinen lista kasvavassa j‰rjestyksess‰
     * @return yhteiset alkiot listana
     */
	public static <E extends Comparable<? super E>> LinkedList<E> yhdiste15(LinkedList<E> L1, LinkedList<E> L2) {
    	//K‰ytet‰‰n t‰ss‰ timantti(diamond)operaattoria. Timanttioperaattori
    	//alustaa uuden LinkedListin aidosti geneerisen‰. Toisinsanoen, uudelle
    	//listalle tulee sama tyyppiparametri kuin lauseen vasemmallekin puolelle.
    	//T‰ll‰ menettelytavalla saadaan geneeristen lauseiden hyˆty ilman ylim‰‰r‰isten
    	//tyyppiparametrien kirjoittamisvaivaa. K‰‰nt‰misen aikana n‰hd‰‰n siis
    	//geneeristen tyyppien paljastamat virheet.
    	LinkedList<E> unionOfTwo = new LinkedList<>();
    	//Ensin lienee tarpeellista poistaa molemmista tapauksista duplikaatit, jotta niit‰ ei
    	//n‰kyisi lopullisessa yhdisteess‰. (Lopullisen yhdisteen j‰rjest‰minen ja sitten
    	//duplikaattien poisto veisi parhaimmillaankin aikaa O(nlogn)
    	//Collectionsin sort-metodilla (mergesort)
    	E poistettava = null;
    	//Ensin L1
    	//Whilen bodyn suorittaminen vie selke‰sti vakioaikaisen ajan koska
    	//se ei riipu n-arvon koosta ja while‰ suoritetaan
    	//n-kertaa (kunnes iteraattorista loppuu tavara).
    	Iterator<E> it = L1.iterator();
    	while (it.hasNext()) {
    		E seuraava=it.next();
			if (seuraava.equals(poistettava)) {
				it.remove();
			}
			else {
				poistettava=seuraava;
			}
			}
    	//Sitten L2
    	poistettava = null;
    	Iterator<E> it2 = L2.iterator();
    	while (it2.hasNext()) {
    		E seuraava=it2.next();
			if (seuraava.equals(poistettava)) {
				it2.remove();
			}
			else {
				poistettava=seuraava;
			}
			}
    	//Nyt molemmat j‰rjestetyt listat ovat duplikaatittomia, toisinsanoen niiden alkup‰‰ss‰ on molemmissa
    	//samoissa indekseiss‰ samat arvot (jos listoissa on yhteisi‰ arvoja). Poistetaan yhteiset
    	//duplikaatit. Tarvitsemme duplikaattien poistoon listIteraattoria, koska tulemme tarvitsemaan
    	//previousta j‰rjestyksen s‰ilytt‰miseen.
    	Iterator<E> duplikaatinPoistajaIteraattoriL1 = L1.iterator();
    	Iterator<E> duplikaatinPoistajaIteraattoriL2 = L2.iterator();
    	while (duplikaatinPoistajaIteraattoriL1.hasNext() && duplikaatinPoistajaIteraattoriL2.hasNext()) {
    		E lista1Arvo = duplikaatinPoistajaIteraattoriL1.next();
    		E lista2Arvo = duplikaatinPoistajaIteraattoriL2.next();
    		Integer compareToResult = lista1Arvo.compareTo(lista2Arvo);
    		switch (compareToResult) {
			case 0:
				//Molemmat arvot ovat yht‰suuret
				//Indeksitˆn add ei traversaa koko linkitetty‰ listaa l‰pi vaan lis‰‰ 
				//tallennettuun loppup‰‰h‰n nextiksi addattavan arvon---> vakioaikainen operaatio
				unionOfTwo.add(lista1Arvo);
				duplikaatinPoistajaIteraattoriL1.remove();
				duplikaatinPoistajaIteraattoriL2.remove();
				break;
			case 1:
				//lista1Arvo on suurempi kuin lista2Arvo
				unionOfTwo.add(lista2Arvo);
				unionOfTwo.add(lista1Arvo);
				duplikaatinPoistajaIteraattoriL1.remove();
				duplikaatinPoistajaIteraattoriL2.remove();
				break;
			case -1:
				//lista1Arvo on pienempi kuin lista2Arvo
				unionOfTwo.add(lista1Arvo);
				unionOfTwo.add(lista2Arvo);
				duplikaatinPoistajaIteraattoriL1.remove();
				duplikaatinPoistajaIteraattoriL2.remove();
				break;
			}
    		
		}
    	//Lis‰t‰‰n lopuksi suurempi tapauksista yhdisteeseen
    		Integer sizeOf1 = L1.size(); //Collection-luokan menetelm‰t tallentavat size-tiedon variableen
    		//eli t‰m‰ lause on vakioaikainen O(1)
    		//public int size() {
    		//return size;
    		//}
    		Integer sizeof2 = L2.size();
    		if (sizeOf1 > sizeof2) {
				for (Iterator<E> iterator = L1.iterator(); iterator
						.hasNext();) {
					unionOfTwo.add(iterator.next());
				}
			} else {
				for (Iterator<E> iterator = L2.iterator(); iterator
						.hasNext();) {
					unionOfTwo.add(iterator.next());
			}
			}
			return unionOfTwo;
    }
	// yhdiste15()
    /**
     * 16-17
     * listan k‰‰ntˆ
     */
   public static <E> void kaannaLista(LinkedList<E> L) {
	   Integer listanKoko = L.size()-1; //O(n) koko lista l‰pi
	   for (int i = 0; i < L.size()/2; i++) {
		E tmp = L.get(i);//O(n) jos worst case eli listan vikana
		L.set(i, L.get(listanKoko)); //O(n) + O(n) jos worst case eli listan vikana
		L.set(listanKoko,tmp); //O(n) jos worst case eli listan vikana
		listanKoko--; //O(1)
	}
	}
   public static <E> void kaannaLista(ArrayList<E> L) {
	   Integer size = L.size();
	   ListIterator<E> fwd = L.listIterator();
	   ListIterator<E> rwd = L.listIterator(size);
	   Integer mid = size>>1;
	   for (int i = 0; i < mid; i++) {
		   E tmp = fwd.next();
		   fwd.set(rwd.previous());
		   rwd.set(tmp);
	}
   } 
} // class

