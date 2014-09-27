// traI_14_x1_pohja.java SJ
// P‰‰ohjelma viikon 4 teht‰v‰‰n X1
import java.util.*;
// TODO: muuta luokan nimeksi oman s‰hkˆpostiosoitteesi alkuosa!
public class akivv {
    // P‰‰ohjelman k‰yttˆ:
    // java traI_14_x1_pohja [N] [N2] [S] [eri]
    // miss‰ N on alkioiden m‰‰r‰, N2 on alkoiden m‰‰r‰ toisessa taulukossa
    // ja S on satunnaislukusiemen
    // jos eri on olemassa, taulukoissa ei ole samoja alkioita
    // kannattaa testata monipuolisesti erilaisilla syˆtteill‰ ja vaikka
    // tehd‰ eri versioita syˆtteen generoinnista
    public static void main(String[] args) {
        // taulukoiden koko
        int N1 = 10;
        if (args.length > 0)
            N1 = Integer.valueOf(args[0]);
        int N2 = N1;
        if (args.length > 0)
            N2 = Integer.valueOf(args[1]);
        // satunnaislukusiemen
        int siemen = 1;
        if (args.length > 2)
            siemen = Integer.valueOf(args[2]);
        // saako olla samoja alkioita
        int eri = 0;
        if (args.length > 3)
            eri = 1;
        // teht‰v‰ X1: j‰rjestettyjen listojen yhdiste
        // luodaan esimerkkitaulukot
        Integer[] T1 = new Integer[N1];
        Integer[] T2 = new Integer[N2];
        java.util.Random r = new java.util.Random(siemen);
        // t‰ytet‰‰n alkioilla ja j‰rjestet‰‰n
        for (int i = 0; i < N1; i++) {
            T1[i] = r.nextInt(N1);
        }
        java.util.Arrays.sort(T1);
        for (int i = 0; i < N2; i++) {
            T2[i] = r.nextInt(N2*2) + eri * N1;
        }
        java.util.Arrays.sort(T2);
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
        // Muodostetaan taulukoista LinkedList:t ja TraLinkedList:t
        LinkedList<Integer> L1 = new LinkedList<Integer>();
        LinkedList<Integer> L2 = new LinkedList<Integer>();
        for (Integer x : T1) {
            L1.add(x);
        }
        for (Integer x : T2) {
            L2.add(x);
        }
        // kutsutaan teht‰v‰‰ X1
        // n‰ist‰ riitt‰‰ tehd‰ toinen!
        LinkedList<Integer> Yhd = yhdisteX1(L1, L2);
        System.out.print("Teht‰v‰ X1    (LinkedList), yhdiste = ");
        if (N1 <= 20 && N2 <= 20) {
            for (Integer i : Yhd)
                System.out.print(" " + i);
            System.out.println();
        } else {
            System.out.println(Yhd.size() + " alkiota");
        }
    }
    
	/**
	 * T‰m‰ metodi mahdollistaa kahden j‰rjestetyn linkitetyn listan yhdist‰misen
	 * lineaarisella aikavaativuudella.
	 * @param L1
	 * @param L2
	 * @return
	 */
	public static <E extends Comparable<? super E>> LinkedList<E> yhdisteX1(LinkedList<E> L1, LinkedList<E> L2) {
    	//Kokonaisuudessaan metodin laskennallisesti merkitt‰v‰t osuudet tekev‰t 4n operaatiota -> O(n)
		//K‰ytet‰‰n t‰ss‰ timantti(diamond)operaattoria. Timanttioperaattori
    	//alustaa uuden LinkedListin aidosti geneerisen‰. Toisinsanoen, uudelle
    	//listalle tulee sama tyyppiparametri kuin lauseen vasemmallekin puolelle.
    	//T‰ll‰ menettelytavalla saadaan geneeristen lauseiden hyˆty ilman ylim‰‰r‰isten
    	//tyyppiparametrien kirjoittamisvaivaa. K‰‰nt‰misen aikana n‰hd‰‰n siis
    	//geneeristen tyyppien paljastamat virheet.
    	LinkedList<E> unionOfTwo = new LinkedList<>();
    	//Ensin lienee tarpeellista poistaa molemmista tapauksista duplikaatit, jotta niit‰ ei
    	//n‰kyisi lopullisessa yhdisteess‰. Kaiken yhdist‰minen nyt tuhoaisi yhdistelistasta
    	//j‰rjestyksen ja olisi v‰ltt‰m‰tˆnt‰ j‰rjest‰‰ uudelleen koko yhdistelista, joka johtaisi
    	//jopa Collectionsin sortilla aikavaativuuteen O(nlogn) (mergesort).
    	E poistettava = null;
    	//Ensin L1
    	//Whilen bodyn suorittaminen vie pahimmassa tapauksessa
    	//if-lauseen ensimm‰isen operaation bodyn verran aikaa, joka se on vakioaikainen
    	//
    	Iterator<E> it = L1.iterator();
    	//O(n)
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
    	//O(n)
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
    	//previousta alkioiden asettamiseen oikeaan j‰rjestykseen (esimerkkitapauksessa listassa L2 on luku yhdeks‰n
    	//joka on pienempi kuin 4,5 ja 8. Emme siis voi vaan poistaa lukua 9, muuten lista ei s‰ilytt‰isi j‰rjestyst‰‰n ja lukuja tulisi minne sattuu)
    	ListIterator<E> duplikaatinPoistajaIteraattoriL1 = L1.listIterator();
    	ListIterator<E> duplikaatinPoistajaIteraattoriL2 = L2.listIterator();
    	E lista1Arvo = null;
    	E lista2Arvo = null;
    	//O(n) pahimman tapauksen mukaan
    	//Pahin tapaus t‰lle ohjelmaosalle on kaksi kasvavaa listaa muodossa
    	//T1 = 1,3,5,7..... T2= 2,4,6,8....
    	//T‰lloin iteraattorin paikkaa joudutaan vaihtamaan kokoajan lis‰ten operaatioita
    	//K‰yt‰nnˆss‰ jokaisella whilen suorituskerralla listalta poistuu kuitenkin
    	//aina yksi alkio, jolloin operaatioita suoritetaan jotakuinkin n+m-1 -> O(n)
    	while (duplikaatinPoistajaIteraattoriL1.hasNext() && duplikaatinPoistajaIteraattoriL2.hasNext()) {
    		//next on vakioaikainen operaatio
    		lista1Arvo = duplikaatinPoistajaIteraattoriL1.next();
    		lista2Arvo = duplikaatinPoistajaIteraattoriL2.next();
    		Integer compareToResult = lista1Arvo.compareTo(lista2Arvo);
				if (compareToResult == 0) {
					//Molemmat arvot ovat yht‰suuret
					//Indeksitˆn add ei traversaa koko linkitetty‰ listaa l‰pi vaan lis‰‰ 
					//tallennettuun loppup‰‰h‰n nextiksi addattavan arvon---> vakioaikainen operaatio
					unionOfTwo.add(lista1Arvo);
					//Myˆskin vakioaikainen operaatio
					duplikaatinPoistajaIteraattoriL1.remove();
					duplikaatinPoistajaIteraattoriL2.remove();
				} else {
					//On huomattavaa, ett‰ n‰iss‰ ei voida suoraan k‰ytt‰‰ == operaatiota!
					//Esimerkiksi Charactereja vertailtaessa compareTo() voi palauttaa muitakin lukuja kuin
					//0, -1 ja 1. K‰ytet‰‰n siis <  ja > merkintˆj‰.
					if (compareToResult > 0) {
						//lista1Arvo on suurempi kuin lista2Arvo
						unionOfTwo.add(lista2Arvo);
						duplikaatinPoistajaIteraattoriL2.remove();
						duplikaatinPoistajaIteraattoriL1.previous();
					} else {
						if (compareToResult < 0) {
							//lista1Arvo on pienempi kuin lista2Arvo
							unionOfTwo.add(lista1Arvo);
							duplikaatinPoistajaIteraattoriL1.remove();
							duplikaatinPoistajaIteraattoriL2.previous();
						}
					}
				}
		}
    		//Lis‰t‰‰n lopuksi suurempi tapauksista yhdisteeseen
    		Integer sizeOf1 = L1.size(); //Collection-luokan menetelm‰t tallentavat size-tiedon variableen
    		//eli t‰m‰ lause on vakioaikainen O(1)
    		//public int size() {
    		//return size;
    		//}
    		Integer sizeof2 = L2.size();
    		//O(n) pidemm‰n tapauksen mukaan
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
    		//Vakioaikainen operaatio
			return unionOfTwo;
    }
}