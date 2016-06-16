package com.example.madhaviruwandika.teacher_assistant.Model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.Calendar;

import android.content.Context;
import android.nfc.Tag;
import android.security.KeyPairGeneratorSpec;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.security.auth.x500.X500Principal;

/**
 * Created by Madhavi Ruwandika on 6/1/2016.
 */
public class PasswardEncryptor {

    private static final String ALGORITHM = "AES";
    private static final String KEY = "1Hbfh667adfDEJ78";

    /*
    this is method for encrypting password
     */
    public static String encrypt(String value) throws Exception
    {
        // generate key for encrypt string using provided private key
        Key key = generateKey();
        Cipher cipher = Cipher.getInstance(PasswardEncryptor.ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte [] encryptedByteValue = cipher.doFinal(value.getBytes("utf-8"));
        String encryptedValue64 = Base64.encodeToString(encryptedByteValue,Base64.DEFAULT);
        return encryptedValue64;

    }

    /*
    thos is a method for decrypting passward
     */
    public static String decrypt(String value) throws Exception
    {
        Key key = generateKey();
        Cipher cipher = Cipher.getInstance(PasswardEncryptor.ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte [] decryptedValue64 = Base64.decode(value.getBytes(), Base64.DEFAULT);
        byte [] decryptedByteValue = cipher.doFinal(decryptedValue64);
        String decryptedValue = new String(decryptedByteValue,"utf-8");
        return decryptedValue;

    }

    private static Key generateKey() throws Exception
    {
        Key key = new SecretKeySpec(PasswardEncryptor.KEY.getBytes(),PasswardEncryptor.ALGORITHM);
        return key;
    }

}
