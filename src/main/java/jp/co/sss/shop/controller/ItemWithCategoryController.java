package jp.co.sss.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sss.shop.entity.Category;
import jp.co.sss.shop.entity.ItemWithCategory;
import jp.co.sss.shop.repository.ItemWithCategoryRepository;

@Controller
public class ItemWithCategoryController {

    @Autowired
    ItemWithCategoryRepository repository;

    @RequestMapping("/items/findItemAndCategory")
    public String showItemAndCategoryList(Model model) {
        model.addAttribute("items", repository.findAll());
        return "items/item_category_list";
    }

    @RequestMapping("/items/searchByCategoryId/{categoryId}")
    public String searchByCategoryId(@PathVariable Integer categoryId, Model model) {
        //外部参照先テーブルに対応付けられたエンティティ Category のオブジェクトを生成
        Category category = new Category();
        //Category のオブジェクト内の id フィールドにパラメータの値を代入
        category.setId(categoryId);
        //Category のオブジェクト内の id フィールドを使用した条件検索を実行
        List<ItemWithCategory> items = repository.findByCategory(category);
        //検索結果をリクエストスコープに保存
        model.addAttribute("items", items);
        //商品一覧画面に遷移
        return "items/item_category_list";
    }
}
