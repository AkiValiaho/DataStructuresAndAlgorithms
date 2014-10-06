package TRA1.Trees;
// traI_14_x2_pohja.java SJ
// runko ja testiohjelma teht‰viin X2 ja 33

import java.util.*;

import fi.joensuu.cs.tra.*;

public class traI_14_x2_pohja {

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


    /**
      * X2a: bin‰‰ripuusta taulukko, wrapper-osa
      * T‰ss‰ wrapperimetodi algoritmille. Tarvitaan, koska rekursion
      * toteuttaminen olisi muuten hankalaa. Tarkastetaan samalla wrapperissa onko
      * kyseess‰ tyhj‰ puu ettei tarvitse turhaan l‰hetell‰ algoritmiosaan.
      * Tehd‰‰n wrapperissa myˆs palautettava ArrayListi, jonka viittaus sitten passataan
      * algoritmiosaan.
      * @param T Listaksi muutettava bin‰‰ripuu
      **/
    public static <E extends Comparable<? super E>>
                ArrayList<E> inorderTreeToArray(BTree<E> T) {
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
    
    /**
     * X2a: bin‰‰ripuusta taulukko: algoritmi-osa
     * Algoritmiosa voidaan suosiolla asettaa privaatiksi. Sen ei tarvitse n‰ky‰ k‰ytt‰j‰lle,
     * vaan wrapper-rajapinta hoitaa k‰ytt‰j‰n kanssa toimimisen.
     * @param bTreeNode
     * @param l
     */
    private static <E> void inorderTreeToArray(BTreeNode<E> bTreeNode, ArrayList<E> l) {
    	if (bTreeNode == null) {
    		return;
			}
    	inorderTreeToArray(bTreeNode.getLeftChild(),l);
    	l.add(bTreeNode.getElement());
    	inorderTreeToArray(bTreeNode.getRightChild(),l);
    }

    /**
      * X2b: taulukosta bin‰‰ripuu
      * Wrapper-metodi hoitaa null- ja tyhj‰tarkastukset
      * sek‰ paloittelee listan ensimm‰isiin rekursoitaviin
      * alipuihin
      */
    public static <E extends Comparable<? super E>>
    BTree<E> arrayToInorderTree(ArrayList<E> L) {
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
    	//alkio on tietysti alkio indeksill‰ 0.
    	T.getRoot().setLeftChild(arrayToInorderTreeAlgorithm(L.subList(0, indexForMiddle)));
    	//Asetetaan oikeaksi lapseksi oikeasta alilistasta keskimm‰inen ja rekursoidaan eteenp‰in.
    	//Oikeassa alilistassa suurimman alkion indeksi taas on listan koko -1. Viimeinen alkio lˆytyy
    	//keskimm‰isen alkion seuraavasta lokerosta
    	T.getRoot().setRightChild(arrayToInorderTreeAlgorithm(L.subList(indexForMiddle+1,L.size())));
    	//Palautetaan valmis puu takaisin
    	return T;
    }
    	/**
    	 * Algoritmi-osuus etsii
    	 * aina keskimm‰isen alkion alilistasta ja 
    	 * rekursoi seuraavat alilistat alasp‰in
    	 * @return
    	 */
    private static <E extends Comparable<? super E>>
    BTreeNode<E> arrayToInorderTreeAlgorithm(List<E> subList)  {
    	Integer listanKeskelle;
    	if (subList.size() % 2 != 0) {
    		listanKeskelle = subList.size()/2;
    	} else {
    		listanKeskelle = subList.size()/2;
    	}
    	int listanKoko = subList.size();
    	if (listanKoko == 0) {
    		return null;
    	}
    	BTreeNode<E> palautettavaAlkio = new BTreeNode<E>(subList.get(listanKeskelle));
    	if (listanKoko == 1) {
    		return palautettavaAlkio;
    	}
    	//Samaan tapaan kuin wrapperissa, vied‰‰n alilistoja rekursion pohjalle
    	BTreeNode<E> leftChild = arrayToInorderTreeAlgorithm(subList.subList(0, listanKeskelle));
    	if (leftChild != null) {
    		palautettavaAlkio.setLeftChild(leftChild);
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

    /**
     * 27) Kirjoita algoritmi, joka lis‰‰ sis‰j‰rjestyksess‰ olevaan 
     * bin‰‰ripuuhun uuden solmun siten, ett‰ puu s‰ilyy sis‰j‰rjestyksess‰. 
     * Jos samannimiˆinen solmu oli jo puussa, niin solmua ei lis‰t‰ puuhun. 
     * Parametrina puu ja alkio, algoritmi luo uuden solmun jos lis‰ys tehd‰‰n. 
     * Algoritmin toiminta k‰ytiin l‰pi luennolla. Aikavaativuus?
     * @param T bin‰‰ripuu
     * @param x lis‰tt‰v‰ alkio
     * @return tehtiinkˆ lis‰ys vai ei
     */
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

    /**
     * 28) Kirjoita operaatio inorderNext() joka saa parametrinaan bin‰‰ripuun
     * solmun ja joka palauttaa ko.  solmun seuraajasolmun sis‰j‰rjestyksess‰.
     * Algoritmi ei tutki solmujen sis‰ltˆ‰, vain puun raken- netta. Solmun
     * seuraaja on oikean lapsen vasemmanpuoleisin j‰lkel‰inen, tai, jollei
     * oikeaa lasta ole, niin se esivanhempi jonka vasemmassa alipuussa
     * alkuper‰inen solmu oli. Jollei edelt‰j‰‰ ole, inorderNext() palauttaa
     * null. Ota pohja www-sivulta. Algoritmin toiminta k‰ytiin l‰pi
     * luennolla.  Aikavaativuus? T‰t‰ toistuvasti kutsuen voidaan k‰yd‰
     * bin‰‰ripuun alkiot l‰pi sis‰j‰rjestyksess‰.  Mik‰ on koko l‰pik‰ynnin
     * aikavaativuus?
     **/

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




    /**
     * 29) Kirjoita algoritmi joka laskee annetun bin‰‰ripuun korkeuden, ts.
     * pisimm‰n polun juuresta lehtisolmuun. Aikavaativuus? Vihje: rekursio.
     **/
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
