<%@ page import="java.util.stream.Collectors" %>

    <%@ page import="java.util.*" %>

        <% /*Escriba un programa que muestre una tirada de un número de dados al azar entre 2 y 7 y los ordene de menor
            a mayor.*/ Random random=new Random(); int tamanoAleatorio=random.nextInt(7 - 2 + 1) + 2; ArrayList<Integer>
            dados = new ArrayList<Integer>();

                for (int i = 0; i < tamanoAleatorio; i++) { dados.add(random.nextInt(6) + 1); } %>

                    <%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
                        <!DOCTYPE html>
                        <html>

                        <head>
                            <title>JSP - Hello World</title>
                        </head>

                        <body>
                            <h1>
                                <%= "Ordenar dados" %>
                            </h1>
                            <p>Actualice la página para mostrar una nueva turada</p>
                            <h2>Tirada de <%= dados.size()%> dados</h2>

                            <% for (int dado : dados) { %>
                                <img src="imgDados/<%= dado %>.svg" alt="Dado <%= dado %>">
                                <% } %>

                                    <h3>Tirada ordenada</h3>

                                    <% List<Integer> dadosOrdenados = dados.stream()
                                        .sorted((dado1, dado2) -> dado1.compareTo(dado2))
                                        .collect(Collectors.toList());

                                        for (int dado : dadosOrdenados) {
                                        %>
                                        
                                        <img src="imgDados/<%= dado %>.svg" alt="Dado <%= dado
%>">

                                        <% } %>

                        </body>

                        </html>