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
<html lang="ru">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Мир Танков</title>
<style>
*{margin:0; padding:0; box-sizing:border-box; font-family:Arial, sans-serif;}
body{
    background: url('background.png') center/cover no-repeat;
    color: white;
    overflow-x: hidden;
}
header{
    display:flex;
    justify-content: space-between;
    padding: 20px 50px;
    background: rgba(0,0,0,0.5);
}
header h1{font-size:28px;}
nav a{
    margin-left:20px;
    text-decoration:none;
    color:white;
    font-weight:bold;
}
nav a:hover{color:#f9ca24;}
.hero{
    text-align:center;
    margin-top:100px;
}
.hero h2{
    font-size:50px;
    text-shadow: 2px 2px 10px black;
}
.hero p{
    font-size:20px;
    margin:20px 0;
}
button{
    padding:12px 25px;
    border:none;
    border-radius:25px;
    font-weight:bold;
    cursor:pointer;
    background:#f9ca24;
    transition:0.3s;
}
button:hover{
    transform:scale(1.1);
}
.carousel{
    display:flex;
    justify-content:center;
    margin-top:50px;
    gap:20px;
}
.carousel img{
    width:200px;
    border-radius:15px;
    transition: transform 0.3s;
}
.carousel img:hover{
    transform: scale(1.1);
}
.heart{
    position:absolute;
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

<header>
    <h1>Мир Танков</h1>
    <nav>
        <a href="#">Игра</a>
        <a href="#">Кланы</a>
        <a href="#">Киберспорт</a>
    </nav>
</header>

<div class="hero">
    <h2>С Днём Победы!</h2>
    <p>Память сильнее времени. Поздравляем всех с праздником!</p>
    <button onclick="showMessage()">Посмотреть видео</button>
</div>

<div class="carousel">
    <img src="background.png" alt="Парад 1">
    <img src="background.png" alt="Парад 2">
    <img src="background.png" alt="Парад 3">
</div>

<script>
function showMessage(){
    alert("С Днём Победы!");
}

function createHeart(){
    const heart = document.createElement("div");
    heart.classList.add("heart");
    heart.innerHTML = "🇷🇺";
    heart.style.left = Math.random()*100 + "vw";
    heart.style.fontSize = (20 + Math.random()*20) + "px";
    document.body.appendChild(heart);
    setTimeout(()=>heart.remove(),6000);
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
