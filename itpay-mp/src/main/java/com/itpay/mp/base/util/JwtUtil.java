package com.itpay.mp.base.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.itpay.core.exception.CoreBusiRunTimeException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;


/**
 * jwt 工具类
 * @author feng
 */
public class JwtUtil {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    private static final String ISSUER = "payment";
    /**
     * token 有效时间
     */
    private static final int EXPTIME = 60*30 ;

    private static final String SECRET = "qugs30f2k4";

    /**
     * 创建一个token
     * @param algorithm 加密信息
     * @param issuer token 的签发者
     * @param subject token 的所属人
     * @param exp token 的有效期
     * @return token
     */
    public static String createToken(Algorithm algorithm,String issuer,String subject,Date exp){
        try {
            return JWT.create()
                    .withIssuer(issuer)
                    .withSubject(subject)
                    .withExpiresAt(exp)
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            logger.error("token 创建失败!",exception);
            return null;
        }
    }

    /**
     * 创建一个默认 配置的token
     * @param algorithm 证书信息
     * @param subject token所属人
     * @return
     */
    public static String createDefaultToken(Algorithm algorithm,String subject){
        String token = createToken(algorithm,ISSUER,subject,DateUtils.addSeconds(new Date(),EXPTIME));
        if(StringUtils.isBlank(token)){
            throw new CoreBusiRunTimeException("token生成失败");
        }
        return token;
    }

    /**
     * 创建一个HMAC256的token
     * @param subject token所属人
     * @return token
     */
    public static String createTokenByHMAC256(String subject){
        Algorithm algorithm = createAlgorithmByHMAC256();
        if(algorithm == null){
            throw new CoreBusiRunTimeException("token生成失败");
        }
        return createDefaultToken(algorithm,subject);
    }

    /**
     * 创建一个RSA256的token
     * @param privateKey 私钥信息
     * @param subject token所属人
     * @return token
     */
    public static String createTokenByRSA256(RSAPrivateKey privateKey,String subject){
        Algorithm algorithm = createAlgorithmByRSA256(null,privateKey);
        if(algorithm == null){
            throw new CoreBusiRunTimeException("token生成失败");
        }
        return createDefaultToken(algorithm,subject);
    }

    /**
     * 验证一个HMAC256加密的token
     * @param token 需要验证的信息
     * @param subject token所属人
     * @return true  验证通过  false验证失败
     */
    public static boolean verifyByHMAC256(String token,String subject){
        Algorithm algorithm = createAlgorithmByHMAC256();
        return algorithm!=null && verify(algorithm,token,subject);
    }
    /**
     * 验证一个HMAC256加密的token
     * @param token 需要验证的信息
     * @return true  验证通过  false验证失败
     */
    public static boolean verifyByHMAC256(String token){
        Algorithm algorithm = createAlgorithmByHMAC256();
        return algorithm!=null && verify(algorithm,token);
    }

    /**
     * 验证RSA256的token
     * @param publicKey 公钥信息
     * @param token 需要验证的信息
     * @param subject token所属人
     * @return
     */
    public static boolean verifyByRSA256(RSAPublicKey publicKey,String token,String subject){
        Algorithm algorithm = createAlgorithmByRSA256(publicKey,null);
        return algorithm!=null && verify(algorithm,token,subject);
    }


    /**
     * 验证RSA256的token
     * @param publicKey 公钥信息
     * @param token 需要验证的信息
     * @return
     */
    public static boolean verifyByRSA256(RSAPublicKey publicKey,String token){
        Algorithm algorithm = createAlgorithmByRSA256(publicKey,null);
        return algorithm!=null && verify(algorithm,token);
    }


    /**
     * 验证token
     * @param algorithm 证书信息
     * @param token token
     * @param subject token所属人
     * @return true  验证通过  false验证失败
     */
    public static boolean verify(Algorithm algorithm,String token,String subject){
        try {
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .withSubject(subject)
                    .acceptExpiresAt(1L)
                    .build();
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException exception){
            logger.warn("验证失败！Invalid signature/claims.",exception);
            return false;
        }
    }

    /**
     * 验证token
     * @param algorithm 证书信息
     * @param token token
     * @return true  验证通过  false验证失败
     */
    public static boolean verify(Algorithm algorithm,String token){
        try {
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .acceptExpiresAt(1L)
                    .build();
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException exception){
            logger.warn("验证失败！Invalid signature/claims.",exception);
            return false;
        }
    }


    /**
     * 创建HMAC256的加密信息
     * @return Algorithm
     */
    public static Algorithm createAlgorithmByHMAC256(){
        try {
            return Algorithm.HMAC256(SECRET);
        } catch (UnsupportedEncodingException e) {
            logger.error("Algorithm 创建失败!",e);
            return null;
        }
    }


    /**
     * 创建RSA256加密信息
     * @param publicKey 公钥 用于验签
     * @param privateKey 私钥 用于加签
     * @return Algorithm
     */
    public static Algorithm createAlgorithmByRSA256(RSAPublicKey publicKey, RSAPrivateKey privateKey){
        try {
            return Algorithm.RSA256(publicKey,privateKey);
        } catch (IllegalArgumentException e) {
            logger.error("Algorithm 创建失败!",e);
            return null;
        }
    }



}
