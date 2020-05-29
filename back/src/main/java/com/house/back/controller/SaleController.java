package com.house.back.controller;


import com.house.back.pojo.Sale;
import com.house.back.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Controller
public class SaleController {
    @Autowired
    @Resource
    SaleService saleService;

    //在首页显示房屋信息
    @CrossOrigin
    @RequestMapping("api/saleshow")
    @ResponseBody
    public List<Sale> saleshow(){
        List<Sale> list = saleService.findall();
        return list;
    }

    //在个人中心里面显示自己上传的房屋的信息
    @CrossOrigin
    @RequestMapping("/api/salemanage")
    @ResponseBody
    public List<Sale> salemanage(@RequestParam("username") String username){
        List<Sale> list = saleService.findSaleByName(username);
        return list;
    }




    //卖家房屋上传信息，发布房源
    @CrossOrigin
    @RequestMapping("/api/saleupload")
    @ResponseBody
    public Integer saleupload(@RequestParam("image") MultipartFile[] file,@RequestParam("username") String username,
    @RequestParam("title") String title,@RequestParam("loaction") String loaction,@RequestParam("shi") String shi,
                             @RequestParam("ting") String ting,@RequestParam("wei") String wei,@RequestParam("direction") String direction,
                             @RequestParam("layernumber") String layernumber, @RequestParam("layertotal") String layertotal,
                             @RequestParam("decoration") String decoration,@RequestParam("heat") String heat,@RequestParam("elevator") String elevator,
                             @RequestParam("housetype") String housetype,@RequestParam("propertyrightstype") String propertyrightstype,
                             @RequestParam("propertyrightsyear") String propertyrightsyear,@RequestParam("square") String square,
                             @RequestParam("price") String price,@RequestParam("feature") String feature) throws IOException {
        Sale sale = new Sale();
        //卖家的姓名
        sale.setUsername(username);
        //卖方的标题
        sale.setTitle(title);
        //房屋的地点
        sale.setLoaction(loaction);
        //几室
        sale.setShi(shi);
        //几厅
        sale.setTing(ting);
        //几卫
        sale.setWei(wei);
        //朝向
        sale.setDirection(direction);
        //房屋所在层数
        sale.setLayernumber(layernumber);
        //房屋总共层数
        sale.setLayertotal(layertotal);
        //装修情况
        sale.setDecoration(decoration);
        //供暖情况
        sale.setHeat(heat);
        //电梯情况
        sale.setElevator(elevator);
        //房屋类型
        sale.setHousetype(housetype);
        //产权类型
        sale.setPropertyrightstype(propertyrightstype);
        //产权年限
        sale.setPropertyrightsyear(propertyrightsyear);
        //房屋面积
        sale.setSquare(square);
        //售价
        sale.setPrice(price);
        //房屋的特色
        sale.setFeature(feature);
        //图片的数量
        int picnumber = file.length;
        sale.setPicnumber(Integer.toString(picnumber));

        //图片的命名
        List<Sale> houselist = saleService.findSaleByName(username); //根据用户名来查这个用户已经发布了几套房了;
        int housenumber = houselist.size();  //发布房子的个数
        //图片的命名规则，用户名+以及发布的房数+最大ID+1 + 图片的1234编号；
        int maxid = saleService.findMaxId();
        String filePath1 = "D:\\HouseTransactions\\graduate\\front\\static\\housepic" ;
        File path = new File(filePath1);
        String allfilename = "";
        //不存在就创建一个文件夹
        if(!path.exists()){
            path.mkdirs();
        }
        for(int i = 0 ; i < file.length ;++i){
            String fileRealName = file[i].getOriginalFilename();//获得原始文件名;
            int pointIndex =  fileRealName.lastIndexOf(".");//点号的位置
            String fileSuffix = fileRealName.substring(pointIndex);//截取文件后缀
            Integer maxidp1 = maxid + 1;
            String newFileName = username + "_" + maxidp1+ "_" + i + fileSuffix;
            allfilename += (filePath1 + "\\" + newFileName + "#");
            File savedFile = new File(filePath1 + "\\" + newFileName);
            file[i].transferTo(savedFile);
        }
        sale.setPicpath(allfilename);
        saleService.addSale(sale);
        return maxid + 1;
    }




//    @CrossOrigin
//    @RequestMapping("/api/saleuploadimag")
//    @ResponseBody
//    public String test(@RequestParam("image") MultipartFile[] file, @RequestParam("username") String username) throws IOException {
//        String currenuser = username;
//        List<Sale> houselist = saleService.findSaleByName(currenuser);
//        //得到最新发布新房的id号，将与下面的图片绑定有关系,
//        int lastid = houselist.get(houselist.size() - 1).getId();
//        String filePath1 = "D:\\houseimag" ;
//        File path = new File(filePath1);
//        //不存在就创建一个文件夹
//        if(!path.exists()){
//            path.mkdirs();
//        }
//        for(int i = 0 ; i < file.length ; ++i){
//            String fileRealName = file[i].getOriginalFilename();//获得原始文件名;
//            int pointIndex =  fileRealName.lastIndexOf(".");//点号的位置
//            String fileSuffix = fileRealName.substring(pointIndex);//截取文件后缀
//            String newFileName = currenuser + "_" + lastid + "_" + i + fileSuffix;
//            File savedFile = new File(filePath1 + "\\" + newFileName);
//            file[i].transferTo(savedFile);
//        }
//        return "ok";
//    }





    //在个人中心里面显示自己上传的房屋的信息
    @CrossOrigin
    @RequestMapping("/api/saledelete")
    @ResponseBody
    public String saledelete(@RequestParam("id") Integer id){
        saleService.deleteSaleById(id);
        return "删除成功";
    }


    //返回根据ID查找的房屋信息
    @CrossOrigin
    @RequestMapping("/api/salemodifyshow")
    @ResponseBody
    public List<Sale> salemodifyshow(@RequestParam("id") Integer id){
        List<Sale> list = saleService.findSaleById(id);
        return list;
    }


    //根据ID修改上传的房子的信息
    @CrossOrigin
    @RequestMapping("/api/salemodifybyid")
    @ResponseBody
    public String salemodifybyid(@RequestParam("id") Integer id,@RequestParam("username") String username,
                                 @RequestParam("title") String title,@RequestParam("loaction") String loaction,@RequestParam("shi") String shi,
                                 @RequestParam("ting") String ting,@RequestParam("wei") String wei,@RequestParam("direction") String direction,
                                 @RequestParam("layernumber") String layernumber, @RequestParam("layertotal") String layertotal,
                                 @RequestParam("decoration") String decoration,@RequestParam("heat") String heat,@RequestParam("elevator") String elevator,
                                 @RequestParam("housetype") String housetype,@RequestParam("propertyrightstype") String propertyrightstype,
                                 @RequestParam("propertyrightsyear") String propertyrightsyear,@RequestParam("square") String square,
                                 @RequestParam("price") String price,@RequestParam("feature") String feature){
        saleService.updateSaleById(decoration,direction,elevator,feature,heat,housetype,layernumber,layertotal,loaction,price,propertyrightstype,propertyrightsyear,shi,square,ting,title,username,wei,id);
        return "ok";
    }

    //根据传来的要删除的图片信息删除图片，修改相应的数据的房屋数据表格,返回新的图片地址数据
    @CrossOrigin
    @RequestMapping("/api/saledeletepicandmodifysalepath")
    @ResponseBody
    public String saledeletepicandmodifysalepath(@RequestParam("deletepath") String deletepath,@RequestParam("saleid") Integer saleid){
        List<Sale> list = saleService.findSaleById(saleid);
        String returnpath = new String();
        if(list.size() != 0){
            Sale sale = list.get(0);
            String orginpicpath = sale.getPicpath();
            //去掉末尾的#
            orginpicpath = orginpicpath.substring(0,orginpicpath.length() - 1);
            String[] splicpath = orginpicpath.split("#");
            //加入到链表比价好处理
            for(int i = 0 ; i < splicpath.length ;++i){
                int lastindex = splicpath[i].lastIndexOf("\\");
                splicpath[i] = splicpath[i].substring(lastindex + 1,splicpath[i].length());
            }
            List<String> spilcestring = new ArrayList<>();
            for(int i = 0 ; i < splicpath.length ;++i){
                spilcestring.add(splicpath[i]);
            }
            int pos = spilcestring.indexOf(deletepath);
            spilcestring.remove(pos);
            String newpath = new String();
            String pichead = "D:\\HouseTransactions\\graduate\\front\\static\\housepic";
            for(int i = 0 ; i < spilcestring.size() ;++i){
                newpath += pichead + "\\"+ spilcestring.get(i) + "#";
            }
            returnpath = newpath;
            //根据id存储新的房屋地址
            saleService.updatesalepicpathbyid(newpath,saleid);
            //删除原来的图片
            String deletepathfin = pichead + "\\" + deletepath;
            File file = new File(deletepathfin);
            file.delete();

        }
        return returnpath;
    }

}
