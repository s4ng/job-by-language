package com.s4ng.jobbylanguage.configuration

import com.s4ng.jobbylanguage.bootstrap.JobbylanguageApplication
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(classes = [JobbylanguageApplication::class])
internal class JasyptConfigTest {

    @Test
    fun `암호화`() {
        val url = "jdbc:mysql://129.154.49.47:3306/jobbylanguage?allowPublicKeyRetriebal=true&useSSL=false"
        val username = "root"
        val password = "1111"

        println(this.jasyptEncoding(url));
        println(this.jasyptEncoding(username));
        println(this.jasyptEncoding(password));
    }

    fun jasyptEncoding(value: String): String {

        val key = "s4ng95";
        val pbeEnc = StandardPBEStringEncryptor();
        pbeEnc.setAlgorithm("PBEWithMD5AndDES");
        pbeEnc.setPassword(key);
        return pbeEnc.encrypt(value);
    }
}