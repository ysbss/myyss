import com.wyw.pojo.*;
import com.wyw.service.BorrowService;
import com.wyw.service.CommentService;
import com.wyw.service.ReturnService;
import com.wyw.service.StudentService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Testt {

    @Test
    public void test(){

       ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        CommentService  commentService =context.getBean("CommentServiceImpl", CommentService.class);
        List<Comment> comments = commentService.QuerryAllComment();
        for (Comment co:comments
             ) {
            System.out.println(co);
        }
    }

    @Test
    public void calculate(){

        System.out.println(-100000+(10000/1.1)+(10000/(1.1*1.1))+(10000/(1.1*1.1*1.1))+(20000/(1.1*1.1*1.1*1.1))+(100000/(1.1*1.1*1.1*1.1*1.1)));
    }
}
