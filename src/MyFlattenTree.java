package flatten;

import java.util.ArrayList;
import java.util.List;

public class MyFlattenTree<T> implements FlattenTree<T> {

    public List<T> flattenInOrder(Tree<T> tree) {

        if (tree == null) {
            throw new IllegalArgumentException();
        }

        List<T> list = new ArrayList<T>();
        traverseTree(tree, list);

        return list;
    }

    private void traverseTree(Tree<T> tree, List list) {
        if (tree.get().isLeft()) {
            list.add(tree.get().ifLeft(leftFunction));
        }

        else {
            list.add(tree.get().ifRight(rightFunction));
        }
    }

    private final Function<T, T> leftFunction = new Function<T, T>() {
        @Override
        public T apply(T t) {
            return t;
        }
    };

    private final Function<Triple<Tree<T>>, List<T>> rightFunction = new Function<Triple<Tree<T>>, List<T>>() {
        @Override
        public List<T> apply(Triple<Tree<T>> treeTriple) {

            List<T> l = new ArrayList<T>();
            l.addAll(flattenInOrder(treeTriple.left()));
            l.addAll(flattenInOrder(treeTriple.middle()));
            l.addAll(flattenInOrder(treeTriple.right()));
            return l;
        }
    };

}