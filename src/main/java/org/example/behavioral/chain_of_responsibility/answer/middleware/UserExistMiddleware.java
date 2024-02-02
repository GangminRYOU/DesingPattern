package org.example.behavioral.chain_of_responsibility.middleware;

import lombok.extern.slf4j.Slf4j;
import org.example.behavioral.chain_of_responsibility.server.Server;

@Slf4j
public class UserExistMiddleware extends Middleware{
    private Server server;

    public UserExistMiddleware(Server server) {
        this.server = server;
    }

    @Override
    public boolean check(String email, String password) {
        if(!server.hasEmail(email)){
            log.error("등록되지 않은 이메일 입니다.");
            return false;
        }
        if(!server.isValidPassword(email, password)){
            log.info("비밀번호가 유효하지 않습니다.");
            return false;
        }
        return checkNext(email, password);
    }
}
