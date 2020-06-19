package br.com.alura.forum.dto.output;

import br.com.alura.forum.model.topic.domain.Topic;
import br.com.alura.forum.model.topic.domain.TopicStatus;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

public class TopicBriefOutputDto {

    private Integer id;
    private String shortDescription;
    private long secondsSinceLastUpdate;
    private String ownerName;
    private String courseName;
    private String subcategoryName;
    private String categoryName;
    private int numberOfResponses;
    private boolean solved;
    private TopicStatus topicStatus;

    public TopicBriefOutputDto(Topic topic) {
        this.id = topic.getId() == null ? 1 : topic.getId().intValue();
        this.shortDescription = topic.getShortDescription();
        this.secondsSinceLastUpdate = getSecondsSince(topic.getLastUpdate());
        this.ownerName = topic.getOwner().getName();
        this.courseName = topic.getCourse().getName();
        this.subcategoryName = topic.getCourse().getSubcategoryName();
        this.categoryName = topic.getCourse().getCategoryName();
        this.numberOfResponses = topic.getNumberOfAnswers();
        this.solved = TopicStatus.SOLVED.equals(topic.getStatus());
        this.topicStatus = topic.getStatus();
    }

    private long getSecondsSince(Instant lastUpdate) {
        return Duration.between(lastUpdate, Instant.now()).get(ChronoUnit.SECONDS);
    }

    public Integer getId() {
        return id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public long getSecondsSinceLastUpdate() {
        return secondsSinceLastUpdate;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getSubcategoryName() {
        return subcategoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public int getNumberOfResponses() {
        return numberOfResponses;
    }

    public boolean isSolved() { return solved; }

    public TopicStatus getTopicStatus() {
        return topicStatus;
    }

    public void setTopicStatus(TopicStatus topicStatus) {
        this.topicStatus = topicStatus;
    }

    public static List<TopicBriefOutputDto> listFromTopics(List<Topic> topics) {
        return topics.stream()
                .map(TopicBriefOutputDto::new)
                .collect(Collectors.toList());
    }
    
    public static Page<TopicBriefOutputDto> listFromTopics(Page<Topic> topics) {
        return topics.map(TopicBriefOutputDto::new);
    }


}