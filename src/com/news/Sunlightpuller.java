package com.news;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Sunlightpuller {
    public static List<Object> sunlightpuller(String filename) throws IOException {
        List<Object> elements_list = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;

        while ((line=br.readLine())!=null){
            if (line.contains("sunlightfoundation.com/")){
                Document document = Jsoup.connect(line).get();
                Elements newscontent = document.select("article")
                        .select("p");
                Elements content_lists = document.select("article")
                        .select("ul")
                        .select("li");
                Elements titles = document.select("article")
                        .select("h1");

                elements_list.add("<a href=\""+line+"\">Sunlightfoundation</a>");
                elements_list.add(titles);
                elements_list.add("an");
                elements_list.add(newscontent);
                elements_list.add(content_lists);
            }
        }
        return elements_list;
    }

    public static void main(String[] args) throws IOException {
        List<Object> lists = new ArrayList<>();
        lists = sunlightpuller("final_urls.txt");
        System.out.println(lists.get(0));
        System.out.println(lists.get(1));
        System.out.println(lists.get(2));
        System.out.println(lists.get(3));
        System.out.println(lists.get(4));
    }



}
