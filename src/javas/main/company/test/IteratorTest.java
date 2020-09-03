package javas.main.company.test;

import javas.main.company.bean.BookShelf;
import javas.main.company.BookIterable;

import java.util.Iterator;

/**
 * @description:
 * @author: wjk
 * @date: 2020/6/30 10:02
 **/
public class IteratorTest {
    public static void main(String[] args) {
        BookIterable[] bookIterables = new BookIterable[10];
        for(int i=0; i<10; i++) {
            bookIterables[i] = new BookIterable(i,"a-"+i);
        }
        BookShelf bookShelf = new BookShelf(bookIterables);
        Iterator<BookIterable> iterableIterator = bookShelf.iterator();
        while(iterableIterator.hasNext()) {
            System.out.println("-----"+iterableIterator.next());
        }
        System.out.println("=================================");
        bookShelf.forEach(bookIterables[2]);
    }
}
