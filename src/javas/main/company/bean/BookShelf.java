package javas.main.company.bean;

import javas.main.company.BookIterable;

import java.util.Iterator;

/**
 * @description:
 * @author: wjk
 * @date: 2020/6/30 9:45
 **/
public class BookShelf implements Iterable<BookIterable> {
    private BookIterable bookIterable[] = null;
    public BookShelf() {
        bookIterable = new BookIterable[10];
    }
    public BookShelf(BookIterable bookIterable[]) {
        this.bookIterable = bookIterable;
    }
    @Override
    public Iterator<BookIterable> iterator() {
        return new BookIterator();
    }


    private class BookIterator implements Iterator<BookIterable> {
        private int next = 0;
        @Override
        public boolean hasNext() {
         return next != bookIterable.length;
        }

        @Override
        public BookIterable next() {
            return bookIterable[next++];
        }
    }

    public static void main(String[] args) {
        HashT h =new HashT();
        h.print();
    }
}
