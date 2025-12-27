package ui.ft.ccit.faculty.transaksi.karyawan.model;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface KaryawanRepository extends JpaRepository<Karyawan, String> {
    
    // Pencarian berdasarkan nama (Case Insensitive)
    List<Karyawan> findByNamaContainingIgnoreCase(String nama);
}