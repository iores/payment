package com.itpay.core.util;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.util.StringUtils;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * 密码工具类
 *
 * @author erika
 * @date 2018/1/7
 */
public class PassWordUtil {


    /**
     * 获取md5值
     *
     * @param key 需要获取md5的内容
     * @return md5
     */
    public static String createMd5(String key) {
        try {
            byte[] btInput = key.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            return new BigInteger(1, md).toString(16);
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 获取大写的md5值
     *
     * @param key 需要获取md5的内容
     * @return 大写的md5
     */
    public static String createMd5ByUpperCase(String key) {
        String md5 = createMd5(key);
        if (!StringUtils.isEmpty(md5)) {
            return md5.toUpperCase();
        }
        return null;
    }

    /**
     * 使用BCrypt 加密
     *
     * @param key 需要加密的内容
     * @return 返回加密的hash
     */
    public static String bcryptHashpw(String key) {
        return BCrypt.hashpw(key, BCrypt.gensalt());
    }

    /**
     * 使用BCrypt 加密
     *
     * @param key       需要加密的内容
     * @param logRounds 加盐参数
     * @return 返回加密的hash
     */
    public static String bcryptHashpw(String key, int logRounds) {
        return BCrypt.hashpw(key, BCrypt.gensalt(logRounds));
    }

    /**
     * 使用BCrypt 加密
     *
     * @param key  需要加密的内容
     * @param salt 加盐的值
     * @return 返回加密的hash
     */
    public static String bcryptHashpw(String key, String salt) {
        return BCrypt.hashpw(key, salt);
    }

    /**
     * 验证bcrypt 加密的内容
     *
     * @param plaintext 内容
     * @param hash      加密后的内容
     * @return 是否验证通过
     */
    public static boolean bcryptCheckpw(String plaintext, String hash) {
        return BCrypt.checkpw(plaintext, hash);
    }


    public static void main(String[] args) {
        String salt=BCrypt.gensalt(5);
        String hash = BCrypt.hashpw("zlf",salt );
        System.out.println(hash);
        //System.out.println(BCrypt.checkpw(hash2, hash));

    }

}
