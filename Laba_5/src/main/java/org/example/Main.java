package org.example;
import java.io.IOException;

public class Main {
    public static void main(String... args) throws IOException, InstantiationException, IllegalAccessException {
        SomeBean somebean = (new Injector<SomeBean>("src/main/java/org/example/properties/inj.properties").inject(new SomeBean()));
        somebean.go();
    }
}
