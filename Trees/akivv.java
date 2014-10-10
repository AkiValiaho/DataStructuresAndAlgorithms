package TRA1.Trees;
import java.util.*;

import fi.joensuu.cs.tra.*;

public class akivv {
    public static void main(String[] args) {
        BTree<Integer> puu = new BTree<Integer>();
        int N = 10;
        if (args.length > 0)
            N = Integer.valueOf(args[0]);
        System.out.println("Puuhun:");
        Random r = new Random(42);
        Integer x = new Integer(0);
        for (int i = 0; i < N; i++) {
            x = r.nextInt(N*2);
            if (N <= 20)
                System.out.print(x + " ");
            inorderInsert(puu, x);
        }
        System.out.println();

        if (N <= 20) {
            System.out.println("Sis‰j‰rjestyksess‰:");
            inorderPrint(puu);
        }

        // puu = exampleBTree();

        System.out.println("L‰pik‰ynti sisajarjestyksessa:");
        int count = 0;
        // testataan l‰pik‰ynti‰
        // etsit‰‰n ensimm‰inen solmu
        BTreeNode<Integer> n = inorderFirst(puu);
        while (n != null) {
            count++;
            if (count <= 20)
                System.out.print(n.getElement() + " ");
            n = inorderNext(n);
        }
        System.out.println("    " + count + " kappaletta");

        System.out.println("Korkeus = " + korkeus(puu));

        // puusta taulukko
        ArrayList<Integer> AL = inorderTreeToArray(puu);
        if (N <= 20)
            System.out.println("Listana: " + AL);
        else
            System.out.println("Listassa: " + AL.size() + " alkiota");

        // taulukosta puu
        BTree<Integer> puu2 = arrayToInorderTree(AL);

        System.out.println("L‰pik‰ynti sisajarjestyksessa:");
        count = 0;
        // testataan l‰pik‰ynti‰
        // etsit‰‰n ensimm‰inen solmu
        n = inorderFirst(puu2);
        while (n != null) {
            count++;
            if (count <= 20)
                System.out.print(n.getElement() + " ");
            n = inorderNext(n);
        }
        System.out.println("    " + count + " kappaletta");

        System.out.println("Korkeus = " + korkeus(puu2));

        for (int i = 0; i < 16; i++) {
            System.out.println("Onko " + i + " : " +
                               inorderMember(puu2, new Integer(i)));
        }

        System.out.println("Onko sis‰j‰rjestetty: " + isInorder(puu));


    } // main()



    public static BTree<Integer> exampleBTree() {

        BTree<Integer> T = new BTree<Integer>();

        // juuri
        T.setRoot(new BTreeNode<Integer>(10));

        BTreeNode<Integer> n = T.getRoot();

        // juuren lapset
        n.setLeftChild(new BTreeNode<Integer>(5));
        n.setRightChild(new BTreeNode<Integer>(15));

        // vasen haara
        BTreeNode<Integer> l = n.getLeftChild();

        l.setLeftChild(new BTreeNode<Integer>(3));
        l.setRightChild(new BTreeNode<Integer>(8));

        l.getLeftChild().setRightChild(new BTreeNode<Integer>(4));

        // oikea haara
        l = n.getRightChild();

        l.setLeftChild(new BTreeNode<Integer>(12));
        l.setRightChild(new BTreeNode<Integer>(18));

        l.getLeftChild().setLeftChild(new BTreeNode<Integer>(11));
        l.getLeftChild().setRightChild(new BTreeNode<Integer>(13));


        System.out.println("                 ");
        System.out.println("       10        ");
        System.out.println("    __/  \\__     ");
        System.out.println("   5        15   ");
        System.out.println("  / \\      /  \\  ");
        System.out.println(" 3   8   12    18");
        System.out.println("  \\      /\\      ");
        System.out.println("   4    11 13    ");
        System.out.println("                 ");

        return T;

    } // exampleBTree()


//    /**
//      * X2a: bin‰‰ripuusta ArrayList, wrapper-osa
//      * T‰ss‰ wrapperimetodi algoritmille. Tarvitaan, koska rekursion
//      * toteuttaminen olisi muuten hankalaa. Tarkastetaan samalla wrapperissa onko
//      * kyseess‰ tyhj‰ puu ettei tarvitse turhaan l‰hetell‰ algoritmiosaan.
//      * Tehd‰‰n wrapperissa myˆs palautettava ArrayListi, jonka viittaus sitten passataan
//      * algoritmiosaan. Tiedostosta lˆytyy lopusta minun kirjoittamani l‰p‰istyt unittestit
//      * t‰lle algoritmille.
//      * Aikavaativuus on lineaarinen, sill‰ syˆtekoosta riippuva osa on yksinkertainen rekursio,
//      * joka k‰yt‰nnˆss‰ k‰y l‰pi kaikki alkiot puusta-> O(n)
//      * Algoritmia voisi ehk‰ viel‰ hioa j‰rkev‰mm‰ksi siten, ett‰ se tutkisi onko 
//      * argumenttina annettu puu sis‰j‰rjestyksess‰ (mik‰ oletetaan teht‰v‰nannon perusteella, mutta
//      * tosiel‰m‰ss‰ lienee parasta ajatella aina pahinta tapausta) ja onko se tasapainoinen ylim‰‰r‰isten
//      * rekursiotapausten v‰ltt‰miseksi. Rekursio vie kuitenkin huonossa tapauksessa paljon muistia, kun muuttujia
//      * pit‰‰ pit‰‰ muistissa.
//      * 
//      * Kaikenkaikkiaan teht‰v‰n kanssa sai hieman n‰hd‰ vaivaa, koska designi ei ollut itsest‰‰nselv‰.
//      * Suunnitelmaa piti hieman hahmotella kyn‰ll‰ ja paperilla ja suoraan koodaamaan-ratkaisu olisi varmasti
//      * tuottanut huonomman tuloksen.
//      * Erityisen tyytyv‰inen olen siihen, ett‰ sain hyv‰n syyn harjoitella JUnitin k‰yttˆ‰.
//      * @param T Listaksi muutettava bin‰‰ripuu
//      **/
    public static <E extends Comparable<? super E>> ArrayList<E> inorderTreeToArray(BTree<E> T) {
    	//Tarkastetaan ensin onko kyseess‰ null-tree
    	if (T == null) {
			return null;
		}
    	//Tarkastetaan sitten onko T:ss‰ yht‰‰n mit‰‰n, null != tyhj‰ puu
    	if (T.getRoot() == null) {
			return null;
		}
    	ArrayList<E> L = new ArrayList<>();
    	inorderTreeToArray(T.getRoot(),L);
    	return L;
    }
    
//    /**
//     * X2a: bin‰‰ripuusta taulukko: algoritmi-osa
//     * Algoritmiosa voidaan suosiolla asettaa privaatiksi. Sen ei tarvitse n‰ky‰ k‰ytt‰j‰lle,
//     * vaan wrapper-rajapinta hoitaa k‰ytt‰j‰n kanssa toimimisen.
//     * @param bTreeNode
//     * @param l
//     */
    private static <E> void inorderTreeToArray(BTreeNode<E> bTreeNode, ArrayList<E> l) {
    	if (bTreeNode == null) {
    		return;
			}
    	inorderTreeToArray(bTreeNode.getLeftChild(),l);
    	l.add(bTreeNode.getElement());
    	inorderTreeToArray(bTreeNode.getRightChild(),l);
    }

//    /**
//      * X2b: taulukosta bin‰‰ripuu
//      * Wrapper-metodi hoitaa null- ja tyhj‰tarkastukset
//      * sek‰ paloittelee listan ensimm‰isiin rekursoitaviin
//      * alipuihin. Tiedostosta lˆytyy lopusta minun kirjoittamani l‰p‰istyt unittestit
//      * t‰lle algoritmille. Aikavaativuus on lineaarinen, sill‰ rekursio-osat k‰yv‰t l‰pi
//      * koko puun. Jokaiselta rekursioinstanssilta palautetaan jokin alkio. 
//      * T‰t‰ algoritmia voisi hioa miettim‰ll‰ hieman puun kasaamiseen liittyv‰n mekanismin toimintaa.
//      * AVL-puurakenteen avulla t‰st‰ algoritmista saataisiin tasapainoisempi puu ulos. Tasapainoisemmalla
//      * puulla rootista nodeen liikkumiseen tarvittavien askelten keskiarvo pienenee
//      */
    public static <E extends Comparable<? super E>> BTree<E> arrayToInorderTree(ArrayList<E> L) {
    	//Tarkastetaan onko arrayList nulli tai tyhj‰! Kaksi eri asiaa
    	if (L == null || L.isEmpty()) {
    		return null;
    	}
    	//K‰ytet‰‰n t‰ss‰ vaiheessa hyv‰ksi ArrayListin suomaa random
    	//accessia ja haetaan listan keskimm‰inen 
    	//alkio talteen. Bitshiftataan
    	//ykkˆsell‰ jolloin size puolittuu j‰rkev‰sti.
    	int indexForMiddle = L.size()>> 1;
		E keskimmainenNodeHaettu = L.get(indexForMiddle);
    	//Luodaan palautettava puu
    	BTree<E> T = new BTree<>();
    	//Asetetaan listan keskimm‰inen node uudeksi rootiksi puulle
    	T.setRoot(new BTreeNode<E>(keskimmainenNodeHaettu));
    	//Asetetaan vasemmaksi lapseksi vasemmasta alilistasta keskimm‰inen ja rekursoidaan eteenp‰in
    	//vasemmassa alilistassa suurimman alkion indeksi on keskimm‰isen indeksi miinus 1. Ensimm‰inen
    	//alkio on tietysti alkio indeksill‰ 0. List.sublist() palauttaa VIEWin alkuper‰iseen arrayListiin
    	//tietyll‰ alkuindeksill‰ ja loppuindeksill‰ + tarvittavalla offsetill‰ alkuper‰iseen listaan n‰hden.
    	if (L.size() > 1) {
    		T.getRoot().setLeftChild(arrayToInorderTreeAlgorithm(L.subList(0, indexForMiddle)));	
		}
    	//Asetetaan oikeaksi lapseksi oikeasta alilistasta keskimm‰inen ja rekursoidaan eteenp‰in.
    	//Oikeassa alilistassa suurimman alkion indeksi taas on listan koko. Viimeinen alkio lˆytyy
    	//keskimm‰isen alkion seuraavasta lokerosta
    	if (L.size() > 2) {
    		T.getRoot().setRightChild(arrayToInorderTreeAlgorithm(L.subList(indexForMiddle+1,L.size())));	
		}
    	//Palautetaan valmis puu takaisin
    	return T;
    }
//    	/**
//    	 * Algoritmi-osuus etsii
//    	 * aina keskimm‰isen alkion alilistasta ja 
//    	 * rekursoi seuraavat alilistat alasp‰in joista etsit‰‰n
//    	 * vastaavasti keskimm‰inen tapaus
//    	 * @return
//    	 */
    private static <E extends Comparable<? super E>> BTreeNode<E> arrayToInorderTreeAlgorithm(List<E> subList)  {
    	Integer listanKeskelle;
    	//List.size() on vakioaikaista palauttamista tallennetusta variablesta.
    	listanKeskelle = subList.size()/2;
    	int listanKoko = subList.size();
    	if (listanKoko == 0) {
    		return null;
    	}
    	BTreeNode<E> palautettavaAlkio = new BTreeNode<E>(subList.get(listanKeskelle));
    	//Tarkastetan voidaanko v‰ltt‰‰ rekursio
    	if (listanKoko == 1) {
    		return palautettavaAlkio;
    	}
    	//Samaan tapaan kuin wrapperissa, vied‰‰n alilistoja rekursion pohjalle
    	BTreeNode<E> leftChild = arrayToInorderTreeAlgorithm(subList.subList(0, listanKeskelle));
    	if (leftChild != null) {
    		palautettavaAlkio.setLeftChild(leftChild);
    	}
    	//V‰ltet‰‰n yksi rekursio, jos listan koko ei ole kolmonen niin eih‰n siell‰
    	//ole mit‰‰n lis‰tt‰v‰‰ oikeaksi lapseksi
    	if (listanKoko == 2) {
			return palautettavaAlkio;
		}
    	BTreeNode<E> rightChild = arrayToInorderTreeAlgorithm(subList.subList(listanKeskelle+1, listanKoko));
    	if (rightChild != null) {
    		palautettavaAlkio.setRightChild(rightChild);
    	}
    	return palautettavaAlkio;
    }
    //Teht‰v‰ 33
    public static <E extends Comparable<? super E>> Boolean isInorder(BTree<E> tarkastettavaPuu) {
     	if (tarkastettavaPuu.getRoot() == null) {
			return true;
		}
    	LinkedList<E> minneLisataan = new LinkedList<>();
    	//Tarkastetaan onko linkedlist j‰rjestyksess‰
    	onkoSisaJarjestysessaAlgoritmi(tarkastettavaPuu.getRoot(), minneLisataan);
    	for (ListIterator<E> iterator = minneLisataan.listIterator(); iterator.hasNext();) {
			E x1 =iterator.next();
			if (!iterator.hasNext()) {
				break;
			}
			E x2 = iterator.next();
			if (x1.compareTo(x2) > 0) {
				return false;
			}
			iterator.previous();
		}
    	return true;
    }
    private static <E> void onkoSisaJarjestysessaAlgoritmi(BTreeNode<E> node,LinkedList<E> minneLisataan) {
    	//Traversataan puu l‰pi
    	if (node == null) {
			return;
		}
    	onkoSisaJarjestysessaAlgoritmi(node.getLeftChild(), minneLisataan);
    	minneLisataan.add(node.getElement());
    	onkoSisaJarjestysessaAlgoritmi(node.getRightChild(), minneLisataan);
    }
    // --------
    // viikon 5 teht‰vien ratkaisuja X2:n testausta varten
//
//    /**
//     * 27) Kirjoita algoritmi, joka lis‰‰ sis‰j‰rjestyksess‰ olevaan 
//     * bin‰‰ripuuhun uuden solmun siten, ett‰ puu s‰ilyy sis‰j‰rjestyksess‰. 
//     * Jos samannimiˆinen solmu oli jo puussa, niin solmua ei lis‰t‰ puuhun. 
//     * Parametrina puu ja alkio, algoritmi luo uuden solmun jos lis‰ys tehd‰‰n. 
//     * Algoritmin toiminta k‰ytiin l‰pi luennolla. Aikavaativuus?
//     * @param T bin‰‰ripuu
//     * @param x lis‰tt‰v‰ alkio
//     * @return tehtiinkˆ lis‰ys vai ei
//     */
    public static <E extends Comparable<? super E>> 
        boolean inorderInsert(BTree<E> T, E x) {
            BTreeNode<E> n = T.getRoot();
            if (n == null) {
                T.setRoot(new BTreeNode<E>(x));
                return true;
            }

            while (true) {

                if (x.compareTo(n.getElement()) == 0)
                    // x jo puussa, eli ei lis‰t‰
                    return false;

                else if (x.compareTo(n.getElement()) < 0) {
                    // x edelt‰‰ n:n alkiota
                    if (n.getLeftChild() == null) {
                        // lis‰yskohta lˆydetty
                        n.setLeftChild(new BTreeNode<E>(x));
                        return true;
                    } else
                        n = n.getLeftChild();
                } else {
                    // x suurempi kuin n
                    if (n.getRightChild() == null) {
                        // lis‰yskohta lˆydetty
                        n.setRightChild(new BTreeNode<E>(x));
                        return true;
                    } else
                        n = n.getRightChild();
                }
            } // while

        } // inorderInsert4()

//    /**
//     * 28) Kirjoita operaatio inorderNext() joka saa parametrinaan bin‰‰ripuun
//     * solmun ja joka palauttaa ko.  solmun seuraajasolmun sis‰j‰rjestyksess‰.
//     * Algoritmi ei tutki solmujen sis‰ltˆ‰, vain puun raken- netta. Solmun
//     * seuraaja on oikean lapsen vasemmanpuoleisin j‰lkel‰inen, tai, jollei
//     * oikeaa lasta ole, niin se esivanhempi jonka vasemmassa alipuussa
//     * alkuper‰inen solmu oli. Jollei edelt‰j‰‰ ole, inorderNext() palauttaa
//     * null. Ota pohja www-sivulta. Algoritmin toiminta k‰ytiin l‰pi
//     * luennolla.  Aikavaativuus? T‰t‰ toistuvasti kutsuen voidaan k‰yd‰
//     * bin‰‰ripuun alkiot l‰pi sis‰j‰rjestyksess‰.  Mik‰ on koko l‰pik‰ynnin
//     * aikavaativuus?
//     **/

    public static <E> BTreeNode<E> inorderNext(BTreeNode<E> n) {

        // jos solmulla on oikea lapsi, haetaan sen
        // vasemmanpuoleinen jalkelainen
        if (n.getRightChild() != null) {
            BTreeNode<E> m = n.getRightChild();
            while (m.getLeftChild() != null)
                m = m.getLeftChild();
            return m;
        }

        // muutoin haetaan ensimmainen esivanhempi jonka
        // vasemmassa alipuussa solmu n on
        else {
            BTreeNode<E> m = n;
            BTreeNode<E> par = n.getParent();
            while (par != null) {
                if (par.getLeftChild() == m)
                    return par;
                else {
                    m = par;
                    par = par.getParent();
                }
            }
        }

        // jollei loytynyt
        return null;

    } // inorderNext4()


    // sis‰j‰rjestyksess‰ ensimm‰nen solmu
    public static <E> BTreeNode<E> inorderFirst(BTree<E> T) {
        BTreeNode<E> n = T.getRoot();
        if (n == null)
            return null;
        while (n.getLeftChild() != null)
            n = n.getLeftChild();
        return n;
    }
//    /**
//     * 29) Kirjoita algoritmi joka laskee annetun bin‰‰ripuun korkeuden, ts.
//     * pisimm‰n polun juuresta lehtisolmuun. Aikavaativuus? Vihje: rekursio.
//     **/
    public static int korkeus(BTree t) {
        return korkeus(t.getRoot());
    }   // korkeus4()


    public static int korkeus(BTreeNode n) {

        // null:n "korkeus" on -1 jotta lehtisolmun korkeus
        // on max(-1,-1)+1 = 0
        if (n == null)
            return -1;

        // solmun korkeus on maksimi lasten korkeuksista + 1
        return java.lang.Math.max(korkeus(n.getLeftChild()),
                                  korkeus(n.getRightChild())) + 1;
    }




    // -------------------------------
    // esimerkkej‰

    public static <E extends Comparable<? super E>>
        boolean inorderMember(BTree<E> T, E x) {
            BTreeNode<E> n = T.getRoot();

            while (n != null) {
                if (x.compareTo(n.getElement()) == 0)
                    return true;
                else if (x.compareTo(n.getElement()) < 0)
                    n = n.getLeftChild();
                else
                    n = n.getRightChild();
            } // while
            return false;

        } // inorderMember()


    // Tulostus sis‰j‰rjestyksess‰ rekursiivisesti
    public static void inorderPrint(BTree T) {
        inorderPrintBranch(T.getRoot());
        System.out.println();
    } // inorderPrint()


    public static void inorderPrintBranch(BTreeNode n) {
        if (n == null)
            return;

        inorderPrintBranch(n.getLeftChild());
        System.out.print(n.getElement() + " ");
        inorderPrintBranch(n.getRightChild());

    } // inorderPrintBranch()


} // class traI_14_x2_pohja


//
//import static org.junit.Assert.*;
//
//import java.util.*;
//
//import org.junit.*;
//
//import fi.joensuu.cs.tra.*;
//
///**
// * 
// */
//
///**
// * L‰p‰istyt unitTestit algoritmille
// * @author Aki
// * @param <E>
// *
// */
//public class traI_14_x2_pohjaTest<E extends Comparable<? super E>> {
//	private akivv x2Pohja;
//	/**
//	 * @throws java.lang.Exception
//	 */
//	@Before
//	public void setUp() throws Exception {
//		this.x2Pohja = new akivv();
//	}
//	/**
//	 * Test method for {@link akivv#inorderTreeToArray(fi.joensuu.cs.tra.BTree)}.
//	 */
//	@Test
//	public void testInorderTreeToArrayWithEmptyTree() {
//		ArrayList<E> tyhjaArrayListi;
//		tyhjaArrayListi = akivv.inorderTreeToArray(new BTree<E>());
//		assertNull(tyhjaArrayListi);
//		
//	}
//	@Test
//	public void testInorderTreeToArrayWithNullTree() {
//		ArrayList<E> tyhjaArrayList;
//		BTree<E> nullTree = null;
//		tyhjaArrayList = akivv.inorderTreeToArray(nullTree);
//		assertNull(tyhjaArrayList);
//	}
//	
//	@Test
//	public void testInorderTreeToArrayWithOneNode() {
//		ArrayList<Integer> tyhjaArrayListi;
//		BTree<Integer> T = new BTree<Integer>();
//		BTreeNode<Integer> uusiNode = new BTreeNode<Integer>(1);
//		T.setRoot(uusiNode);
//		tyhjaArrayListi = akivv.inorderTreeToArray(T);
//		ArrayList<Integer> wanted = new ArrayList<>();
//		wanted.add(1);
//		assertArrayEquals(wanted.toArray(), tyhjaArrayListi.toArray());
//	}
//	@Test
//	public void testInorderTreeToArrayWithThreeNodesSimpleBuildToTheLeft() {
//		ArrayList<Integer> tyhjaArrayListi;
//		BTree<Integer> T = new BTree<Integer>();
//		BTreeNode<Integer> uusiNode = new BTreeNode<Integer>(10);
//		T.setRoot(uusiNode);
//		T.getRoot().setLeftChild(new BTreeNode<Integer>(7));
//		T.getRoot().getLeftChild().setLeftChild(new BTreeNode<Integer>(6));
//		tyhjaArrayListi = akivv.inorderTreeToArray(T);
//		ArrayList<Integer> wanted = new ArrayList<>();
//		wanted.add(6);
//		wanted.add(7);
//		wanted.add(10);
//		assertArrayEquals(wanted.toArray(), tyhjaArrayListi.toArray());
//	}
//	@Test
//	public void testInorderTreeToArrayWithThreeNodesSimpleBuildToTheRight() {
//		ArrayList<Integer> tyhjaArrayListi;
//		BTree<Integer> T = new BTree<Integer>();
//		BTreeNode<Integer> uusiNode = new BTreeNode<Integer>(10);
//		T.setRoot(uusiNode);
//		T.getRoot().setRightChild(new BTreeNode<Integer>(11));
//		T.getRoot().getRightChild().setRightChild(new BTreeNode<Integer>(12));
//		tyhjaArrayListi = akivv.inorderTreeToArray(T);
//		ArrayList<Integer> wanted = new ArrayList<>();
//		wanted.add(10);
//		wanted.add(11);
//		wanted.add(12);
//		assertArrayEquals(wanted.toArray(), tyhjaArrayListi.toArray());
//	}
//	@Test
//	public void testInorderTreeToArrayWithThreeBalancedNodes() {
//		ArrayList<Integer> tyhjaArrayListi;
//		BTree<Integer> T = new BTree<Integer>();
//		BTreeNode<Integer> uusiNode = new BTreeNode<Integer>(10);
//		T.setRoot(uusiNode);
//		T.getRoot().setRightChild(new BTreeNode<Integer>(11));
//		T.getRoot().setLeftChild(new BTreeNode<Integer>(9));
//		tyhjaArrayListi = akivv.inorderTreeToArray(T);
//		ArrayList<Integer> wanted = new ArrayList<>();
//		wanted.add(9);
//		wanted.add(10);
//		wanted.add(11);
//		assertArrayEquals(wanted.toArray(), tyhjaArrayListi.toArray());
//	}
//	@Test
//	public void testInorderTreeToArrayWithXBalancedNodes() {
//		for (int i = 0; i < 10000; i++) {
//			ArrayList<Integer> tyhjaArrayListi = new ArrayList<>();
//			Random ran = new Random();
//			/*    Pseudosatunnainen integer rangella [min,max],
//			    ekslusiivinen topvaluen suhteen joten lis‰t‰‰n 1
//			    ett‰ oikea max saadaan, esim. (max = 6, viimeinen
//			      saatava luku = 5). Pelkk‰ nextInt antaa siis rangen
//			    0-(arg-1)*/
//			int luku = ran.nextInt(1000) + 1;
//			for (int j = 0; j < luku; j++) {
//				tyhjaArrayListi.add(ran.nextInt(1000)+10);
//			}
//			Collections.sort(tyhjaArrayListi);
//			ArrayList<Integer> wanted = akivv.inorderTreeToArray(akivv.arrayToInorderTree(tyhjaArrayListi));
//			assertArrayEquals(wanted.toArray(), tyhjaArrayListi.toArray());
//		}
//	}
//	/**
//	 * Test method for {@link akivv#arrayToInorderTree(java.util.ArrayList)}.
//	 */
//	@Test
//	public void testArrayToInorderTreeNullArray() {
//		ArrayList<Integer> tyhjaArrayListi = null;
//		BTree<Integer> palautettavaTree = akivv.arrayToInorderTree(tyhjaArrayListi);
//		assertNull(palautettavaTree);
//		
//	}
//	@Test
//	public void testArrayToInorderTreeEmptyArray() {
//		ArrayList<Integer> tyhjaArrayListi = new ArrayList<>();
//		BTree<Integer> palautettavaTree = akivv.arrayToInorderTree(tyhjaArrayListi);
//		assertNull(palautettavaTree);
//		
//	}
//	@Test
//	public void testArrayToInorderTree10Elements() {
//		Integer[] required = {3, 4, 5, 8, 10, 13, 18, 19,20,100000};
//		ArrayList<Integer> requiredArrayList = new ArrayList<>();
//		for (int j = 0; j < required.length; j++) {
//			requiredArrayList.add(required[j]);
//		}
//		
//		int i;
//		BTree<Integer> palautettavaTree = akivv.arrayToInorderTree(requiredArrayList);
//		ArrayList<Integer> output = akivv.inorderTreeToArray(palautettavaTree);
//	
//		assertEquals(requiredArrayList, output);
//		Boolean sisajarjestys =akivv.isInorder(palautettavaTree);
//		assertTrue(sisajarjestys);
//		//BTreePrinter asdf = new BTreePrinter();
//		//BTreePrinter.printNode(palautettavaTree.getRoot());
//		
//	}
//	@Test
//	public void testArrayToInorderTreeOneElement() {
//		Integer[] required = {3};
//		ArrayList<Integer> requiredArrayList = new ArrayList<>();
//		for (int j = 0; j < required.length; j++) {
//			requiredArrayList.add(required[j]);
//		}
//		
//		int i;
//		BTree<Integer> palautettavaTree = akivv.arrayToInorderTree(requiredArrayList);
//		ArrayList<Integer> output = akivv.inorderTreeToArray(palautettavaTree);
//	
//		assertEquals(requiredArrayList, output);
//		Boolean sisajarjestys =akivv.isInorder(palautettavaTree);
//		assertTrue(sisajarjestys);
////		BTreePrinter asdf = new BTreePrinter();
////		BTreePrinter.printNode(palautettavaTree.getRoot());
//		
//	}
//	@Test
//	public void testArrayToInorderTreeTwoElements() {
//		Integer[] required = {3,4};
//		ArrayList<Integer> requiredArrayList = new ArrayList<>();
//		for (int j = 0; j < required.length; j++) {
//			requiredArrayList.add(required[j]);
//		}
//		
//		int i;
//		BTree<Integer> palautettavaTree = akivv.arrayToInorderTree(requiredArrayList);
//		ArrayList<Integer> output = akivv.inorderTreeToArray(palautettavaTree);
//	
//		assertEquals(requiredArrayList, output);
//		Boolean sisajarjestys =akivv.isInorder(palautettavaTree);
//		assertTrue(sisajarjestys);
////		BTreePrinter asdf = new BTreePrinter();
////		BTreePrinter.printNode(palautettavaTree.getRoot());
//		
//	}
//	@Test
//	public void testArrayToInorderTreeThreeElements() {
//		Integer[] required = {3,4,5};
//		ArrayList<Integer> requiredArrayList = new ArrayList<>();
//		for (int j = 0; j < required.length; j++) {
//			requiredArrayList.add(required[j]);
//		}
//		int i;
//		BTree<Integer> palautettavaTree = akivv.arrayToInorderTree(requiredArrayList);
//		ArrayList<Integer> output = akivv.inorderTreeToArray(palautettavaTree);
//		assertEquals(requiredArrayList, output);
//		Boolean sisajarjestys =akivv.isInorder(palautettavaTree);
//		assertTrue(sisajarjestys);
////		BTreePrinter asdf = new BTreePrinter();
////		BTreePrinter.printNode(palautettavaTree.getRoot());
//	}
//	@Test
//	public void testArrayToInorderTreeXElements() {
//		for (int i = 0; i < 10000; i++) {
//			ArrayList<Integer> requiredArrayList = new ArrayList<>();
//			Random ran = new Random();
//			/*    Pseudosatunnainen integer rangella [min,max],
//			    ekslusiivinen topvaluen suhteen joten lis‰t‰‰n 1
//			    ett‰ oikea max saadaan, esim. (max = 6, viimeinen
//			      saatava luku = 5). Pelkk‰ nextInt antaa siis rangen
//			    0-(arg-1)*/
//			int luku = ran.nextInt(1000) + 1;
//			for (int j = 0; j < luku; j++) {
//				requiredArrayList.add(ran.nextInt(1000)+1);
//			}
//			Collections.sort(requiredArrayList);
//			BTree<Integer> palautettavaTree = akivv.arrayToInorderTree(requiredArrayList);
//			ArrayList<Integer> output = akivv.inorderTreeToArray(palautettavaTree);
//			assertEquals(requiredArrayList, output);
//			Boolean sisajarjestys =akivv.isInorder(palautettavaTree);
//			assertTrue(sisajarjestys);
////			BTreePrinter asdf = new BTreePrinter();
////			BTreePrinter.printNode(palautettavaTree.getRoot());
////			//Printataan nodejen m‰‰r‰->
////			System.out.println("\n");
////			System.out.println("\n");
////			System.out.println(output.size());
////			System.out.println("\n");
////			System.out.println(traI_14_t27_30_pohja.korkeus(palautettavaTree));
//			//Tarkastetaan viel‰ onko korkeus varmasti logaritminen
//			assertTrue(Math.log10(output.size())/Math.log10(2)<traI_14_t27_30_pohja.korkeus(palautettavaTree)+1);
//		}
//		}
//		
//
//}
