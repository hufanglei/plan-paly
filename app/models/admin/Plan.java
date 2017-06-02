package models.admin;
import models.base.DateUtil;
import models.enums.DeletedStatus;
import models.enums.PlanType;
import models.enums.WorkRates;
import play.Logger;
import play.db.jpa.Model;
import javax.persistence.*;
import java.util.Date;
import java.util.List;
/**
 *  Created by hufanglei
 *  计划
 */
@Entity
@Table(name = "plan")
public class Plan extends Model {
    /**
     * 创建时间
     */
    @Column(name = "create_at", length = 100)
    public Date createAt;


    /**
     * 生效时间
     */
    @Column(name = "effect_time", length = 100)
    public Date effectTime;

    /**
     * 计划内容
     */
    @Column(name = "content" , length = 255)
    public String content;

    /**
     * 计划类型
     */
    @Column(name = "plan_type" , length = 20)
    @Enumerated(EnumType.STRING)
    public PlanType type;

    /**
     * 所属项目
     */
    @ManyToOne
    @JoinColumn(name = "project_id", nullable = true)
    public Project project;

    /**
     * 所属员工
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    public User user;


    /**
     * 工作进度
     */
    @Column(name = "work_rate" , length = 20)
    @Enumerated(EnumType.STRING)
    public WorkRates workRate;

    /**
     * 总结
     */
    @Column(name = "summarize" , length = 255)
    public String summarize;



    /**
     * 更新时间
     */
    @Column(name = "update_at", length = 100)
    public Date updateAt;

    /**
     * 逻辑删除
     */
    @Column(name = "deleted", length = 20)
    @Enumerated(EnumType.ORDINAL)
    public DeletedStatus deleted;


    /**
     * 年字段
     */
    @Column(name = "year" , length = 50)
    public String year;

    /**
     * 月字段
     */
    @Column(name = "month" , length = 50)
    public String month;


    /**
     * 日字段
     */
    @Column(name = "day" , length = 50)
    public String day;






    /**
     * 更新对象.
     */
    public static Plan update(Long id , Plan newObject) {
        Logger.info("获取到的Plan的id :" + newObject.id + "- - | ID :" + id);
        Plan plan = Plan.findById(id);
        if (plan == null) {
            return null;
        }
        plan.updateAt = new Date();
        plan.project = newObject.project;
        plan.summarize = newObject.summarize;
        plan.content = newObject.content;
        plan.workRate = newObject.workRate;
        plan.type = newObject.type;
        plan.effectTime = newObject.effectTime;
        plan.deleted = newObject.deleted == null ? DeletedStatus.UN_DELETED : newObject.deleted;
        plan.save();
        return plan;
    }

    /**
     * 删除指定ID的用户
     */
    public static void delete(Long id) {
        Plan plan = Plan.findById(id);
        if(plan != null){
            plan.deleted = DeletedStatus.DELETED;
            plan.save();
        }
    }

    /**
     * 查询所有没有删除的计划
     */
    public static List<Plan> findByUndeleted(){
       return Plan.find("deleted = ? order by effectTime desc" , DeletedStatus.UN_DELETED).fetch();
    }



    /**
     * 获得当前时间的日计划
     */
    public static  List<Plan> findByTodayAndTypAndUser(User user){
        return Plan.find("day = ? and type = ? and user = ? and deleted = ? order by effectTime desc", DateUtil.getDate(new Date()) ,
                PlanType.DAYPLAN , user , DeletedStatus.UN_DELETED).fetch();
    }

    /**
     * 获得当前时间的周计划 ：  只能看登录用户的计划
     */
    public static  List<Plan> findByWeekAndTypAndUser(User user){
        return Plan.find("effectTime  between ? and ? and type = ? and deleted = ? and user = ? order by effectTime desc",
                DateUtil.getFirstDayOfWeek(new Date()) , DateUtil.getEndDayOfWeek(new Date()) ,
                PlanType.WEEKPLAN , DeletedStatus.UN_DELETED , user).fetch();
    }

    /**
     * 查询当前月内的所有的周计划
     */
    public static  List<Plan> findByMonthAndWeektypeAndUser(User user){
        return Plan.find("month =? and type =? and user =? and deleted =? order by effectTime desc" , DateUtil.getMonth(new Date()) ,
                PlanType.WEEKPLAN , user , DeletedStatus.UN_DELETED).fetch();
    }

    /**
     * 查询当前月内的所有的日计划
     */
    public static  List<Plan> findByMonthAndDatetypeAndUser(User user){
        return Plan.find("month =? and type =? and user =? and deleted =? order by effectTime desc" , DateUtil.getMonth(new Date()) ,
                PlanType.DAYPLAN , user , DeletedStatus.UN_DELETED).fetch();
    }

    /**
     * 获得当前时间的月计划 ： 只能看登录用户的计划
     */
    public static  List<Plan> findByMonthAndTypeAndUser(User user){
        return Plan.find("month = ? and type = ? and user = ? and deleted = ? order by effectTime desc", DateUtil.getMonth(new Date()) ,
                PlanType.MONTHPLAN , user , DeletedStatus.UN_DELETED).fetch();
    }

    /**
     * 获得当前时间的年计划 ： 只能看登录用户的计划
     */
    public static  List<Plan> findByYear(User user){
        return Plan.find("year = ? and user = ? and deleted = ? order by effectTime desc", DateUtil.getYear(new Date()) , user , DeletedStatus.UN_DELETED).fetch();
    }

    /**
     * 选定开始时间到现在的计划
     */
    public static  List<Plan> findByStartTime(Date date){
        return Plan.find("effectTime <= ?", date).fetch();
    }
    /**
     * 选定开始时间到现在 + 类型 的计划
     */
    public static  List<Plan> findByStartTimeAndType(Date date , PlanType planType){
        return Plan.find("effectTime <= ? and type = ?", date , planType).fetch();
    }

    /**
     * 选定开始时间和结束时间查询计划
     */
    public static  List<Plan> findByStartTimeAndEndTime(Date startTime , Date endTime){
        return Plan.find("effectTime between ? and ?", startTime , endTime).fetch();
    }
    /**
     * 选定开始时间和结束时间  + 类型 查询计划
     */
    public static  List<Plan> findByStartTimeByAndEndTimeAndType(Date startTime , Date endTime , PlanType planType){
        return Plan.find("effectTime between ? and ? and type = ?", startTime , endTime , planType).fetch();
    }

    /**
     * 选定计划类型 查询计划
     */
    public static  List<Plan> findByType(PlanType planType){
        return Plan.find("type =?", planType).fetch();
    }

    /**
     * 选定开始时间和结束时间  + 类型 + 用户 查询计划
     */
    public static  List<Plan> findByStartTimeByAndEndTimeAndTypeAndUser(String startDate , String endDate , PlanType planType , User user){
        return Plan.find("DATE_FORMAT( effectTime, '%Y-%m-%d') between ? and ? and type = ? and user = ? and deleted = ? order by effectTime desc", startDate , endDate , planType , user , DeletedStatus.UN_DELETED).fetch();
    }

    /**
     * 按照用户查询
     */
    public static  List<Plan> findByUser(User user){
        return Plan.find("user = ? and deleted = ? order by effectTime desc" , user , DeletedStatus.UN_DELETED).fetch();
    }

}
