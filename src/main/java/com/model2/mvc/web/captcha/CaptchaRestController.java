package com.model2.mvc.web.captcha;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/captcha")
public class CaptchaRestController {

    @Value("${api.captcha.url}")
    private String clientId;

    @Value("${api.captcha.secret.key}")
    private String clientSecret;
    
    @Value("${captcha.dir}")
    private String captchaDir;

    @GetMapping("/nkey")
    public ResponseEntity<String> getCaptchaKey(@RequestParam("code") String code) {
        try {
            String apiURL = "https://naveropenapi.apigw.ntruss.com/captcha/v1/nkey?code=" + code;
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
            con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            return new ResponseEntity<>(response.toString(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
//    @GetMapping("/image")
//    public ResponseEntity<String> getCaptchaImage(@RequestParam("key") String key) {
//        try {
//            String apiURL = "https://naveropenapi.apigw.ntruss.com/captcha-bin/v1/ncaptcha?key=" + key + "&X-NCP-APIGW-API-KEY-ID=" + clientId;
//            URL url = new URL(apiURL);
//            HttpURLConnection con = (HttpURLConnection) url.openConnection();
//            con.setRequestMethod("GET");
//            int responseCode = con.getResponseCode();
//            if (responseCode == 200) {
//                InputStream is = con.getInputStream();
//                byte[] bytes = new byte[1024];
//                String tempname = captchaDir + Long.valueOf(new Date().getTime()).toString();
//                File f = new File(tempname + ".jpg");
//                f.createNewFile();
//                OutputStream outputStream = new FileOutputStream(f);
//                int read;
//                while ((read = is.read(bytes)) != -1) {
//                    outputStream.write(bytes, 0, read);
//                }
//                is.close();
//
//                
//                return new ResponseEntity<>(f.getName(), HttpStatus.OK);
//                //return new ResponseEntity<>(relativePath, HttpStatus.OK);
//                
//            } else {
//                BufferedReader br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
//                String inputLine;
//                StringBuffer response = new StringBuffer();
//                while ((inputLine = br.readLine()) != null) {
//                    response.append(inputLine);
//                }
//                br.close();
//                return new ResponseEntity<>(response.toString(), HttpStatus.BAD_REQUEST);
//            }
//        } catch (Exception e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
    
//    @GetMapping("/image")
//    public ResponseEntity<String> getCaptchaImage(@RequestParam("key") String key) {
//        try {
//            String apiURL = "https://naveropenapi.apigw.ntruss.com/captcha-bin/v1/ncaptcha?key=" + key + "&X-NCP-APIGW-API-KEY-ID=" + clientId;
//            URL url = new URL(apiURL);
//            HttpURLConnection con = (HttpURLConnection) url.openConnection();
//            con.setRequestMethod("GET");
//            int responseCode = con.getResponseCode();
//            if (responseCode == 200) {
//                InputStream is = con.getInputStream();
//                byte[] bytes = new byte[1024];
//                String fileName = Long.valueOf(new Date().getTime()).toString() + ".jpg";
//                File f = new File(captchaDir + fileName);
//                f.createNewFile();
//                OutputStream outputStream = new FileOutputStream(f);
//                int read;
//                while ((read = is.read(bytes)) != -1) {
//                    outputStream.write(bytes, 0, read);
//                }
//                is.close();
//                outputStream.close();
//
//                // 웹 경로를 생성하여 클라이언트에 반환
//                String relativePath = "/static/captcha/" + fileName;
//                return new ResponseEntity<>(relativePath, HttpStatus.OK);
//            } else {
//                BufferedReader br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
//                String inputLine;
//                StringBuffer response = new StringBuffer();
//                while ((inputLine = br.readLine()) != null) {
//                    response.append(inputLine);
//                }
//                br.close();
//                return new ResponseEntity<>(response.toString(), HttpStatus.BAD_REQUEST);
//            }
//        } catch (Exception e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
    
//    @GetMapping("/image")
//    public ResponseEntity<String> getCaptchaImage(@RequestParam("key") String key) {
//        try {
//            String apiURL = "https://naveropenapi.apigw.ntruss.com/captcha-bin/v1/ncaptcha?key=" + key + "&X-NCP-APIGW-API-KEY-ID=" + clientId;
//            URL url = new URL(apiURL);
//            HttpURLConnection con = (HttpURLConnection) url.openConnection();
//            con.setRequestMethod("GET");
//            int responseCode = con.getResponseCode();
//            if (responseCode == 200) {
//                InputStream is = con.getInputStream();
//                byte[] bytes = new byte[1024];
//                String fileName = Long.valueOf(new Date().getTime()).toString() + ".jpg";
//                File f = new File(captchaDir + fileName);
//                f.createNewFile();
//                OutputStream outputStream = new FileOutputStream(f);
//                int read;
//                while ((read = is.read(bytes)) != -1) {
//                    outputStream.write(bytes, 0, read);
//                }
//                is.close();
//                outputStream.close();
//
//                // 웹 경로를 생성하여 클라이언트에 반환
//                String relativePath = "/captcha/" + fileName;
//                return new ResponseEntity<>(relativePath, HttpStatus.OK);
//            } else {
//                BufferedReader br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
//                String inputLine;
//                StringBuffer response = new StringBuffer();
//                while ((inputLine = br.readLine()) != null) {
//                    response.append(inputLine);
//                }
//                br.close();
//                return new ResponseEntity<>(response.toString(), HttpStatus.BAD_REQUEST);
//            }
//        } catch (Exception e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
    @GetMapping("/image")
    public void getCaptchaImage(@RequestParam("key") String key, HttpServletResponse response) throws Exception {
        try {
            String apiURL = "https://naveropenapi.apigw.ntruss.com/captcha-bin/v1/ncaptcha?key=" + key + "&X-NCP-APIGW-API-KEY-ID=" + clientId;
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            if (responseCode == 200) {
                // 이미지를 응답으로 전송
                response.setContentType("image/jpeg"); // 이미지 형식에 맞게 Content-Type 설정
                InputStream is = con.getInputStream();
                OutputStream os = response.getOutputStream();
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = is.read(buffer)) != -1) {
                    os.write(buffer, 0, bytesRead);
                }
                os.flush();
                os.close();
                is.close();
            } else {
                // 오류 응답 전송
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Failed to fetch captcha image");
            }
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal server error");
        }
    }
    
    @GetMapping("/verify")
    public ResponseEntity<String> verifyCaptcha(@RequestParam("key") String key, @RequestParam("value") String value) {
        try {
            String code = "1";
            String apiURL = "https://naveropenapi.apigw.ntruss.com/captcha/v1/nkey?code=" + code + "&key=" + key + "&value=" + value;
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
            con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            return new ResponseEntity<>(response.toString(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
