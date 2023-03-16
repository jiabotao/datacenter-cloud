package org.datacenter.common.security.utils;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import java.text.ParseException;
import java.util.UUID;

public class JWTUtil {

    private static final String sharedSecret = UUID.randomUUID().toString().replaceAll("-","")+UUID.randomUUID().toString().replaceAll("-","");

    private static final JWSSigner signer;

    static {
        try {
            signer = new MACSigner(sharedSecret);
        } catch (KeyLengthException e) {
            throw new RuntimeException(e);
        }
    }
    private static final JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);

    private static final JWSVerifier verifier;

    static {
        try {
            verifier = new MACVerifier(sharedSecret);
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }

    public static String createJWT(String payloadJson){
        JWSObject jwsObject = new JWSObject(jwsHeader, new Payload(payloadJson));
        try {
            jwsObject.sign(signer);
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
        String jwt = jwsObject.serialize();
        return jwt;
    }

    public static boolean verifyJWT(String jwt){
        boolean verifiered = false;
        try {
            JWSObject jwsObject = JWSObject.parse(jwt);
            verifiered = jwsObject.verify(verifier);
        } catch (ParseException | JOSEException e) {
            throw new RuntimeException(e);
        }
        return verifiered;
    }

    public static String getPayload(String jwt){
        if(verifyJWT(jwt)){
            try {
                JWSObject jwsObject = JWSObject.parse(jwt);
                String decode = jwsObject.getPayload().toString();
                return decode;
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

}
