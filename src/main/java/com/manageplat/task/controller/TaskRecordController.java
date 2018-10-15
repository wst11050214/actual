package com.manageplat.task.controller;

import cn.gfire.base.jpa.dao.query.BaseQuery;
import cn.gfire.base.mvc.controller.CrudController;
import cn.gfire.base.mvc.form.BaseDataResponse;
import cn.gfire.base.mvc.form.BaseForm;
import com.country.utils.DateUtils;
import com.manageplat.admin.domain.AdminUser;
import com.manageplat.admin.domain.Permission;
import com.manageplat.admin.service.AdminUserService;
import com.manageplat.admin.service.PermissionService;
import com.manageplat.task.domain.Task;
import com.manageplat.task.domain.TaskRecord;
import com.manageplat.task.form.TaskRecordForm;
import com.manageplat.task.query.TaskQuery;
import com.manageplat.task.query.TaskRecordQuery;
import com.manageplat.task.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CTY on 2018/8/16/016.
 */
@RequestMapping("taskRecord")
@Controller
public class TaskRecordController extends CrudController<TaskRecord,Long> {

    private String ADD_URL="taskrecord/add";

    private String LIST_URL="taskrecord/list";

    @Resource
    private TaskService taskService;

    @Resource
    private AdminUserService adminUserService;

    //校验的权限
    private String chekPremisson ="WORK_LOG_SHOW";

    @Resource
    private PermissionService permissionService;


    @RequestMapping("list")
    public  String list(Model model, TaskRecordQuery query) {
        AdminUser currentUser = adminUserService.getCurrentUser();
        //这里需要做权限判断
        List<Permission> permissions = currentUser.getRole().getPermissions();
        Boolean checkResult= permissionService.checkIncludePerm(permissions,chekPremisson);
        if(!checkResult){  //无权限者只能查自己的
            query.setAdminUser(currentUser.getId());
        }
        return super.list(model, query);
    }

    @RequestMapping("show")
    public String show(Model model,Long id){
        TaskRecord taskRecord = service.findOne(id).get();
        model.addAttribute("record",taskRecord);
        return "taskrecord/show";
    }

    @GetMapping("add")
    public String add(Model model, Long taskId, HttpServletRequest request) {
        Task task=null;
        List<Task> list =null;
        AdminUser loginUser=(AdminUser)request.getSession().getAttribute("currentUser");
        if(taskId!=null){
             task= taskService.findOne(taskId).get();
        }else {
            //查询当前用户task
            TaskQuery query = new TaskQuery();
            query.setSize(20);
            query.setTaskLeader(loginUser.getId());
            query.setTaskState(1); //进行中的任务
            list = taskService.findAll(query).getContent();
        }
        model.addAttribute("tasks",list);
        model.addAttribute("task",task);
        return super.add(model);
    }


    @PostMapping("add")
    @ResponseBody
    public BaseDataResponse add(String taskContent,Integer progress,Long taskId,HttpServletRequest request){
        AdminUser loginUser=(AdminUser)request.getSession().getAttribute("currentUser");
        TaskRecord taskRecord = new TaskRecord();
        taskRecord.setPushState(0);
        taskRecord.setAdminUser(loginUser);
        taskRecord.setRecordTitle(DateUtils.getCurZhCNDate()+"工作日志");
        taskRecord.setRecordContent(taskContent);
        if(taskId!=null){
            Task task = taskService.findOne(taskId).get();
            taskRecord.setRecordTitle(loginUser.getUserName()+"于"+DateUtils.getCurZhCNDate()+"更新了任务日志");
            task.setProgress(progress);
            if(progress==100){
                task.setTaskState(3);
            }
            Task task1 = taskService.save(task).get();
            taskRecord.setTask(task1);
        }
        service.save(taskRecord);
        return BaseDataResponse.ok().jumpUrl("/task/list");
    }

    @Override
    protected BaseForm<TaskRecord, Long> getBlankForm() {
        return new TaskRecordForm();
    }

    @Override
    protected BaseForm<TaskRecord, Long> getForm(TaskRecord taskRecord) {
        return new TaskRecordForm();
    }

    @Override
    protected String getListUrl() {
        return LIST_URL;
    }

    @Override
    protected String getFormUrl() {
        return ADD_URL;
    }
}
