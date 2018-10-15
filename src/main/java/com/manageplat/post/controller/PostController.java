package com.manageplat.post.controller;

import cn.gfire.base.mvc.controller.CrudController;
import cn.gfire.base.mvc.form.BaseDataResponse;
import cn.gfire.base.mvc.form.BaseForm;
import com.manageplat.admin.domain.AdminUser;
import com.manageplat.admin.query.AdminUserQuery;
import com.manageplat.admin.service.AdminUserService;
import com.manageplat.post.domain.Post;
import com.manageplat.post.form.PostForm;
import com.manageplat.post.query.PostQuery;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by CTY on 2018/8/27/027.
 */
@RequestMapping("post")
@Controller
public class PostController extends CrudController<Post,Long> {

    private String ADD_URL="post/add";

    private String LIST_URL="post/list";


    @Resource
    private AdminUserService adminUserService;
    

    @RequestMapping("edit")
    @ResponseBody
    public BaseDataResponse edit(Long id){
        Post post = service.findOne(id).get();
        return BaseDataResponse.ok().data(post);
    }


    @RequestMapping("delete")
    @ResponseBody
    public BaseDataResponse delete(Long id){
        AdminUserQuery query = new AdminUserQuery();
        query.setPost(id);
        List<AdminUser> content = adminUserService.findAll(query).getContent();
        if(content!=null&&content.size()>0){
            return BaseDataResponse.fail().msg("该岗位正在使用中暂不允许删除");
        }
        service.delete(id);
        return BaseDataResponse.ok().jumpUrl("/post/list");
    }


    @RequestMapping("add")
    @ResponseBody
    public BaseDataResponse add(PostForm form, BindingResult bindingResult) {
        return super.add(form, bindingResult).jumpUrl("/post/list");
    }

    @RequestMapping("list")
    public  String list(Model model, PostQuery query) {
        return super.list(model, query);
    }

    @Override
    protected BaseForm<Post, Long> getBlankForm() {
        return null;
    }

    @Override
    protected BaseForm<Post, Long> getForm(Post post) {
        return null;
    }

    @Override
    protected String getListUrl() {
        return null;
    }

    @Override
    protected String getFormUrl() {
        return null;
    }
}
