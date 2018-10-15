package com.manageplat.admin.controller;

import cn.gfire.base.mvc.controller.CrudController;
import cn.gfire.base.mvc.form.BaseDataResponse;
import cn.gfire.base.mvc.form.BaseForm;
import com.country.utils.FileOpUtils;
import com.country.utils.MD5Util;
import com.country.utils.StringUtils;
import com.manageplat.admin.domain.AdminUser;
import com.manageplat.admin.domain.Role;
import com.manageplat.admin.form.AdminUserForm;
import com.manageplat.admin.query.AdminUserQuery;
import com.manageplat.admin.service.AdminUserService;
import com.manageplat.admin.service.RoleService;
import com.manageplat.common.domain.Constant;
import com.manageplat.common.service.ConstantService;
import com.manageplat.dept.domain.Department;
import com.manageplat.dept.service.DepartmentService;
import com.manageplat.post.domain.Post;
import com.manageplat.post.service.PostService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wust
 * @create 2018-04-03 9:36
 * DESC
 **/
@Controller
@RequestMapping("admin")
@ConfigurationProperties(prefix = "com.idmoa")
public class AdminController extends CrudController<AdminUser,Long> {

    private String FORM_URL="admin/add";

    private String LIST_URL="admin/list";

    private String userPhotoPaht;

    @Resource
    private RoleService roleService;

    @Resource
    private DepartmentService departmentService;

    @Resource
    private ConstantService constantService;

    @Resource
    private PostService postService;


    @RequestMapping("userProfile")
    public String userProfile(Model model,HttpServletRequest request){
        Constant company = constantService.findByName("company");
        AdminUser loginUser=(AdminUser)request.getSession().getAttribute("currentUser");
        model.addAttribute("company",company.getConstValue());
        Department department = service.findOne(loginUser.getId()).get().getDepartment();
        model.addAttribute("dept",department.getDeptName());
        return "admin/profile";
    }


    /**
     * 展示用户头像
     * @param response
     * @param photoName
     * @throws Exception
     */
    @RequestMapping("showPhoto")
    public void showPhoto(HttpServletResponse response,String photoName) throws Exception{
        if(StringUtils.isNullData(photoName)){
            photoName="default.jpg";
        }
        FileOpUtils.downFile(response,new File(userPhotoPaht+"/"+photoName),"demo");
    }


    @RequestMapping("logout")
    public String logout(Model model){
        SecurityUtils.getSubject().logout();
        return "admin/login";
    }


    @RequestMapping("perfect")
    public String perfectInfo(Model model,HttpServletRequest request){
        AdminUser loginUser=(AdminUser)request.getSession().getAttribute("currentUser");
        model.addAttribute("userId",loginUser.getId());
        return "admin/perfect";
    }

    @RequestMapping("perfectBaseInfo")
    public String perfectBaseInfo(Model model, Long userId, String userDesc, MultipartFile photoFile,String email,HttpServletRequest request)throws Exception{
        //进行安全性验证
        AdminUser loginUser=(AdminUser)request.getSession().getAttribute("currentUser");
        if(loginUser==null||userId==null){
            return "admin/login";
        }
        if(loginUser.getId()!=userId){
            return "admin/security";
        }

        if(photoFile!=null){
            File file = new File(userPhotoPaht+"/user"+userId+".jpg");
            photoFile.transferTo(file);
            loginUser.setUserPhoto(file.getName());
        }
        if(!StringUtils.isNullData(userDesc)){
            loginUser.setUserDesc(userDesc);
        }
        if(!StringUtils.isNullData(email)){
            loginUser.setEmail(email);
        }
        AdminUser adminUser = service.save(loginUser).get();
        //更新session 中的user
        request.getSession().setAttribute("currentUser",adminUser);
        return "redirect:/index/home";
    }

    @GetMapping("login")
    public String login(Model model){
        return "admin/login";
    }

    @PostMapping("login")
    public String login(Model model, AdminUserForm form,HttpServletRequest request) {
        //1  获取Subject
        Subject currentUser = SecurityUtils.getSubject();

        form.setPassWord(MD5Util.Md5SaltEncode(form.getPassWord(), "MD5", form.getLoginName()));
        //2 判断当前用户是否登录
        if (!currentUser.isAuthenticated()) {
            // 把用户名和密码封装为 UsernamePasswordToken 对象
            UsernamePasswordToken token = new UsernamePasswordToken(form.getLoginName(), form.getPassWord());
            // rememberme
            token.setRememberMe(true);
            //执行登录操作
            try {
                // 执行登录调用到realm
                currentUser.login(token);
            } catch (UnknownAccountException ue) { //账户不存在
                model.addAttribute("errorIfno", "账户不存在");
                return "admin/login";

            } catch (IncorrectCredentialsException ice) {  //登录密码不对
                model.addAttribute("errorIfno", "登录密码不正确");
                return "admin/login";
            } catch (AuthenticationException ae) {
                model.addAttribute("errorIfno", ae.getMessage());
                return "admin/login";

            }
            model.addAttribute("session", currentUser.getSession());
        }


        AdminUser loginUser=(AdminUser)currentUser.getSession().getAttribute("currentUser");
        //未设置头像
        if(loginUser!=null&& StringUtils.isNullData(loginUser.getUserPhoto())){
            return "redirect:/admin/perfect";
        }

        return "redirect:/index/home";

    }


    @RequestMapping("queryDeptUsers")
    @ResponseBody
    public BaseDataResponse queryDeptUsers(Long deptId){
        AdminUserQuery query= new AdminUserQuery();
        query.setSize(200);
        query.setDepartment(deptId);
        List<AdminUser> content = service.findAll(query).getContent();
        return BaseDataResponse.ok().data(handUser(content));
    }

    private List<AdminUserForm> handUser(List<AdminUser> content) {
        List<AdminUserForm> adminUserFormList= new ArrayList<>();
        content.forEach(adminUser -> {
            AdminUserForm baseForm = (AdminUserForm) new AdminUserForm().setEntity(adminUser);
            adminUserFormList.add(baseForm);
        });
        return adminUserFormList;
    }


    @PostMapping("add")
    @ResponseBody
    public BaseDataResponse add(@RequestBody @Valid AdminUserForm form, BindingResult bindingResult) {
        form.setPassWord(MD5Util.Md5SaltEncode(form.getPassWord(), "MD5", form.getLoginName()));
        form.setState(1);
        AdminUser adminUser =((AdminUserService)service).findByLoginName(form.getLoginName());
        Role role = roleService.findOne(form.getRole()).get();
        Department department = departmentService.findOne(form.getDepartment()).get();
        if(adminUser!=null&&form.getId()==null){
            return BaseDataResponse.fail().msg("登录账号已存在");
        }
        AdminUser admin = form.as();
        if(bindingResult.hasErrors()) {
            return BaseDataResponse.verificationFail(bindingResult);
        }
        if(form.getId()!=null){
            AdminUser queryAdmin = service.findOne(form.getId()).get();
            admin.setPassWord(queryAdmin.getPassWord());
        }
        Post post = postService.findOne(form.getPost()).get();
        admin.setPost(post);
        admin.setRole(role);
        admin.setDepartment(department);
        service.save(admin);
        return BaseDataResponse.ok().jumpUrl("/admin/list");
    }

    @GetMapping("add")
    public String add(Model model) {
        List<Role> all = roleService.findAll();
        List<Department> depts = departmentService.findAll();
        List<Post> posts = postService.findAll();
        model.addAttribute("roles",all);
        model.addAttribute("depts",depts);
        model.addAttribute("posts",posts);
        return super.add(model);
    }


    @RequestMapping("edit")
    public String edit(Model model,Long id){
        AdminUser adminUser = service.findOne(id).get();
        model.addAttribute("form",adminUser);
        List<Role> all = roleService.findAll();
        List<Department> depts = departmentService.findAll();
        model.addAttribute("roles",all);
        model.addAttribute("depts",depts);
        List<Post> posts = postService.findAll();
        model.addAttribute("posts",posts);
        return FORM_URL;
    }


    @RequestMapping("bind")
    public String bindUser(Model model,Long id){
        model.addAttribute("id",id);
        AdminUser adminUser = service.findOne(id).get();
        String userName = adminUser.getUserName();
        model.addAttribute("userName",userName);
        return "admin/bind";
    }

    @RequestMapping("list")
    public  String list(Model model,AdminUserQuery query) {
        return super.list(model, query);
    }


    @RequestMapping("delete")
    @ResponseBody
    public BaseDataResponse delete(Long id){
        service.delete(id);
        return BaseDataResponse.ok();
    }

    @RequestMapping("body")
    public String body(Model model){
        return "base/body";
    }


    @Override
    protected BaseForm<AdminUser, Long> getBlankForm() {
        return new AdminUserForm();
    }

    @Override
    protected BaseForm<AdminUser, Long> getForm(AdminUser adminUser) {
        return new AdminUserForm();
    }

    @Override
    protected String getListUrl() {
        return LIST_URL;
    }

    @Override
    protected String getFormUrl() {
        return FORM_URL;
    }

    public String getUserPhotoPaht() {
        return userPhotoPaht;
    }

    public void setUserPhotoPaht(String userPhotoPaht) {
        this.userPhotoPaht = userPhotoPaht;
    }
}
