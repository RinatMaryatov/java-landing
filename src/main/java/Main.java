import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class Main {

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/", new MyHandler());
        server.setExecutor(null);
        server.start();

        System.out.println("Server started at http://localhost:8080");
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = """
                    <!DOCTYPE html>
                    <html>
                    <head>
                        <meta charset="UTF-8">
                        <title>Java Landing</title>
                        <style>
                            body {
                                margin:0;
                                height:100vh;
                                display:flex;
                                justify-content:center;
                                align-items:center;
                                background: linear-gradient(135deg,#ff6a00,#ee0979);
                                font-family: Arial;
                                color:white;
                            }
                            .card{
                                background:rgba(255,255,255,0.1);
                                padding:40px;
                                border-radius:20px;
                                text-align:center;
                            }
                            button{
                                margin-top:20px;
                                padding:10px 20px;
                                border:none;
                                border-radius:20px;
                                cursor:pointer;
                            }
                        </style>
                    </head>
                    <body>
                        <div class="card">
                            <h1>🔥 Привет Мама :)!</h1>
                            <p>Как дела?</p>
                            <button onclick="alert('Я сделал свой сайт')">Нажми</button>
                        </div>
                    </body>
                    </html>
                    """;

            exchange.sendResponseHeaders(200, response.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
