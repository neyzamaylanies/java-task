package ui.ft.ccit.faculty.transaksi.pelanggan.view;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ui.ft.ccit.faculty.transaksi.DataNotFoundException;
import ui.ft.ccit.faculty.transaksi.pelanggan.model.Pelanggan;
import ui.ft.ccit.faculty.transaksi.pelanggan.model.PelangganRepository;

@Service
@Transactional
public class PelangganService {

    private final PelangganRepository repository;

    public PelangganService(PelangganRepository repository) {
        this.repository = repository;
    }

    // CREATE / UPDATE
    public Pelanggan save(Pelanggan pelanggan) {
        return repository.save(pelanggan);
    }

    // READ (ONE)
    public Pelanggan getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Pelanggan", id));
    }

    // DELETE
    public void delete(String id) {
        if (!repository.existsById(id)) {
            throw new DataNotFoundException("Pelanggan", id);
        }
        repository.deleteById(id);
    }

    // === METHOD SAKTI (SEARCH + PAGINATION + SORT) ===
    public Page<Pelanggan> search(String keyword, int page, int size, String sortBy, String direction) {
        // 1. Atur arah sort (naik/turun)
        Sort.Direction dir = direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        
        // 2. Bungkus info halaman & sort jadi satu objek Pageable
        Pageable pageable = PageRequest.of(page, size, Sort.by(dir, sortBy));

        // 3. Cek logika: Kalau keyword kosong -> Ambil Semua. Kalau ada -> Cari.
        // Inilah yang membuat endpoint tidak redundan.
        if (keyword == null || keyword.isBlank()) {
            return repository.findAll(pageable);
        } else {
            return repository.findByNamaContainingIgnoreCase(keyword, pageable);
        }
    }
}