package ui.ft.ccit.faculty.transaksi.transaksi.view;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ui.ft.ccit.faculty.transaksi.DataAlreadyExistsException;
import ui.ft.ccit.faculty.transaksi.DataNotFoundException;
import ui.ft.ccit.faculty.transaksi.transaksi.model.Transaksi;
import ui.ft.ccit.faculty.transaksi.transaksi.model.TransaksiRepository;

import java.util.List;

@Service
@Transactional
public class TransaksiService {

    private final TransaksiRepository repository;

    public TransaksiService(TransaksiRepository repository) {
        this.repository = repository;
    }

    public List<Transaksi> getAll() {
        return repository.findAll();
    }

    public List<Transaksi> getAllWithPagination(int page, int size) {
        return repository.findAll(PageRequest.of(page, size)).getContent();
    }

    public Transaksi getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Transaksi", id));
    }

    // CREATE SINGLE
    public Transaksi save(Transaksi t) {
        if (t.getKodeTransaksi() == null || t.getKodeTransaksi().isBlank()) {
            throw new IllegalArgumentException("Kode Transaksi wajib diisi");
        }
        if (repository.existsById(t.getKodeTransaksi())) {
            throw new DataAlreadyExistsException("Transaksi", t.getKodeTransaksi());
        }
        // Validasi Pelanggan & Karyawan idealnya di sini, tapi kita fokus struktur dulu
        return repository.save(t);
    }

    // CREATE BULK
    public List<Transaksi> saveBulk(List<Transaksi> listTransaksi) {
        for (Transaksi t : listTransaksi) {
            if (t.getKodeTransaksi() == null || t.getKodeTransaksi().isBlank()) {
                throw new IllegalArgumentException("Kode Transaksi wajib diisi");
            }
            if (repository.existsById(t.getKodeTransaksi())) {
                throw new DataAlreadyExistsException("Transaksi", t.getKodeTransaksi());
            }
        }
        return repository.saveAll(listTransaksi);
    }

    // UPDATE
    public Transaksi update(String id, Transaksi updated) {
        Transaksi existing = getById(id);
        existing.setTglTransaksi(updated.getTglTransaksi());
        existing.setIdPelanggan(updated.getIdPelanggan());
        existing.setIdKaryawan(updated.getIdKaryawan());
        return repository.save(existing);
    }

    // DELETE SINGLE
    public void delete(String id) {
        if (!repository.existsById(id)) throw new DataNotFoundException("Transaksi", id);
        repository.deleteById(id);
    }

    // DELETE BULK
    public void deleteBulk(List<String> ids) {
        List<Transaksi> existing = repository.findAllById(ids);
        if (existing.size() != ids.size()) {
            throw new DataNotFoundException("Sebagian Transaksi", "bulk");
        }
        repository.deleteAllById(ids);
    }
}