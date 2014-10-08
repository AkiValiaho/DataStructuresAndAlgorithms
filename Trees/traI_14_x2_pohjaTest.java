package TRA1.Trees;


import static org.junit.Assert.*;

import java.util.*;

import org.junit.*;

import fi.joensuu.cs.tra.*;

/**
 * 
 */

/**
 * L‰p‰istyt unitTestit algoritmille
 * @author Aki
 * @param <E>
 *
 */
public class traI_14_x2_pohjaTest<E extends Comparable<? super E>> {
	private akivv x2Pohja;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.x2Pohja = new akivv();
	}
	/**
	 * Test method for {@link akivv#inorderTreeToArray(fi.joensuu.cs.tra.BTree)}.
	 */
	@Test
	public void testInorderTreeToArrayWithEmptyTree() {
		ArrayList<E> tyhjaArrayListi;
		tyhjaArrayListi = akivv.inorderTreeToArray(new BTree<E>());
		assertNull(tyhjaArrayListi);
		
	}
	@Test
	public void testInorderTreeToArrayWithNullTree() {
		ArrayList<E> tyhjaArrayList;
		BTree<E> nullTree = null;
		tyhjaArrayList = akivv.inorderTreeToArray(nullTree);
		assertNull(tyhjaArrayList);
	}
	
	@Test
	public void testInorderTreeToArrayWithOneNode() {
		ArrayList<Integer> tyhjaArrayListi;
		BTree<Integer> T = new BTree<Integer>();
		BTreeNode<Integer> uusiNode = new BTreeNode<Integer>(1);
		T.setRoot(uusiNode);
		tyhjaArrayListi = akivv.inorderTreeToArray(T);
		ArrayList<Integer> wanted = new ArrayList<>();
		wanted.add(1);
		assertArrayEquals(wanted.toArray(), tyhjaArrayListi.toArray());
	}
	@Test
	public void testInorderTreeToArrayWithThreeNodesSimpleBuildToTheLeft() {
		ArrayList<Integer> tyhjaArrayListi;
		BTree<Integer> T = new BTree<Integer>();
		BTreeNode<Integer> uusiNode = new BTreeNode<Integer>(10);
		T.setRoot(uusiNode);
		T.getRoot().setLeftChild(new BTreeNode<Integer>(7));
		T.getRoot().getLeftChild().setLeftChild(new BTreeNode<Integer>(6));
		tyhjaArrayListi = akivv.inorderTreeToArray(T);
		ArrayList<Integer> wanted = new ArrayList<>();
		wanted.add(6);
		wanted.add(7);
		wanted.add(10);
		assertArrayEquals(wanted.toArray(), tyhjaArrayListi.toArray());
	}
	@Test
	public void testInorderTreeToArrayWithThreeNodesSimpleBuildToTheRight() {
		ArrayList<Integer> tyhjaArrayListi;
		BTree<Integer> T = new BTree<Integer>();
		BTreeNode<Integer> uusiNode = new BTreeNode<Integer>(10);
		T.setRoot(uusiNode);
		T.getRoot().setRightChild(new BTreeNode<Integer>(11));
		T.getRoot().getRightChild().setRightChild(new BTreeNode<Integer>(12));
		tyhjaArrayListi = akivv.inorderTreeToArray(T);
		ArrayList<Integer> wanted = new ArrayList<>();
		wanted.add(10);
		wanted.add(11);
		wanted.add(12);
		assertArrayEquals(wanted.toArray(), tyhjaArrayListi.toArray());
	}
	@Test
	public void testInorderTreeToArrayWithThreeBalancedNodes() {
		ArrayList<Integer> tyhjaArrayListi;
		BTree<Integer> T = new BTree<Integer>();
		BTreeNode<Integer> uusiNode = new BTreeNode<Integer>(10);
		T.setRoot(uusiNode);
		T.getRoot().setRightChild(new BTreeNode<Integer>(11));
		T.getRoot().setLeftChild(new BTreeNode<Integer>(9));
		tyhjaArrayListi = akivv.inorderTreeToArray(T);
		ArrayList<Integer> wanted = new ArrayList<>();
		wanted.add(9);
		wanted.add(10);
		wanted.add(11);
		assertArrayEquals(wanted.toArray(), tyhjaArrayListi.toArray());
	}
	@Test
	public void testInorderTreeToArrayWithXBalancedNodes() {
		for (int i = 0; i < 10000; i++) {
			ArrayList<Integer> tyhjaArrayListi = new ArrayList<>();
			Random ran = new Random();
			/*    Pseudosatunnainen integer rangella [min,max],
			    ekslusiivinen topvaluen suhteen joten lis‰t‰‰n 1
			    ett‰ oikea max saadaan, esim. (max = 6, viimeinen
			      saatava luku = 5). Pelkk‰ nextInt antaa siis rangen
			    0-(arg-1)*/
			int luku = ran.nextInt(1000) + 1;
			for (int j = 0; j < luku; j++) {
				tyhjaArrayListi.add(ran.nextInt(1000)+10);
			}
			HashSet<Integer> hs = new HashSet<Integer>();
			hs.addAll(tyhjaArrayListi);
			tyhjaArrayListi.clear();
			tyhjaArrayListi.addAll(hs);
			Collections.sort(tyhjaArrayListi);
			ArrayList<Integer> wanted = akivv.inorderTreeToArray(akivv.arrayToInorderTree(tyhjaArrayListi));
			assertArrayEquals(wanted.toArray(), tyhjaArrayListi.toArray());
		}
	}
	/**
	 * Test method for {@link akivv#arrayToInorderTree(java.util.ArrayList)}.
	 */
	@Test
	public void testArrayToInorderTreeNullArray() {
		ArrayList<Integer> tyhjaArrayListi = null;
		BTree<Integer> palautettavaTree = akivv.arrayToInorderTree(tyhjaArrayListi);
		assertNull(palautettavaTree);
		
	}
	@Test
	public void testArrayToInorderTreeEmptyArray() {
		ArrayList<Integer> tyhjaArrayListi = new ArrayList<>();
		BTree<Integer> palautettavaTree = akivv.arrayToInorderTree(tyhjaArrayListi);
		assertNull(palautettavaTree);
		
	}
	@Test
	public void testArrayToInorderTree10Elements() {
		Integer[] required = {3, 4, 5, 8, 10, 13, 18, 19,20,100000};
		ArrayList<Integer> requiredArrayList = new ArrayList<>();
		for (int j = 0; j < required.length; j++) {
			requiredArrayList.add(required[j]);
		}
		
		int i;
		BTree<Integer> palautettavaTree = akivv.arrayToInorderTree(requiredArrayList);
		ArrayList<Integer> output = akivv.inorderTreeToArray(palautettavaTree);
	
		assertEquals(requiredArrayList, output);
		Boolean sisajarjestys =akivv.isInorder(palautettavaTree);
		assertTrue(sisajarjestys);
		//BTreePrinter asdf = new BTreePrinter();
		//BTreePrinter.printNode(palautettavaTree.getRoot());
		
	}
	@Test
	public void testArrayToInorderTreeOneElement() {
		Integer[] required = {3};
		ArrayList<Integer> requiredArrayList = new ArrayList<>();
		for (int j = 0; j < required.length; j++) {
			requiredArrayList.add(required[j]);
		}
		
		int i;
		BTree<Integer> palautettavaTree = akivv.arrayToInorderTree(requiredArrayList);
		ArrayList<Integer> output = akivv.inorderTreeToArray(palautettavaTree);
	
		assertEquals(requiredArrayList, output);
		Boolean sisajarjestys =akivv.isInorder(palautettavaTree);
		assertTrue(sisajarjestys);
//		BTreePrinter asdf = new BTreePrinter();
//		BTreePrinter.printNode(palautettavaTree.getRoot());
		
	}
	@Test
	public void testArrayToInorderTreeTwoElements() {
		Integer[] required = {3,4};
		ArrayList<Integer> requiredArrayList = new ArrayList<>();
		for (int j = 0; j < required.length; j++) {
			requiredArrayList.add(required[j]);
		}
		
		int i;
		BTree<Integer> palautettavaTree = akivv.arrayToInorderTree(requiredArrayList);
		ArrayList<Integer> output = akivv.inorderTreeToArray(palautettavaTree);
	
		assertEquals(requiredArrayList, output);
		Boolean sisajarjestys =akivv.isInorder(palautettavaTree);
		assertTrue(sisajarjestys);
//		BTreePrinter asdf = new BTreePrinter();
//		BTreePrinter.printNode(palautettavaTree.getRoot());
		
	}
	@Test
	public void testArrayToInorderTreeThreeElements() {
		Integer[] required = {3,4,5};
		ArrayList<Integer> requiredArrayList = new ArrayList<>();
		for (int j = 0; j < required.length; j++) {
			requiredArrayList.add(required[j]);
		}
		int i;
		BTree<Integer> palautettavaTree = akivv.arrayToInorderTree(requiredArrayList);
		ArrayList<Integer> output = akivv.inorderTreeToArray(palautettavaTree);
		assertEquals(requiredArrayList, output);
		Boolean sisajarjestys =akivv.isInorder(palautettavaTree);
		assertTrue(sisajarjestys);
//		BTreePrinter asdf = new BTreePrinter();
//		BTreePrinter.printNode(palautettavaTree.getRoot());
	}
	@Test
	public void testArrayToInorderTreeXElements() {
		for (int i = 0; i < 10000; i++) {
			ArrayList<Integer> requiredArrayList = new ArrayList<>();
			Random ran = new Random();
			/*    Pseudosatunnainen integer rangella [min,max],
			    ekslusiivinen topvaluen suhteen joten lis‰t‰‰n 1
			    ett‰ oikea max saadaan, esim. (max = 6, viimeinen
			      saatava luku = 5). Pelkk‰ nextInt antaa siis rangen
			    0-(arg-1)*/
			int luku = ran.nextInt(1000) + 1;
			for (int j = 0; j < luku; j++) {
				requiredArrayList.add(ran.nextInt(1000)+1);
			}
			HashSet<Integer> hs = new HashSet<Integer>();
			hs.addAll(requiredArrayList);
			requiredArrayList.clear();
			requiredArrayList.addAll(hs);
			Collections.sort(requiredArrayList);
			BTree<Integer> palautettavaTree = akivv.arrayToInorderTree(requiredArrayList);
			ArrayList<Integer> output = akivv.inorderTreeToArray(palautettavaTree);
			assertEquals(requiredArrayList, output);
			Boolean sisajarjestys =akivv.isInorder(palautettavaTree);
			assertTrue(sisajarjestys);
//			BTreePrinter asdf = new BTreePrinter();
//			BTreePrinter.printNode(palautettavaTree.getRoot());
//			//Printataan nodejen m‰‰r‰->
//			System.out.println("\n");
//			System.out.println("\n");
//			System.out.println(output.size());
//			System.out.println("\n");
//			System.out.println(traI_14_t27_30_pohja.korkeus(palautettavaTree));
			//Tarkastetaan viel‰ onko korkeus varmasti logaritminen
			assertTrue((Math.log10(output.size())/Math.log10(2))+0.01>traI_14_t27_30_pohja.korkeus(palautettavaTree));
		}
		}
		

}