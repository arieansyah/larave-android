package id.kuyfutsal.kuyfutsal;

/**
 * Created by borneo on 16/01/18.
 */

public class Booked {
    private String id_booked, jam, status, nama, noHp;

    public Booked(String id_booked, String jam, String status) {
        this.id_booked = id_booked;
        this.jam = jam;
        this.status = status;
        this.nama = nama;
        this.noHp = noHp;
    }

    public String getId_booked() {
        return id_booked;
    }

    public void setId_booked(String id_booked) {
        this.id_booked = id_booked;
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

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }
}
