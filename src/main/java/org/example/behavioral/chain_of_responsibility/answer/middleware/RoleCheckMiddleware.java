package org.example.behavioral.chain_of_responsibility.middleware;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RoleCheckMiddleware extends Middleware{
    @Override
    public boolean check(String email, String password) {
       if(email.equals("admin@example.com")) {
           log.error("Hello Admin!");
           return true;
       }
       log.error("Hello User!");
       return checkNext(email, password);
    }
}
