public class PolyalphabeticCipher {

    public static String encrypt(String plaintext, String key) {
        StringBuilder ciphertext = new StringBuilder();
        key = key.toLowerCase();
        int keyIndex = 0;

        for (char ch : plaintext.toCharArray()) {
            if (Character.isLetter(ch)) {
                boolean isUpperCase = Character.isUpperCase(ch);
                char base = isUpperCase ? 'A' : 'a';
                int shift = key.charAt(keyIndex % key.length()) - 'a'; 
                char encryptedChar = (char) ((ch - base + shift) % 26 + base);
                ciphertext.append(encryptedChar);

                keyIndex++; 
            } else {
              
                ciphertext.append(ch);
            }
        }

        return ciphertext.toString();
    }

    public static String decrypt(String ciphertext, String key) {
        StringBuilder plaintext = new StringBuilder();
        key = key.toLowerCase(); 
        int keyIndex = 0;

        for (char ch : ciphertext.toCharArray()) {
            if (Character.isLetter(ch)) {
                boolean isUpperCase = Character.isUpperCase(ch);
                char base = isUpperCase ? 'A' : 'a';
                int shift = key.charAt(keyIndex % key.length()) - 'a'; 
                char decryptedChar = (char) ((ch - base - shift + 26) % 26 + base);
                plaintext.append(decryptedChar);

                keyIndex++; 
            } else {
                
                plaintext.append(ch);
            }
        }

        return plaintext.toString();
    }

    public static void main(String[] args) {
        String plaintext = "Hello, World!";
        String key = "KEY";

        System.out.println("Original Text: " + plaintext);
        System.out.println("Key: " + key);

        String encrypted = encrypt(plaintext, key);
        System.out.println("Encrypted Text: " + encrypted);

        String decrypted = decrypt(encrypted, key);
        System.out.println("Decrypted Text: " + decrypted);
    }
}
