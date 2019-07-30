package me.qihao.reflection.dynamicproxy;

public class UserDao implements IUserDao {
    @Override
    public void save() {
        System.out.println("---- save user data ----");
    }
}
