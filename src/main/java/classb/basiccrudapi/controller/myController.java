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
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin
@ResponseBody
@RequestMapping("/barang")

public class myController {

    Barang brg = new Barang(); // declare entity
    BarangJpaController ctrl = new BarangJpaController(); // declare jpa controller

    List<Barang> barangList = new ArrayList<Barang>(); // declare new List variable

    @GetMapping()
    public List<Barang> viewAll() {
        try {
            return ctrl.findBarangEntities(); // return data if data is available
        } catch (Exception e) {
            return List.of(); // check data is empty or no
        }

    }

    @GetMapping("/{id}")
    public List<Barang> viewDatabyId(@PathVariable("id") int id) {
        try {
            brg = ctrl.findBarang(id); // get data from entity
            barangList.clear(); // clear data in list
            barangList.add(brg); // fill list
            return barangList; // show data
        } catch (Exception e) {
            return List.of(); // data is empty
        }
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE) // convert string to JSON
    public String editData(@PathVariable("id") int id, @RequestBody Barang data) {
        String rslt = "Data has been updated";
        try {
            data.setId(id); // inserting data
            ctrl.edit(data); // updating data in entity
        } catch (Exception e) {
            rslt = e.toString() + " Update Failed";
        }

        return rslt;

    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE) // convert string to json
    public String delete(@PathVariable("id") int id) {
        String rslt = "Data Berhasil Di Hapus";
        try {
            ctrl.destroy(id); // delete data
        } catch (Exception e) {
            rslt = "Data Tidak Berhasil di hapus";
        }

        return rslt;
    }

}
