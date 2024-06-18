package com.evaluation.client.utils;

import org.springframework.stereotype.Component;

@Component
public class Encryption {
    public String resolveSha256(String base) {
        try {
            java.security.MessageDigest resolve = java.security.MessageDigest.getInstance(Constants.ALGORITHM);
            byte[] hash = resolve.digest(base.getBytes(Constants.CHARSET_NAME));
            StringBuilder result = new StringBuilder();
            for (byte h : hash) {
                String representation = Integer.toHexString(0xff & h);
                if (representation.length() == 1)
                    result.append('0');
                result.append(representation);
            }
            return result.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
