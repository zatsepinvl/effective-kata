package com.effective.kata.codecracker;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CodeCrackerTest {

    private CodeCracker cracker;

    @Test
    void test_encrypt_then_decrypt() {
        // Given
        cracker = new CodeCracker(
                "abcdefghijklmnopqrstuvwxyz, ",
                "!)\"(+*%&><@abcdefghijklmno, "
        );
        String originalText = "hello, world";

        // When
        String encrypted = cracker.encrypt(originalText);
        String decrypted = cracker.decrypt(encrypted);

        // Then
        System.out.println("Original: " + originalText);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Descrypted: " + decrypted);
        assertThat(decrypted).isEqualTo(originalText);
    }
}
