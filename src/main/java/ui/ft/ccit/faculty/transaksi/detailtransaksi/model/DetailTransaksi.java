package ui.ft.ccit.faculty.transaksi.detailtransaksi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "detail_transaksi")
@IdClass(DetailTransaksiId.class)
public class DetailTransaksi {

    @Id
    @Column(name = "kode_transaksi", length = 4)
    private String kodeTransaksi;

    @Id
    @Column(name = "id_barang", length = 4)
    private String idBarang;

    @Column(name = "jumlah")
    private Integer jumlah;

    protected DetailTransaksi() {
        // for JPA
    }

    public DetailTransaksi(String kodeTransaksi, String idBarang, Integer jumlah) {
        this.kodeTransaksi = kodeTransaksi;
        this.idBarang = idBarang;
        this.jumlah = jumlah;
    }

    public String getKodeTransaksi() { 
        return kodeTransaksi; 
    }

    public void setKodeTransaksi(String kodeTransaksi) { 
        this.kodeTransaksi = kodeTransaksi; 
    }

    public String getIdBarang() { 
        return idBarang; 
    }

    public void setIdBarang(String idBarang) { 
        this.idBarang = idBarang; 
    }

    public Integer getJumlah() { 
        return jumlah; 
    }

    public void setJumlah(Integer jumlah) { 
        this.jumlah = jumlah; 
    }
}