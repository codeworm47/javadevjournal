package com.javadevjournal.controller;

import com.javadevjournal.service.CustomerService;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
public class TokenController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/token")
    public String getToken(@RequestParam("username") final String username,
                           @RequestParam("password") final String password,
                           HttpServletRequest request, HttpSession session) {
        String token = customerService.login(username, password);
        session.invalidate();
        session = request.getSession();
        session.setAttribute("token", token);
        if (StringUtils.isEmpty(token)) {
            return "no token found";
        }
        return token;
    }

    @PostMapping("/token/fp")
    public String getTokenByFp(@RequestParam("file") MultipartFile fingerPrint) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("data:image/png;base64,");
        sb.append(org.apache.commons.codec.binary.StringUtils.newStringUtf8(Base64.encodeBase64(fingerPrint.getBytes(), false)));
        String s = sb.toString();
        String sha256hex = DigestUtils.sha256Hex(s);
        return sha256hex;
    }
}
