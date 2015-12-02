package net.bandoviet.tool;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

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
    String id = UUID.randomUUID().toString();
    System.out.println(id);
  }
}
