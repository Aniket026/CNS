public class CaesarCipher {

    public static String encrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            if (Character.isUpperCase(ch)) {
                char encryptedChar = (char) (((ch + shift - 'A') % 26) + 'A');
                result.append(encryptedChar);
            }
            else if (Character.isLowerCase(ch)) {
                char encryptedChar = (char) (((ch + shift - 'a') % 26) + 'a');
                result.append(encryptedChar);
            } else {
                result.append(ch);
            }
        }

        return result.toString();
    }


    public static String decrypt(String text, int shift) {
        return encrypt(text, 26 - shift); 
    }
    public static void main(String[] args) {
        String text = "Hello, World!";
        int shift = 3;

        String encryptedText = encrypt(text, shift);
        System.out.println("Encrypted: " + encryptedText);

        String decryptedText = decrypt(encryptedText, shift);
        System.out.println("Decrypted: " + decryptedText);
    }
}
