import fi.joensuu.cs.tra.*;


public class PreOrderSearch {

	public static void main(String[] args) {
		//Rakennetaan yksinkertainen puu
		Tree<Integer> yksinkertainenPuu =teePuu();
		System.out.println("Haetaan arvoa 5, joka on viimeinen arvo");
		Integer tulos = PreOrderSearchFromTree(yksinkertainenPuu, 5).getElement();
		
		System.out.println("Tulos "+tulos);
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
	private static <E> TreeNode<E> PreOrderSearchFromTree(Tree<E> puu, E alkio) {
		if (puu.getRoot() == null) {
			return null;
		}
		TreeNode<E> palautettuAlkio = PreOrderSearchAlgorithm(puu.getRoot(),alkio);
		return palautettuAlkio;
	}
	/**
	 * @return 
	 * 
	 */
	private static Tree<Integer> teePuu() {
		Tree<Integer> yksinkertainenPuu = new Tree<>();
		TreeNode<Integer> rootNode = new TreeNode<Integer>(1);
		yksinkertainenPuu.setRoot(rootNode);
		rootNode.setLeftChild(new TreeNode<Integer>(2));
		rootNode.getLeftChild().setLeftChild(new TreeNode<Integer>(3));
		rootNode.getLeftChild().setRightSibling(new TreeNode<Integer>(5));
		return yksinkertainenPuu;
	}

}
