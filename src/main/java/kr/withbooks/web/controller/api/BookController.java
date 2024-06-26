package kr.withbooks.web.controller.api;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.withbooks.web.entity.Book;
import kr.withbooks.web.entity.Category;
import kr.withbooks.web.service.AladinAPIService;
import kr.withbooks.web.service.BookService;
import kr.withbooks.web.service.CategoryService;

@RestController("apiBookController")
@RequestMapping("api/book")
public class BookController {

    @Autowired
    private BookService service;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AladinAPIService apiService;

    // ======================================================================
    // mariaDB
    @GetMapping("detail")
    public Book detail(Long id){

        System.out.println("id = "+id);
        Book book = service.getById(id);
        return book;
    }

    @GetMapping("bestseller")
    public Integer bestseller(Long bookId){
        System.out.println("bookId = "+bookId);
        Integer result  = service.getBestseller(bookId);
        return result;
    }


    // 북쇼츠, 북로그 등 책검색
    @GetMapping("list")
    public List<Book> list(
                                @RequestParam(name = "q", required = false) String query
                                ,@RequestParam(name = "c", required = false) Long categoryId
                                ,@RequestParam(name = "s", required = false) Long size
                                ,@RequestParam(name = "p", required = false) Long page
                                ) {

        // 카테고리 선택하지 않고 책 검색 시 0으로 보내는 값을 null로 처리
       if(categoryId == 0)
           categoryId=null;


        // List<Book> list = service.getList(query, categoryId);
        List<Book> list = service.getList(query, categoryId, size, page);
        System.out.println("list : " + list);

        return list;

    }
    
    @GetMapping("addBestseller")
    public Integer addBestseller(@RequestParam(required = false) Long bookId
                                ,@RequestParam(required = false) List<Long> ids
                                ){

        if(ids == null){
            ids = new ArrayList<>();
            ids.add(bookId);
        }
        
        Integer result = service.addBestseller(ids);
        return result;
    }

    @GetMapping("deleteBestseller")
    public Integer deleteBestseller(@RequestParam(required = false) Long bookId
                                ,@RequestParam(required = false) List<Long> ids
                                ){

        if(ids == null){
            ids = new ArrayList<>();
            ids.add(bookId);
        }
        
        Integer result = service.deleteBestseller(ids);
        return result;
    }

    @GetMapping("editPublic")
    public Integer editPublic(Long bookId, Integer yn){
        Integer result = service.editBookPublicYn(bookId, yn);
        return result;
    }

    @PostMapping("editBook")
    public Integer editBook(
                            // @RequestBody String jsonString
                            @RequestParam(name="bookId") Long bookId,
                            @RequestParam(name="price") Integer price,
                            @RequestParam(name="description") String description,
                            @RequestParam(name="purchaseLink") String purchaseLink
                            ){
        Integer result = 0;
        // System.out.println(jsonString);
        System.out.println(bookId);
        System.out.println(price);
        System.out.println(description);
        System.out.println(purchaseLink);
        result = service.editBook(bookId, price, description, purchaseLink);
        
        return result;
    }


    // =====================================================================

    // =====================================================================
    // aladdin API
    // admin/book/alaldinList -> reg
    @PostMapping("reg")
    public Integer reg(@RequestBody List<Book> list){
        
        if(list == null)
            return null;

        List<Category> categoryList = categoryService.getList();

        Integer insertedCount = service.reg(list, categoryList);
        System.out.println("인서트개수 = "+insertedCount);

        return insertedCount;
    }


    @GetMapping("getByISBN13")
    public Book getByISBN13(String isbn13){

        List<Book> list = new ArrayList<>();
        Integer result = apiService.getByISBN13(list,isbn13);
        System.err.println("요상타 = "+list.get(0));
        return list.get(0);
    }
}
