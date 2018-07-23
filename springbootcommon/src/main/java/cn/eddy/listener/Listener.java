package cn.eddy.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by Administrator on 2018/5/7.
 */
public class Listener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("监听器初始化...");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
