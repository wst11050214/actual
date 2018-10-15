package com.manageplat.filerecord.controller;

import cn.gfire.base.mvc.controller.BaseController;
import com.country.utils.FileOpUtils;
import com.manageplat.filerecord.domain.FileRecord;
import com.manageplat.filerecord.service.FileRecordService;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * Created by CTY on 2018/9/5/005.
 */


@Controller
@RequestMapping("fileRecord")
@ConfigurationProperties(prefix = "com.imdoa")
public class FileRecordController extends BaseController {

    private String attachFiles;

    @Resource
    private FileRecordService fileRecordService;



    @RequestMapping("downLoadFile")
    @ResponseBody
    public void downLoadFile(Long id, HttpServletResponse response)throws Exception{
        FileRecord fileRecord = fileRecordService.findOne(id).get();
        if(fileRecord!=null){
            String fileName = fileRecord.getFileName();
            File file = new File(attachFiles+"/"+fileName);
            String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
            FileOpUtils.downFile(response,file,"task_"+id+"."+suffix);
        }
        return;
    }

    public String getAttachFiles() {
        return attachFiles;
    }

    public void setAttachFiles(String attachFiles) {
        this.attachFiles = attachFiles;
    }
}
