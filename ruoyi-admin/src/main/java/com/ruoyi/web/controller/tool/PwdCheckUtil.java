package com.ruoyi.web.controller.tool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.StringUtils;
import static org.junit.Assert.*;

/**
 * @Comment
 * @Author LiYuan
 * @Date 2021-3-2
 */
@Slf4j
public class PwdCheckUtil {

  private static int LENGTH_MAX = 20;
  private static int LENGTH_MIN = 6;

  public Boolean checkPassword(String password) {
    if (StringUtils.isEmpty(password)) {
      log.info("密码为空");
      return Boolean.FALSE;
    }
    if (password.length() < LENGTH_MIN || password.length() > LENGTH_MAX) {
      log.info("密码长度在6位到20位之间");
      return Boolean.FALSE;
    }

    String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$!\"#$%&'()*+,-./:;<=>?@ \\]\\[\\^_`{|}~&])[A-Za-z\\d$!\"#$%&'()*+,-./:;<=>?@ \\]\\[\\^_`{|}~&]{6,20}";

    Matcher matcher = Pattern.compile(regex).matcher(password);
    if (matcher.matches()) {
      return Boolean.TRUE;
    }
    return Boolean.FALSE;
  }

  @Test
  public void test_check() {

    assertEquals(false,checkPassword(""));
    assertEquals(false,checkPassword("123"));
    assertEquals(false,checkPassword("123456789123456789123456789"));
    assertEquals(false,checkPassword("A"));
    assertEquals(false,checkPassword("Aa"));
    assertEquals(false,checkPassword("Aa1"));
    assertEquals(false,checkPassword("AAaa12456"));
    assertEquals(true,checkPassword("AAaa1!"));
    assertEquals(true,checkPassword("AAAAAaaaaa11111!!!!!"));
    assertEquals(false,checkPassword("AAAAAaaaaa11111!!!!!A"));
    assertEquals(true,checkPassword("AAaa12!!"));
    assertEquals(true,checkPassword("AAa a12!!@AAaa"));
    assertEquals(true,checkPassword("Aa2!#$%&'()*@^_`{|}~"));
    assertEquals(true,checkPassword("Aa2! $%&'()*+,-./:;<"));
  }
}
