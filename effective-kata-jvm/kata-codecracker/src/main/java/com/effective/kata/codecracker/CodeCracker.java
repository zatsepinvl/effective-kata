package com.effective.kata.codecracker;

import java.util.HashMap;
import java.util.Map;

import static java.lang.String.format;
import static java.util.Collections.unmodifiableMap;

public class CodeCracker {
    private final Map<Character, Character> encryptionMapping;
    private final Map<Character, Character> decryptionMapping;

    public CodeCracker(String alphabet, String key) {
        if (alphabet.length() != key.length()) {
            throw new IllegalArgumentException("Alphabet should be the same length as key");
        }

        Map<Character, Character> encryptionMapping = new HashMap<>(alphabet.length());
        Map<Character, Character> decryptionMapping = new HashMap<>(alphabet.length());

        var keyChars = key.toCharArray();
        var alphabetChars = alphabet.toCharArray();
        for (var i = 0; i < alphabetChars.length; i++) {
            encryptionMapping.put(alphabetChars[i], keyChars[i]);
            decryptionMapping.put(keyChars[i], alphabetChars[i]);
        }
        this.encryptionMapping = unmodifiableMap(encryptionMapping);
        this.decryptionMapping = unmodifiableMap(decryptionMapping);
    }

    public String encrypt(String text) {
        return convert(text, encryptionMapping);
    }

    public String decrypt(String text) {
        return convert(text, decryptionMapping);
    }

    private String convert(String text, Map<Character, Character> mapping) {
        var builder = new StringBuilder();
        for (var ch : text.toCharArray()) {
            var symbol = mapping.get(ch);
            if (symbol == null) {
                throw new IllegalArgumentException(format("Unsupported symbol: '%s'", ch));
            }
            builder.append(symbol);
        }
        return builder.toString();
    }
}
