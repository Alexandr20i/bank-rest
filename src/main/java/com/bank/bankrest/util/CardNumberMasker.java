package com.bank.bankrest.util;

public class CardNumberMasker {

    private CardNumberMasker() {}

    public static String mask(String encryptedNumber) {
        // здесь будет расшифровка + маска
        // пока считаем, что decrypted = "1234567812345678"
        String decrypted = decrypt(encryptedNumber);

        return "**** **** **** " + decrypted.substring(decrypted.length() - 4);
    }

    private static String decrypt(String encrypted) {
        // ВРЕМЕННО: реальную криптографию сделаем позже
        return encrypted;
    }
}
