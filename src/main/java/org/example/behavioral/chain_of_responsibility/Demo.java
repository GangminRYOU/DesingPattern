package org.example.behavioral.chain_of_responsibility;

import org.example.behavioral.chain_of_responsibility.answer.middleware.Middleware;
import org.example.behavioral.chain_of_responsibility.answer.middleware.RoleCheckMiddleware;
import org.example.behavioral.chain_of_responsibility.answer.middleware.ThrottlingMiddleware;
import org.example.behavioral.chain_of_responsibility.answer.middleware.UserExistMiddleware;
import org.example.behavioral.chain_of_responsibility.answer.server.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Demo {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static Server server;

    private static void init(){
        server = new Server();
        server.register("admin@example.com", "admin_pass");
        server.register("user@example.com", "user_pass");

        Middleware middleWare = Middleware.link(
                new ThrottlingMiddleware(2),
                new UserExistMiddleware(server),
                new RoleCheckMiddleware()
        );
        server.setMiddleware(middleWare);
    }

    public static void main(String[] args) throws IOException {
        init();

        boolean success = false;
        do {
            System.out.println("Enter Email: ");
            String email = bufferedReader.readLine();
            System.out.println("Input password: ");
            String password = bufferedReader.readLine();
            success = server.logIn(email, password);
        }while (!success);
    }
}
