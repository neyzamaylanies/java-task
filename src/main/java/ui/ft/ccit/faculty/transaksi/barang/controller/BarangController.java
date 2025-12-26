package ui.ft.ccit.faculty.transaksi.barang.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import ui.ft.ccit.faculty.transaksi.barang.model.Barang;
import ui.ft.ccit.faculty.transaksi.barang.view.BarangService;

import java.util.List;

@RestController
@RequestMapping("/api/barang")
public class BarangController {

    private final BarangService service;

    public BarangController(BarangService service) {
        this.service = service;
    }

    // GET list semua barang
    @GetMapping
    @Operation(summary = "Mengambil daftar semua barang", description = "Mengambil seluruh data barang yang tersedia di sistem.\r\n"
            + //
            "Mendukung pagination opsional melalui parameter `page` dan `size`.")
    public List<Barang> list(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        // TANPA pagination
        if (page == null && size == null) {
            return service.getAll();
        }

        // DENGAN pagination
        int p = (page != null && page >= 0) ? page : 0;
        int s = (size != null && size > 0) ? size : 5;
        return service.getAllWithPagination(p, s);
    }

    // GET satu barang by id
    @GetMapping("/{id}")
    @Operation(summary = "Mengambil detail satu barang", description = "Mengambil detail satu barang berdasarkan ID.")
    public Barang get(@PathVariable String id) {
        return service.getById(id);
    }

    // SEARCH by nama
    @GetMapping("/search")
    @Operation(summary = "Mencari barang berdasarkan nama", description = "Mencari barang berdasarkan kata kunci pada nama.")
    public List<Barang> search(@RequestParam String q) {
        return service.searchByNama(q);
    }

    // POST - create barang baru
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Membuat barang baru", description = "Membuat satu data barang baru ke dalam sistem.")
    public Barang create(@RequestBody Barang barang) {
        return service.save(barang);
    }

    // POST - create barang bulk baru
    @PostMapping("/bulk")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Membuat barang secara bulk", description = "Membuat banyak barang baru dalam satu transaksi.")
    public List<Barang> createBulk(@RequestBody List<Barang> barang) {
        return service.saveBulk(barang);
    }

    // PUT - edit/update barang
    @PutMapping("/{id}")
    @Operation(summary = "Memperbarui data barang", description = "Memperbarui data barang berdasarkan ID.")
    public Barang update(@PathVariable String id, @RequestBody Barang barang) {
        return service.update(id, barang);
    }

    // DELETE - hapus multiple barang
    @DeleteMapping("/bulk")
    @Operation(summary = "Menghapus barang secara bulk", description = "Menghapus banyak barang berdasarkan daftar ID.")
    public void deleteBulk(@RequestBody List<String> ids) {
        service.deleteBulk(ids);
    }

    // DELETE - hapus barang
    @DeleteMapping("/{id}")
    @Operation(summary = "Menghapus barang", description = "Menghapus satu barang berdasarkan ID.")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
