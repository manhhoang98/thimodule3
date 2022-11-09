package controller;

import dao.config.HocVienDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ClassRoom;
import model.HocVien;
import service.ClassRoomService;
import service.HocVienService;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@WebServlet(urlPatterns = "/home")
public class HomeServlet extends HttpServlet {
    HocVienService hocVienService = new HocVienService();
    ClassRoomService classRoomService = new ClassRoomService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");


        if (action == null) {
            action = "";
        }
        RequestDispatcher requestDispatcher;
        switch (action) {
            case "edit":
                int idHocVien = Integer.parseInt(req.getParameter("id"));
                HocVien hocVien = HocVienService.returnHocVien(idHocVien);
                req.setAttribute("hocvien", hocVien);

                List<ClassRoom> studentClasses = classRoomService.getAll();
                req.setAttribute("listClass", studentClasses);

                RequestDispatcher requestDispatcher1 = req.getRequestDispatcher("/view/editHocVien.jsp");
                requestDispatcher1.forward(req, resp);
                break;
            case "delete":
                int id1 = Integer.parseInt(req.getParameter("id"));
                HocVienDao.deleteHocVien(id1);
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
                String name1 = req.getParameter("name");
                String address1 = req.getParameter("address");
                LocalDate date1 = LocalDate.parse(req.getParameter("date"));
                String phone1 = req.getParameter("phone");
                String email1 = req.getParameter("email");
                int idClassRoom1 = Integer.parseInt(req.getParameter("idClassRoom"));
                HocVien hocVien = new HocVien(id,name1, address1, date1, phone1, email1, idClassRoom1);
                HocVienDao.editHocVien(hocVien);
                RequestDispatcher requestDispatcher2 = req.getRequestDispatcher("/home");
                requestDispatcher2.forward(req, resp);
                break;

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
