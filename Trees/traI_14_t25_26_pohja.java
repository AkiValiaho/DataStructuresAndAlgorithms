package TRA1.Trees;

import fi.joensuu.cs.tra.*;

import java.util.*;


public class traI_14_t25_26_pohja<E> {
    public static void main(String[] args) {

        Tree<Integer> puu = exampleTree();

        System.out.println("Esijärjestyksessä:");
        preorderPrint(puu);
        

        System.out.println("Tasoittain:");
        printByLevel(puu);

        System.out.println("Haku");
        for (int i = 1; i < 16; i++) {
        	TreeNode<Integer> n = preorderSearch(puu, new Integer(i));
        	if (n != null)
        		System.out.println("Alkion " + i + " solmu: " + n + " arvo " +
                               n.getElement());
        	else
        		System.out.println("Alkiota " + i + " ei löydy");
            
        }
        ArrayList<Integer> satunnaisiaObjekteja = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
			satunnaisiaObjekteja.add(i);
		}

        

    } // main()

    // rakentaa pienen esijärjestetyn yleisen puun
    public static Tree<Integer> exampleTree() {
        Tree<Integer> puu = new Tree<Integer>();

        TreeNode<Integer> n, m, l = null;

        /* rakennetaan tällainen puu:

            1
          //\\
         /|  |\
        / |  | \
       2  6 10  14_____________
      / \ |  |  |  \  \  \  \  \
      3 5 8 11  15 16 17 18 19 20
             |
            12
        */

        // juuri
        n = new TreeNode<Integer>(1);
        puu.setRoot(n);

        // juuren vasemmanpuoleisin lapsi
        m  = new TreeNode<Integer>(2);
        n.setLeftChild(m);

        // juuren muut lapset
        for (int i = 6 ; i <= 14 ; i+=4) {
            l = new TreeNode<Integer>(i);
            m.setRightSibling(l);
            m = l;
        }

        // 2:n lapset
        m = puu.getRoot().getLeftChild();
        m.setLeftChild(new TreeNode<Integer>(3));
        m.getLeftChild().setRightSibling(new TreeNode<Integer>(5));

        // 6:n lapsi
        m = m.getRightSibling();
        m.setLeftChild(new TreeNode<Integer>(8));

        // 10:n lapsi
        m = m.getRightSibling();
        m.setLeftChild(new TreeNode<Integer>(11));
        // 10:n lapsenlapsi
        m = m.getLeftChild();
        m.setLeftChild(new TreeNode<Integer>(12));

        // 14:n lapset
        m = new TreeNode<Integer>(15);
        l.setLeftChild(m);
        for (int i = 16 ; i <= 20 ; i++) {
            l = new TreeNode<Integer>(i);
            m.setRightSibling(l);
            m = l;
        }

        System.out.println("        1");
        System.out.println("      //\\\\");
        System.out.println("     /|  |\\");
        System.out.println("    / |  | \\");
        System.out.println("   2  6 10  14_____________");
        System.out.println("  / \\ |  |  |  \\  \\  \\  \\  \\");
        System.out.println("  3 5 8 11  15 16 17 18 19 20");
        System.out.println("         |");
        System.out.println("        12");
        System.out.println();

        return puu;

    } // exampleTree()


    // Tulostus esijärjestyksessä rekursiivisesti
    public static void preorderPrint(Tree T) {
        if (T.getRoot() != null)
            preorderPrintBranch(T.getRoot());
        System.out.println();
    } // preorderPrint()


    public static void preorderPrintBranch(TreeNode n) {
        System.out.print(n.getElement() + " ");
        TreeNode child = n.getLeftChild();
        while (child != null) {
            preorderPrintBranch(child);
            child = child.getRightSibling();
        }
    } // preorderPrintBranch()

    // Tulostus tasoittain
    public static void printByLevel(Tree T) {
        LinkedQueue<TreeNode> Q = new LinkedQueue<TreeNode>();
        if (T.getRoot() != null)
            Q.offer(T.getRoot());

        while (! Q.isEmpty()) {
            TreeNode n = Q.poll();
            System.out.print(n.getElement() + " ");
            n = n.getLeftChild();
            while (n != null) {
                Q.offer(n);
                n = n.getRightSibling();
            }
        }
        System.out.println();
    } // printByLevel()



    /**
     * 26) Kirjoita algoritmi joka etsii esijärjestetystä yleisestä puusta
     * annetun alkion sisältämän solmun. Parametreina siis puu ja alkio,
     * palautusarvona puusolmu (tai null jollei alkiota löydy).
     * Algoritmin toimintaperiaate käytiin läpi luennolla.
     * @param T puu 
     * @param x etsittävä alkio
     * @return alkion sisältävä solmu tai null jollei löydy
     */
    public static <E extends Comparable<? super E>> TreeNode<E> preorderSearch(Tree<E> T, E x) {
    	if (T.getRoot() == null) {
			return null;
		}
		TreeNode<E> palautettuAlkio = PreOrderSearchAlgorithm(T.getRoot(),x);
		return palautettuAlkio;

    }
	private static <E> TreeNode<E> PreOrderSearchAlgorithm(TreeNode<E> node, E alkio) {
		TreeNode<E> child = null;
		if (node.getElement().equals(alkio)) {
			return node;
		}
		if (node.getLeftChild()!= null) {
			child = PreOrderSearchAlgorithm(node.getLeftChild(), alkio);
		}
		if (node.getRightSibling() != null && child == null) {
			child =PreOrderSearchAlgorithm(node.getRightSibling(), alkio);
		}
		return child;
	}



    /**
     * 25) Kirjoita algoritmi, joka luo ja palauttaa eräänlaisen k:n korkuisen
     * kuusipuun. k:n korkuisessa kuusipuussa on k runkosolmua, joilla
     * kullakin on kolme lasta. Lapsista keskimmäisellä on edelleen kolme
     * lasta, vanhemmilla ja nuoremmilla lapsilla ei ole lapsia. Alimman
     * (syvyydellä k–1 olevan) runkosolmun keskimmäinen lapsi on tyvisolmu,
     * eikä sillä ole lapsia. Kuvassa on 4:n korkuinen kuusipuu. Solmut voit
     * nimetä haluamallasi tavalla.
     * @param k puun korkeus
     * @return puu
     */

	public static <E> Tree<E> palautaKuusiPuuRekursiivisesti(Integer syvyys,Integer k, TreeNode<E> node, ArrayList<E> satunnaisiaObjekteja) {
		Tree<E> osaPuu = new Tree<>();
		osaPuu.setRoot(node);
		Random ran = new Random();
		int luku = ran.nextInt(100) + 1;
		node.setLeftChild(new TreeNode<E>(satunnaisiaObjekteja.get(luku)));
		TreeNode<E> leftChild = node.getLeftChild();
		k++;
		if (k == syvyys) {
			luku = ran.nextInt(100) + 1;
			leftChild.setRightSibling(new TreeNode<E>(satunnaisiaObjekteja.get(luku)));
		} else {
			luku = ran.nextInt(100) + 1;
			leftChild.setRightSibling(palautaKuusiPuuRekursiivisesti(syvyys, k, new TreeNode<E>(satunnaisiaObjekteja.get(luku)), satunnaisiaObjekteja));
		}
		luku = ran.nextInt(100) + 1;
		leftChild.getRightSibling().setRightSibling(new TreeNode<E>(satunnaisiaObjekteja.get(luku)));
		return osaPuu;
	}


}
