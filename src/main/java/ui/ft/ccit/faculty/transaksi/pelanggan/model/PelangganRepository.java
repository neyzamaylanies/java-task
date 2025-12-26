package ui.ft.ccit.faculty.transaksi.pelanggan.model;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PelangganRepository extends JpaRepository<Pelanggan, String> {
    // Return List
    List<Pelanggan> findByNamaContainingIgnoreCase(String keyword);

    // Contoh: mencari berdasarkan alamat
    List<Pelanggan> findByAlamatContainingIgnoreCase(String keyword);

    // Hitung berapa banyak pelanggan dengan idPelanggan dalam daftar tertentu
    long countByIdPelangganIn(List<String> ids);
}