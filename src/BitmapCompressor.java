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
        boolean lastBit = false;
        boolean currentBit = false;
        // Go through each bit in the file
        while(!BinaryStdIn.isEmpty()){
            currentBit = BinaryStdIn.readBoolean();
            // Check if differs from last bit read
            if(currentBit != lastBit){
                // Write current count as a byte
                BinaryStdOut.writeByte(counter);
                // Reset count
                counter = 0;
            }
            // Check if we reach the maximum a byte can hold
            else if(counter > 255){
                // Write Counter
                BinaryStdOut.writeByte(counter);
                // Write a 0
                BinaryStdOut.writeByte(0);
                // Reset count
                counter = 0;
            }
            // Increment Count
            counter++;
            lastBit = currentBit;
        }
        // Writes the last length of 0's or 1's
        BinaryStdOut.writeByte(counter);
        BinaryStdOut.close();
    }

    /**
     * Reads a sequence of bits from standard input, decodes it,
     * and writes the results to standard output.
     */
    public static void expand() {

        // TODO: complete expand()
        int num = 0;
        // Keeps track of if we are printing 0s or 1s, we start with
        boolean startNum = false;
        // Go through the file by 8 bits
        while(!BinaryStdIn.isEmpty()){
            num = BinaryStdIn.readByte();
            // Print number of 1's or 0's
            for(int i = 0; i < num; i++){
                BinaryStdOut.write(startNum);
            }
            startNum = !startNum;
        }
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