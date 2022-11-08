package com.s4ng.jobbylanguage.configuration

import org.jasypt.encryption.StringEncryptor
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JasyptConfig {

    @Bean(name = ["jasyptStringEncryptor"])
    fun stringEncryptor(): StringEncryptor {

        val key = "s4ng95";
        val encryptor = PooledPBEStringEncryptor();
        val config = SimpleStringPBEConfig();
        config.password = key; // 암호화할 때 사용하는 키
        config.algorithm = "PBEWithMD5AndDES"; // 암호화 알고리즘
        config.setKeyObtentionIterations("1000"); // 반복할 해싱 회수
        config.setPoolSize("1"); // 인스턴스 pool
        config.providerName = "SunJCE";
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator"); // salt 생성 클래스
        config.stringOutputType = "base64"; //인코딩 방식
        encryptor.setConfig(config);
        return encryptor;
    }
}