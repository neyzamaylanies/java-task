package ui.ft.ccit.faculty.transaksi.karyawan.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KaryawanRepository extends JpaRepository<Karyawan, String> {
    Page<Karyawan> findByNamaContainingIgnoreCase(String nama, Pageable pageable);
}