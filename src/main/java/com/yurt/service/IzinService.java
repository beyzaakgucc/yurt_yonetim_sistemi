package com.yurt.service;

import com.yurt.domain.IzinTalebi;
import com.yurt.domain.IzinDurumu;
import com.yurt.observer.Observer;
import com.yurt.observer.OgrenciObserver;
import com.yurt.repository.IzinRepository;
import com.yurt.state.BeklemedeState;
import com.yurt.state.IzinState;
import com.yurt.state.OnaylandiState;
import com.yurt.state.ReddedildiState;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IzinService {

    private final IzinRepository izinRepository;
    private final List<Observer> observers = new ArrayList<>();

    public IzinService(IzinRepository izinRepository) {
        this.izinRepository = izinRepository;
        this.observers.add(new OgrenciObserver());
    }

    // CREATE
    public IzinTalebi izinOlustur(IzinTalebi izin) {
        izin.setDurum(IzinDurumu.BEKLEMEDE);
        return izinRepository.save(izin);
    }

    // STATE + OBSERVER
    public void izinOnayla(Long izinId) {
        IzinTalebi izin = izinRepository.findById(izinId)
                .orElseThrow(() -> new RuntimeException("İzin bulunamadı"));

        IzinState state = new BeklemedeState();
        state.onayla(izin);

        izinRepository.save(izin);
        bildir("İzin talebiniz ONAYLANDI");
    }

    public void izinReddet(Long izinId) {
        IzinTalebi izin = izinRepository.findById(izinId)
                .orElseThrow(() -> new RuntimeException("İzin bulunamadı"));

        IzinState state = new BeklemedeState();
        state.reddet(izin);

        izinRepository.save(izin);
        bildir("İzin talebiniz REDDEDİLDİ");
    }

    private void bildir(String mesaj) {
        for (Observer observer : observers) {
            observer.update(mesaj);
        }
    }
}
