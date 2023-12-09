package com.ui.book.service;

import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ui.book.dto.Book;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BooksRestClient {

    private static final String REST_URI = "http://localhost:38080/management-service/rest/book/";
    
    //Try 
    // @Autowired
    // private  Client client;
    private  Client client = ClientBuilder.newClient();

    // Get Book by ID
    public Book getBook(long id) {

        try {
            return client
                    .target(REST_URI)
                    .path("getbook/"+String.valueOf(id))
                    .request(MediaType.APPLICATION_JSON)
                    .get(Book.class);
        } catch (Exception e) {
            log.error("Exception in getBook, message is {}", e);
            throw e;
        }
        
    }

    // Get a JSON list of books
    public List<Book> getBooks(){

        try {
             return client.target(REST_URI)
                .path("getbooks")
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<Book>>() {});
        } catch (Exception e) {
            log.error("Exception in getBooks, message is {}", e);
            throw e;
        }
       
    }

    // Create a book
    public Response createBook(Book book){

        try {
            return client
                    .target(REST_URI)
                    .path("createBook")
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(book, MediaType.APPLICATION_JSON));
        } catch (Exception e) {
              log.error("Exception in createBook, message is {}", e);
            throw e;
        }
        
    }

    // Update a book by ID
    public void updateBook(long id, Book book){

        try {
             Response response = client 
                            .target(REST_URI)
                            .path("updateBook/" + String.valueOf(id))
                            .request(MediaType.APPLICATION_JSON)
                            .put(Entity.entity(book, MediaType.APPLICATION_JSON));

            if(response.getStatus() != Response.Status.OK.getStatusCode()){
                throw new RuntimeException("Failed to update  book. HTTP error code " + response.getStatus());
            }
        } catch (Exception e) {
            log.error("Exception in updateBook, message is {}", e);
            throw e;
        }
       
    }

    //Delete book by ID
    public void deleteBook(long id){

        try {
                Response response = client
                                .target(REST_URI)
                                .path("removeBook/" + String.valueOf(id))
                                .request(MediaType.APPLICATION_JSON)
                                .delete();

            if(response.getStatus() != Response.Status.OK.getStatusCode()){
                throw new RuntimeException("Failed to delete book. HTTP error code " + response.getStatus());
            }
        } catch (Exception e) {
            log.error("Exception in deleteBook, message is {}", e);
            throw e;
        }
        
    }

}
