package br.edu.inteli.redisapi.service;

import org.springframework.stereotype.Service;

import java.util.Base64;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Service for Redis
 */
@Service
public class RedisService {
    /**
     * Encode a CPF to a SHA-256 hash and then to a Base64 string
     * 
     * @param cpf CPF to be encoded
     * @return Redis hashSet key for user
     */
    public String encodePessoaFisicaId(String cpf) {
        String formatedCpf;
        try {
            Long.parseLong(cpf);
            System.out.println("CPF is a number");
            formatedCpf = cpf.replaceAll("(...)(...)(...)(..)", "$1.$2.$3-$4");
        } catch (NumberFormatException e) {
            System.out.println("CPF is a string");
            formatedCpf = cpf;
        }
        System.out.println("CPF: " + formatedCpf);
        try {
            final MessageDigest SHA256 = MessageDigest.getInstance("SHA-256");
            byte[] hash = SHA256.digest(formatedCpf.getBytes());
            System.out.println("Hash: " + Base64.getEncoder().encodeToString(hash));
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            return formatedCpf;
        }
    }
}
