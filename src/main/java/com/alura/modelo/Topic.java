package com.alura.modelo;

import com.alura.modelo.dto.TopicRegisterData;
import com.alura.modelo.dto.TopicUpdateDataById;
import com.alura.modelo.enums.TopicStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.alura.modelo.enums.TopicStatus.getTopicStatusByTopicStatusName;

@Entity(name = "Topic")
@Table(name = "topics")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Topic {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String title;
	@Column(unique = true)
	private String message;

	private LocalDateTime creationDate = LocalDateTime.now();
	@Enumerated(EnumType.STRING)
	private TopicStatus status = TopicStatus.NO_ANSWERED;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable=false)
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "course_id", nullable=false)
	private Course course;

	@OneToMany(mappedBy = "topic", cascade = CascadeType.ALL)
	private List<Answer> answers = new ArrayList<>();

	public Topic(TopicRegisterData topicRegisterData, User user, Course course) {
		this.title = topicRegisterData.title();
		this.message = topicRegisterData.message();
		this.user = user;
		this.course = course;

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Topic other = (Topic) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public void updateData(TopicUpdateDataById topicUpdateDataById, Course course) {
		if (!topicUpdateDataById.title().isBlank()){
			this.title = topicUpdateDataById.title();
		}
		if (!topicUpdateDataById.message().isBlank()){
			this.message = topicUpdateDataById.message();
		}
		if (!topicUpdateDataById.topicStatus().isBlank()){
			try {
				this.status = getTopicStatusByTopicStatusName(topicUpdateDataById.topicStatus());
			} catch (NoSuchFieldException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}
		this.course = course;
	}

	public void topicSolved(){
		this.status = TopicStatus.SOLVED;
	}


}
