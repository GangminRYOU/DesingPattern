package org.example.behavioral.chain_of_responsibility.middleware;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThrottlingMiddleware extends Middleware{
    private int requestPerMinute;
    private int request;
    private long currentTime;

    public ThrottlingMiddleware(int requestPerMinute) {
        this.requestPerMinute = requestPerMinute;
        this.currentTime = System.currentTimeMillis();
    }

    @Override
    public boolean check(String email, String password) {
        if(System.currentTimeMillis() > currentTime + 60_000) {
            request = 0;
            currentTime = System.currentTimeMillis();
        }
        request++;
        if(request > requestPerMinute){
            log.error("요청 제한을 넘어섰습니다.");
            Thread.currentThread().interrupt();
        }
        return checkNext(email, password);
    }
}
