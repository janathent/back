package com.house.back.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class TestController {

    @CrossOrigin
    @RequestMapping("api/test")
    @ResponseBody
    public String test(@RequestParam("image") MultipartFile[] file,@RequestParam("username") String username) throws IOException {



        for(int i = 0 ; i < file.length ; ++i){
            String fileRealName = file[i].getOriginalFilename();
            String newFileName = Integer.toString(i) + fileRealName;
            String filePath = "D:\\FileAll" ;
            File path = new File(filePath);
            if(!path.exists()){
                path.mkdirs();
            }
            File savedFile = new File(filePath + "\\" + newFileName);
            file[i].transferTo(savedFile);
        }
        return "ok";
    }

        //System.out.println(request.getParameter("username")+"======");
        //try {




       //     String fileRealName = file.getOriginalFilename();//获得原始文件名;
           // int pointIndex =  fileRealName.lastIndexOf(".");//点号的位置
           // String fileSuffix = fileRealName.substring(pointIndex);//截取文件后缀
           // String fileNewName = "newFile1";//新文件名,自己设置
           // String saveFileName = fileNewName.concat(fileSuffix);//新文件完整名（含后缀）
           // System.out.println(saveFileName);
//            String filePath  = "D:\\FileAll" ;
//            File path = new File(filePath); //判断文件路径下的文件夹是否存在，不存在则创建
//            if (!path.exists()) {
//                path.mkdirs();
//            }
          //  File savedFile = new File(filePath+"\\"+saveFileName);
//            File savedFile = new File(filePath + "\\" + fileRealName);
//            boolean isCreateSuccess = savedFile.createNewFile(); // 是否创建文件成功
//            if(isCreateSuccess){
//                file.transferTo(savedFile);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "ok";
   // }

}
