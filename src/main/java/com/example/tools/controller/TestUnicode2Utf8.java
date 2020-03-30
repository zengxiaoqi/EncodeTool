package com.example.tools.controller;

import com.example.tools.Util.UTF2GBK;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;

@Slf4j
@RestController
@RequestMapping(value="/")
public class TestUnicode2Utf8 {
    /*@RequestMapping(value="",method = RequestMethod.POST)
    public void getBody(@RequestBody byte[] reqContent) throws ServletException {
        String strUcs = new String(reqContent, StandardCharsets.UTF_8);
        System.out.println("请求内容strUcs："+ strUcs);
        *//*String strUtf8 = UTF2GBK.unicodeToUtf8(strUcs);
        System.out.println("请求内容strUtf8："+ strUtf8);*//*
    }*/

    @RequestMapping(value="",method = RequestMethod.POST)
    public void httpRequest(HttpServletRequest request, HttpServletResponse httpServletResponse) throws ServletException {

        printHttpHeaderLog(request);
        //printHttpParamLog(request);
        try {
            String strBody = getHttpBody(request);
            String strUtf8 = UTF2GBK.unicodeToUtf8(strBody);
            System.out.println("UTF-8报文："+strUtf8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static void printHttpHeaderLog(HttpServletRequest request) {
        StringBuilder params = new StringBuilder("");
        Enumeration<String> headers = request.getHeaderNames();
        while (headers.hasMoreElements()) {
            String name = (String) headers.nextElement();
            String value = request.getHeader(name);
            params.append("HEADER:: > ");
            params.append(name);
            params.append(":");
            params.append(value);
            params.append("\n");
        }
        log.info(params.toString());
    }

    public static void printHttpParamLog(HttpServletRequest request) {
        log.info("请求类型：【{}】", request.getMethod());
        StringBuilder params = new StringBuilder("?");
        Enumeration<String> names = request.getParameterNames();
        /*if( request.getMethod().equals("GET") )*/ {
            while (names.hasMoreElements()) {
                String name = (String) names.nextElement();
                params.append(name);
                params.append("=");
                params.append(request.getParameter(name));
                params.append("&");
            }
            log.info("请求参数：【{}】", params.toString());
            return;
        }
        //return;
    }

    public static String getHttpBody(HttpServletRequest request) throws UnsupportedEncodingException {
        byte buffer[]=getRequestBytes(request);
        String charEncoding=request.getCharacterEncoding();
        if(charEncoding==null){
            log.info("没有上送编码类型：默认UTF-8");
            charEncoding="unicode";
        }
        log.info("报文编码：{}", charEncoding);
        String body= new String(buffer,charEncoding);
        log.info("BODY:: >{}" ,body);
        log.info("GBK编码：{}", new String(buffer,"GBK"));
        log.info("UTF8编码：{}", new String(buffer,"UTF-8"));
        log.info("UTF_16BE编码：{}", new String(buffer,"UTF_16BE"));
        log.info("UTF_16LE编码：{}", new String(buffer,"UTF_16LE"));
        log.info("UTF-16编码：{}", new String(buffer,"UTF-16"));
        log.info("US-ASCII编码：{}", new String(buffer,"US-ASCII"));
        log.info("ISO-8859-1编码：{}", new String(buffer,"ISO-8859-1"));
        log.info("unicode编码：{}", new String(buffer,"unicode"));
        return body;
    }

    public static byte[] getRequestBytes(HttpServletRequest request){
        int contentLength = request.getContentLength();
        byte buffer[] = new byte[contentLength];
        for (int i = 0; i < contentLength;) {
            try {

                int readlen = request.getInputStream().read(buffer, i, contentLength - i);
                if (readlen == -1) {
                    break;
                }
                i += readlen;
            } catch (IOException ioexception) {
                ioexception.printStackTrace();
            } finally {
                // logger.info("Json Request:" + requestPacket);
            }
        }
        return buffer;
    }
}


