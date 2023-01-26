package org.example.practice6;


public class GLLaba6 {

    public static byte mul02(byte b) {
        byte result = (byte) (b << 1);
        if ((b & 0x80) != 0) {
            result ^= 0x1b;
        }
        return result;
    }

    public static byte mul03(byte b) {
        return (byte) (mul02(b) ^ b);
    }

    public static void main(String[] args) {
        byte d4 = (byte) 0xD4;
        byte bf = (byte) 0xBF;

        System.out.printf("D4 * 02 = 0x%02X\n", mul02(d4));
        System.out.printf("BF * 03 = 0x%02X\n", mul03(bf));
    }
}
