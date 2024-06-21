package com.example.demo.rome;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.net.URL;

@AllArgsConstructor
@Getter
public class Rome {
    private String urlFeed;


    public void lerFeed() {
        try {
            URL url = new URL(getUrlFeed());

            SyndFeedInput entrada = new SyndFeedInput();
            SyndFeed feed = entrada.build(new XmlReader(url));

            System.out.println("Título do Feed: " + feed.getTitle());
            System.out.println("Link do Feed: " + feed.getLink());
            System.out.println("Descrição do Feed: " + feed.getDescription());

            feed.getEntries().forEach(noticia -> {
                System.out.println("Título da Notícia: " + noticia.getTitle());
                System.out.println("Link da Notícia: " + noticia.getLink());
                System.out.println("Descrição da Notícia: " + noticia.getDescription().getValue());
                System.out.println("Data de Publicação: " + noticia.getPublishedDate());
                System.out.println("----------------------------------------");
            });
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
