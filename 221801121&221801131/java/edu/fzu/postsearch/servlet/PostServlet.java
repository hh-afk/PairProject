package edu.fzu.postsearch.servlet;
import edu.fzu.postsearch.pojo.Post;
import edu.fzu.postsearch.dao.PostDAO;
import edu.fzu.postsearch.dao.PostDAOimpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/postList")
public class PostServlet extends BaseBackServlet{
    private PostDAO postDAO;
    @Override
    public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
        List posts = (List) req.getAttribute("posts");// 取得共享里面的数据
        if (posts == null){
            postDAO = new PostDAOimpl();
            posts = postDAO.list();
        }
        //List<Post> posts = postDAO.list();
        // 接收分页页面传递过来的页面数
        String strNum = req.getParameter("pageNum");
        int pageNum = 0;// 表示当前要显示的页面数
        int maxPage = 0;// 最大页
        int pageCount = posts.size();// 得到查询出来的所有数据的数目

        // 如果是第一次执行,就会接收不到数据
        if (strNum == null) {
            strNum = "0";
        } else {// 接收到了用户点击的第几(pageNum)页
            pageNum = Integer.parseInt(strNum);
        }

        // 计算出要分多少页
        if (pageCount % 4 == 0) {
            maxPage = pageCount / 4;
        } else {
            maxPage = pageCount / 4 + 1;
        }
        req.setAttribute("maxPage", maxPage);// 存储最大页数
        req.setAttribute("pageNum", pageNum);// 将当前页面存储起来,给分页页面使用
        req.setAttribute("posts", posts);
        req.getRequestDispatcher("/postList.jsp").forward(req, resp);
    }

    @Override
    public String add(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @Override
    public String delete(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @Override
    public String edit(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @Override
    public String update(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}