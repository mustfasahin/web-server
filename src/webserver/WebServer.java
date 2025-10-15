package webserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;

public class WebServer {

    private static final int PORT = 1989;
    private static final String WEB_ROOT = "./"; // Dosyalar aynı dizinde

    public static void main(String[] args){

        System.out.println("Web Server is starting...");
        System.out.println("The files are provided from this folder: " + WEB_ROOT);
        System.out.println("Open this address in your browser: http://localhost:" + PORT);

        try(ServerSocket serverSocket = new ServerSocket(PORT)){
            System.out.println("The server is listening on port " + PORT + "...\n");

            while(true){
                Socket clientSocket = serverSocket.accept();
                // ÖNEMLİ: Thread'i başlat!
                new RequestHandler(clientSocket).start();
            }
        } catch (IOException e){
            System.err.println("Server error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static class RequestHandler extends Thread{

        private Socket socket;

        public RequestHandler(Socket socket){
            this.socket = socket;
        }

        @Override
        public void run(){

            try(
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    OutputStream out = socket.getOutputStream();
                    PrintWriter writer = new PrintWriter(out);
            ){

                String requestLine = in.readLine();
                if(requestLine == null || requestLine.isEmpty()){
                    return;
                }

                System.out.println("Request: " + requestLine);

                String line;
                while((line = in.readLine()) != null && !line.isEmpty()){
                    // HTTP başlıklarını atla
                }

                String[] parts = requestLine.split(" ");
                if(parts.length < 2){
                    sendError(writer, out, 400, "Bad Request");
                    return;
                }

                String path = parts[1];

                if(path.equals("/") || path.equals("")){
                    path = "/index.html";
                }

                File file = new File(WEB_ROOT + path);

                if(!file.exists()){
                    System.out.println("File not found: " + file.getAbsolutePath());
                    sendError(writer, out, 404, "File Not Found");
                    return;
                }

                if(file.isDirectory()){
                    sendError(writer, out, 403, "Forbidden");
                    return;
                }

                sendFile(writer, out, file);
                System.out.println("Sent: " + file.getName() + "\n");

            } catch(IOException e){
                System.err.println("Request processing error: " + e.getMessage());
            } finally {
                try{
                    socket.close();
                } catch(IOException e){
                    e.printStackTrace();
                }
            }
        }

        private void sendFile(PrintWriter writer, OutputStream out, File file) throws IOException{

            String contentType = getContentType(file.getName());

            writer.println("HTTP/1.1 200 OK");
            writer.println("Content-Type: " + contentType);
            writer.println("Content-Length: " + file.length());
            writer.println("Connection: close");
            writer.println();
            writer.flush();

            Files.copy(file.toPath(), out);
            out.flush();
        }

        private void sendError(PrintWriter writer, OutputStream out, int code, String message) throws IOException{
            String htmlContent = "<html><body><h1>" + code + " - " + message + "</h1></body></html>";

            writer.println("HTTP/1.1 " + code + " " + message);
            writer.println("Content-Type: text/html");
            writer.println("Content-Length: " + htmlContent.length());
            writer.println("Connection: close");
            writer.println();
            writer.println(htmlContent);
            writer.flush();
        }

        private String getContentType(String fileName) {
            if (fileName.endsWith(".html") || fileName.endsWith(".htm")) {
                return "text/html; charset=UTF-8";
            } else if (fileName.endsWith(".css")) {
                return "text/css";
            } else if (fileName.endsWith(".js")) {
                return "application/javascript";
            } else if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) {
                return "image/jpeg";
            } else if (fileName.endsWith(".png")) {
                return "image/png";
            } else if (fileName.endsWith(".gif")) {
                return "image/gif";
            } else if (fileName.endsWith(".json")) {
                return "application/json";
            } else {
                return "text/plain";
            }
        }
    }
}