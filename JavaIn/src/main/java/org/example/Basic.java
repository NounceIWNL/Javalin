package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import persons.Person;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Basic {
    public static void main(String[] args) {
//        hello();
//        getStaticPage();
//        createPerson();
//        createPersonForm();
//        changePerson();
//        deletePerson();
        inputPersonFormHeader();
    }

    static void hello() {
        var app = Javalin.create()
                .get("/", ctx -> ctx.result("Hello world"))
                .get("/hello", ctx -> ctx.result("Hello world"))
                .get("/hello/{name}", ctx -> {
                            ctx.result("Hello " + ctx.pathParam("name"));
                            ctx.status(202);
                        }
                )
                .get("/hello2/{name}/{surname}", ctx ->
                        ctx.json(new Person(ctx.pathParam("name"), ctx.pathParam("surname")))
                )
                .get("/sum", ctx -> { //http://127.0.0.1:7070/sum?a=1&b=2
                    int a = Integer.parseInt(ctx.queryParam("a"));
                    int b = Integer.parseInt(ctx.queryParam("b"));
                    ctx.result(String.valueOf(a / b));
                })

                .start(7070);
    }


    static void getStaticPage() {
        var app = Javalin.create(config -> config.addStaticFiles("src/main/resources", Location.EXTERNAL))
                .get("/", ctx -> ctx.html(new String(Files.readAllBytes(Paths.get("src/main/resources/hello.html")))))
                .get("/hello", ctx -> ctx.html("<h1>Hello world</h1>"))
                .start(7070);
    }


    static void createPerson() {
         /*
            fetch('http://localhost:7070/person', {
            method: 'POST',
            headers: {
              'Accept': 'application/json',
              'Content-Type': 'application/json'
            },
            body: JSON.stringify({"name": "Ivan", "surname": "Ivanov"})
          });
         */
        Set<Person> persons = new HashSet<>();
//        persons.add(new Person("name", "surname"));
        persons.stream().forEach(System.out::println);
        var app = Javalin.create(config -> config.enableCorsForAllOrigins())
                .get("/", ctx -> ctx.html(new String(Files.readAllBytes(Paths.get("src/main/resources/form.html")))))
                .post("/person", ctx -> {

                    persons.add(ctx.bodyAsClass(Person.class));
                    ctx.status(201);
                    persons.stream().forEach(System.out::println);
                })
                .start(7070);
    }

    static void changePerson() {
        /*
            fetch('http://localhost:7070/person/3', {
            method: 'PUT',
            headers: {
              'Accept': 'application/json',
              'Content-Type': 'application/json'
            },
            body: JSON.stringify({"name": "Ivan", "surname": "Ivanov"})
          });
         */
        Set<Person> persons = new HashSet<>();
        persons.add(new Person(-1, "name", "surname"));
        persons.add(new Person(2, "name2", "surname2"));
        var app = Javalin.create(config -> config.enableCorsForAllOrigins())
                .put("/person/{id}", ctx -> {
                    Optional<Person> person = persons.stream()
                            .filter(p -> p.getId() == Long.parseLong(ctx.pathParam("id")))
                            .findFirst();
                    Person personNew = ctx.bodyAsClass(Person.class);
                    if (person.isPresent()) {
                        person.get().setName(personNew.getName());
                        person.get().setSurname(personNew.getSurname());
                        ctx.status(201);
                    } else ctx.status(404);

                    persons.stream
                            ().forEach(System.out::println);
                })
                .start(7070);
    }

    static void deletePerson() {
        /*
            fetch('http://localhost:7070/person/1', {
            method: 'DELETE'
          });
         */
        Set<Person> persons = new HashSet<>();
        persons.add(new Person(-1, "name", "surname"));
        persons.add(new Person(2, "name2", "surname2"));
        var app = Javalin.create(config -> config.enableCorsForAllOrigins())
                .delete("/person/{id}", ctx -> {
                    Optional<Person> person = persons.stream()
                            .filter(p -> p.getId() == Long.parseLong(ctx.pathParam("id")))
                            .findFirst();
                    persons.remove(person.get());
                    persons.stream().forEach(System.out::println);
                })
                .start(7070);
    }

    static void createPersonForm() {
         /*
            fetch('http://localhost:7070/person', {
            method: 'PUT',
            headers: {
              'Accept': 'application/json',
              'Content-Type': 'application/json'
            },
            body: JSON.stringify({"name": "Ivan", "surname": "Ivanov"})
          });
         */
        Set<Person> persons = new HashSet<>();
        persons.add(new Person(1, "name", "surname"));
        persons.add(new Person(2, "name2", "surname2"));
        persons.stream().forEach(System.out::println);
        var app = Javalin.create(config -> config.enableCorsForAllOrigins())
                .get("/", ctx -> ctx.html(new String(Files.readAllBytes(Paths.get("src/main/resources/form.html")))))
                .post("/person", ctx -> {

                    persons.add(ctx.bodyAsClass(Person.class));
                    ctx.status(201);
                    persons.stream().forEach(System.out::println);
                })
                .start(7070);
    }

    //Передача данных через headers
    static void inputPersonFormHeader() {
//        fetch('http://localhost:7070/person', {
//                method: 'PUT',
//                headers: {
//            'Accept': 'application/json',
//                    'Content-Type': 'application/json'
//        },
//        body: JSON.stringify({"name": "Ivan", "surname": "Ivanov"})
//          });
        Set<Person> persons = new HashSet();
        persons.add(new Person("default", "default"));
        ObjectMapper mapper = new ObjectMapper();

        var app = Javalin.create(config ->
                        config.enableCorsForAllOrigins())
                .get("/form", ctx -> {
                    ctx.html(new String(Files.readAllBytes(Paths.get("src\\main\\resources\\html\\formListHeader.html"))));
                    ctx.header("person", "{name: name, surname: surname");
                })
                .start(7070);
    }
}


