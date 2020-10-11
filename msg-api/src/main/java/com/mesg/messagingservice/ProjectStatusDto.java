package com.mesg.messagingservice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectStatusDto {

	private Long id;
	private String productName;
	private String authorEmailAdres;
	private String authorName;
}
