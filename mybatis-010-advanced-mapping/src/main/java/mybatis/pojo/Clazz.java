package mybatis.pojo;

import java.util.List;
import java.util.Objects;

/**
 * 班级信息
 */
public class Clazz {
    private Integer  cid;
    private String cname;
    private List<Student> students;

    @Override
    public String toString() {
        return "Clazz{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                ", students=" + students +
                '}';
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clazz clazz = (Clazz) o;
        return Objects.equals(cid, clazz.cid) && Objects.equals(cname, clazz.cname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cid, cname);
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public Clazz(Integer cid, String cname) {
        this.cid = cid;
        this.cname = cname;
    }

    public Clazz() {
    }
}
