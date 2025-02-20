package com.example.spring_backend.metadata;

import com.example.spring_backend.shared.BaseService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MetadataService extends BaseService<Metadata, Long> {

    private final MetadataRepository linkRepository;

    public MetadataService(JpaRepository<Metadata, Long> repository, MetadataRepository metadataRepository) {
        super(repository);
        this.linkRepository = metadataRepository;
    }

    public Metadata extractMetadata(String url) {
        System.out.println("Extracting metadata from " + url + "...");
        Document document = null;
        try {
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            System.out.println("Error while extracting metadata" + e);
            return null;
        }

        String title = document.select("meta[property=og:title]").attr("content");
        if (title.isEmpty()) {
            title = document.title();
        }
        String image = document.select("meta[property=og:image]").attr("content");

        return create(new Metadata(title, image));
    }
}
