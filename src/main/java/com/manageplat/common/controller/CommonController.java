package com.manageplat.common.controller;

import cn.gfire.base.mvc.controller.BaseController;
import cn.gfire.base.mvc.form.BaseDataResponse;
import com.country.utils.FileOpUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Map;

/**
 * Created by CTY on 2018/8/17/017.
 *  处理公共内容的url
 */
@Controller
@RequestMapping("common")
@ConfigurationProperties(prefix = "com.imdoa")
public class CommonController extends BaseController {

    private String  imagePath;

    private String attachFiles; //额外附件


    @RequestMapping("batchUpload")
    @ResponseBody
    public BaseDataResponse batchUpload(MultipartFile file){
        System.out.println(file);
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        File newFile = FileOpUtils.uplaodFile(file, attachFiles,"task_"+System.nanoTime()+"."+suffix);
        String name = newFile.getName();
        return BaseDataResponse.ok().data(name);
    }

    @RequestMapping("upload")
    @ResponseBody
    public BaseDataResponse upload(@RequestParam(value="myFileName")MultipartFile mf){
        File file = FileOpUtils.uplaodFile(mf, imagePath);
        String name = file.getName();
        String url ="/common/showPic?fileName="+name;
        return BaseDataResponse.ok().data(url);
    }

    @RequestMapping("showPic")
    public void showPic(HttpServletResponse response, String fileName)throws Exception{
        File file = new File(imagePath+"/"+fileName);
        FileOpUtils.downFile(response,file,"demo");
    }


    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getAttachFiles() {
        return attachFiles;
    }

    public void setAttachFiles(String attachFiles) {
        this.attachFiles = attachFiles;
    }
}
