/**
 * Create at 2016年5月29日 by cpt725@qq.com
 */
package lab.io.rush;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;


/**
 * @author cpt725@qq.com
 *         打包成war需要
 */
public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(MovieTickesSnapSystemApplication.class);
    }

}
