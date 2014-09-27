import java.util.*;

import fi.joensuu.cs.tra.*;


public class kuusiPuu<E> {
	Tree<E> printattavaPuu = null;
	public kuusiPuu(Integer syvyys, ArrayList<E> satunnaisiaObjekteja) {
		Random ran = new Random();
		int luku = ran.nextInt(100) + 1;
		printattavaPuu = palautaKuusiPuuRekursiivisesti(syvyys, 0, new TreeNode<>(satunnaisiaObjekteja.get(luku)), satunnaisiaObjekteja);
		//Printataan puu taso kerrallaan
		printtaaKuusiPuu(syvyys);

	}
	/**
	 * @param syvyys
	 */
	private void printtaaKuusiPuu(Integer syvyys) {
		Integer k = 1;
		System.out.println("	"+printattavaPuu.getRoot().getElement());
		TreeNode<E> leftNode = printattavaPuu.getRoot().getLeftChild();
		TreeNode<E> middleNode = leftNode.getRightSibling();
		TreeNode<E> rightNode = middleNode.getRightSibling();
		while (k < syvyys) {
			System.out.println(leftNode.getElement()+"	"+middleNode.getElement()+"	"+rightNode.getElement());
			leftNode = middleNode.getLeftChild();
			middleNode = leftNode.getRightSibling();
			rightNode = middleNode.getRightSibling();
			k++;
		}
	}
	public Tree<E> palautaKuusiPuuRekursiivisesti(Integer syvyys,Integer k, TreeNode<E> node, ArrayList<E> satunnaisiaObjekteja) {
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

	public static void main(String[] args) {
		ArrayList<Integer> satunnaisiaObjekteja = new ArrayList<>();
		for (int i = 0; i < 200; i++) {
			satunnaisiaObjekteja.add(i);
		}
		kuusiPuu<Integer> uusPuu = new kuusiPuu<>(20, satunnaisiaObjekteja);
	}

}
