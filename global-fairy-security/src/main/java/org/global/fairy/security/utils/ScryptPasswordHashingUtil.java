package org.global.fairy.security.utils;

import com.lambdaworks.crypto.SCryptUtil;

/**
 * 加密算法
 * 
 * @author jiao
 *
 */
public class ScryptPasswordHashingUtil {
	
	/**
	 * 创建密码密文
	 * 
	 * @param originalPassword
	 * @return
	 */
	public static String createScryptHash(String originalPassword){
		String generatedSecuredPasswordHash = SCryptUtil.scrypt(originalPassword, 16, 16, 16);
        System.out.println(generatedSecuredPasswordHash);
		return generatedSecuredPasswordHash;
	}
	
	/**
	 * 验证密码
	 * 
	 * @param originalPassword
	 * @param pwdHash
	 * @return
	 */
	public static boolean checkPassword(String originalPassword,String pwdHash){
		 return SCryptUtil.check(originalPassword, pwdHash);
	}
	public static void main(String[] args) {
        String originalPassword = "password";
        String generatedSecuredPasswordHash = SCryptUtil.scrypt(originalPassword, 16, 16, 16);
        System.out.println(generatedSecuredPasswordHash);
         
        boolean matched = SCryptUtil.check("password", generatedSecuredPasswordHash);
        System.out.println(matched);
         
        matched = SCryptUtil.check("passwordno", generatedSecuredPasswordHash);
        System.out.println(matched);
    }

}
