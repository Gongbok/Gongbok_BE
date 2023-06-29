package Gongbok_BE.Gongbok.review.service;

import Gongbok_BE.Gongbok.review.domain.Content;
import Gongbok_BE.Gongbok.review.repository.ContentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ContentService {
    private ContentRepository contentRepository;

    public ContentService(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    /*public Content getContentById(Long id) {
        return contentRepository.findById(id).orElse(null);
    }*/

    public Content saveContent(Content content) {
        return contentRepository.save(content);
    }
}

