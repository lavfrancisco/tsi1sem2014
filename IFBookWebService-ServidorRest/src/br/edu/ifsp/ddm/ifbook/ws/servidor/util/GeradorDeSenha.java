package br.edu.ifsp.ddm.ifbook.ws.servidor.util;

import java.util.Random;

public class GeradorDeSenha {


	  public String gerar() {
		  char[] chars = "abcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
		  StringBuilder sb = new StringBuilder();
		  Random random = new Random();
		  for (int i = 0; i < 6; i++) {
		      char c = chars[random.nextInt(chars.length)];
		      sb.append(c);
		  }
		  return sb.toString();
	  }

}
