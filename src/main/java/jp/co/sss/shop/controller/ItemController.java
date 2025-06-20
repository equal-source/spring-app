package jp.co.sss.shop.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.sss.shop.bean.ItemBean;
import jp.co.sss.shop.entity.Item;
import jp.co.sss.shop.form.ItemForm;
import jp.co.sss.shop.repository.ItemRepository;

@Controller
public class ItemController {

    @Autowired
    ItemRepository repository;

    @RequestMapping("/items/findAll")
    public String showItemList(Model model) {
        model.addAttribute("items", repository.findAll());
        System.out.println(model.getAttribute("items"));
        return "items/item_list";
    }

    @RequestMapping("/items/findAllByOrderByPriceDesc")
    public String showItemListByOrderByPriceDesc(Model model) {
        model.addAttribute("items", repository.findAllByOrderByPriceDesc());
        return "items/item_list";
    }

    @RequestMapping("/items/getReferenceById/{id}")
    public String showItem(@PathVariable int id, Model model) {
        Item item = repository.getReferenceById(id);
        ItemBean itemBean = new ItemBean();
        itemBean.setId(item.getId());
        itemBean.setName(item.getName());
        itemBean.setPrice(item.getPrice());
        model.addAttribute("item", itemBean);
        return "items/item";
    }

    @RequestMapping("/items/findByPrice/{price}")
    public String showItemListByPrice(@PathVariable Integer price, Model model) {
        model.addAttribute("items", repository.findByPrice(price));
        return "items/item_list";
    }

    @RequestMapping("/items/findByNameAndPrice/{name}/{price}")
    public String showItemListByNameAndPrice(
            @PathVariable String name, @PathVariable Integer price, Model model) {
        model.addAttribute("items", repository.findByNameAndPrice(name, price));
        return "items/item_list";
    }

    @RequestMapping("/items/findByNameLike/{name}")
    public String showItemListByNameLike(@PathVariable String name, Model model) {
        model.addAttribute("items", repository.findByNameContaining(name));
        return "items/item_list";
    }

    @RequestMapping("/items/findAllAndSetDropdown")
    public String itemListSetDropdown(Model model) {
        model.addAttribute("items", repository.findAll());
        return "items/item_list_dropdown";
    }

    @RequestMapping("/items/create/input")
    public String createInput() {
        return "items/create_input";
    }

    @RequestMapping(path = "/items/create/complete", method = RequestMethod.POST)
    public String createComplete(ItemForm form, Model model) {
        Item item = new Item();
        // formに存在するユーザー情報を、itemオブジェクトにコピーする
        BeanUtils.copyProperties(form, item, "id");
        // repositoryを使用して、itemオブジェクトをデータベースに保存する
        item = repository.save(item);
        ItemBean itemBean = new ItemBean();
        BeanUtils.copyProperties(item, itemBean);
        model.addAttribute("item", itemBean);
        return "items/item";
    }

    @RequestMapping("/items/update/input/{id}")
    public String updateInput(@PathVariable Integer id, Model model) {
        Item item = repository.getReferenceById(id);
        ItemBean itemBean = new ItemBean();
        BeanUtils.copyProperties(item, itemBean);
        model.addAttribute("item", itemBean);
        return "items/update_input";
    }

    @RequestMapping(path = "/items/update/complete/{id}", method = RequestMethod.POST)
    public String updateComplete(@PathVariable Integer id, ItemForm form, Model model) {
        Item item = repository.getReferenceById(id);
        BeanUtils.copyProperties(form, item, "id");
        item = repository.save(item);
        ItemBean itemBean = new ItemBean();
        BeanUtils.copyProperties(item, itemBean);
        model.addAttribute("item", itemBean);
        return "items/item";
    }

    @RequestMapping("/items/delete/input")
    public String deleteInput(Model model) {
        model.addAttribute("items", repository.findAll());
        return "items/delete_input";
    }

    @RequestMapping(path = "/items/delete/complete", method = RequestMethod.POST)
    public String deleteComplete(ItemForm form) {
        repository.deleteById(form.getId());
        return "redirect:/items/findAll";
    }

    //登録画面表示
    @RequestMapping("items/create/input/hidden")
    public String itemInputHidden() {
//入力画面遷移
        return "items/create_input_hidden";
    }
//確認画面表示

    @RequestMapping(path = "/create/check/hidden", method = RequestMethod.POST)
    public String itemCheckHidden(ItemForm form, Model model) {
        model.addAttribute("item", form);
//確認画面遷移
        return "items/create_check_hidden";
    }
//登録完了画面表示

    @RequestMapping("/create/complete/hidden")
    public String itemCompleteHidden(ItemForm form) {
//完了画面遷移
        return "items/create_complete_hidden";
    }
}
