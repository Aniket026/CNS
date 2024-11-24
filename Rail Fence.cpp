#include <iostream>
#include <string>
#include <vector>

using namespace std;

string encryptRailFence(string text, int key) {
    if (key == 1) {
        return text;  
    }

    vector<string> rail(key);  
    bool direction_down = false;
    int row = 0;

    for (char ch : text) {
        rail[row].push_back(ch);

        if (row == 0 || row == key - 1) {
            direction_down = !direction_down;
        }

        row += (direction_down) ? 1 : -1;
    }

    string result;
    for (const string &r : rail) {
        result += r;
    }

    return result;
}

int main() {
    string text;
    int key;

    cout << "Enter the text to encrypt: ";
    getline(cin, text);
    cout << "Enter the number of rails: ";
    cin >> key;

    string encryptedText = encryptRailFence(text, key);
    cout << "Encrypted Text: " << encryptedText << endl;

    return 0;
}
