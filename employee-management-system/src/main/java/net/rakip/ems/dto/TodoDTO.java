package net.rakip.ems.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodoDTO {
	
	private Long id;
	private String title;
	private String description;
	private boolean completed;
	
	@Override
	public String toString() {
		
		return "id: " + id +", title: " + title + ", description: " + description + ", completed: " + completed;
	} 
	
}
