package DataAccessLayer;

import DataTransferObject.SubjectDTO;
import Utils.DatabaseUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAL {
    private DatabaseUtils DBU = null;
    private Connection conn = null;
    private PreparedStatement pres = null;
    private ResultSet rs = null;

    public static void main(String[] args) {
        SubjectDAL dal = new SubjectDAL();
        SubjectDTO s = new SubjectDTO("S01","Nhập môn game",4,"CNPM");
        SubjectDTO s1 = new SubjectDTO("S02","Mạng máy tính",4,"KHMT");
        SubjectDTO s2 = new SubjectDTO("S03","Cơ sở dữ liệu",4,"KHDL");
//        dal.InsertSubject(s);
//        dal.InsertSubject(s1);
//        dal.InsertSubject(s2);

        String name = dal.GetSubjectNameById("S03");
        System.out.println("Name>>? " + name);



    }



    public int InsertSubject(SubjectDTO s) {
        String sql = "insert into subject values (?,?,?,?);";
        int result = -1;
        try {
            DBU = new DatabaseUtils();
            conn = DBU.createConnection();
            pres = conn.prepareStatement(sql);
            pres.setString(1, s.getSubjectID());
            pres.setString(2, s.getSubjectName());
            pres.setInt(3, s.getCredits());
            pres.setString(4, s.getFaculty());

            result = pres.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
                pres.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }


    public String GetSubjectNameById(String id) {
        String sql = "select * from subject where subjectid =?";
        int result = -1;
        String name = null;
        try {
            DBU = new DatabaseUtils();
            conn = DBU.createConnection();
            pres = conn.prepareStatement(sql);
            pres.setString(1, id);

            rs = pres.executeQuery();
            if (rs.next()) {
                name = rs.getString("subjectName");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
                pres.close();
                rs.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return name;
    }

}