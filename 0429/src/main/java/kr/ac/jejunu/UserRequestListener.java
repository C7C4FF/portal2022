package kr.ac.jejunu;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class UserRequestListener implements ServletRequestListener {

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("******* requestlistener init *******");
        ServletRequestListener.super.requestInitialized(sre);
    }
}
