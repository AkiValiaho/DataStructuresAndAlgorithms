package TRA1.Trees;
// BinPuuEsim.java SJ

import java.util.*;

import fi.joensuu.cs.tra.*;

public class traI_14_t27_30_pohja {

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
            System.out.print(x + " ");
            inorderInsert(puu, x);
        }
        System.out.println();

        System.out.println("Sisäjärjestyksessä:");
        inorderPrint(puu);

        for (int i = 0; i < 16; i++) {
            System.out.println("Onko " + i + " : " +
                               inorderMember(puu, new Integer(i)));
        }

        puu = exampleBTree();

        System.out.println("Sisajarjestyksessa:");
        inorderPrint(puu);

        System.out.println("Läpikäynti sisajarjestyksessa:");
        // testataan läpikäyntiä
        // etsitään ensimmäinen solmu
        BTreeNode n = inorderFirst(puu);
        while (n != null) {
            System.out.print(n.getElement() + " ");
            n = inorderNext(n);
        }
        System.out.println();

        System.out.println("Korkeus = " + korkeus(puu));


        n = matalin(puu);
        if (n == null)
            System.out.println("Matalin = null");
        else
            System.out.println("Matalin = " + n.getElement());


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
     * 27) Kirjoita algoritmi, joka lisää sisäjärjestyksessä olevaan 
     * binääripuuhun uuden solmun siten, että puu säilyy sisäjärjestyksessä. 
     * Jos samannimiöinen solmu oli jo puussa, niin solmua ei lisätä puuhun. 
     * Parametrina puu ja alkio, algoritmi luo uuden solmun jos lisäys tehdään. 
     * Algoritmin toiminta käytiin läpi luennolla. Aikavaativuus?
     * @param T binääripuu
     * @param x lisättävä alkio
     * @return tehtiinkö lisäys vai ei
     */
    public static <E extends Comparable<? super E>> 
        boolean inorderInsert(BTree<E> T, E x) {
    		if (T.getRoot() != null) {
    			Boolean added = inorderInsertAlgorihm(T.getRoot(), x);
    			if (added) {
					return true;
				}
			} else {
				T.setRoot(new BTreeNode<E>(x));
			}
            return false;
        } // inorderInsert()
    private static <E extends Comparable<? super E>> boolean inorderInsertAlgorihm(BTreeNode<E> T, E x) {
    	//Onko pienempi kuin node
    	Boolean addedOrNot = false;
    	if (x.compareTo(T.getElement()) < 0) {
			if (T.getLeftChild()!= null) {
    			addedOrNot =inorderInsertAlgorihm(T.getLeftChild(), x);
			} else {
				T.setLeftChild(new BTreeNode<E>(x));
				return true;
			}
		}
    	if (x.compareTo(T.getElement())> 0) {
    		if (T.getRightChild()!= null) {
    			addedOrNot = inorderInsertAlgorihm(T.getRightChild(), x);
			} else {
				T.setRightChild(new BTreeNode<E>(x));
				return true;
			}
		}
    	return addedOrNot;
    }
    /**
     * 28) Kirjoita operaatio inorderNext() joka saa parametrinaan binääripuun
     * solmun ja joka palauttaa ko.  solmun seuraajasolmun sisäjärjestyksessä.
     * Algoritmi ei tutki solmujen sisältöä, vain puun raken- netta. Solmun
     * seuraaja on oikean lapsen vasemmanpuoleisin jälkeläinen, tai, jollei
     * oikeaa lasta ole, niin se esivanhempi jonka vasemmassa alipuussa
     * alkuperäinen solmu oli. Jollei edeltäjää ole, inorderNext() palauttaa
     * null. Ota pohja www-sivulta. Algoritmin toiminta käytiin läpi
     * luennolla.  Aikavaativuus? Tätä toistuvasti kutsuen voidaan käydä
     * binääripuun alkiot läpi sisäjärjestyksessä.  Mikä on koko läpikäynnin
     * aikavaativuus?
     * @param <E>
     **/

    public static <E> BTreeNode<E> inorderNext(BTreeNode<E> n) {
        // jos solmulla on oikea lapsi, haetaan sen
        // vasemmanpuoleinen jalkelainen
    	if (n.getRightChild() != null) {
    		BTreeNode<E> toReturn = n.getRightChild();
    		while (toReturn.getLeftChild() != null) {
				toReturn = toReturn.getLeftChild();
			}
    		return toReturn;
		} else {
			BTreeNode<E> parent = n.getParent();
			BTreeNode<E> tmpParent = n;
			while (parent.getRightChild().equals(tmpParent)) {
				tmpParent = parent;
				parent = parent.getParent();
				if (parent == null) {
					break;
				}
			}
			return parent;
		}
        // muutoin haetaan ensimmainen esivanhempi jonka
        // vasemmassa alipuussa solmu n on

        // jollei loytynyt

    } // inorderNext()


    // sisäjärjestyksessä ensimmänen solmu
    public static BTreeNode inorderFirst(BTree T) {

        BTreeNode n = T.getRoot();
        if (n == null)
            return null;
        while (n.getLeftChild() != null)
            n = n.getLeftChild();
        return n;

    }

    /**
     * 29) Kirjoita algoritmi joka laskee annetun binääripuun korkeuden, ts.
     * pisimmän polun juuresta lehtisolmuun. Aikavaativuus? Vihje: rekursio.
     **/
    public static int korkeus(BTree t) {
        Integer korkeus = Math.max(korkeus(t.getRoot().getRightChild()), korkeus(t.getRoot().getLeftChild()));
    	return korkeus+1; 
    }
    // solmun korkeus
    public static int korkeus(BTreeNode n) {
    	if (n == null) {
			return -1;
		}
    	return Math.max(korkeus(n.getLeftChild()), korkeus(n.getRightChild())) +1;
    }
    /**
     * 30. Kirjoita algoritmi joka etsii binääripuun matalimman (vähiten syvän)
     * lehtisolmun. Aikavaativuus?
     * @param T
     * @param x
     * @return
     */

    public static BTreeNode matalin(BTree T) {
    	BTreeNode root = T.getRoot();
    	Queue<BTreeNode> tasoittainenJono = new LinkedList<BTreeNode>();
    	tasoittainenJono.add(root);
    	while (!tasoittainenJono.isEmpty()) {
    		//Poistetaan node jonosta->ensimmäiseksi lisätty...
    		//Katsotaan onko lapsia, jos ei ole niin kyseessä on selkeästi tapaus, jolla ei ole lapsia eli vähiten syvin
    		//lehtisolmu
    		BTreeNode node = tasoittainenJono.poll();
    		if (node.getLeftChild() != null) {
				tasoittainenJono.add(node.getLeftChild());
			}
			if (node.getRightChild() != null) {
				tasoittainenJono.add(node.getRightChild());
			}
			if (node.getLeftChild() == null && node.getRightChild() == null) {
				return node;
			}
    		
		}
		return null;
    } // matalin()



    // -------------------------------
    // esimerkkejä

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


    // Tulostus sis�j�rjestyksess� rekursiivisesti
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


} // class traI_14_t27_30_pohja
