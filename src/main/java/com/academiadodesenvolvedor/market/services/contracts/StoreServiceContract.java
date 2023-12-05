package com.academiadodesenvolvedor.market.services.contracts;

import com.academiadodesenvolvedor.market.models.Store;
import com.academiadodesenvolvedor.market.requests.CreateStoreRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StoreServiceContract {

    Store createStore(Store store);

    Store updateStore(Long id, CreateStoreRequest store);

    Store getStoreById(Long id);

    void deleteStore(Store store);

    List<Store> getStores();

    Page<Store> getStores(Pageable pageable);


}
