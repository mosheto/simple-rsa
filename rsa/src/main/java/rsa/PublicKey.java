package rsa;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class PublicKey {
    private BigInteger publicExponent;
    private BigInteger modulus;

    public PublicKey(BigInteger publicExponent, BigInteger modulus) {
        this.publicExponent = publicExponent;
        this.modulus = modulus;
    }

    /**
     * Exports the key into a file and it will be in the current directory
     * if there is a file with the same name it'll be overridden
     * @param filename
     */
    public void save(String filename) throws IOException {
        try(BufferedWriter bw = Files.newBufferedWriter(Paths.get(filename + ".pub"))) {

            String e = Base64.getEncoder().encodeToString(this.publicExponent.toByteArray());
            String n = Base64.getEncoder().encodeToString(this.modulus.toByteArray());

            bw.write(e);
            bw.newLine();
            bw.write(n);
        }
    }

    /**
     * Imports the key from a file
     * @param filename
     */
    public static PublicKey load(String filename) throws IOException {
        BigInteger e, n;
        try(BufferedReader br = Files.newBufferedReader(Paths.get(filename + ".pub"))) {
            e = new BigInteger(Base64.getDecoder().decode(br.readLine()));
            n = new BigInteger(Base64.getDecoder().decode(br.readLine()));
        }

        return new PublicKey(e, n);
    }

    public BigInteger getPublicExponent() {
        return publicExponent;
    }

    public BigInteger getModulus() {
        return modulus;
    }

    @Override
    public String toString() {
        return this.publicExponent + " " + this.modulus;
    }
}