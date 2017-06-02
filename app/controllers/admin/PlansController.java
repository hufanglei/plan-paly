package controllers.admin;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import models.admin.Plan;
import models.admin.Project;
import models.admin.User;
import models.base.DateUtil;
import models.enums.DeletedStatus;
import models.enums.PlanType;
import models.enums.WorkRates;
import org.apache.commons.collections.map.HashedMap;
import play.Logger;
import play.mvc.Controller;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by hufanglei
 *
 * 计划管理控制类
 */
public class PlansController extends Controller {

    public static void index(){
        Long userId =  Long.valueOf(session.get("userId"));
        User user = User.findById(userId);
        List<Plan> planList = Plan.findByMonthAndDatetypeAndUser(user);
        List<Plan> WeekPlans =  Plan.findByMonthAndWeektypeAndUser(user);
        List<Plan> MonthPlans = Plan.findByMonthAndTypeAndUser(user);
        List<User> userList = User.findByUndeteled();
        render(planList , WeekPlans , user , userList ,MonthPlans);
    }

    /**
     * 进入查看计划详情页面
     */
    public static  void look(Long id){
        Plan plan = Plan.findById(id);
        render(plan);
    }

    /**
     * 进入编辑页面
     */
    public static  void edit(Long id){
        Plan plan = Plan.findById(id);
        List<Project> projectList = Project.findByUndeleted();
        Long userId =  Long.valueOf(session.get("userId"));
        User user = User.findById(userId);
        WorkRates[] workRates = WorkRates.values();
        PlanType[] planTypes = PlanType.values();
        render(plan , projectList , user , workRates , planTypes);
    }

    /**
     * 更新计划
     */
    public static  void update(Long id , Plan plan){
        Plan.update(id , plan);
        index();
    }

    /**
     * 跳转到新增界面
     */
    public  static  void add(){
        PlanType[] types = PlanType.values();
        List<Project> projectList = Project.findByUndeleted();
        render(types , projectList);
    }

    /**
     * 创建计划
     */
    public static void create(Plan plan){
        plan.createAt = new Date();
        plan.deleted = DeletedStatus.UN_DELETED;
        plan.workRate = WorkRates.ZERO;
        plan.year = DateUtil.getYear(new Date());
        plan.month = DateUtil.getMonth(new Date());
        plan.day = DateUtil.getDate(new Date());
        Long userId =  Long.valueOf(session.get("userId"));
        User user = User.findById(userId);
        plan.user = user;
        plan.save();
        Logger.info("创建时间： = %s" , plan.createAt);
        index();
    }


    /**
     * 删除用户
     */
    public static  void delete(Long id){
        Plan.delete(id);
        index();
    }

    /**
     * 今日计划
     */
    public static void searchToday(){
        Long userId =  Long.valueOf(session.get("userId"));
        User user = User.findById(userId);
        List<Plan> planList = Plan.findByTodayAndTypAndUser(user);
        Logger.info("planList  = %s" ,  "跳到searchToday方法啦奥");
        List<User> userList = User.findByUndeteled();
        renderTemplate("admin/PlansController/index.html", planList , userList);
    }
    /**
     * 本周计划
     */
    public static void searchWeek(){
        Long userId =  Long.valueOf(session.get("userId"));
        User user = User.findById(userId);
        List<Plan> planList = Plan.findByWeekAndTypAndUser(user);
        List<User> userList = User.findByUndeteled();
        renderTemplate("admin/PlansController/index.html", planList , userList);
    }

    /**
     * 本月计划
     */
    public static void searchMonth(){
        Long userId =  Long.valueOf(session.get("userId"));
        User user = User.findById(userId);
        List<User> userList = User.findByUndeteled();
        List<Plan> planList = Plan.findByMonthAndTypeAndUser(user);
        renderTemplate("admin/PlansController/index.html", planList , userList);
    }

    /**
     * 跳转到查询主页
     */
    public static void queryIndex(){
        PlanType[] types = PlanType.values();
        List<User> userList = User.findByUndeteled();
        render(types , userList );
    }

    public static void queryAll(String startTime , String endTime , Plan searchPlan){
        Logger.info("startTime  = %s" ,  startTime);
        /*   Date startDate = DateUtil.stringDateAddSeconds(startTime , "00");
           Date endDate = DateUtil.stringDateAddSeconds(endTime, "59");*/
           List<Plan> planList = Plan.findByStartTimeByAndEndTimeAndTypeAndUser(startTime , endTime , searchPlan.type , searchPlan.user);
           PlanType[] types = PlanType.values();
           List<User> userList = User.findByUndeteled();
        renderTemplate("admin/PlansController/queryIndex.html", planList , types , endTime , startTime , userList ,searchPlan);
    }

    /**
     * 查询同事计划searchWorkmate
     */
    public static void searchWorkmate(Long userId) {
     Map<String, String> resultMap = new HashedMap();
        if (userId == null) {
            return;
        }
        User user = User.findById(userId);
        Gson gson = new Gson();
        List<Plan> planList = Plan.findByMonthAndDatetypeAndUser(user);
        List<Plan> WeekPlans =  Plan.findByMonthAndWeektypeAndUser(user);
        List<Plan> MonthPlans = Plan.findByMonthAndTypeAndUser(user);
        String plans = gson.toJson(planList);
        String weekPlans = gson.toJson(WeekPlans);
        String monthPlans = gson.toJson(MonthPlans);
        resultMap.put("plans" , plans);
        resultMap.put("WeekPlans" , weekPlans);
        resultMap.put("MonthPlans" , monthPlans);
        resultMap.put("msg" , "<h3 style='color: red'>查询记录不存在</h3>");
        renderJSON(resultMap);
    }

 }


