package ui.ft.ccit.faculty.transaksi.detailtransaksi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import ui.ft.ccit.faculty.transaksi.detailtransaksi.model.DetailTransaksi;
import ui.ft.ccit.faculty.transaksi.detailtransaksi.model.DetailTransaksiId;
import ui.ft.ccit.faculty.transaksi.detailtransaksi.view.DetailTransaksiService;

import java.util.List;

@RestController
@RequestMapping("/api/detail-transaksi")
public class DetailTransaksiController {

    private final DetailTransaksiService service;

    public DetailTransaksiController(DetailTransaksiService service) {
        this.service = service;
    }

    // GET ALL
    @GetMapping
    public List<DetailTransaksi> list(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        if (page == null & size == null){
            return service.getAll();
        }

        int p = (page != null && page >= 0) ? page : 0;
        int s = (size != null && size > 0) ? size : 5;
        return service.getAllWithPagination(p, s);
    }

    // GET By Header (Struk Tertentu)
    @GetMapping("/trx/{kode}")
    public List<DetailTransaksi> getByHeader(@PathVariable String kode) {
        return service.getByKodeTransaksi(kode);
    }

    // GET ONE (Composite Key)
    @GetMapping("/{kode}/{idBarang}")
    public DetailTransaksi get(@PathVariable String kode, @PathVariable String idBarang) {
        return service.getById(kode, idBarang);
    }

    // POST SINGLE
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DetailTransaksi create(@RequestBody DetailTransaksi dt) {
        return service.save(dt);
    }

    // POST BULK (Plek Ketiplek Barang)
    @PostMapping("/bulk")
    @ResponseStatus(HttpStatus.CREATED)
    public List<DetailTransaksi> createBulk(@RequestBody List<DetailTransaksi> detailList) {
        return service.saveBulk(detailList);
    }

    // PUT SINGLE
    @PutMapping("/{kode}/{idBarang}")
    public DetailTransaksi update(@PathVariable String kode, 
                                  @PathVariable String idBarang, 
                                  @RequestBody DetailTransaksi dt) {
        return service.update(kode, idBarang, dt);
    }

    // DELETE BULK (Plek Ketiplek Barang)
    // Ingat: Input JSON-nya Array of Object { "kodeTransaksi": "...", "idBarang": "..." }
    @DeleteMapping("/bulk")
    public void deleteBulk(@RequestBody List<DetailTransaksiId> ids) {
        service.deleteBulk(ids);
    }

    // DELETE SINGLE
    @DeleteMapping("/{kode}/{idBarang}")
    public void delete(@PathVariable String kode, @PathVariable String idBarang) {
        service.delete(kode, idBarang);
    }
}