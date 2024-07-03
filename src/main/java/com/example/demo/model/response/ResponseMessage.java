package com.example.demo.model.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseMessage {
	
	private RequestStatus requestStatus;
	
	private ResponseStatus responseStatus;
	
}
