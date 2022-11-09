package dao.config;

import model.HocVien;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class HocVienDao {
    static Connection connection = ConnectMySql.getConnection();

    public static List<HocVien> getAll() {
        String selectAll = "SELECT hocvien.*, classroom.name as classname FROM thuthuchanh_module3.hocvien join classroom on hocvien.idClassroom = classroom.id";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectAll);
            List<HocVien> hocViens = new ArrayList<>();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                LocalDate date = resultSet.getDate("dateofbirth").toLocalDate();
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                String className = resultSet.getString("classname");
                int idClassRoom = resultSet.getInt("idClassRoom");
                hocViens.add(new HocVien(id, name, address, date, phone, email, className, idClassRoom));
            }
            return hocViens;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void saveHocVien(HocVien hocVien){
        String insert = "INSERT INTO hocvien (`name`, `address`, `dateofbirth`, `phone`, `email`,`idClassRoom`) VALUES (?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setString(1,hocVien.getName());
            preparedStatement.setString(2,hocVien.getAddress());
            preparedStatement.setDate(3, Date.valueOf(hocVien.getDate()));
            preparedStatement.setString(4,hocVien.getPhone());
            preparedStatement.setString(5,hocVien.getEmail());
            preparedStatement.setInt(6,hocVien.getClassRoom());

            preparedStatement.execute();

        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }


    public static List<HocVien> findByName(String nameFind) {
        String find = "SELECT hocvien.*, classroom.name as classname FROM thuthuchanh_module3.hocvien join classroom on hocvien.idClassroom = classroom.id\n" +
                "where hocvien.name like '%"+nameFind+"%\'";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(find);
            List<HocVien> hocViens = new ArrayList<>();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                LocalDate date = resultSet.getDate("date").toLocalDate();
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                String className = resultSet.getString("classname");
                int idClassRoom = resultSet.getInt("idClassRoom");
                hocViens.add(new HocVien(id, name, address, date, phone, email, className, idClassRoom));
            }
            return hocViens;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void deleteHocVien(int id) {
        try {
            String sql = "DELETE FROM  hocvien  WHERE (id= ?)";
            Connection connection = ConnectMySql.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void editHocVien(HocVien hocVien) {
        try{
            String sql = "UPDATE `login_casestudy3`.`product` SET  `name` = ?, `address` = ?, `dateofbirth` = ?, `phone` = ?, `email` = ?, `idClassRoom` = ? WHERE (`id` = ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,hocVien.getName());
            preparedStatement.setString(2,hocVien.getAddress());
            preparedStatement.setDate(3, Date.valueOf(hocVien.getDate()));
            preparedStatement.setString(4,hocVien.getPhone());
            preparedStatement.setString(5,hocVien.getEmail());
            preparedStatement.setInt(6,hocVien.getClassRoom());
            preparedStatement.setInt(7,hocVien.getId());
            preparedStatement.execute();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }




}
