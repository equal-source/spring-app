package jp.co.sss.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.sss.shop.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

    //6章 ソースコード6-7 追加
    List<Item> findAllByOrderByPriceDesc();

    //6章 ソースコード6-14 追加
    List<Item> findByPrice(Integer price);

    //6章 ソースコード6-17 追加
    List<Item> findByNameAndPrice(String name, Integer price);

    //6章 ソースコード6-20 追加
    List<Item> findByNameContaining(String name);
}
