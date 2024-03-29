package rsa;

import java.io.IOException;

/**
 * This class creates a wrapper for rsa library to use as cli app
 * for generating keys
 *
 * Usage: ./rsa filename
 *
 * filename is used for both private and public keys as follows:
 * - filename.pri for private key file
 * - filename.pub for public key file
 *
 */
class App {

    public static void main(String[] args) throws IOException {
        if(args.length < 1) {
            System.err.println("Usage: ./rsa filename");
            System.err.println("The generated files will be filename.pub and filename.pri");
            return;
        }

        long t0, t1;

        System.out.println("Generating Keys...");

        t0 = System.currentTimeMillis();
        KeyPair keyPair = KeyPair.generate();
        t1 = System.currentTimeMillis();

        keyPair.getPublic().save(args[0]);
        keyPair.getPrivate().save(args[0]);

        System.out.println("Keys are generated successfully");
        System.out.println("Time to generate keys is: " + (t1 - t0) + "ms");
    }
}
