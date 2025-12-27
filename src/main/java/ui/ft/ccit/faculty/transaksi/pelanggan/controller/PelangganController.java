package ui.ft.ccit.faculty.transaksi.pelanggan.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import ui.ft.ccit.faculty.transaksi.pelanggan.model.Pelanggan;
import ui.ft.ccit.faculty.transaksi.pelanggan.view.PelangganService;

import java.util.List;

@RestController
@RequestMapping("/api/pelanggan")
public class PelangganController {

    private final PelangganService service;

    public PelangganController(PelangganService service) {
        this.service = service;
    }

    // GET List / Pagination
    @GetMapping
    @Operation(summary = "Mengambil daftar semua pelanggan", description = "Mengambil seluruh data pelanggan yang tersedia di sistem.\r\n"
            + //
            "Mendukung pagination opsional melalui parameter `page` dan `size`.")
    public List<Pelanggan> list(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        
        // Without pagination
        if (page == null && size == null) {
            return service.getAll();
        }

        // With pagination
        int p = (page != null && page >= 0) ? page : 0;
        int s = (size != null && size > 0) ? size : 5;
        return service.getAllWithPagination(p, s);
    }

    // GET One by id
    @GetMapping("/{id}")
    @Operation(summary = "Mengambil detail satu pelanggan", description = "Mengambil detail satu pelanggan berdasarkan ID.")
    public Pelanggan get(@PathVariable String id) {
        return service.getById(id);
    }

    // SEARCH by nama
    @GetMapping("/search")
    @Operation(summary = "Mencari pelanggan berdasarkan nama", description = "Mencari pelanggan berdasarkan kata kunci pada nama.")
    public List<Pelanggan> search(@RequestParam String q) {
        return service.searchByNama(q);
    }

    // POST (Create)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pelanggan create(@RequestBody Pelanggan pelanggan) {
        return service.save(pelanggan);
    }

    // POST (Create) - Bulk
    @PostMapping("/bulk")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Pelanggan> createBulk(@RequestBody List<Pelanggan> pelanggans) {
        return service.saveBulk(pelanggans);
    }

    // PUT (Update)
    @PutMapping("/{id}")
    public Pelanggan update(@PathVariable String id, @RequestBody Pelanggan pelanggan) {
        return service.update(id, pelanggan);
    }

   // DELETE - hapus multiple barang
    @DeleteMapping("/bulk")
    public void deleteBulk(@RequestBody List<String> ids) {
        service.deleteBulk(ids);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}