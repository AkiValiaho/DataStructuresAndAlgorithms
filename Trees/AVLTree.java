package Trees;

/**
 * Created by akivv on 27.5.2015.
 */
public class AVLTree<E extends Comparable<? super E>> {

	private Node<E> root;

	public AVLTree(E value) {
		this.root = new Node(value);
	}

	private Integer nodeBalanceFactor(Node node) {
		int leftTreeHeight = 0, rightTreeHeight = 0;
		if (node.getLeftChild() != null) {
			leftTreeHeight = calculateTreeHeight(node.getLeftChild());
		}
		if (node.getRightChild() != null) {
			rightTreeHeight = calculateTreeHeight(node.getRightChild());
		}
		return leftTreeHeight - rightTreeHeight;
	}

	private Integer calculateTreeHeight(Node node) {
		if (node.getLeftChild() == null && node.getRightChild() == null) {
			return 0;
		}
		int leftHeight = 0, rightHeight = 0;
		if (node.getLeftChild() != null) {
			leftHeight = calculateTreeHeight(node.getLeftChild());
		}
		if (node.getRightChild() != null) {
			rightHeight = calculateTreeHeight(node.getRightChild());
		}
		return Math.max(leftHeight, rightHeight) + 1;
	}

	public void insert(E value) {
		Node tmp = root;
		while (true) {
			if (value.compareTo(tmp.getValue()) > 0) {
				if (tmp.getRightChild() == null) {
					Node newOne = new Node(value);
					newOne.setParent(tmp);
					tmp.setRightChild(newOne);
					break;
				} else {
					tmp = tmp.getRightChild();
					continue;
				}
			}
			if (value.compareTo(tmp.getValue()) < 0) {
				if (tmp.getLeftChild() == null) {
					Node newOne = new Node(value);
					newOne.setParent(tmp);
					tmp.setLeftChild(newOne);
					break;
				} else {
					tmp = tmp.getLeftChild();
					continue;
				}
			}

		}
		//Tarkastetaan balanssit nodeille
		int rootBalance = nodeBalanceFactor(root);
		if (rootBalance == 2) {
			//Ajetaan ekaan nodeen, jossa arvona -1 tai 1
			tmp = root.getLeftChild();
			int tmpBalance = nodeBalanceFactor(tmp);
			int lastBalance = tmpBalance;
			ajaja(tmp, tmpBalance, lastBalance);
			//Sitten katsotaan parentin balanssi ja tämän balanssi ja mietitään
			//onko ne samanmerkkiset
			kiertoDontrolleri(tmp, tmpBalance, lastBalance);

		} else if (rootBalance == -2) {
			//Ajetaan ekaan nodeen, jossa arvona -1 tai 1
		}
	}

	private void kiertoDontrolleri(Node tmp, int tmpBalance, int lastBalance) {
		if (lastBalance == 2 && tmpBalance == 1) {
			//Mihin liiteteaan
			Node liittoNode = tmp.getParent().getParent();
			kierraOikealle(tmp);
			liittoNode.setLeftChild(tmp);
		} else if (lastBalance == -2 && tmpBalance == -1) {
			Node liittoNode = tmp.getParent().getParent();
			kierraVasemmalle(tmp);
			liittoNode.setRightChild(tmp);
		} else if (lastBalance == 2 && tmpBalance == -1) {
			Node liittoNode = tmp.getParent().getParent();
			oikeaVasen(tmp.getLeftChild());
			kierraVasemmalle(tmp);
			//right-left kierto
		} else if (lastBalance == -2 && tmpBalance == 1) {
			vasenOikea(tmp.getRightChild());
			kierraOikealle(tmp);
			//left-right kierto
		}
	}

	private Node kierraVasemmalle(Node tmp) {
		Node jononPaa = tmp.getParent();
		Node tmpRightChild = null;
		if (tmp.getRightChild() != null) {
			tmpRightChild = tmp.getRightChild();
		}
		if (tmpRightChild != null) {
			jononPaa.setRightChild(tmpRightChild);
		}
		jononPaa.setParent(tmp);
		tmp.setParent(null);
		tmp.setRightChild(jononPaa);
		tmpRightChild.setParent(jononPaa);
		return tmp;
	}

	private Node kierraOikealle(Node tmp) {
		//Jonon pää talteen
		Node jononPaa = tmp.getParent();
		Node tmpLeftChild = null;
		if (tmp.getLeftChild() != null) {
			tmpLeftChild = tmp.getLeftChild();
		}
		if (tmpLeftChild != null) {
			jononPaa.setLeftChild(tmpLeftChild);
		}
		jononPaa.setParent(tmp);
		tmp.setParent(null);
		tmp.setLeftChild(jononPaa);
		tmpLeftChild.setParent(jononPaa);
		return tmp;
	}

	private void ajaja(Node tmp, int tmpBalance, int lastBalance) {
		while (tmpBalance != -1 || tmpBalance != 1) {
			if (tmpBalance == 2) {
				tmp = tmp.getLeftChild();
			}
			if (tmpBalance == -2) {
				tmp = tmp.getRightChild();
			}
			lastBalance = tmpBalance;
			tmpBalance = nodeBalanceFactor(tmp);
		}
	}

	class Node {
		private E value;
		private Node leftChild;
		private Node rightChild;
		private Node parent;

		public Node(E value) {
			this.value = value;
		}

		public Node getParent() {
			return parent;
		}

		public void setParent(Node parent) {
			this.parent = parent;
		}

		public Node getRightChild() {
			return rightChild;
		}

		public void setRightChild(Node rightChild) {

			this.rightChild = rightChild;
		}

		public Node getLeftChild() {
			return leftChild;
		}

		public void setLeftChild(Node leftChild) {
			this.leftChild = leftChild;
		}

		public E getValue() {
			return value;
		}

		public void setValue(E value) {
			this.value = value;
		}
	}
}

