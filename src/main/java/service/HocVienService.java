package service;

import dao.config.HocVienDao;
import model.HocVien;

import java.util.List;

public class HocVienService {
 public static    List<HocVien> hocVien = HocVienDao.getAll();
    public List<HocVien> getAll(){
        return HocVienDao.getAll();
    }
    public List<HocVien> findByName(String name){
        return HocVienDao.findByName(name);
    }

    public void save(HocVien hocVien){
        HocVienDao.saveHocVien(hocVien);
    }

    public static void edit(int id, HocVien hocViens){
        for (int i = 0; i < hocVien.size(); i++) {
            hocVien.set(i, hocViens);
            HocVienDao.editHocVien(hocViens);
        } {

        }
    }

    public static HocVien returnHocVien(int idHocVien) {
        HocVien hocVien = null;
        for (HocVien s : HocVienDao.getAll()) {
            if (s.getId() == idHocVien ) {
                hocVien = s;
            }
        }

        return hocVien;
    }
}
