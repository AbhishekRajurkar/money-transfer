# money-transfer
Technologies
JAX-RS API
Jetty Container (for Test and Demo app)

How to Build 

mvn clean install

How to run

mvn exec:java

Application starts a jetty server on localhost port 8080; In- memory Data Store- Hash Map is used. Map stores values as Account Id(long) and Account object.

This is the minimlistic implemetation. There is a potential downside on performance in using Map as Data - store As it needs to be locked for atomicity. 
Other approach could be to use H2 as in- memory DS. But i decided against it as it would add little more complexity around connections, managing transactions and controlling commits.

I am using JAX-RS and Jetty, even that could have been avoided - to use no framework at all. 

for example something like below could be writtn, but that would need lot of ground work on handling methods, reading params , close the exchange , flush the output. Avoided that againt to keep it simple and not create lot of clutter. 

int serverPort = 8080;
        HttpServer server = HttpServer.create(new InetSocketAddress(serverPort), 0);
        server.createContext("/transfer", (exchange -> {
            String respText = "withour framework";
            exchange.sendResponseHeaders(200, respText.getBytes().length);
            OutputStream output = exchange.getResponseBody();
            output.write(respText.getBytes());
            output.flush();
            exchange.close();
        }));
        server.setExecutor(null); // creates a default executor
        server.start();

Application initializes with some test data in the Map. 

Example PUT API - 

curl -H "Content-Type: application/json" -X PUT -d '{"amount":10,"fromAccountId":123,"toAccountId":124}' http://localhost:8080/transfer

Http Status
200 OK: The request has succeeded
500 Internal Server Error:
