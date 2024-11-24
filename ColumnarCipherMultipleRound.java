import java.util.Arrays;

public class ColumnarCipherMultipleRound {

    // Encrypt using columnar transposition
    public static String encrypt(String text, int[] key, int rounds) {
        String encrypted = text;
        for (int i = 0; i < rounds; i++) {
            encrypted = singleRoundEncrypt(encrypted, key);
        }
        return encrypted;
    }

    // Single round of columnar encryption
    private static String singleRoundEncrypt(String text, int[] key) {
        int cols = key.length;
        int rows = (int) Math.ceil((double) text.length() / cols);

        char[][] grid = new char[rows][cols];

        // Fill the grid row-wise
        int index = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (index < text.length()) {
                    grid[r][c] = text.charAt(index++);
                } else {
                    grid[r][c] = ' '; // Fill with spaces if necessary
                }
            }
        }

        // Read columns in the order specified by the key
        StringBuilder result = new StringBuilder();
        for (int k : key) {
            for (int r = 0; r < rows; r++) {
                result.append(grid[r][k]);
            }
        }

        return result.toString();
    }

    // Decrypt using columnar transposition
    public static String decrypt(String text, int[] key, int rounds) {
        String decrypted = text;
        for (int i = 0; i < rounds; i++) {
            decrypted = singleRoundDecrypt(decrypted, key);
        }
        return decrypted;
    }

    // Single round of columnar decryption
    private static String singleRoundDecrypt(String text, int[] key) {
        int cols = key.length;
        int rows = (int) Math.ceil((double) text.length() / cols);

        char[][] grid = new char[rows][cols];

        // Fill the grid column-wise
        int index = 0;
        for (int k : key) {
            for (int r = 0; r < rows; r++) {
                if (index < text.length()) {
                    grid[r][k] = text.charAt(index++);
                }
            }
        }

        // Read the grid row-wise
        StringBuilder result = new StringBuilder();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                result.append(grid[r][c]);
            }
        }

        return result.toString().trim(); // Trim any extra spaces
    }

    public static void main(String[] args) {
        String plaintext = "This is a secret message!";
        int[] key = {2, 0, 3, 1}; // Column order
        int rounds = 3; // Number of encryption rounds

        System.out.println("Original Text: " + plaintext);

        // Encrypt
        String encrypted = encrypt(plaintext, key, rounds);
        System.out.println("Encrypted Text: " + encrypted);

        // Decrypt
        String decrypted = decrypt(encrypted, key, rounds);
        System.out.println("Decrypted Text: " + decrypted);
    }
}
