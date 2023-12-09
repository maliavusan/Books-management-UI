package com.ui.book.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ui.book.dto.Book;
import com.ui.book.service.BooksRestClient;

@Controller
public class BookController {

    @Autowired
    private BooksRestClient objBooksRestClient;

   
    @GetMapping("/")
    public String indexPage(Model model){
        model.addAttribute("listBooks", objBooksRestClient.getBooks());
        return "home_page";
    }

    @GetMapping("/showNewBook")
    public String showNewBookForm(Model model){
        Book book = new Book();
        model.addAttribute("book", book);
        List<String> bookList = Arrays.asList("EBOOK","SOFTCOVER", "HARDCOVER");
        model.addAttribute("bookList", bookList);
        return "new_book";
    }

    @PostMapping("/saveBook")
    public String saveBook(@ModelAttribute("book") Book book){
        objBooksRestClient.createBook(book);
        return "redirect:/";
    }

    @PostMapping("/updateBook/{id}")
    public String updateBook(int id, @ModelAttribute("book") Book book){
        objBooksRestClient.updateBook(id, book);
        return "redirect:/";
    }

    @GetMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable(value = "id") int id){
        objBooksRestClient.deleteBook(id);
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") int id, Model model){
        Book book = objBooksRestClient.getBook(id);

        model.addAttribute("book", book);
        List<String> bookList = Arrays.asList("EBOOK","SOFTCOVER", "HARDCOVER");
        model.addAttribute("bookList", bookList);

        return "update_book";       
    
    }

}
