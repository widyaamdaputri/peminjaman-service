/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.widya.peminjaman.service.Service;

import com.widya.peminjaman.service.Entity.Peminjaman;
import com.widya.peminjaman.service.Repository.PeminjamanRepository;
import com.widya.peminjaman.service.Vo.Anggota;
import com.widya.peminjaman.service.Vo.Buku;
import com.widya.peminjaman.service.Vo.ResponseTemplateVo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author hp
 */
@Service
public class PeminjamanService {
    @Autowired
    private PeminjamanRepository peminjamanRepository;
    
    @Autowired
    private RestTemplate restTemplate;
    
   
    public Peminjaman savePeminjaman(Peminjaman peminjaman) {
       return peminjamanRepository.save(peminjaman); 
    }

     public ResponseTemplateVo getPeminjaman(Long peminjamanId){
        ResponseTemplateVo vo = new ResponseTemplateVo();
        Peminjaman peminjaman = peminjamanRepository.findByPeminjamanId(peminjamanId);
        Anggota anggota = restTemplate.getForObject("http://localhost:8111/anggota/" 
              + peminjaman.getAnggotaId(), Anggota.class);
        Buku buku = restTemplate.getForObject("http://localhost:8117/buku/" 
                + peminjaman.getBukuId(), Buku.class);
        Vo.setPeminjaman(peminjaman);
        Vo.setAnggota(anggota);
        Vo.setBuku(buku);
        return vo;
    }
     
     
     
    public List<Peminjaman> getAllPeminjaman(){
        return peminjamanRepository.findAll();
    }
    
    public void deletePeminjaman(Long peminjamanId) {
        peminjamanRepository.deleteById(peminjamanId);
    }
    
    public Peminjaman updatePeminjaman(Peminjaman peminjaman){
        return peminjamanRepository.save(peminjaman);
    }

    private static class Vo {

        private static void setBuku(Buku buku) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private static void setAnggota(Anggota anggota) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private static void setPeminjaman(Peminjaman peminjaman) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        public Vo() {
        }
    }
}
