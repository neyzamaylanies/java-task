package ui.ft.ccit.faculty.transaksi.transaksi.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransaksiRepository extends JpaRepository<Transaksi, String> {
    // Saat ini belum ada custom search, tapi wajib extends JpaRepository
    // biar method .save(), .findAll(), .findById() bisa dipakai.
}