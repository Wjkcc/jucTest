package javas.main.company;
import java.util.function.Consumer;



/**
 * @description:
 * @author: wjk
 * @date: 2020/6/30 9:44
 **/
public class BookIterable implements Consumer<BookIterable> {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public BookIterable(int id,String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "BookIterable{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public void accept(BookIterable bookIterable) {
        System.out.println(bookIterable);
    }
}
