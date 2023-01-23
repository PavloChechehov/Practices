package org.example.practice3;

import java.util.Random;

public class RSAAlgorithms {

    static int power(int x, int y, int p) {

        int res = 1; // Initialize result

        x = x % p;

        while (y > 0) {

            // If y is odd, multiply x with result
            if ((y & 1) == 1)
                res = (res * x) % p;

            // y must be even now
            y = y >> 1; // y = y/2
            x = (x * x) % p;
        }

        return res;
    }

    static boolean millerTest(int d, int n) {

        int a = 2 + (int)(Math.random() % (n - 4));

        // Compute a^d % n
        int x = power(a, d, n);

        if (x == 1 || x == n - 1)
            return true;

        while (d != n - 1) {
            x = (x * x) % n;
            d *= 2;

            if (x == 1)
                return false;
            if (x == n - 1)
                return true;
        }

        // Return composite
        return false;
    }

    public static boolean isPrime(int n, int k) {

        // Corner cases
        if (n <= 1 || n == 4)
            return false;
        if (n <= 3)
            return true;

        // Find r such that n = 2^d * r + 1
        // for some r >= 1
        int d = n - 1;

        while (d % 2 == 0)
            d /= 2;

        // Iterate given number of 'k' times
        for (int i = 0; i < k; i++)
            if (!millerTest(d, n))
                return false;

        return true;
    }


    private static int generatePrime(int min, int max) {
        int k = 4; // Number of iterations*
        int probablyPrime = new Random().nextInt(min, max);

        for (int prime = probablyPrime; prime > min; prime--) {
            if (isPrime(prime, k)) {
                return prime;
            }
        }

        return min;
    }

    public static void main(String[] args) {
        int p = generatePrime(2, 10);
        int q = generatePrime(10, 20);

        int n = p * q;

        int e = 7;
        int phi = (p - 1) * (q - 1);

        int d = gcdex(e, phi);

        // Message to be encrypted
        int msg = 12;

        System.out.println("Message data = " + msg);

        // Encryption c = (msg ^ e) % n
        int c = Math.floorMod((long) Math.pow(msg, e), n);
        System.out.println("Encrypted data = " + c);

        // Decryption m = (c ^ d) % n
        int m = Math.floorMod((long) Math.pow(c, d), n);
        System.out.println("Original Message Sent = " + m);
    }

    private static int gcdex(int e, int phi) {
        int x0 = phi;
        int x1 = e;
        int y0 = phi;
        int y1 = 1; // base case

        while (x1 != 1) {
            //left column
            int div = x0 / x1; // 5
            int res = x1 * div; // 35
            int tempX1 = x1; // 7
            x1 = x0 - res;      // 40-35=5
            x0 = tempX1; // 7

            //right column
            int resY = y1 * div; // 5
            int tempY1 = y1;
            y1 = y0 - resY; // 40 - 35
            if (y1 < 0) {
                y1 = Math.floorMod(y1, phi);
            }
            y0 = tempY1;

        }

        return y1;
    }

}
