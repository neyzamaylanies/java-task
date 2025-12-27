package ui.ft.ccit.faculty.transaksi.transaksi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ui.ft.ccit.faculty.transaksi.transaksi.model.Transaksi;
import ui.ft.ccit.faculty.transaksi.transaksi.view.TransaksiService;

import java.util.List;

@RestController
@RequestMapping("/api/transaksi")
public class TransaksiController {

    private final TransaksiService service;

    public TransaksiController(TransaksiService service) { 
        this.service = service; 
    }

    @GetMapping
    public List<Transaksi> list(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size) {
        if (page == null && size == null) return service.getAll();
        int p = (page != null && page >= 0) ? page : 0;
        int s = (size != null && size > 0) ? size : 5;
        return service.getAllWithPagination(p, s);
    }

    @GetMapping("/{id}")
    public Transaksi get(@PathVariable String id) { 
        return service.getById(id); 
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Transaksi create(@RequestBody Transaksi t) { 
        return service.save(t); 
    }

    // POST BULK
    @PostMapping("/bulk")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Transaksi> createBulk(@RequestBody List<Transaksi> list) { 
        return service.saveBulk(list); 
    }

    @PutMapping("/{id}")
    public Transaksi update(@PathVariable String id, @RequestBody Transaksi t) { 
        return service.update(id, t); 
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) { 
        service.delete(id); 
    }

    // DELETE BULK
    @DeleteMapping("/bulk")
    public void deleteBulk(@RequestBody List<String> ids) { 
        service.deleteBulk(ids); 
    }
}