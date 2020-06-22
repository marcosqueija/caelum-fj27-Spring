package br.com.alura.forum.controller;

import br.com.alura.forum.annotation.ApiPageable;
import br.com.alura.forum.dto.input.TopicSearchDto;
import br.com.alura.forum.dto.output.TopicBriefOutputDto;
import br.com.alura.forum.service.TopicService;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.forum.annotation.ApiPageable;
import br.com.alura.forum.dto.input.TopicSearchDto;
import br.com.alura.forum.dto.output.TopicBriefOutputDto;
import br.com.alura.forum.model.Category;
import br.com.alura.forum.model.Course;
import br.com.alura.forum.model.User;
import br.com.alura.forum.model.topic.domain.Topic;
import br.com.alura.forum.repository.TopicRepository;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Arrays;
import java.util.List;

import static br.com.alura.forum.dto.output.TopicBriefOutputDto.listFromTopics;


@RestController
public class TopicController {

	@Autowired
	private TopicRepository topicRepository;

	public TopicController(TopicRepository topicRepository) {
		this.topicRepository = topicRepository;
	}
//  passando pelo dto para customizar o json    
	@GetMapping(value = "/api/topics", produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<TopicBriefOutputDto> listTopics(TopicSearchDto topicSearch, Pageable pageRequest) {
		Specification<Topic> topicSearchSpecification = topicSearch.toSpecification();
		Page<Topic> topics = this.topicRepository.findAll(topicSearchSpecification, pageRequest);
		return  TopicBriefOutputDto.listFromTopics(topics);
	}

	// json direto 
  	@GetMapping(value = "/api/topicsold", produces = MediaType.APPLICATION_JSON_VALUE) 
	public List<TopicBriefOutputDto> topicList() {
		return listFromTopics(topicRepository.findAll());
	}

  	//  opcao de acessar o servico de procura por categoria - retorna uma lista com paginacao  	
    @GetMapping(value="/api/search")
    @ApiPageable
    public Page<TopicBriefOutputDto> listarPorStatusCategoria(TopicSearchDto topicSearchDto, @ApiIgnore Pageable pageable) {
        Page<Topic> topicos = topicRepository.findAll(topicSearchDto.toSpecification(), pageable);
        return listFromTopics(topicos);
    }
/*
	@GetMapping(value = "api/teste", produces = MediaType.APPLICATION_JSON_VALUE)
	public Topic listTopics() {
		Category subcategory = new Category("Java", new Category("Programação"));
		Course course = new Course("Java e JSF", subcategory);
		Topic topic = new Topic("Problemas com o JSF", "Erro ao fazer conversão da data",
				new User("Fulano", "fulano@gmail.com", "123456"), course);
		return topic;
	}
	
	@GetMapping(value = "api/teste_dto", produces = MediaType.APPLICATION_JSON_VALUE)
	public TopicBriefOutputDto listTopicsdto() {
		Category subcategory = new Category("Java", new Category("Programação"));
		Course course = new Course("Java e JSF", subcategory);
		Topic topic = new Topic("Problemas com o JSF", "Erro ao fazer conversão da data x",
				new User("Fulano", "fulano@gmail.com", "123456"), course);
		return new TopicBriefOutputDto(topic);
	}
*/
}