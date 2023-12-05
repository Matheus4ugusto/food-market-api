package com.academiadodesenvolvedor.market.services;

import com.academiadodesenvolvedor.market.exceptions.ResourceNotFoundException;
import com.academiadodesenvolvedor.market.models.Store;
import com.academiadodesenvolvedor.market.repositories.StoreRepository;
import com.academiadodesenvolvedor.market.requests.CreateStoreRequest;
import com.academiadodesenvolvedor.market.services.contracts.StoreServiceContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService implements StoreServiceContract {
    private StoreRepository storeRepository;

    @Autowired
    public StoreService(StoreRepository storeRepository){
        this.storeRepository = storeRepository;
    }

    @Override
    public Store createStore(Store store) {
        return this.storeRepository.save(store);
    }

    @Override
    public Store updateStore(Long id, CreateStoreRequest request) {
        Store store = this.getStoreById(id);

        return this.storeRepository.save(request.update(store));
    }

    @Override
    public Store getStoreById(Long id){
        return this.storeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Loja não encontrada"));
    }

    @Override
    public void deleteStore(Store store) {
        this.storeRepository.delete(store);
    }

    @Override
    public List<Store> getStores() {
        return this.storeRepository.findAll();
    }

    @Override
    public Page<Store> getStores(Pageable pageable) {
        return this.storeRepository.findAll(pageable);
    }
}
