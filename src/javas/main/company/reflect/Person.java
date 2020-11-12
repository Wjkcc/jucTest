package company.reflect;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import lombok.Data;

@Data
public class Person
{
    public String name;
    private String id;
    public int age;
    private String appName;
    public void setAge(int age) {
        this.age = age;
    }
    public Person(){
        super();
    }
    private Person(String id) {
        this.id = id;
    }
    public void print() {
        System.out.println(1);
    }
    private String add(int a) {
        System.out.println(a+1);
        return "s";
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString()
    {
        System.out.println(this.age);
        return "Person{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
