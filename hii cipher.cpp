#include <iostream>
#include <vector>

using namespace std;

// Function to perform matrix multiplication and mod 26 operation
vector<int> multiplyMatrix(const vector<vector<int>>& keyMatrix, const vector<int>& textVector, int n) {
    vector<int> result(n, 0);

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            result[i] += keyMatrix[i][j] * textVector[j];
        }
        result[i] = result[i] % 26;  // Mod 26 as we're working with English alphabet
    }

    return result;
}

// Function to convert text to numbers (A=0, B=1, ..., Z=25)
vector<int> textToNumbers(const string& text) {
    vector<int> numbers;
    for (char c : text) {
        numbers.push_back(toupper(c) - 'A');
    }
    return numbers;
}

// Function to convert numbers back to text
string numbersToText(const vector<int>& numbers) {
    string text;
    for (int num : numbers) {
        text += (num + 'A');
    }
    return text;
}

int main() {
    int n; // Size of key matrix (n x n)

    // Input size of the matrix
    cout << "Enter the size of the key matrix (n x n): ";
    cin >> n;

    // Input the key matrix
    vector<vector<int>> keyMatrix(n, vector<int>(n));
    cout << "Enter the key matrix (row-wise, elements between 0 and 25):\n";
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cin >> keyMatrix[i][j];
        }
    }

    // Input plaintext
    string plaintext;
    cout << "Enter the plaintext (A-Z only): ";
    cin >> plaintext;

    // Padding plaintext if its length is not divisible by n
    while (plaintext.length() % n != 0) {
        plaintext += 'X';  // Padding with 'X' (any filler character could be used)
    }

    cout << "\nEncrypting...\n";

    // Encryption process
    string ciphertext = "";
    for (size_t i = 0; i < plaintext.length(); i += n) {
        // Convert block of text to numbers
        string block = plaintext.substr(i, n);
        vector<int> textVector = textToNumbers(block);

        // Multiply key matrix with text vector
        vector<int> cipherVector = multiplyMatrix(keyMatrix, textVector, n);

        // Convert result back to text and append to ciphertext
        ciphertext += numbersToText(cipherVector);
    }

    cout << "Ciphertext: " << ciphertext << endl;

    return 0;
}
