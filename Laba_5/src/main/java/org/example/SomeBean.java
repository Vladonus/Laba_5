package org.example;
import org.example.reflection.SomeInterface;
import org.example.reflection.SomeOtherInterface;

class SomeBean {
   @AutoInjectable
   private SomeInterface someField;
   @AutoInjectable
   private SomeOtherInterface otherField;
    public SomeBean() {
    }
    public void go(){
       someField.doSome();
       otherField.doSome();
   }
}
