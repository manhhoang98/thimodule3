package controller;

import dao.config.HocVienDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.HocVien;
import service.ClassRoomService;
import service.HocVienService;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

@WebServlet(urlPatterns = "/home")
public class HomeServlet extends HttpServlet {
    HocVienService hocVienService = new HocVienService();
    ClassRoomService classRoomService = new ClassRoomService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        int id = Integer.parseInt(req.getParameter("id"));

        if (action == null) {
            action = "";
        }
        RequestDispatcher requestDispatcher;
        switch (action) {
            case "edit":
                for (HocVien p: HocVienDao.getAll()) {
                    if (p.getId() == id){
                        req.setAttribute("hocvien", p);
                    }
                }
                RequestDispatcher dispatcher = req.getRequestDispatcher("/editHocVien.jsp");
                dispatcher.forward(req,resp);
                break;
            case "delete":
                HocVienDao.deleteHocVien(id);
                resp.sendRedirect("/home");
                break;
            case "create":
                req.setAttribute("listClass", classRoomService.getAll());
                requestDispatcher = req.getRequestDispatcher("/view/createHocVien.jsp");
                requestDispatcher.forward(req, resp);
                break;
            default:
                req.setAttribute("listHV", hocVienService.getAll());
                requestDispatcher = req.getRequestDispatcher("/view/showHocVien.jsp");
                requestDispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action == null) {
            action = "";
        }

        RequestDispatcher requestDispatcher;

        switch (action) {
            case "edit":
                int id = Integer.parseInt(req.getParameter("id"));
                String name = req.getParameter("name");
                String img = req.getParameter("img");
                double price = Double.parseDouble(req.getParameter("price"));
                int amount = Integer.parseInt(req.getParameter("amount"));
                String describe = req.getParameter("describe");
                int id_category = Integer.parseInt(req.getParameter("id_category"));
                HocVienDao.edit(id ,new Product(id,name,img,price,amount,describe,id_category));
                resp.sendRedirect("/product");

            case "create":
                String name = req.getParameter("name");
                String address = req.getParameter("address");
                LocalDate date = LocalDate.parse(req.getParameter("date"));
                String phone = req.getParameter("phone");
                String email = req.getParameter("email");
                int idClassRoom = Integer.parseInt(req.getParameter("idClassRoom"));

                hocVienService.save(new HocVien(name, address, date, phone, email, idClassRoom));
                resp.sendRedirect("/home");
                break;

            case "search":
                String nameSearch = req.getParameter("search");
                req.setAttribute("listHV", hocVienService.findByName(nameSearch));
                requestDispatcher = req.getRequestDispatcher("/view/showHocVien.jsp");
                requestDispatcher.forward(req, resp);
                break;
        }

    }
}
