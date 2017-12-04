package br.isaac.mycsc.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;

public class MyHash {

    public MyHash() {

    }

    public static int generateToken() {
        return (int) (Math.random() * 1000000);
    }

    public static String generate(String str, int token) {

        try {

            str = str + token;
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] digest = md.digest();
            String res = DatatypeConverter.printHexBinary(digest).toLowerCase();

            return res;

        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }

        return null;

    }

    public static boolean match(String hash, String str, int token) {
        String h = generate(str, token);
        return hash.equals(h);
    }

}
