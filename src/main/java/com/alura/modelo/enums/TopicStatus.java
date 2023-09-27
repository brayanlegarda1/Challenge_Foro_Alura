package com.alura.modelo.enums;

import java.lang.reflect.Field;

public enum TopicStatus {
	
	NO_ANSWERED ("No answered"),
	UNSOLVED("Unsolved"),
	SOLVED("Solved"),
	CLOSE("Close");

	final String topicStatusName;

	TopicStatus(String topicStatusName) {
		this.topicStatusName = topicStatusName;
	}

	@Override
	public String toString() {
		return topicStatusName;
	}


	public static TopicStatus getTopicStatusByTopicStatusName(String topicStatusName) throws NoSuchFieldException, IllegalAccessException {

		for (TopicStatus option : TopicStatus.values()) {
			Field stringValueField = TopicStatus.class.getDeclaredField("topicStatusName");
			stringValueField.setAccessible(true);

			String enumStringValue = (String) stringValueField.get(option);

			if (topicStatusName.equals(enumStringValue)) {
				return option;
			}
		}

		throw new NoSuchFieldException();
	}

}
