package ui.ft.ccit.faculty.transaksi.jenisbarang.model;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JenisBarangRepository extends JpaRepository<JenisBarang, Byte> {

    // Dipanggil oleh JenisBarangService.searchByNama()
    List<JenisBarang> findByNamaJenisContainingIgnoreCase(String namaJenis);
}