package ui.ft.ccit.faculty.transaksi.karyawan.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "karyawan")
public class Karyawan {

    @Id
    @Column(name = "id_karyawan", length = 4)
    private String idKaryawan;

    @Column(name = "nama", length = 20, nullable = false)
    private String nama;

    @Column(name = "jenis_kelamin", length = 1)
    private Character jenisKelamin; // 'L' atau 'P'

    @Column(name = "alamat", length = 50)
    private String alamat;

    @Column(name = "telepon", length = 15)
    private String telepon;

    @Column(name = "tgl_lahir")
    private LocalDate tglLahir;

    @Column(name = "gaji")
    private Double gaji;

    protected Karyawan() {
        // For JPA
    }

    public Karyawan(String idKaryawan, String nama, Character jenisKelamin, String alamat, String telepon, LocalDate tglLahir, Double gaji) {
        this.idKaryawan = idKaryawan;
        this.nama = nama;
        this.jenisKelamin = jenisKelamin;
        this.alamat = alamat;
        this.telepon = telepon;
        this.tglLahir = tglLahir;
        this.gaji = gaji;
    }

    // === GETTERS & SETTERS (Bisa generate otomatis dengan klik kanan lalu pilih source code) ===

    public String getIdKaryawan() { 
        return idKaryawan; 
    }

    public void setIdKaryawan(String idKaryawan) { 
        this.idKaryawan = idKaryawan; 
    }

    public String getNama() { 
        return nama; 
    }

    public void setNama(String nama) { 
        this.nama = nama; 
    }

    public Character getJenisKelamin() { 
        return jenisKelamin; 
    }

    public void setJenisKelamin(Character jenisKelamin) { 
        this.jenisKelamin = jenisKelamin; 
    }

    public String getAlamat() { 
        return alamat; 
    }

    public void setAlamat(String alamat) { 
        this.alamat = alamat; 
    }

    public String getTelepon() { 
        return telepon;     
    }

    public void setTelepon(String telepon) { 
        this.telepon = telepon; 
    }

    public LocalDate getTglLahir() { 
        return tglLahir; 
    }

    public void setTglLahir(LocalDate tglLahir) { 
        this.tglLahir = tglLahir; 
    }

    public Double getGaji() { 
        return gaji; 
    }

    public void setGaji(Double gaji) { 
        this.gaji = gaji; 
    }
}