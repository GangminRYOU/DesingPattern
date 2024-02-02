package org.example.behavioral.chain_of_responsibility.middleware;

public abstract class Middleware {
    private Middleware next;

    /**
     * 순회하며 객체들을 체이닝한다.
     * @param first
     * @param chain
     * @return
     */
    public static Middleware link(Middleware first, Middleware... chain){
        Middleware head = first;
        for (Middleware nextInChain : chain) {
            head.next = nextInChain;
            head = nextInChain;
        }
        return first;
    }

    /**
     * 검증 로직
     * @param email
     * @param password
     * @return
     */
    public abstract boolean check(String email, String password);

    /**
     * next가 null인 경우 모든 검증 통과 <-> 아닌 경우, 다음 검증 진행
     * @param email
     * @param password
     * @return
     */
    protected boolean checkNext(String email, String password){
        if(next == null){
            return true;
        }
        return next.check(email, password);
    }
}
