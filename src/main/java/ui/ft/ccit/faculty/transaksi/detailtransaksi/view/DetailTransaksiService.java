package ui.ft.ccit.faculty.transaksi.detailtransaksi.view;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ui.ft.ccit.faculty.transaksi.DataAlreadyExistsException;
import ui.ft.ccit.faculty.transaksi.DataNotFoundException;
import ui.ft.ccit.faculty.transaksi.detailtransaksi.model.DetailTransaksi;
import ui.ft.ccit.faculty.transaksi.detailtransaksi.model.DetailTransaksiId;
import ui.ft.ccit.faculty.transaksi.detailtransaksi.model.DetailTransaksiRepository;

import java.util.List;

@Service
@Transactional
public class DetailTransaksiService {

    private final DetailTransaksiRepository repository;

    public DetailTransaksiService(DetailTransaksiRepository repository) {
        this.repository = repository;
    }

     public List<DetailTransaksi> getAllWithPagination(int page, int size) {
        return repository
                .findAll(PageRequest.of(page, size))
                .getContent();
    }

    // 1. GET ALL
    public List<DetailTransaksi> getAll() {
        return repository.findAll();
    }

    // 2. GET BY HEADER (Semua item dalam satu struk)
    public List<DetailTransaksi> getByKodeTransaksi(String kodeTransaksi) {
        return repository.findByKodeTransaksi(kodeTransaksi);
    }

    // 3. GET ONE (Butuh 2 Kunci)
    public DetailTransaksi getById(String kodeTransaksi, String idBarang) {
        DetailTransaksiId id = new DetailTransaksiId(kodeTransaksi, idBarang);
        return repository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("DetailTransaksi", kodeTransaksi + "-" + idBarang));
    }

    // 4. CREATE SINGLE (Validasi Manual)
    public DetailTransaksi save(DetailTransaksi dt) {
        // Validasi Input
        if (dt.getKodeTransaksi() == null || dt.getKodeTransaksi().isBlank()) {
            throw new IllegalArgumentException("Kode Transaksi wajib diisi");
        }
        if (dt.getIdBarang() == null || dt.getIdBarang().isBlank()) {
            throw new IllegalArgumentException("ID Barang wajib diisi");
        }

        // Cek Duplikat
        DetailTransaksiId id = new DetailTransaksiId(dt.getKodeTransaksi(), dt.getIdBarang());
        if (repository.existsById(id)) {
            throw new DataAlreadyExistsException("DetailTransaksi", dt.getKodeTransaksi() + "-" + dt.getIdBarang());
        }

        return repository.save(dt);
    }

    // 5. CREATE BULK (Looping Validasi)
    @Transactional
    public List<DetailTransaksi> saveBulk(List<DetailTransaksi> detailList) {
        for (DetailTransaksi dt : detailList) {
            // Validasi Input
            if (dt.getKodeTransaksi() == null || dt.getIdBarang() == null) {
                throw new IllegalArgumentException("Kode Transaksi dan ID Barang wajib diisi untuk setiap data");
            }

            // Cek Duplikat
            DetailTransaksiId id = new DetailTransaksiId(dt.getKodeTransaksi(), dt.getIdBarang());
            if (repository.existsById(id)) {
                throw new DataAlreadyExistsException("DetailTransaksi", dt.getKodeTransaksi() + "-" + dt.getIdBarang());
            }
        }
        return repository.saveAll(detailList);
    }

    // 6. UPDATE (Manual Set Field)
    public DetailTransaksi update(String kodeTransaksi, String idBarang, DetailTransaksi updated) {
        DetailTransaksi existing = getById(kodeTransaksi, idBarang);
        
        // Update field (Hanya jumlah yang logis untuk diupdate)
        existing.setJumlah(updated.getJumlah());

        return repository.save(existing);
    }

    // 7. DELETE SINGLE
    public void delete(String kodeTransaksi, String idBarang) {
        DetailTransaksiId id = new DetailTransaksiId(kodeTransaksi, idBarang);
        if (!repository.existsById(id)) {
            throw new DataNotFoundException("DetailTransaksi", kodeTransaksi + "-" + idBarang);
        }
        repository.deleteById(id);
    }

    // 8. DELETE BULK
    @Transactional
    public void deleteBulk(List<DetailTransaksiId> ids) {
        if (ids == null || ids.isEmpty()) {
            throw new IllegalArgumentException("List ID tidak boleh kosong");
        }

        // Cek apakah semua data ada (Atomic)
        List<DetailTransaksi> existingData = repository.findAllById(ids);
        if (existingData.size() != ids.size()) {
             throw new IllegalStateException("Sebagian data tidak ditemukan, operasi dibatalkan");
        }

        repository.deleteAllById(ids);
    }
}