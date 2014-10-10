// traI_14_t34_35_pohja.java SJ

import java.util.*;

public class traI_14_t34_35_pohja {


    // P‰‰ohjelman k‰yttˆ:
    // java traI_14_t34_35_pohja [N] [S] [erilliset]
    // miss‰ N on alkioiden m‰‰r‰
    // S on satunnaislukusiemen
    // erilliset varmistaa, ettei taulukoissa ole samoja alkioita
    public static void main(String[] args) {

        // taulukoiden koko
        int N = 10;
        if (args.length > 0)
            N = Integer.valueOf(args[0]);

        // satunnaislukusiemen
        int siemen = 42;
        if (args.length > 1)
            siemen = Integer.valueOf(args[1]);

        // saako olla samoja alkioita
        int eri = 0;
        if (args.length > 2)
            eri = 1;

        // luodaan esimerkkitaulukot
        Integer[] T1 = new Integer[N];
        Integer[] T2 = new Integer[N];

        // t‰ytet‰‰n alkioilla
        java.util.Random r = new java.util.Random(siemen);
        for (int i = 0; i < N; i++) {
            T1[i] = r.nextInt(N/2);
            T2[i] = r.nextInt(N*2) + eri * N;
        }

        // tulostetaan taulukot
        if (N <= 20) {
            System.out.print("T1: ");
            for (int i = 0; i < N; i++)
                System.out.print(" " + T1[i]);
            System.out.println();

            System.out.print("T2: ");
            for (int i = 0; i < N; i++)
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

        // kutsutaan teht‰v‰‰ 35
        int k = 2;
        poistaMonet(L1, k);
        System.out.println("Teht‰v‰ 35: poistettu useammat kuin " + k);
        if (N <= 20) {
            for (Integer x1 : L1)
                System.out.print(" " + x1);
            System.out.println();
        }

        // kutsutaan teht‰v‰‰ 34
        retainAll(L1, L2);
        System.out.println("Teht‰v‰ 34, L1 = L1 & L2:");

        if (N <= 20) {
            for (Integer x1 : L1)
                System.out.print(" " + x1);
            System.out.println();
        }



    } // main()


    /**
      * 34.
      * Poistaa listasta A kaikki alkiot joita ei esiinny kokoelmassa B
      * @param A k‰sitelt‰v‰ lista
      * @param B s‰ilytett‰v‰t alkiot
      */
    public static <E> void retainAll(LinkedList<E> A, Collection<E> B) {
    	HashSet<E> hashSet = new HashSet<>(B);
    	for (Iterator<E> iterator = A.iterator(); iterator.hasNext();) {
			E aAlkio = iterator.next();
    		if (!hashSet.contains(aAlkio)) {
				iterator.remove();
			}
		}
        // TODO

    } // retainAll

    /**
     * 35.
     * Poistaa listasta enemm‰t kuin k esiintym‰‰ samaa alkiota
     * @param L muokattava lista
     */
    public static <E> void poistaMonet(LinkedList<E> L, int k) {
        if (k == 0) {
			L.clear();
		}
    	// luodaan esiintymiskuvaus
    	//Jos haluan myˆhemmin vaihtaa vaikka treemapiksi on j‰rkev‰mp‰‰
    	//k‰ytt‰‰ t‰ss‰ map-rajapintaa
        Map<E, Integer> C = new HashMap<E, Integer>();
        for (Iterator<E> iterator = L.iterator(); iterator.hasNext();) {
			E e = iterator.next();
			Integer count = C.get(e);
			if (count == null) {
				C.put(e, 1);
				continue;
			} else {
				C.put(e, count+1);
				count++;
			}
			if (count > k) {
				C.put(e, count-1);
				iterator.remove();
			}
        }
    } // poistaMonet()
} // class

