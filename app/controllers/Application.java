package controllers;

import play.*;
import play.libs.Images;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {

    public static void index() {

        render();
    }
    /**
     * 注销登录
     */
    public static void logout(){
        session.clear();
        index();
    }
}