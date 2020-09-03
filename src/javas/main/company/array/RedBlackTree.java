package javas.main.company.array;

/**
 * @description: 红黑树
 * @author: wjk
 * @date: 2020/8/11 10:59
 **/
public class RedBlackTree<K extends Comparable<K>,V> {
    private  static final boolean RED = true;
    private static final boolean BLACK = false;
    private Node root;

    /**
     * 获取当前节点的父亲节点
     * @param node
     * @return
     */
    private Node getParent(Node node) {
        if (node != null) {
            return node.parent;
        }
        return null;
    }

    /**
     * 设置当前节点为红色
     * @param node
     * @return
     */
    private boolean setRed(Node node) {
        if (node != null) {
            node.color = RED;
            return true;
        }
        return false;
    }
    /**
     * 设置当前节点为黑色
     * @param node
     * @return
     */
    private boolean setBlack(Node node) {
        if (node != null) {
            node.color = BLACK;
            return true;
        }
        return false;
    }

    /**
     * 中序遍历当前红黑树
     */
    public void print() {
        print(root);
    }

    /**
     * 中序遍历点前节点下的节点
     * @param node
     */
    private void print(Node node) {
        if (node != null) {
            print(node.left);
            System.out.println(node.print());
            print(node.right);
        }
    }

    /**
     * 判断当前节点是否为红色
     * @param node
     * @return
     */
    private boolean isRed(Node node) {
        if (node != null) {
            return node.color == RED;
        }
        return false;
    }
    /**
     * 判断当前节点是否为黑色
     * @param node
     * @return
     */
    private boolean isBlack(Node node) {
        if (node != null) {
            return node.color == BLACK;
        }
        return false;
    }

    /**
     * 获取当前节点的颜色
     * @param node
     * @return
     */
    public String getColor(Node node) {
        if (node != null) {
            return node.color == RED ? "red" : "black";
        }
        return null;
    }

    /**
     * 左旋
     *                p            p
     *                |            |
     *                x            c
     *               / \          / \
     *              xr  c  -->   x   cr
     *                /  \      / \
     *               cl   cr   xr cl
     *
     * 当前节点的右节点变为点前节点
     * 当前节点的变为右边节点的左节点
     * 右边节点的左节点变为当前节点的右节点
     * @param node
     */
    private void leftRoTate(Node node) {
        if (node != null) {
            Node p = node.getParent();
            Node current = node.right;
            // 当前节点变为右子节点
            if (p != null) {
                current.parent = p;
                // 当前节点的父节点的左节点或右节点变为当前节点的右子节点
                if ( node == p.left) {
                    p.left = current;
                }else {
                    p.right = current;
                }
            }else {
                this.root = current;
            }
            node.parent = current;
            // 右边节点的左节点变为当前节点的右节点
            Node left = current.left;
            node.right = left;
            if (current.left != null) {
                left.parent = node;
            }
            // 当前节点的变为右子节点的左节点
            current.left = node;

        }
    }

    /**
     * 右旋
     *             p            p
     *             |            |
     *             x            c
     *            / \          / \
     *           c  xr  -->   cl  x
     *         /  \              / \
     *        cl   cr          cr   xr
     *
     * 当前节点的左子节点变成当前节点
     * 当前节点变成左子节点的右子节点
     * 当前节点的左子节点右节点变成当前节点的左子节点
     * @param node
     */
    private void rightRotate(Node node) {
        if (node != null) {
            Node p = node.getParent();
            Node current = node.left;
            // 当前节点的变为左子节点
            if (p != null) {
                current.parent = p;
                // 当前节点的父节点的左节点或右节点变为当前节点的左子节点
                if ( node == p.left) {
                    p.left = current;
                }else {
                    p.right = current;
                }
            } else {
                this.root = current;
            }
            node.parent = current;
            // 左子节点的右节点变为当前节点的右子节点
            Node right = current.right;
            node.left = right;
            if (current.right != null) {
                right.parent = node;
            }
            // 点前节点变为左子节点的右节点
            current.right = node;
        }
    }

    /**
     * 插入节点
     * @param node
     */
    public void insert(K key, V value) {
        // 新建节点，颜色为红色
        Node node = new Node(key,value,RED,null,null,null);
        if (this.root == null) {
            root = node;
            node.setColor(BLACK);
            return;
        }
        insert(node);

    }

    private void insert(Node node) {
      Node x = this.root;
      Node current = x;
      // 找到插入的位置
      while (x!= null) {
          current = x;
          /**
           * >0 node.key > x.key ,找x的右子树
           * =0 替换value
           * <0 node.key < x.key，找x的左子树
           */
          if (x.key.compareTo(node.key) > 0) {
              x = x.right;
          }
          else if (x.key.compareTo(node.key) == 0) {
              x.setValue(node.value);
          }
          else {
              x = x.left;
          }
      }
      node.setParent(current);
      if (current.key.compareTo(node.key) > 0) {
          current.right = node;
      }else{
          current.left = node;
      }
      // 修正红黑树
        insertFix(node);
    }

    /**
     * 节点规则
     * 1节点分为红色或者黑色；
     * 2根节点必为黑色；
     * 3叶子节点都为黑色，且为null；
     * 4连接红色节点的两个子节点都为黑色（红黑树不会出现相邻的红色节点）；
     * 5从任意节点出发，到其每个叶子节点的路径中包含相同数量的黑色节点；
     * 6新加入到红黑树的节点为红色节点；
     * @param node
     */
    private void insertFix(Node node) {
        // 插入节点为根节点，节点变为黑色
        if (node == root) {
            node.setColor(BLACK);
            return;
        }
        Node p = node.getParent();
        // 如果插入节点的父亲节点为黑色，没有破坏平衡,否则需要进行变色和旋转等操作来保持平衡
        if (p.color == RED) {
            // 爷爷节点
            Node pp = p.getParent();
            Node uncle = null;
            if (pp.getLeft() == p) {
                uncle  = pp.getRight();
                // 有叔叔节点为黑色，进行变色操作
                if (uncle != null && uncle.color == RED){
                     p.setColor(BLACK);
                     pp.getRight().setColor(BLACK);
                     pp.setColor(RED);
                     // 以爷爷节点为当前节点继续修正
                     insertFix(pp);
                     return;
                }
                if (uncle == null || uncle.color == BLACK){
                    if (node == p.getRight()) {
                         // 先以父亲节点左旋
                        leftRoTate(p);
                        // 以爸爸为节点再次调整，最下面的子节点。爸爸节点变成了当前节点的子节点
                        insertFix(p);
                        return;
                    }  // 变色，再以爷爷节点右旋
                    if(node == p.getLeft()){
                        p.setColor(BLACK);
                        pp.setColor(RED);
                        rightRotate(pp);
                        return;
                    }
                }
            }
            else{
                uncle = pp.getLeft();
                if (uncle != null && uncle.color == RED){
                    p.setColor(BLACK);
                    pp.getRight().setColor(BLACK);
                    pp.setColor(RED);
                    // 以爷爷节点为当前节点继续修正
                    insertFix(pp);
                    return;
                }
                if (uncle == null || uncle.color == BLACK){
                    if (node == p.getRight()) {

                        p.setColor(BLACK);
                        pp.setColor(RED);
                        leftRoTate(pp);
                        return;
                    }  // 变色，再以爷爷节点右旋
                    if(node == p.getLeft()){
                        // 先以父亲节点左旋
                        rightRotate(p);
                        // 以爸爸为节点再次调整
                        insertFix(p);
                        return;
                    }
                }
            }

        }
        
    }

    /**
     * 节点
     * @param <V>
     */
    static class Node<K extends Comparable<K>,V> {
        private K key;
        private V value;
        private boolean color;
        private Node left;
        private Node right;
        private Node parent;

        public Node() {
            super();
        }
        public Node(K key, V value, boolean color, Node left, Node right, Node parent) {
            this.key = key;
            this.value = value;
            this.color = color;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", value=" + value +
                    ", color=" + color +
                    ", left=" + left +
                    ", right=" + right +
                    ", parent=" + parent +
                    '}';
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public boolean isColor() {
            return color;
        }

        public void setColor(boolean color) {
            this.color = color;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }
        public String print(){
            return "Node{" +
                    "key=" + key +
                    ", value=" + value +"}";
        }
    }

    public static void main(String[] args) {

    }
}
