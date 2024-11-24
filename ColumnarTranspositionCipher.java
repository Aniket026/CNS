import java.util.Arrays;
import java.util.Comparator;

public class ColumnarTranspositionCipher {

    public static String encrypt(String text, String key) {
        text = text.replaceAll("\\s+", "").toUpperCase();
        int keyLength = key.length();
        int textLength = text.length();
        
        int numRows = (int) Math.ceil((double) textLength / keyLength);
        
        char[][] matrix = new char[numRows][keyLength];
        
        for (int i = 0; i < textLength; i++) {
            matrix[i / keyLength][i % keyLength] = text.charAt(i);
        }
        
        Integer[] keyOrder = new Integer[keyLength];
        for (int i = 0; i < keyLength; i++) {
            keyOrder[i] = i;
        }
        
        Arrays.sort(keyOrder, new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                return Character.compare(key.charAt(a), key.charAt(b));
            }
        });
        
        StringBuilder encryptedText = new StringBuilder();
        for (int i = 0; i < keyLength; i++) {
            int col = keyOrder[i];
            for (int j = 0; j < numRows; j++) {
                if (j < textLength / keyLength + (i < textLength % keyLength ? 1 : 0)) {
                    encryptedText.append(matrix[j][col]);
                }
            }
        }
        
        return encryptedText.toString();
    }

    public static String decrypt(String text, String key) {
        int keyLength = key.length();
        int textLength = text.length();
        
        int numRows = (int) Math.ceil((double) textLength / keyLength);
        
        char[][] matrix = new char[numRows][keyLength];
        
        int[] colLengths = new int[keyLength];
        for (int i = 0; i < textLength; i++) {
            colLengths[i % keyLength]++;
        }
        
        Integer[] keyOrder = new Integer[keyLength];
        for (int i = 0; i < keyLength; i++) {
            keyOrder[i] = i;
        }
        
        Arrays.sort(keyOrder, new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                return Character.compare(key.charAt(a), key.charAt(b));
            }
        });
        
        int index = 0;
        for (int i = 0; i < keyLength; i++) {
            int col = keyOrder[i];
            for (int j = 0; j < colLengths[col]; j++) {
                matrix[j][col] = text.charAt(index++);
            }
        }
        
        StringBuilder decryptedText = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < keyLength; j++) {
                if (matrix[i][j] != '\0') {
                    decryptedText.append(matrix[i][j]);
                }
            }
        }
        
        return decryptedText.toString();
    }

    public static void main(String[] args) {
        String key = "CIPHER";
        String text = "HELLO WORLD";
        
        String encryptedText = encrypt(text, key);
        System.out.println("Encrypted: " + encryptedText);
        
        String decryptedText = decrypt(encryptedText, key);
        System.out.println("Decrypted: " + decryptedText);
    }
}