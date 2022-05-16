package com.hj.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@Controller
public class TestController {

    @RequestMapping("/")
    public String test2(){
        return "index";
    }

    /**
     * 文件上传
     * @param photo
     * @param session
     * @return
     * @throws IOException
     */
    @RequestMapping("/testUp")
    public String test(MultipartFile photo, HttpSession session) throws IOException {
        String fileName = photo.getOriginalFilename();
        System.out.println("fileName"+fileName);
        ServletContext servletContext = session.getServletContext();
        String realPath = servletContext.getRealPath("photo");
        System.out.println("realpath"+realPath);
        File file = new File(realPath);
        if (!file.exists()){
            file.mkdir();
        }
        String finalPath = realPath + File.separator + fileName;
        System.out.println(finalPath);
        photo.transferTo(new File(finalPath));
        return "order/list";
    }
}
