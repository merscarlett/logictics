package com.project.logistics.models.mapper;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.project.logistics.controllers.auth.JWTToken;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-29T18:33:29+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class JWTTokenMapperImpl implements JWTTokenMapper {

    @Override
    public JWTToken toPayload(DecodedJWT jwt) {
        if ( jwt == null ) {
            return null;
        }

        JWTToken jWTToken = new JWTToken();

        jWTToken.setToken( jwt.getToken() );
        jWTToken.setType( jwt.getType() );
        jWTToken.setAlgorithm( jwt.getAlgorithm() );

        jWTToken.setExpiresAt( jwt.getExpiresAt().getTime() / 1000 );

        return jWTToken;
    }
}
