package com.jeux.tiptop_app.entity;


import javax.persistence.*;

@Entity
@Table(name = "sendmail")
public class SendMail {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;




    @Column(name = "user", nullable = true)
    private String user;
    @Column(name = "toemail", nullable = true)
    private String toemail;
    @Column(name = "message", nullable = true)
    private String message;
    @Column(name = "subject", nullable = true)
    private String subject;




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getToemail() {
        return toemail;
    }

    public void setToemail(String toemail) {
        this.toemail = toemail;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }


}
