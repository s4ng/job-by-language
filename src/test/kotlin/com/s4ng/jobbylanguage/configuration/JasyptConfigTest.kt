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
        val string = ""

        println(this.jasyptEncoding(string))
    }

    fun jasyptEncoding(value: String): String {

        val key = "s4ng95"
        val pbeEnc = StandardPBEStringEncryptor()
        pbeEnc.setAlgorithm("PBEWithMD5AndDES")
        pbeEnc.setPassword(key)
        return pbeEnc.encrypt(value)
    }
}