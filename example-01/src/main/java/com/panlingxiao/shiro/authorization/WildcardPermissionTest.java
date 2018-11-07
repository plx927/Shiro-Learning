package com.panlingxiao.shiro.authorization;

import org.apache.shiro.authz.permission.WildcardPermission;
import org.apache.shiro.authz.permission.WildcardPermissionResolver;

/**
 * Created by panlingxiao on 2016/9/13.
 * Shiro 中的Permission源码分析
 */
public class WildcardPermissionTest {

    public static void main(String[] args) {
        // 1.Simple Usage
        WildcardPermission editNewsletter = new WildcardPermission("editNewsletter");
        /*
         * 使用上面的方式可以使用在简单的应用中
         * 比如定义类似的权限为:viewNewsletter、deleteNewsletter、createNewsletter等等
         * 可以使用*来表示用户具有所有的权限，但是使用这种方式无法简单地表示用户具备对Newsletter具备所有的权限。
         * 因此出现了后面的多级别权限。
         */
        System.out.println(new WildcardPermission("*").implies(editNewsletter));
        System.out.println(new WildcardPermission("*Newsletter").equals(editNewsletter));


        // 2. Multiple Parts

        /*
         * 使用":"进行分割，domain:operation
         * 使用*表示具有所有的权限
         * newsletter:* 表示对newsletter具被所有操作
         *  *:view : 表示对所有是实例具有view权限
         */
        final WildcardPermission permission = new WildcardPermission("newsletter:edit");
        final WildcardPermission permission1 = new WildcardPermission("newsletter:*");
        System.out.println(permission1.implies(permission));
        System.out.println(permission1.implies(new WildcardPermission("newsletter:view")));
        System.out.println(permission1.implies(new WildcardPermission("newsletter:create")));

        // 3. Multiple Values
        /*
         *  对于 printer:print,printer:query这种写法，shiro提供了简写的方式
         *  即 printer:print,query
         */
        System.out.println(new WildcardPermission("printer:print,query").implies(new WildcardPermission("printer:query")));
        System.out.println(new WildcardPermission("printer:print,query").implies(new WildcardPermission("printer:print")));


        WildcardPermissionResolver wildcardPermissionResolver = new WildcardPermissionResolver();
        System.out.println(new WildcardPermission("*:view").implies(wildcardPermissionResolver.resolvePermission("foo:view")));


        // 4.Instance-Level Access Control 控制实例级别的访问
        System.out.println(new WildcardPermission("printer:print:*").implies(wildcardPermissionResolver.resolvePermission("printer:print:lp7200")));


    }
}
