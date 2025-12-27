package ui.ft.ccit.faculty.transaksi.detailtransaksi.model;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DetailTransaksiRepository extends JpaRepository<DetailTransaksi, DetailTransaksiId> {
    
    // Cari semua item dalam satu struk transaksi
    List<DetailTransaksi> findByKodeTransaksi(String kodeTransaksi);
}