
public class PlayfairCipher {

    private static String prepareText(String text) {
        text = text.toUpperCase().replaceAll("[^A-Z]", ""); 
        StringBuilder preparedText = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);
            preparedText.append(currentChar);

            if (i + 1 < text.length() && currentChar == text.charAt(i + 1)) {
                preparedText.append('X');
            }
        }

        if (preparedText.length() % 2 != 0) {
            preparedText.append('X');
        }

        return preparedText.toString();
    }

    private static char[][] createMatrix(String key) {
        StringBuilder uniqueChars = new StringBuilder();
        boolean[] used = new boolean[26];

        for (char ch : key.toUpperCase().toCharArray()) {
            if (ch >= 'A' && ch <= 'Z' && !used[ch - 'A']) {
                used[ch - 'A'] = true;
                uniqueChars.append(ch);
            }
        }

        for (char ch = 'A'; ch <= 'Z'; ch++) {
            if (ch != 'J' && !used[ch - 'A']) {
                uniqueChars.append(ch);
            }
        }

        char[][] matrix = new char[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                matrix[i][j] = uniqueChars.charAt(i * 5 + j);
            }
        }

        return matrix;
    }

    private static String encrypt(String text, String key) {
        String preparedText = prepareText(text);
        char[][] matrix = createMatrix(key);
        StringBuilder encryptedText = new StringBuilder();

        for (int i = 0; i < preparedText.length(); i += 2) {
            char first = preparedText.charAt(i);
            char second = preparedText.charAt(i + 1);

            int[] pos1 = findPosition(matrix, first);
            int[] pos2 = findPosition(matrix, second);

            if (pos1[0] == pos2[0]) { // Same row
                encryptedText.append(matrix[pos1[0]][(pos1[1] + 1) % 5]);
                encryptedText.append(matrix[pos2[0]][(pos2[1] + 1) % 5]);
            } else if (pos1[1] == pos2[1]) { // Same column
                encryptedText.append(matrix[(pos1[0] + 1) % 5][pos1[1]]);
                encryptedText.append(matrix[(pos2[0] + 1) % 5][pos2[1]]);
            } else { 
                encryptedText.append(matrix[pos1[0]][pos2[1]]);
                encryptedText.append(matrix[pos2[0]][pos1[1]]);
            }
        }

        return encryptedText.toString();
    }

    private static int[] findPosition(char[][] matrix, char ch) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (matrix[i][j] == ch) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        String key = "PLAYFAIR EXAMPLE";
        String text = "HIDE THE GOLD IN THE TREE";

        String encryptedText = encrypt(text, key);
        System.out.println("Encrypted: " + encryptedText);
    }
}