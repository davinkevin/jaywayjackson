package com.github.davinkevin.poc;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.TypeRef;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
import lombok.Data;
import org.junit.Test;

import java.util.List;

/**
 * jaywayjackson
 * Created by kdavin on 08/06/2016.
 */
public class Jawayjackson {

    @Test
    public void should_parse_the_json() {
        /* GIVEN */
        String json = "{\n" +
                "    \"store\": {\n" +
                "        \"book\": [\n" +
                "            {\n" +
                "                \"category\": \"reference\",\n" +
                "                \"author\": \"Nigel Rees\",\n" +
                "                \"title\": \"Sayings of the Century\",\n" +
                "                \"price\": 8.95\n" +
                "            },\n" +
                "            {\n" +
                "                \"category\": \"fiction\",\n" +
                "                \"author\": \"Evelyn Waugh\",\n" +
                "                \"title\": \"Sword of Honour\",\n" +
                "                \"price\": 12.99\n" +
                "            },\n" +
                "            {\n" +
                "                \"category\": \"fiction\",\n" +
                "                \"author\": \"Herman Melville\",\n" +
                "                \"title\": \"Moby Dick\",\n" +
                "                \"isbn\": \"0-553-21311-3\",\n" +
                "                \"price\": 8.99\n" +
                "            },\n" +
                "            {\n" +
                "                \"category\": \"fiction\",\n" +
                "                \"author\": \"J. R. R. Tolkien\",\n" +
                "                \"title\": \"The Lord of the Rings\",\n" +
                "                \"isbn\": \"0-395-19395-8\",\n" +
                "                \"price\": 22.99\n" +
                "            }\n" +
                "        ],\n" +
                "        \"bicycle\": {\n" +
                "            \"color\": \"red\",\n" +
                "            \"price\": 19.95\n" +
                "        }\n" +
                "    },\n" +
                "    \"expensive\": 10\n" +
                "}";

        Configuration conf = Configuration
                .builder()
                .mappingProvider(new JacksonMappingProvider())
                .build();

        TypeRef<List<Book>> type = new TypeRef<List<Book>>(){};


        List<Book> books = JsonPath
                .using(conf)
                .parse(json)
                .read("store.book", type);
                /*.getJsonObject("store.book", Book.class)*/
        /* THEN  */

        System.out.println(books);
    }

    @Data
    public static class Book {
        String category;
        String author;
        String title;
        String isbn;
        Long price;
    }

}
