package com.xuzi.share.utils;

import com.xuzi.share.entity.Admin;
import com.xuzi.share.entity.Designer;
import com.xuzi.share.entity.User;
import org.springframework.stereotype.Component;

/**
 * 持有用户信息,用于代替session对象.
 */
@Component
public class HostHolder {

    private ThreadLocal<User> users = new ThreadLocal<>();
    private ThreadLocal<Designer> designers = new ThreadLocal<>();
    private ThreadLocal<Admin> admins = new ThreadLocal<>();


    public void setUser(User user) {
        users.set(user);
    }
    public User getUser() {
        return users.get();
    }


    public void setDesigner(Designer designer) {
        designers.set(designer);
    }
    public Designer getDesigner() {
        return designers.get();
    }


    public void setAdmin(Admin admin) {
        admins.set(admin);
    }
    public Admin getAdmin() {
        return admins.get();
    }


    public void clearUser() {
        users.remove();
    }
    public void clearDesigner() {
        designers.remove();
    }
    public void clearAdmin() {
        admins.remove();
    }

}
