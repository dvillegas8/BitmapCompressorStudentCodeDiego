/******************************************************************************
 *  Compilation:  javac BitmapCompressor.java
 *  Execution:    java BitmapCompressor - < input.bin   (compress)
 *  Execution:    java BitmapCompressor + < input.bin   (expand)
 *  Dependencies: BinaryIn.java BinaryOut.java
 *  Data files:   q32x48.bin
 *                q64x96.bin
 *                mystery.bin
 *
 *  Compress or expand binary input from standard input.
 *
 *  % java DumpBinary 0 < mystery.bin
 *  8000 bits
 *
 *  % java BitmapCompressor - < mystery.bin | java DumpBinary 0
 *  1240 bits
 ******************************************************************************/

/**
 *  The {@code BitmapCompressor} class provides static methods for compressing
 *  and expanding a binary bitmap input.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 *  @author Zach Blick
 *  @author Diego Villegas
 */
public class BitmapCompressor {

    /**
     * Reads a sequence of bits from standard input, compresses them,
     * and writes the results to standard output.
     */
    public static void compress() {

        // TODO: complete compress()
        // New Method
        int counter = 0;
        boolean lastBit = BinaryStdIn.readBoolean();
        counter++;
        boolean bit = false;
        // read a bit
        bit = BinaryStdIn.readBoolean();
        // if it defers from the last bit read
        if(lastBit != bit){
            // write the current count
            BinaryStdOut.write(counter);
            // reset count
            counter = 1;
        }
        // if the same as last bit read, and the count is a maximum
        // write a 0 count
        // reset count
        else{
            counter++;
        }

        // if the same as last bit read, and the count is a maxixum
        // write a 0 count
        // reset count
        String s  = BinaryStdIn.readString();
        int n = s.length();
        int i = 0;
        // For each number in s
        while(i < n){
            // Check the next 8 bits (as a char) is only 0's
            if(s.charAt(i) == 0) {
                // Write a single 0 meaning a line with 8 bits of 0's
                BinaryStdOut.write(false);
            }
            else{
                // Writes the rest of the char/byte (since there is probably a 1)
                BinaryStdOut.write(s.charAt(i));
            }
            i++;
        }
        BinaryStdOut.close();
    }

    /**
     * Reads a sequence of bits from standard input, decodes it,
     * and writes the results to standard output.
     */
    public static void expand() {

        // TODO: complete expand()

        BinaryStdOut.close();
    }

    /**
     * When executed at the command-line, run {@code compress()} if the command-line
     * argument is "-" and {@code expand()} if it is "+".
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        if      (args[0].equals("-")) compress();
        else if (args[0].equals("+")) expand();
        else throw new IllegalArgumentException("Illegal command line argument");
    }
}