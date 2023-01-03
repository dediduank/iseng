/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classb.basiccrudapi.controller;


import java.util.ArrayList;
import java.util.List;
import model.entity.Barang;
import model.jpacontroller.BarangJpaController;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author Duank
 */
@RestController
@ResponseBody
@RequestMapping("/barang")

public class myController {

    Barang brg = new Barang();
    BarangJpaController ctrl = new BarangJpaController();

    List<Barang> barangList = new ArrayList<Barang>();

    @GetMapping()
    public List<Barang> viewAll() {
        if (ctrl.findBarangEntities().isEmpty()) {
            return List.of();
        }
        return ctrl.findBarangEntities();
    }

    @GetMapping("/{id}")
    public List<Barang> viewDatabyId(@PathVariable("id") int id) {

        brg = ctrl.findBarang(id);
        barangList.clear(); // clear data agar tidak tampil lebih dari sekali
        barangList.add(brg); // input data ke dalam list

        return barangList;
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String editData(@PathVariable("id") int id, @RequestBody Barang data ) {
        String rslt = "Data has been updated";
        try {
            data.setId(id);
            ctrl.edit(data);
        } catch (Exception e) {
            rslt = e.toString() + " Update Failed";
        }

        return rslt;

    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String delete(@PathVariable("id") int id) {
        String rslt = "Data Berhasil Di Hapus";
        try {
            ctrl.destroy(id);
        } catch (Exception e) {
            rslt = "Data Tidak Berhasil di hapus";
        }

        return rslt;
    }

}
