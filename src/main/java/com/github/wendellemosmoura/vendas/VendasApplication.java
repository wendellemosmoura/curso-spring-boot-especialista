package com.github.wendellemosmoura.vendas;

import com.github.wendellemosmoura.vendas.domain.entity.Cliente;
import com.github.wendellemosmoura.vendas.domain.repository.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class VendasApplication {

    @Bean
    public CommandLineRunner init(@Autowired Clientes clientes) {
        return args -> {
            System.out.println("Salvando clientes");
            clientes.save(new Cliente("Wendel"));
            clientes.save(new Cliente("Jane"));

            List<Cliente> todosClientes = clientes.findAll();
            todosClientes.forEach(System.out::println);

            System.out.println("Atualizando clientes");
            todosClientes.forEach(c -> {
                c.setNome(c.getNome() + " atualizado.");
                clientes.save(c);
            });

            System.out.println("Buscando clientes");
            clientes.findByNomeLike("Wen").forEach(System.out::println);

            System.out.println("Deletando clientes");
            clientes.deleteAll(clientes.findAll());

            todosClientes = clientes.findAll();
            if (todosClientes.isEmpty()) {
                System.out.println("Nenhum cliente encontrado.");
            } else {
                todosClientes.forEach(System.out::println);
            }
        };
    }

    @GetMapping("/")
    public String HelloWorld() {
        return "Hello, World!";
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}