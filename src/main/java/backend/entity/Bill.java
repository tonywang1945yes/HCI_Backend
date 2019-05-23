package backend.entity;

import javax.persistence.*;

@Entity
@Table(name="bill")
public class Bill {
    @Id @GeneratedValue
    @Column(name="id")
    long id;

    @Column(name="bid")
    String bid="";

    @Column(name="gid")
    String gid="";

    @Column(name="gname")
    String gname="";

    @Column(name="gnum")
    int gnum=0;


    @Column(name="uid")
    String uid="";

    @Column(name="sid")
    String sid="";

    @Column(name="time")
    String time="";


    public Bill() {
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Bill( String bid, String gid, String gname, int gnum, String uid, String sid, String time) {
        this.bid = bid;
        this.gid = gid;
        this.gname = gname;
        this.gnum = gnum;
        this.uid = uid;
        this.sid = sid;
        this.time = time;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public int getGnum() {
        return gnum;
    }

    public void setGnum(int gnum) {
        this.gnum = gnum;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
