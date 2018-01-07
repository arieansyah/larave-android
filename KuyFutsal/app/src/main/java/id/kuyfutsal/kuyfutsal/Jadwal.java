package id.kuyfutsal.kuyfutsal;

/**
 * Created by borneo on 26/12/17.
 */

public class Jadwal {
    private String id_jadwal;
    private String jam, status;

    public Jadwal(String id_jadwal, String jam, String status) {
        this.id_jadwal = id_jadwal;
        this.jam = jam;
        this.status = status;
    }

    public String getId_jadwal() {
        return id_jadwal;
    }

    public void setId_jadwal(String id_jadwal) {
        this.id_jadwal = id_jadwal;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
