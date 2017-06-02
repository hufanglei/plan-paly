package models.admin;

import models.enums.DeletedStatus;
import models.enums.UsersType;
import play.Logger;
import play.db.jpa.Model;


import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 *  Created by hufanglei
 *  用户表
 */
@Entity
@Table(name = "user")
public class User  extends Model{
      /**
       * 姓名
       */
      public  String name;

      /**
       * 密码
       */
      @Column(name = "password", length = 50)
      public  String password;
      /**
       * 电话
       */
      @Column(name = "phone", length = 20)
      public  String phone;
      /**
       * 地址
       */
      @Column(name = "address", length = 100)
      public  String address;

     /**
      * 会员类型 ：  管理员/普通用户
      */
     @Column(name = "user_type" , length = 20)
     @Enumerated(EnumType.STRING)
     public UsersType type;


    /**
       * 创建时间
       */
      @Column(name = "create_at", length = 100)
       public Date createAt;

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
     * 更新对象.
     */
    public static User update(Long id, User newObject) {
        Logger.info("获取到的内容 :" + newObject.id + "- - | ID :" + id);
        User user = User.findById(id);
        if (user == null) {
            return null;
        }
        user.name = newObject.name;
        user.password = newObject.password;
        user.phone = newObject.phone;
        user.address= newObject.address;

        user.updateAt = new Date();
        user.deleted = newObject.deleted == null ? DeletedStatus.UN_DELETED : newObject.deleted;
        user.save();
        return user;
    }

      /**
       * 删除指定ID的用户
       */
      public static void delete(Long id) {
            User user = User.findById(id);
          if(user != null){
              user.deleted = DeletedStatus.DELETED;
              user.save();
          }
      }


    /**
     * 根据用户和密码和删除状态查询
     */
    public static User findByNameAndPasswordAndUndelted(String username , String password){
        return User.find("name = ? and password = ? and deleted = ?" , username , password , DeletedStatus.UN_DELETED).first();
    }

    /**
     * 查询所有未删除的用户
     */
    public static List<User> findByUndeteled(){
        return  User.find("deleted = ? order by createAt desc" , DeletedStatus.UN_DELETED).fetch();
    }

    /**
     * 根据姓名和手机号查询
     */
    public static List<User> findByNameAndPhoneAndUndeteled(User user){
       return User.find("name = ? and phone = ? and deleted =?" , user.name , user.phone , DeletedStatus.UN_DELETED).fetch();
    }
    /**
     * 根据姓名查询
     */
    public static List<User> findByNameAndUndeteled(User user){
        return User.find("name = ? and deleted = ?" , user.name , DeletedStatus.UN_DELETED).fetch();
    }

    /**
     * 根据手机号查询
     */
    public static List<User> findByPhoneAndUndeteled(User user){
        return User.find("phone = ? and deleted =?" , user.phone , DeletedStatus.UN_DELETED).fetch();
    }
}
