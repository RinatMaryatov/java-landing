import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

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
<title>Мир танков</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<style>
*{
    margin:0;
    padding:0;
    box-sizing:border-box;
    font-family:Arial, sans-serif;
}

body{
    height:100vh;
    display:flex;
    justify-content:center;
    align-items:center;
    overflow:hidden;
    background: linear-gradient(-45deg,#ff6ec4,#7873f5,#42e695,#f9ca24);
    background-size:400% 400%;
    animation: gradient 10s ease infinite;
    color:white;
}

@keyframes gradient{
    0%{background-position:0% 50%;}
    50%{background-position:100% 50%;}
    100%{background-position:0% 50%;}
}

.card{
    background:rgba(255,255,255,0.15);
    backdrop-filter:blur(10px);
    padding:50px;
    border-radius:25px;
    text-align:center;
    box-shadow:0 20px 40px rgba(0,0,0,0.3);
    animation: pop 1s ease forwards;
}

@keyframes pop{
    from{transform:scale(0.8); opacity:0;}
    to{transform:scale(1); opacity:1;}
}

h1{
    font-size:40px;
    margin-bottom:15px;
}

p{
    font-size:20px;
    margin-bottom:30px;
}

button{
    padding:12px 30px;
    border:none;
    border-radius:30px;
    cursor:pointer;
    font-weight:bold;
    transition:0.3s;
}

button:hover{
    transform:scale(1.1);
    background:#f9ca24;
    color:white;
}

.heart{
    position:absolute;
    bottom:-20px;
    font-size:20px;
    animation: float 6s linear infinite;
    opacity:0.7;
}

@keyframes float{
    0%{transform:translateY(0) scale(0); opacity:0;}
    50%{opacity:1;}
    100%{transform:translateY(-110vh) scale(1.5); opacity:0;}
}
</style>
</head>

<body>

<div class="card">
    <h1>Эдик петух</h1>
    <p>Ай фука капаток</p>
    <button onclick="showMessage()">Открыть</button>
</div>

<script>
function showMessage(){
    alert("ПУТИН ПУТИН ПУТИН!");
}

function createHeart(){
    const heart = document.createElement("div");
    heart.classList.add("heart");
    heart.innerHTML = "🇷🇺";
    heart.style.left = Math.random()*100 + "vw";
    heart.style.fontSize = (20 + Math.random()*20) + "px";
    document.body.appendChild(heart);

    setTimeout(()=>{
        heart.remove();
    },6000);
}

setInterval(createHeart,500);
</script>

</body>
</html>
""";

            exchange.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
            byte[] bytes = response.getBytes(StandardCharsets.UTF_8);

            exchange.sendResponseHeaders(200, bytes.length);
            OutputStream os = exchange.getResponseBody();
            os.write(bytes);
            os.close();
        }
    }
}
