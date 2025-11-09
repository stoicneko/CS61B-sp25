public class RedBlackTree<T extends Comparable<T>> {


    /**
     * @author stoicneko
     * @version 1.0
     * @since 2025.11.8
     **/

    /* Root of the tree. */
    RBTreeNode<T> root;

    static class RBTreeNode<T> {

        final T item;
        boolean isBlack;
        RBTreeNode<T> left;
        RBTreeNode<T> right;

        /**
         * Creates a RBTreeNode with item ITEM and color depending on ISBLACK
         * value.
         * @param isBlack
         * @param item
         */
        RBTreeNode(boolean isBlack, T item) {
            this(isBlack, item, null, null);
        }

        /**
         * Creates a RBTreeNode with item ITEM, color depending on ISBLACK
         * value, left child LEFT, and right child RIGHT.
         * @param isBlack
         * @param item
         * @param left
         * @param right
         */
        RBTreeNode(boolean isBlack, T item, RBTreeNode<T> left,
                   RBTreeNode<T> right) {
            this.isBlack = isBlack;
            this.item = item;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * Creates an empty RedBlackTree.
     */
    public RedBlackTree() {
        root = null;
    }

    /**
     * Flips the color of node and its children. Assume that NODE has both left
     * and right children
     * @param node
     */
    void flipColors(RBTreeNode<T> node) {
        node.isBlack = !node.isBlack;
        node.left.isBlack = !node.right.isBlack;
        node.right.isBlack = !node.right.isBlack;

    }

    /**
     * Rotates the given node to the right. Returns the new root node of
     * this subtree. For this implementation, make sure to swap the colors
     * of the new root and the old root!
     * @param node
     * @return

     * `指针`, 和`值`的区别
     * 旋转的本质是“换根”，不是“局部调整引用”。
     * 每部分函数做每部分的事
     */
    RBTreeNode<T> rotateRight(RBTreeNode<T> node) {
        // 修改指针之前，先保存原引用。
        // 否则一旦引用被覆盖，原结构就无法恢复。
        RBTreeNode<T> pivot = node.left;
        node.left = pivot.right;
        pivot.right = node;
        swapColors(node, pivot);
        // 要返回新根, 而不是原本的node
        return pivot;
    }

    private void swapColors(RBTreeNode<T> a, RBTreeNode<T> b) {
        boolean tmp = a.isBlack;
        a.isBlack = b.isBlack;
        b.isBlack = tmp;
    }

    /**
     * Rotates the given node to the left. Returns the new root node of
     * this subtree. For this implementation, make sure to swap the colors
     * of the new root and the old root!
     * @param node
     * @return
     */
    RBTreeNode<T> rotateLeft(RBTreeNode<T> node) {
        RBTreeNode<T> pivot = node.right;
        node.right = pivot.left;
        pivot.left = node;
        swapColors(node, pivot);
        return pivot;
    }

    /**
     * Helper method that returns whether the given node is red. Null nodes (children or leaf
     * nodes) are automatically considered black.
     * @param node
     * @return
     */
    private boolean isRed(RBTreeNode<T> node) {
        return node != null && !node.isBlack;
    }

    /**
     * Inserts the item into the Red Black Tree. Colors the root of the tree black.
     * @param item
     */
    public void insert(T item) {
        root = insertHelper(root, item);
        root.isBlack = true;
    }

    /**
     * Helper method to insert the item into this Red Black Tree. Comments have been provided to help break
     * down the problem. For each case, consider the scenario needed to perform those operations.
     * Make sure to also review the other methods in this class!
     * @param node
     * @param item
     * @return
     */
    private RBTreeNode<T> insertHelper(RBTreeNode<T> node, T item) {
        // 自己之后做项目, 独立的, 也要拆解问题写TODO

        // 终止条件放在哪里? 放前面
        // LLRB 也是平衡树, 平衡树家族的那些树都要遵守平衡树的性质, 必须比较

        if (node == null) {
            return new RBTreeNode<>(false, item, null, null);
        }

        int cmp = item.compareTo(node.item);
        if (cmp < 0) { // item < node.item, 向左走
            // **** 递归结果一定要接回来!!!
            node.left = insertHelper(node.left, item);
        } else if (cmp > 0) {
            node.right =  insertHelper(node.right, item);
        } else {
            // map
            // node.value = item;
            // set
            return node;
        }

        // 操作完成之后呢? 哪些方法有返回值?
        if (!isRed(node.left) && isRed(node.right)) {
            node = rotateLeft(node);
        }
        // 根据性质写
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }
        return node;
    }


}
