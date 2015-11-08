package net.bandoviet.tool;

import java.util.Calendar;
import java.util.Date;

class Holder {
  public int held;

  public void bump(Holder theHolder) {
    theHolder.held++;
  }
}

class Base {
  public void display(int i) {
    System.out.println("Value is " + i);
  }
}

class Sub extends Base {
  public void display(int i) {
    System.out.println("This value is " + i);
  }
  
  public void ds(int i) {
    System.out.println("this " + i);
  }
}

public class Test {

  public static void main(String args[]) {
    Base b = new Sub();
    b.display(10);
    int prim = 1;
    Integer wrapper = new Integer(9);
    
   // prim = wrapper;
    prim = new Integer(10);
    wrapper = 9;
    
    System.out.println(prim);
    System.out.println(wrapper);
    
    Date a = new Date();
    Calendar d = Calendar.getInstance();
    d.setTime(a);
    d.get(Calendar.YEAR);
    
    Base[] sdb = new Base[6];
    Base[] qds[];
    Base qq[][] = new Base[2][];
  }
}
