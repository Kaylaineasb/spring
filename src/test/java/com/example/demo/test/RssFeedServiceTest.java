package com.example.demo.test;

import com.example.demo.repository.NoticiaRepository;
import com.example.demo.service.RssFeedService;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.StringReader;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@SpringBootTest
public class RssFeedServiceTest {

    @Mock
    private NoticiaRepository noticiaRepository;

    @InjectMocks
    private RssFeedService rssFeedService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testConsumirFeedRss() throws Exception {
        // Simula um feed RSS com uma entrada de notícia
        String rss = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "<rss version=\"2.0\">\n" +
                "  <channel>\n" +
                "    <title>G1 - Todas as notícias</title>\n" +
                "    <item>\n" +
                "      <title>Notícia de Teste</title>\n" +
                "      <description><![CDATA[<img src=\"http://imagem.com/teste.jpg\">Descrição da notícia de teste]]></description>\n" +
                "      <link>http://g1.globo.com/noticia-teste</link>\n" +
                "      <pubDate>Wed, 14 Jun 2024 00:00:00 GMT</pubDate>\n" +
                "    </item>\n" +
                "  </channel>\n" +
                "</rss>";

        // Usa o Rome para parsear o feed simulado
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = input.build(new StringReader(rss));

        // Define o comportamento do mock para evitar duplicação de notícias
        when(noticiaRepository.existsByTitulo("Notícia de Teste")).thenReturn(false);

        // Chame diretamente o método consumirFeedRss com a string do feed simulado
        rssFeedService.consumirFeedRss(String.valueOf(feed));

        // Verifica se o método save foi chamado uma vez com a notícia correta
        verify(noticiaRepository, times(1)).save(argThat(noticia ->
                noticia.getTitulo().equals("Notícia de Teste") &&
                        noticia.getDescricao().contains("Descrição da notícia de teste") &&
                        noticia.getLink().equals("http://g1.globo.com/noticia-teste") &&
                        noticia.getImagem().equals("http://imagem.com/teste.jpg") &&
                        noticia.getDataPublicacao().equals(
                                Date.from(LocalDateTime.of(2024, 6, 14, 0, 0)
                                        .atZone(ZoneId.systemDefault()).toInstant()))
        ));
    }
}
