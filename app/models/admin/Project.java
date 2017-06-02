package models.admin;

import models.enums.DeletedStatus;
import play.db.jpa.Model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by hufanglei
 * 项目
 *
 */
@Entity
@Table(name = "project")
public class Project extends Model {

    /**
     * 项目名称
     */
    @Column(name = "name", length = 50)
    public String name;

    /**
     * 逻辑删除
     */
    @Column(name = "deleted", length = 20)
    @Enumerated(EnumType.ORDINAL)
    public DeletedStatus deleted;

    /**
     * 查询所有没有删除的计划
     */
    public static List<Project> findByUndeleted(){
        return Project.find("deleted = ?" , DeletedStatus.UN_DELETED).fetch();
    }

}
