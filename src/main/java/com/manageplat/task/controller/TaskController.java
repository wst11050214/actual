package com.manageplat.task.controller;

import cn.gfire.base.jpa.dao.query.BaseQuery;
import cn.gfire.base.mvc.controller.CrudController;
import cn.gfire.base.mvc.form.BaseDataResponse;
import cn.gfire.base.mvc.form.BaseForm;
import com.country.utils.*;
import com.manageplat.admin.domain.AdminUser;
import com.manageplat.admin.domain.Permission;
import com.manageplat.admin.service.AdminUserService;
import com.manageplat.admin.service.PermissionService;
import com.manageplat.dept.domain.Department;
import com.manageplat.dept.service.DepartmentService;
import com.manageplat.filerecord.domain.FileRecord;
import com.manageplat.filerecord.service.FileRecordService;
import com.manageplat.task.domain.Task;
import com.manageplat.task.domain.TaskRecord;
import com.manageplat.task.form.TaskForm;
import com.manageplat.task.query.TaskQuery;
import com.manageplat.task.query.TaskRecordQuery;
import com.manageplat.task.service.TaskRecordService;
import com.manageplat.task.service.TaskService;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by CTY on 2018/8/16/016.
 */
@RequestMapping("task")
@Controller
@ConfigurationProperties(prefix = "com.imdoa")
public class TaskController extends CrudController<Task,Long>{


    private String  LIST_URL="task/list";

    private String FROM_URL="task/add";

    private String attachFiles;

    @Resource
    private DepartmentService departmentService;

    @Resource
    private AdminUserService adminUserService;

    @Resource
    private TaskRecordService taskRecordService;

    @Resource
    private PermissionService permissionService;

    @Resource
    private FileRecordService fileRecordService;


    @RequestMapping("show")
    public String show(Model model,Long id){


        Task task = service.findOne(id).get();
//        task.setTaskContent(HtmlHan(task.getTaskContent()));
        model.addAttribute("task",task);

        TaskRecordQuery query = new TaskRecordQuery();
        query.setSize(100);
        query.setTask(task.getId());
        List<TaskRecord> content = taskRecordService.findAll(query).getContent();
        List<TaskRecord> list = new ArrayList<>();
        content.forEach(taskRecord -> {
            if(!StringUtils.isNullData(taskRecord.getRecordContent())){
//                taskRecord.setRecordContent(HtmlUtils.(taskRecord.getRecordContent()));
            }
            list.add(taskRecord);
        });
        List<FileRecord> fileRecords = task.getFileRecords();
        model.addAttribute("fileRecords",fileRecords);
        model.addAttribute("taskRecord",list);
        return "task/show";
    }




    @RequestMapping("list")
    public  String list(Model model, TaskQuery query,HttpServletRequest request) {

        AdminUser currentUser = getCurrentUser(request);
        List<Permission> permissions = currentUser.getRole().getPermissions();

        Permission permission = permissionService.findOne(9L).get();
        if(permissions.contains(permission)){
            List<Task> content = service.findAll(query).getContent();
            model.addAttribute("tasks",content);
            return LIST_URL;
        }

        List<Task> list = ((TaskService) service).queryUserTask(currentUser.getId());

        model.addAttribute("tasks",list);
        return LIST_URL;

    }

    @GetMapping("add")
    public String add(Model model) {
        List<Department> all = departmentService.findAll();
        model.addAttribute("depts",all);
        return super.add(model);
    }


    @PostMapping("add")
    @ResponseBody
    public BaseDataResponse add(TaskForm form,String uploadFiles) {
        form.setTaskState(1);
        Long[] adminUsers = form.getAdminUsers();
        List<AdminUser> list=adminUserService.queryAdminsByIds(adminUsers);
        Task task = form.as();
        task.setAdminUserList(list);
        if(form.getTaskLeader()==null&&list.size()==1){
            task.setTaskLeader(list.get(0));
        }
        //新增附件内容
        List<FileRecord> fileRecords = new ArrayList<>();
        if(!StringUtils.isNullData(uploadFiles)){
            String[] split = uploadFiles.split("#");
            for(String fileName:split){
                if(!StringUtils.isNullData(fileName)){
                    FileRecord fileRecord = new FileRecord();
                    fileRecord.setFileName(fileName);
                    String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
                    fileRecord.setSuffix(suffix);
                    fileRecord.setType(1);
                    FileRecord fileRecord1 = fileRecordService.save(fileRecord).get();
                    fileRecords.add(fileRecord1);
                }
            }
        }
        String finishDate = form.getFinishDate();
        if(!StringUtils.isNullData(finishDate)){
            Date date = DateUtils.formartDateStr(finishDate, DateUtils.DATE_FORMAT);
            task.setFinishDate(date);
        }
        task.setFileRecords(fileRecords);
        Task newTask = service.save(task).get();
        if(list!=null&&list.size()>0){
            list.forEach(adminUser -> {
                List<Task> tasks = adminUser.getTasks()==null?new ArrayList<Task>():adminUser.getTasks();
                tasks.add(newTask);
                adminUser.setTasks(tasks);
                adminUserService.save(adminUser);
            });
        }
        return BaseDataResponse.ok().jumpUrl("/task/list");
    }

    @RequestMapping("downAllFile")
    @ResponseBody
    public void downAllFile(Long id,HttpServletRequest request, HttpServletResponse response) throws Exception {
        Task task = service.findOne(id).get();
        List<FileRecord> fileRecords = task.getFileRecords();
        List<File> files = new ArrayList<>();
        if(fileRecords!=null&&fileRecords.size()>0){
            fileRecords.forEach(fileRecord -> {
                String fileName = fileRecord.getFileName();
                File file = new File(attachFiles+"/"+fileName);
                files.add(file);
            });
        }
        if(files.size()>0){
            File  tempFile = new File(attachFiles+"/task_"+id+".zip");
            ZipUtils.downLoadzipFiles(files,request,response,tempFile);
        }
        return;
    }

    @RequestMapping("delete")
    @ResponseBody
    public BaseDataResponse delete(Long id) {
        TaskRecordQuery query = new TaskRecordQuery();
        query.setTask(id);
        query.setSize(100);
        List<TaskRecord> content = taskRecordService.findAll(query).getContent();
        taskRecordService.delete(content);
//        ((TaskService)service).deleteTaskUser(id);
        return super.delete(id);
    }

    private AdminUser getCurrentUser(HttpServletRequest request){
        AdminUser adminUser =(AdminUser) request.getSession().getAttribute("currentUser");
        return adminUser;
    }

    public String getAttachFiles() {
        return attachFiles;
    }

    public void setAttachFiles(String attachFiles) {
        this.attachFiles = attachFiles;
    }

    @Override
    protected BaseForm<Task, Long> getBlankForm() {
        return new TaskForm();
    }

    @Override
    protected BaseForm<Task, Long> getForm(Task task) {
        return new TaskForm();
    }

    @Override
    protected String getListUrl() {
        return LIST_URL;
    }

    @Override
    protected String getFormUrl() {
        return FROM_URL;
    }
}
