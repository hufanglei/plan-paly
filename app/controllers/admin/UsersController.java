package controllers.admin;

import models.admin.Project;
import models.admin.User;
import models.enums.DeletedStatus;
import models.enums.PlanType;
import models.enums.UsersType;
import play.Logger;
import play.mvc.Controller;
import play.mvc.Scope;

import java.util.Date;
import java.util.List;


/**
 *用户管理
 */
public class UsersController extends Controller {

    public static  void index() {
        List<User> userList = User.findByUndeteled();
        render(userList);
    }

    public static  void query(){
        List<User> userList = User.findByUndeteled();
        render(userList);
    }

    public static  void doQuery(Long userId){
        User searchUser =   User.findById(userId);
        List<User> userList = User.findByUndeteled();
        renderTemplate("admin/UsersController/query.html", userList, userId , searchUser);
    }


    /**
     * 进入主页+用户登录
     */
    public static  void login(String username , String password){
       User user = User.findByNameAndPasswordAndUndelted(username, password);
        if(user != null){
           session.put("userId" , user.id);
           session.put("type" , user.type);
           session.put("username" , user.name);
            PlansController.index();
        }else{
            renderArgs.put("message", "用户名或密码错误,请重新输入");
            render("Application/index.html");
        }
    }

    /**
     * 进入编辑页面
     */
    public static  void edit(Long id){
        User user = User.findById(id);
        render(user);
    }

    /**
     * 更新用户
     */
    public static  void update(Long id , User user){
        User.update(id , user);
        index();
    }

    /**
     * 删除用户
     */
    public static  void delete(Long id){
        User.delete(id);
        index();
    }
    /**
     * 查看别人的信息
    */
    public static  void look(Long id){
        User user = User.findById(id);
        render(user);
    }

    /**
     * 跳转到新增界面
     */
    public  static  void add(){
        UsersType[] typelist = UsersType.values();
        Logger.info("types[0] = %s", typelist[0]);
        Logger.info("types[1] = %s" , typelist[1]);
        render(typelist);
    }

    /**
     * 创建用户
     */
    public static void create(User user){
        user.createAt = new Date();
        user.deleted = DeletedStatus.UN_DELETED;
        user.save();
        index();
    }
}
