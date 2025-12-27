package ui.ft.ccit.faculty.transaksi.pelanggan.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "pelanggan")
public class Pelanggan {

    @Id
    @Column(name = "id_pelanggan", length = 4)
    private String idPelanggan;

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

    @Column(name = "jenis_pelanggan", length = 1)
    private Character jenisPelanggan; // 'S' atau 'G'

    protected Pelanggan() {
        // Diperlukan oleh JPA
    }

    public Pelanggan(String idPelanggan, String nama, Character jenisKelamin, String alamat, String telepon, LocalDate tglLahir, Character jenisPelanggan) {
        this.idPelanggan = idPelanggan;
        this.nama = nama;
        this.jenisKelamin = jenisKelamin;
        this.alamat = alamat;
        this.telepon = telepon;
        this.tglLahir = tglLahir;
        this.jenisPelanggan = jenisPelanggan;
    }

    // === Getters & Setters ===

    public String getIdPelanggan() { return idPelanggan; }
    public void setIdPelanggan(String idPelanggan) { this.idPelanggan = idPelanggan; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public Character getJenisKelamin() { return jenisKelamin; }
    public void setJenisKelamin(Character jenisKelamin) { this.jenisKelamin = jenisKelamin; }

    public String getAlamat() { return alamat; }
    public void setAlamat(String alamat) { this.alamat = alamat; }

    public String getTelepon() { return telepon; }
    public void setTelepon(String telepon) { this.telepon = telepon; }

    public LocalDate getTglLahir() { return tglLahir; }
    public void setTglLahir(LocalDate tglLahir) { this.tglLahir = tglLahir; }

    public Character getJenisPelanggan() { return jenisPelanggan; }
    public void setJenisPelanggan(Character jenisPelanggan) { this.jenisPelanggan = jenisPelanggan; }
}