package br.com.alura.forum.service;

import br.com.alura.forum.dto.input.TopicSearchDto;
import br.com.alura.forum.dto.output.TopicBriefOutputDto;
import br.com.alura.forum.repository.TopicRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static br.com.alura.forum.dto.output.TopicBriefOutputDto.listFromTopics;

@AllArgsConstructor
@Service
public class TopicService {

    private TopicRepository topicRepository;

    public Page<TopicBriefOutputDto> getTopics(TopicSearchDto topicSearchDto, Pageable pageable) {
        return listFromTopics(topicRepository.findAll(topicSearchDto.toSpecification(), pageable));
    }

}
