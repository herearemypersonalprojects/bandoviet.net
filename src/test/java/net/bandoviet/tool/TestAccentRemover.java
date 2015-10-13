package net.bandoviet.tool;

import org.junit.Test;

public class TestAccentRemover {
  @Test
  public void testAccentRemover() {
    System.out.println(AccentRemover.toUrlFriendly("Artist Lê Bá Đảng- France- VC vinh danh gọi là “Những sứ giả Lạc Hồng”- 2005."));
  }
}
