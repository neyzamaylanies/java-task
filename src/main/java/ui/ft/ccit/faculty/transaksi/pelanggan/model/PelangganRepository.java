package ui.ft.ccit.faculty.transaksi.pelanggan.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PelangganRepository extends JpaRepository<Pelanggan, String> {

    // Mencari pelanggan yang namanya mirip (ignore case), sekaligus support pagination
    Page<Pelanggan> findByNamaContainingIgnoreCase(String nama, Pageable pageable);
}