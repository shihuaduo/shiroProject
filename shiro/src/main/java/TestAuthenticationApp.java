import lombok.extern.apachecommons.CommonsLog;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

/**
 * shiro认证
 */
//@CommonsLog
@Slf4j
public class TestAuthenticationApp {
//    private static final transient Logger log= LoggerFactory.getLogger(TestAuthenticationApp.class);

     @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        log.info("First Shiro Application");
        String username="zhangsan";
        String password="123456";
        //创建安全管理器的工厂对象
        Factory<SecurityManager> factory=new IniSecurityManagerFactory("classpath:shiro_realm.ini");
        //2.使用工厂创建安全管理器
        SecurityManager securityManager=factory.getInstance();
        //3.安全管理器绑定到当前线程
        SecurityUtils.setSecurityManager(securityManager);
        //4.使用SecurityUtils.getSubject()得到主体对象
        Subject subject =SecurityUtils.getSubject();
        //5.封装主体用户名和密码
        AuthenticationToken authenticationToken=new UsernamePasswordToken(username,password);
        //6.进行认证
        try {
            subject.login(authenticationToken);
            System.out.println("认证通过");
        } catch (AuthenticationException e) {
            System.out.println("用户名或者密码错误");
//            e.printStackTrace();
        }
         System.out.println("认是否成功"+subject.isAuthenticated());
        log.info("认证{}成功",subject.isAuthenticated());
        //退出登录
//         subject.logout();
//         System.out.println("判断是否登录:"+subject.isAuthenticated());
         //判断是否有权限
         boolean create = subject.hasRole("role1");
         System.out.println("判断是否有角色role1:"+create);
          boolean cre = subject.isPermitted("save");
         System.out.println("用户是否有save权限:"+cre);

//        System.out.println("认证通过");

    }
}
