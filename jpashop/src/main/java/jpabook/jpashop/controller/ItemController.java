package jpabook.jpashop.controller;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.service.ItemService;
import jpabook.jpashop.validation.BookSaveForm;
import jpabook.jpashop.validation.BookUpdateForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping(value = "/items/new")
    public String createForm(Model model){
        model.addAttribute("form", new BookSaveForm());
        return "items/createItemForm";
    }

    @PostMapping(value = "/items/new")
    public String create(@Validated @ModelAttribute("form") BookSaveForm form, BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "items/createItemForm";
        }

        //성공 로직
        Book book = new Book();
        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());

        itemService.saveItem(book);
        return "redirect:/items";
    }

    @GetMapping(value = "/items")
    public String list(Model model) {
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "items/itemList";
    }

    @GetMapping(value = "/items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model){
        Book item = (Book) itemService.findOne(itemId);

        BookUpdateForm form = new BookUpdateForm();
        form.setId(item.getId());
        form.setName(item.getName());
        form.setPrice(item.getPrice());
        form.setStockQuantity(item.getStockQuantity());
        form.setAuthor(item.getAuthor());
        form.setIsbn(item.getIsbn());

        model.addAttribute("form", form);
        return "items/updateItemForm";
    }

    @PostMapping(value = "/items/{itemId}/edit")
    public String updateItem(@PathVariable Long itemId, @Validated @ModelAttribute("form") BookUpdateForm form, BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "items/updateItemForm";
        }

        itemService.updateBook(itemId, form.getName(), form.getPrice(), form.getStockQuantity(),form.getAuthor(), form.getIsbn());
        return "redirect:/items";
    }
}
